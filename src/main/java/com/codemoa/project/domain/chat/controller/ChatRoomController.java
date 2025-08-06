package com.codemoa.project.domain.chat.controller;

import com.codemoa.project.domain.chat.dto.ChatRoomDto;
import com.codemoa.project.domain.chat.service.ChatService;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatService chatService;

    // [디버깅을 위해 Authentication 파라미터 추가]
    @GetMapping("/rooms")
    public String rooms(Model model, Authentication authentication) {

        // ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼ 디버깅용 코드 추가 ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
        if (authentication != null && !(authentication.getPrincipal() instanceof String)) {
            try {
                CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
                String userId = userDetails.getUsername();
                Object grade = userDetails.getUser().getGrade();

                System.out.println("=================================================");
                System.out.println("### 현재 로그인한 사용자 ID: " + userId);
                System.out.println("### 실제 인식된 등급(Grade) 객체: " + grade);
                if (grade != null) {
                    System.out.println("### 등급 이름(Grade.name()): " + userDetails.getUser().getGrade().name());
                }
                System.out.println("=================================================");

            } catch (Exception e) {
                System.out.println("### 디버깅 중 에러 발생: " + e.getMessage());
            }
        }
        // ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲ 디버깅용 코드 끝 ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲

        List<ChatRoomDto> rooms = chatService.findAllRooms();
        model.addAttribute("rooms", rooms);
        return "views/chat/chatList";
    }


    @PostMapping("/room")
    // ▼▼▼▼▼ [핵심 수정 부분] ▼▼▼▼▼
    public String createRoom(@RequestParam("name") String name, Authentication authentication) {
    // ▲▲▲▲▲ [핵심 수정 부분] ▲▲▲▲▲
        
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();

        if (user.getGrade() == null || !"admin".equalsIgnoreCase(user.getGrade().name())) {
            throw new AccessDeniedException("채팅방을 생성할 권한이 없습니다.");
        }

        chatService.createRoom(name);
        return "redirect:/chat/rooms";
    }

 // 채팅방 입장
    @GetMapping("/room/{roomId}")
    // ▼▼▼▼▼ [핵심 수정 부분] ▼▼▼▼▼
    public String roomDetail(@PathVariable("roomId") String roomId, Model model, Principal principal) {
    // ▲▲▲▲▲ [핵심 수정 부분] ▲▲▲▲▲
        ChatRoomDto room = chatService.findRoomById(roomId);
        model.addAttribute("room", room);
        
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        } else {
            model.addAttribute("username", "guest" + (int)(Math.random() * 100));
        }

        return "views/chat/chatRoom";
    }
}