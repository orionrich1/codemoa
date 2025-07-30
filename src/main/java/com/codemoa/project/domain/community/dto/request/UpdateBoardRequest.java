package com.codemoa.project.domain.community.dto.request;

import lombok.Getter;

@Getter
public class UpdateBoardRequest {
    private String title;
    private String content;
    private String category;
}