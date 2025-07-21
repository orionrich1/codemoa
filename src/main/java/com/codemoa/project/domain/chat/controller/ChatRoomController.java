package com.codemoa.project.domain.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatRoomController {

    @GetMapping("/chatroom")
    public String chatRoomPage() {
    	return "views/chat/chatRoom"; 
    }
}