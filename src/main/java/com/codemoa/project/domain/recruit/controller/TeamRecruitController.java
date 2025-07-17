//종효
package com.codemoa.project.domain.recruit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codemoa.project.domain.recruit.service.TeamRecruitService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TeamRecruitController {
	
	@Autowired
	private TeamRecruitService teamRecruitService;
	
	@GetMapping("/addTeamRecruit")
	public String addteamRecruit(){
		return "veiws/teamRecruitwriteForm";
	}
	
	@GetMapping("/teamRecruitDetail")
	public String getTeamRecruit(Model model, @RequestParam("recruitId") int recruitId) {
		model.addAttribute("teamRecruit", teamRecruitService.getTeamRecruit(recruitId));
		return "views/teamRecruitDetail";
	}
	
	@GetMapping({"/", "/teamRecruitList"})
	public String TeamRecruitList(Model model) {
		model.addAttribute("bList", teamRecruitService.teamRecruitList());
		
		return "views/teamRecruitList";
	}
}
