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
	public Map<String, Object> orderLecture(@RequestParam(value = "keyword", required = false, defaultValue = "null") String keyword,
		@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
		@RequestParam(value = "order", required = false, defaultValue = "null") String order,
		@RequestParam(value = "type", required = false, defaultValue = "null") String type) {
		
		return informationService.lectureList(pageNum, type, keyword, 8, 10, order);
	}
	
	@GetMapping("/lecturesearch")
	public Map<String, Object> lectureSearch(@RequestParam("keyword") String keyword,
			@RequestParam("order") String order) {
		
		return informationService.lectureList(1, "title", keyword, 8, 10, order);
	}
	

	
	@GetMapping("/ordercontest.ajax")
	public Map<String, Object> ordercontest(@RequestParam("order") String order) {
		
		return informationService.contestList(1, "null", "null", 8, 10, order);
	}
	
	@GetMapping("/contestsearch")
	public Map<String, Object> contestSearch(@RequestParam(value = "keyword", required = false, defaultValue = "null") String keyword,
			@RequestParam(value = "order", required = false, defaultValue = "null") String order,
			@RequestParam(value = "type", required = false, defaultValue = "null") String type) {
		
		return informationService.contestList(1, "title", keyword, 8, 10, order);
	}
	
	
	@GetMapping("/orderbook.ajax")
	public Map<String, Object> orderBook(@RequestParam("keyword") String keyword,
			@RequestParam("order") String order,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			@RequestParam(value = "type", required = false, defaultValue = "null") String type) {
		
		return informationService.bookList(pageNum, type, keyword, 8, 10, order);
	}
	
	@GetMapping("/booksearch")
	public Map<String, Object> bookSearch(@RequestParam(value = "keyword", required = false, defaultValue = "null") String keyword,
			@RequestParam(value = "order", required = false, defaultValue = "null") String order,
			@RequestParam(value = "type", required = false, defaultValue = "null") String type) {
		
		return informationService.bookList(1, "title", keyword, 8, 10, order);
	}
}