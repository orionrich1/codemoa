package com.codemoa.project.domain.ranking.service;

import com.codemoa.project.domain.ranking.dto.response.RankingPageResponse;
import com.codemoa.project.domain.ranking.dto.response.UserRankingResponse;
import com.codemoa.project.domain.ranking.dto.response.WeeklyPointSummary;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.repository.PointLogRepository;
import com.codemoa.project.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankingService {

    private final PointLogRepository pointLogRepository;
    private final UserRepository userRepository;

    public RankingPageResponse getRankingPageData(String currentUserId) {
        // 1. 주간 TOP 10 랭킹 목록 조회
        List<UserRankingResponse> weeklyTop10 = getWeeklyTop10();

        // 2. 현재 내 랭킹 정보 조회
        User currentUser = userRepository.findByUserId(currentUserId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        RankingPageResponse.MyRankingInfo myInfo = RankingPageResponse.MyRankingInfo.from(currentUser);
        
        // 3. 최종 응답 데이터 조립
        return RankingPageResponse.builder()
                .weeklyTop10(weeklyTop10)
                .myRankingInfo(myInfo)
                .build();
    }
    
    private List<UserRankingResponse> getWeeklyTop10() {
        // 이번 주 월요일 0시 0분 계산
        LocalDateTime startOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).atStartOfDay();
        
        // Repository에서 주간 랭킹 데이터 조회 (상위 10명)
        List<WeeklyPointSummary> top10Summaries = pointLogRepository.findWeeklyTopRankers(startOfWeek, PageRequest.of(0, 10));

        AtomicInteger rank = new AtomicInteger(1);
        return top10Summaries.stream()
                .map(summary -> {
                    User user = userRepository.findByUserId(summary.getUserId()).orElse(null);
                    if (user == null) return null;
                    return new UserRankingResponse(rank.getAndIncrement(), user, summary.getWeeklyPoints());
                })
                .filter(response -> response != null)
                .collect(Collectors.toList());
    }
}