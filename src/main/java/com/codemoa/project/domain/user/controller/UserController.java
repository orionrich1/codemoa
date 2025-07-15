//기찬
package com.codemoa.project.domain.user.controller;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.codemoa.project.domain.recruit.service.TeamRecruitService;
=======
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

>>>>>>> 67e9df614c146b80a2661f781ba37147f19c06c3

import lombok.extern.slf4j.Slf4j;

@Controller
<<<<<<< HEAD
@Slf4j
public class UserController {
	
	@Autowired
	private TeamRecruitService teamRecruitService;
	
	@GetMapping({"/", "/TeamRecruitList"})
	public String TeamRecruitList(Model model) {
		model.addAttribute("bList", teamRecruitService.teamRecruitList());
		
		return "main";
	}
=======
@SessionAttributes("user")
@Slf4j
public class UserController {
	
	
>>>>>>> 67e9df614c146b80a2661f781ba37147f19c06c3

}
