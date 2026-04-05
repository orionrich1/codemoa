package com.codemoa.project.domain.problem.dto.response;

public record SaveSubmissionResponse(
        int submissionId,
        int pointAwarded,
        boolean alreadyReceivedToday,
        String message
) {}
