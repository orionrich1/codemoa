//기찬
package com.codemoa.project.domain.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.codemoa.project.domain.recruit.service.TeamRecruitService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	
	@Autowired
	private TeamRecruitService teamRecruitService;
	
	@GetMapping({"/", "/TeamRecruitList"})
	public String TeamRecruitList(Model model) {
		model.addAttribute("bList", teamRecruitService.teamRecruitList());
		
		return "main";
	}

}
