package com.codemoa.project.domain.information.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codemoa.project.domain.information.entity.Book;
import com.codemoa.project.domain.information.entity.Contest;
import com.codemoa.project.domain.information.service.InformationService;

@RestController
public class InformationAjaxController {

	@Autowired
	private InformationService informationService;
	
	@GetMapping("/orderlecture.ajax")
	public Map<String, Object> orderlecture(@RequestParam("order") String order) {
		
		return informationService.lectureList(1, "null", "null", 8, 10, order);
	}

	@GetMapping("/ordercontest.ajax")
	public Map<String, Object> ordercontest(@RequestParam("order") String order) {
		
		return informationService.contestList(1, "null", "null", 8, 10, order);
	}
	
	@GetMapping("/orderbook.ajax")
	public Map<String, Object> orderbook(@RequestParam("order") String order) {
		
		return informationService.bookList(1, "null", "null", 8, 10, order);
	}
}

