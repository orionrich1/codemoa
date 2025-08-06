package com.codemoa.project.domain.employment.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EmploymentBatchService {
    private final EmploymentApiService employmentApiService;

    public EmploymentBatchService(EmploymentApiService employmentApiService) {
        this.employmentApiService = employmentApiService;
    }

    @Scheduled(cron = "0 0 3 * * ?") // 매일 새벽 3시 실행 예시
    public void crawlEmploymentData() {
        employmentApiService.fetchAndSaveMultiplePages(50, 30);
    }
}