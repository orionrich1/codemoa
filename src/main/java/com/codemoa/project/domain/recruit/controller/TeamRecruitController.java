package com.codemoa.project.domain.recruit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.UUID;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codemoa.project.domain.recruit.entity.TeamRecruit;
import com.codemoa.project.domain.recruit.service.TeamRecruitService;
import com.codemoa.project.domain.user.security.CustomUserDetails;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class TeamRecruitController {
	
	private final String UPLOAD_DIR = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/files").toString();
	
	@Autowired
	private TeamRecruitService teamRecruitService;
	

	@GetMapping("/TeamRecruitList")
	public String redirectTeamRecruitList() {
		return "redirect:/teamRecruitList";
	}
	
	@DeleteMapping("/recruit/{recruitId}")
	public ResponseEntity<?> deleteRecruit(@PathVariable("recruitId") int recruitId,
	                                       @AuthenticationPrincipal CustomUserDetails principal){
	    if (principal == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
	    }
	    String loginId = principal.getUsername();

	    TeamRecruit teamRecruit = teamRecruitService.getTeamRecruit(recruitId);
	    if (teamRecruit == null || !teamRecruit.getUserId().equals(loginId)){
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("삭제 권한이 없습니다.");
	    }
	    boolean result = teamRecruitService.deleteRecruit(recruitId);
	    if(result) {
	        return ResponseEntity.ok().body("삭제 완료");
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패");
	    }
	}
	
	
	@GetMapping("/recruit/updateForm")
	public String showUpdateForm(@RequestParam("recruitId") int recruitId, 
			@AuthenticationPrincipal CustomUserDetails principal, Model model, 
			HttpServletResponse response) throws IOException {
	   
		
	    if(principal == null) {
	        response.sendRedirect("/loginForm"); // 로그인 페이지로 리다이렉트
	        return null;
	    }
	    String loginId = principal.getUsername();

	    boolean userIdCheck = teamRecruitService.userIdCheck(recruitId, loginId);
	    if(!userIdCheck) {
	        response.setContentType("text/html; charset=utf-8");
	        PrintWriter out = response.getWriter();
	        out.print("<script>alert('작성자가 일치하지 않습니다.'); history.back();</script>");
	        out.flush();
	        return null;
	    }
		 	
	    TeamRecruit teamRecruit = teamRecruitService.getTeamRecruit(recruitId);
	    model.addAttribute("teamRecruit", teamRecruit);
	    return "views/recruit/teamRecruitUpdateForm";
	}
	
	@PostMapping("/teamRecruitUpdate")
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
		return "redirect:/recruit/teamRecruitDetail?recruitId=" + teamRecruit.getRecruitId();
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
	


	
	@PostMapping("/recruit/write")
	public String writeRecruit(TeamRecruit teamRecruit, 
			@RequestParam(value = "attachmentFile", required = false) MultipartFile attachmentFile,
			RedirectAttributes redirectAttrs, 
			@AuthenticationPrincipal CustomUserDetails principal) {
		log.info("팀 모집 등록 요청 title={}", teamRecruit.getTitle());
		
		
		if (principal == null) {
			redirectAttrs.addFlashAttribute("errorMsg", "로그인이 필요합니다.");
			return "redirect:/loginForm";
	    }
		String loginId = principal.getUsername();
		teamRecruit.setUserId(loginId);
		
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
		    return "redirect:/teamRecruitList";
		}	
		
	@GetMapping("/addTeamRecruit")
	public String addTeamRecruit(Model model){
		TeamRecruit recruit = new TeamRecruit();
		recruit.setRecruitType("");
		model.addAttribute("teamRecruit", recruit);
		return "views/recruit/teamRecruitWriteForm";
	}
	
	@GetMapping("/teamRecruitDetail")
	public String getTeamRecruit(Model model, 
			@RequestParam("recruitId") int recruitId,
			@AuthenticationPrincipal CustomUserDetails principal) {
		teamRecruitService.increaseViewCount(recruitId);
		
		TeamRecruit teamRecruit = teamRecruitService.getTeamRecruit(recruitId);
		 log.info("teamRecruit nickname = {}", teamRecruit.getNickname()); 
		
		 model.addAttribute("teamRecruit", teamRecruit);
		
		 if(principal != null) {
			   model.addAttribute("loginId", principal.getUsername());
		    } else {
		        model.addAttribute("loginId", null);
		    }
		
		return "views/recruit/teamRecruitDetail";
	}
	
	
	@GetMapping("/teamRecruitList")
	public String TeamRecruitList(Model model, 
			@RequestParam(value = "pageNum", required=false, defaultValue="1") int pageNum,
			@RequestParam(value = "type", required=false, defaultValue="") String type,
			@RequestParam(value = "keyword", required=false, defaultValue="") String keyword,
			@AuthenticationPrincipal CustomUserDetails principal
			) {
		
		if("null".equalsIgnoreCase(type)) type ="";
		if("null".equalsIgnoreCase(keyword)) keyword = "";
		
		log.info("TeamRecruitList: teamRecruitList({}, {}, {})", pageNum, type, keyword);
		model.addAllAttributes(teamRecruitService.teamRecruitList(pageNum, type.trim(), keyword.trim()));
		
		model.addAttribute("type", type);
		model.addAttribute("keyword", keyword);
		
		 if (principal != null) {
		        model.addAttribute("loginId", principal.getUsername());
		    } else {
		        model.addAttribute("loginId", null);
		    }
		 
		return "views/recruit/teamRecruitList";
	}
}
