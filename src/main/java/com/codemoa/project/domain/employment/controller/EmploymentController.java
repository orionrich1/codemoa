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
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Employment> posts = employmentService.getAll();
		model.addAttribute("posts", posts);
		return "views/employment/employmentList";
	}
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Long id, Model model) {
		Employment post = employmentService.getById(id);
		model.addAttribute("post", post);
		return "views/employment/employmentDetail";
	}
}
