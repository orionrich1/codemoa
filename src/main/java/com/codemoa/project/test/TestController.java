package com.codemoa.project.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@NoArgsConstructor
public class TestController {

	@GetMapping("/")
	public String test() {
		return "test/test";
	}
}
