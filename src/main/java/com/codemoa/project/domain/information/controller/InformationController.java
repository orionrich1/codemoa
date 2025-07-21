//재용
package com.codemoa.project.domain.information.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codemoa.project.domain.information.service.InformationService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class InformationController {

	@Autowired
	private InformationService informationService;
	
	@GetMapping("/information")
	public String informationMain() {
		return "views/information/informationMain";
	}
	
	@GetMapping("/information/lectureDetail")
	public String informationLectureDetail(Model model, 
			@RequestParam(value = "no") int no,
			@RequestParam(value = "isCount", defaultValue = "false") boolean isCount) {
		
		model.addAttribute(informationService.getLecture(no, isCount));
		return "views/information/informationLectureDetail";
	}
	
	@GetMapping("/information/bookDetail")
	public String informationBookDetail(Model model, 
			@RequestParam(value = "no") int no,
			@RequestParam(value = "isCount", defaultValue = "false") boolean isCount) {
		
		model.addAttribute(informationService.getLecture(no, isCount));
		return "views/information/informationBookDetail";
	}
	
	@GetMapping("/information/contestDetail")
	public String informationContestDetail(Model model, 
			@RequestParam(value = "no") int no,
			@RequestParam(value = "isCount", defaultValue = "false") boolean isCount) {
		
		model.addAttribute(informationService.getLecture(no, isCount));
		return "views/information/informationContestDetail";
	}
	
	@GetMapping("/information/list")
	public String informationList() {
		return "views/information/informationList";
	}
	
	
}
