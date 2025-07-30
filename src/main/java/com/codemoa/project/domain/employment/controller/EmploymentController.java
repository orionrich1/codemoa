package com.codemoa.project.domain.employment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.codemoa.project.domain.employment.entity.Employment;
import com.codemoa.project.domain.employment.service.EmploymentService;

@Controller
@RequestMapping("/employment")
public class EmploymentController {
	
	@Autowired
	private EmploymentService employmentService;
	
	@GetMapping("/employmentList")
	public String list(Model model) {
		List<Employment> jobs = employmentService.getAll();
		model.addAttribute("jobs", jobs);
		return "views/employment/employmentList";
	}
	
}
