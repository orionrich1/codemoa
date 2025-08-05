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

    // 채팅방 목록 조회
    @GetMapping("/rooms")
    public String rooms(Model model) {
        List<ChatRoomDto> rooms = chatService.findAllRooms();
        model.addAttribute("rooms", rooms);
        return "views/chat/chatList";
    }

    // [최종 수정] 채팅방 생성 - Controller에서 grade를 직접 확인
    @PostMapping("/room")
    public String createRoom(@RequestParam String name, Authentication authentication) {
        // 1. Principal 객체를 CustomUserDetails 타입으로 형변환합니다.
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        // 2. User 엔티티를 가져옵니다.
        User user = userDetails.getUser();

        // 3. 사용자의 grade를 가져와서 이름(name)이 'admin'인지 대소문자 구분 없이 확인합니다.
        //    (DB에는 'admin', Enum에는 'ADMIN'으로 저장되어 있을 수 있으므로 equalsIgnoreCase 사용)
        if (user.getGrade() == null || !"admin".equalsIgnoreCase(user.getGrade().name())) {
            throw new AccessDeniedException("채팅방을 생성할 권한이 없습니다.");
        }

        // 4. 권한이 확인된 경우에만 방 생성 로직을 실행합니다.
        chatService.createRoom(name);
        return "redirect:/chat/rooms";
    }

    // 채팅방 입장
    @GetMapping("/room/{roomId}")
    public String roomDetail(@PathVariable String roomId, Model model, Principal principal) {
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