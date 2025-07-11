//재용
package com.codemoa.project.domain.information.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class InformationController {
	
	@GetMapping("/information")
	public String informationMain() {
		return "views/information/informationMain";
	}
	
	@GetMapping("/information/detail")
	public String informationDetail() {
		return "views/information/informationDetail";
	}
	
	@GetMapping("/information/list")
	public String informationList() {
		return "views/information/informationList";
	}
	
}
