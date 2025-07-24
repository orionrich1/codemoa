//윤식
package com.codemoa.project.domain.chat.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageRequest {
	private String sender;
    private String message;

}
