package com.codemoa.project.domain.recruit.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Map;
import java.util.UUID;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codemoa.project.domain.recruit.entity.TeamRecruit;
import com.codemoa.project.domain.recruit.service.TeamRecruitService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class TeamRecruitController {
	
	private final String UPLOAD_DIR = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/files").toString();
	
	@GetMapping("/recruit/updateForm")
	public String showUpdateForm(@RequestParam("recruitId") int recruitId, HttpSession session, Model model, HttpServletResponse response) throws IOException {
	   
		  // 로그인 체크 (추후 로그인 구현 완료 시 활성화)
		/*
		String loginUserId = (String) session.getAttribute("loginUserId");
	    if(loginUserId == null) {
	        response.sendRedirect("/login"); // 로그인 페이지로 리다이렉트
	        return null;
	    }

	    boolean userIdCheck = teamRecruitService.userIdCheck(recruitId, loginUserId);
	    if(!userIdCheck) {
	        response.setContentType("text/html; charset=utf-8");
	        PrintWriter out = response.getWriter();
	        out.print("<script>alert('작성자가 일치하지 않습니다.'); history.back();</script>");
	        out.flush();
	        return null;
	    }
		 	*/
	    TeamRecruit teamRecruit = teamRecruitService.getTeamRecruit(recruitId);
	    model.addAttribute("teamRecruit", teamRecruit);
	    return "views/recruit/teamRecruitUpdateForm";
	}
	
	@PostMapping("/TeamRecruitUpdate")
	public String updateTeamRecruit(
			@ModelAttribute TeamRecruit teamRecruit,
			@RequestParam(value = "attachmentFile", required = false) MultipartFile attachmentFile,
			RedirectAttributes redirectAttrs	) {
		try {
			teamRecruitService.updateTeamRecruit(teamRecruit, attachmentFile);
			redirectAttrs.addFlashAttribute("msg", "수정이 완료되었습니다.");
		} catch (Exception e) {
			log.error("수정 중 오류 발생", e);
			redirectAttrs.addFlashAttribute("errorMsg", "수정중 오류가 발생되었습니다.");
			return "redirect:/recruit/updateForm?recruitId=" + teamRecruit.getRecruitId();
		}
		return "redirect:/recruit/TeamRecruitDetail?recruitId=" + teamRecruit.getRecruitId();
	}
	
	
	@GetMapping("/files/{filename}")
	public ResponseEntity<Resource> downloadFile(@PathVariable ("filename") String filename) throws IOException{
		Path filePath = Paths.get("src/main/resources/static/files/" + filename);
		
		Resource resource = new UrlResource(filePath.toUri());
		if(!resource.exists() || !resource.isReadable()) {
			return ResponseEntity.notFound().build();
		}
		
		String encodedFileName = URLEncoder.encode(resource.getFilename(), "UTF-8").replaceAll("\\+", "%20");

		return ResponseEntity.ok()
		        .contentType(MediaType.APPLICATION_OCTET_STREAM)
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"")
		        .body(resource);
	} 
	

	@Autowired
	private TeamRecruitService teamRecruitService;
	
	
	@PostMapping("/recruit/write")
	public String writeRecruit(TeamRecruit teamRecruit, 
			@RequestParam(value = "attachmentFile", required = false) MultipartFile attachmentFile,
			RedirectAttributes redirectAttrs) {
		log.info("팀 모집 등록 요청 title={}", teamRecruit.getTitle());
		
		teamRecruit.setUserId("testUser"); // 로그인 완성 후 세션에서 가져오도록 수정 필요
	    //String loginUserId = (String) session.getAttribute("loginUserId");
	    //teamRecruit.setUserId(loginUserId);
		
		if("TEAM_JOIN".equals(teamRecruit.getRecruitType())) {
			teamRecruit.setRemainingMembers(0);
			if(teamRecruit.getStatus() == null || teamRecruit.getStatus().isEmpty()) {
				teamRecruit.setStatus("CLOSED");
			}			
		}
		
		//파일 업로드 처리
		 try {
		        if (attachmentFile != null && !attachmentFile.isEmpty()) {
		            Files.createDirectories(Paths.get(UPLOAD_DIR));

		            String originalName = StringUtils.cleanPath(attachmentFile.getOriginalFilename());
		            String ext = "";
		            int dot = originalName.lastIndexOf('.');
		            if (dot != -1) {
		                ext = originalName.substring(dot);
		            }
		            String saveName = UUID.randomUUID() + ext;
		            Path savePath = Paths.get(UPLOAD_DIR, saveName);
		            attachmentFile.transferTo(savePath.toFile());

		            teamRecruit.setAttachmentUrl(saveName);
		        }
		    } catch (IOException e) {
		        log.error("파일 업로드 실패", e);
		        redirectAttrs.addFlashAttribute("uploadError", "파일 업로드에 실패했습니다.");
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
	
		
	@GetMapping("/addTeamRecruit")
	public String addTeamRecruit(Model model){
		TeamRecruit recruit = new TeamRecruit();
		recruit.setRecruitType("");
		model.addAttribute("teamRecruit", recruit);
		return "views/recruit/teamRecruitWriteForm";
	}
	
	@GetMapping("/TeamRecruitDetail")
	public String getTeamRecruit(Model model, 
			@RequestParam("recruitId") int recruitId,
			HttpSession session) {
		teamRecruitService.increaseViewCount(recruitId);
		
		model.addAttribute("teamRecruit", teamRecruitService.getTeamRecruit(recruitId));
		
		String loginId = (String) session.getAttribute("loginId");
		model.addAttribute("loginId", loginId);
		
		return "views/recruit/teamRecruitDetail";
	}
	
	@GetMapping("/TeamRecruitList")
	public String TeamRecruitList(Model model, 
			@RequestParam(value = "pageNum", required=false, defaultValue="1") int pageNum,
			@RequestParam(value = "type", required=false, defaultValue="") String type,
			@RequestParam(value = "keyword", required=false, defaultValue="") String keyword
			) {
		if("null".equalsIgnoreCase(type)) type ="";
		if("null".equalsIgnoreCase(keyword)) keyword = "";
		
		log.info("TeamRecruitList: teamRecruitList({}, {}, {})", pageNum, type, keyword);
		model.addAllAttributes(teamRecruitService.teamRecruitList(pageNum, type.trim(), keyword.trim()));
		
		model.addAttribute("type", type);
		model.addAttribute("keyword", keyword);
		
		return "views/recruit/teamRecruitList";
	}
}
