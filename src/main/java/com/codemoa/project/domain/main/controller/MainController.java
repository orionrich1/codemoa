package com.codemoa.project.domain.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    /**
     * 메인 페이지를 반환합니다.
     * @return views/main/index
     */
    @GetMapping("/")
    public String mainPage() {
        // templates 폴더를 기준으로 /views/main/index.html 파일을 찾아 반환합니다.
        return "views/main/index";
    }
}