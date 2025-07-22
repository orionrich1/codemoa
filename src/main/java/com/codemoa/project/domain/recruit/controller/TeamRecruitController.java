//종효
package com.codemoa.project.domain.recruit.controller;

import java.io.PrintWriter;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codemoa.project.domain.recruit.entity.TeamRecruit;
import com.codemoa.project.domain.recruit.service.TeamRecruitService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class TeamRecruitController {
	
	@Autowired
	private TeamRecruitService teamRecruitService;
	
	public String updateTeamRecruit;
	
	private final Path uploadRoot = Paths.get("uploads", "recruit");
	
	@PostMapping("/recruit/write")
	public String writeRecruit(TeamRecruit teamRecruit, 
			@RequestParam(value = "attachmentFile", required = false) MultipartFile attachmentFile,
			RedirectAttributes redirectAttrs) {
		log.info("팀 모집 등록 요청 title=()", teamRecruit.getTitle());
		
		//파일 업로드 처리
		if(attachmentFile != null & !attachmentFile.isEmpty()) {
			try {
				Files.createDirectories(uploadRoot);
				
				//원본 확장자 추출
				String originalName = StringUtils.cleanPath(attachmentFile.getOriginalFilename());
				String ext = "";
				int dot = originalName.lastIndexOf('.');
				if(dot != -1) {
					ext = originalName.substring(dot);
				}
				String saveName = UUID.randomUUID() + ext;
				Path savePath = uploadRoot.resolve(saveName);
				attachmentFile.transferTo(savePath.toFile());
				
				//DB에 저장할 URL
				teamRecruit.setAttachmentUrl(saveName);
				
			} catch (IOException  e) {
				log.error("파일 업로드 실패", e);
				redirectAttrs.addFlashAttribute("uploadError", "파일 업로드에 실패했습니다.");
			}
		}
		teamRecruitService.addTeamRecruit(teamRecruit);
		redirectAttrs.addFlashAttribute("msg", "등록이 완료되었습니다.");
		return "redirect:/TeamRecruitList";
	}
	
	/*
	@PostMapping("/delete")
	public String deleteTeamRecruit(RedirectAttribute reAttrs,
			HttpServletResponse response, PrintWriter out,
			@RequestParam("recruitId") int recruitId, @RequestParam("userId") String userId,
			@RequestParam("value="pageNum", defaultValue="1" ) int pageNum){"
					+ "}
			
			)
	*/
	
	@PostMapping("/updateForm")
	public String updateTeamRecruit(Model model, HttpServletResponse response, 
			PrintWriter out, @RequestParam("recruitId") int recruitId, 
			@RequestParam("userId") String userId) {
		boolean userIdCheck = teamRecruitService.userIdCheck(recruitId, userId);
		if(! userIdCheck) {
			response.setContentType("text/html: charset=utf-8");
			out.print("<script>");
			out.print("alert('작성자가 일치하지 않습니다.);");
			out.print("history.back();");
			out.print("</script>");
			return null;
		}
		TeamRecruit teamRecruit = teamRecruitService.getTeamRecruit(recruitId);
		model.addAttribute("teamRecruit", teamRecruit);
		return "views/updateForm";
	}
	
	@PostMapping("/addTeamRecruit")
	public String addTeamRecruit(TeamRecruit teamRecruit) {
		log.info("title : ", teamRecruit.getTitle());
		teamRecruitService.addTeamRecruit(teamRecruit);
		return "redirect:TeamRecruitList";
		
	}
	
	@GetMapping("/addTeamRecruit")
	public String addTeamRecruit(Model model){
		TeamRecruit recruit = new TeamRecruit();
		recruit.setRecruitType("");
		model.addAttribute("teamRecruit", recruit);
		return "views/recruit/teamRecruitwriteForm";
	}
	
	@GetMapping("/TeamRecruitDetail")
	public String getTeamRecruit(Model model, @RequestParam("recruitId") int recruitId) {
		model.addAttribute("teamRecruit", teamRecruitService.getTeamRecruit(recruitId));
		return "views/recruit/teamRecruitDetail";
	}
	
	@GetMapping({"/", "/TeamRecruitList"})
	public String TeamRecruitList(Model model) {
		var list = teamRecruitService.teamRecruitList();
		log.info("조회된 게시글 수 : {}", list.size());
		model.addAttribute("bList", list);
		
		return "views/recruit/teamRecruitList";
	}
}
