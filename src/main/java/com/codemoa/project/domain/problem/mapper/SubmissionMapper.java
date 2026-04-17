package com.codemoa.project.domain.problem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.codemoa.project.domain.problem.entity.ProblemSubmission;

@Mapper
public interface SubmissionMapper {

    void insertSubmission(ProblemSubmission submission);

    void updatePointAwarded(@Param("submissionId") int submissionId, @Param("points") int points);

    /** 오늘 이미 해당 문제로 포인트를 받았는지 확인 */
    boolean existsTodayPointForProblem(@Param("userId") String userId, @Param("problemId") int problemId);

    /** 내 제출 이력을 최신 순으로 조회 */
    List<ProblemSubmission> findMyHistory(@Param("userId") String userId);

    /** 특정 문제의 내 최고 점수 조회 */
    Integer findBestScore(@Param("userId") String userId, @Param("problemId") int problemId);

    /** 문제 목록용: 내가 풀었는지 확인할 문제 ID 목록에 대한 최고 점수 맵 조회 */
    List<ProblemSubmission> findBestScorePerProblem(@Param("userId") String userId);

    /** 내가 푼 문제 수 */
    int countSolvedProblems(@Param("userId") String userId);

    // ─── 기능 7: 학습 통계 ───────────────────────────────────────────

    /** 총 제출 수 */
    int countTotalSubmissions(@Param("userId") String userId);

    /** 평균 점수 */
    Double avgScore(@Param("userId") String userId);

    /** 최고 점수 */
    Integer maxScore(@Param("userId") String userId);

    /** 언어별 평균 점수 목록 */
    List<LangStat> avgScoreByLang(@Param("userId") String userId);

    /** 난이도별 70점 이상 비율 */
    List<DiffStat> statsByDifficulty(@Param("userId") String userId);

    /** 최근 7일 일별 제출 수 */
    List<DailyStat> recentDailyCount(@Param("userId") String userId);

    /** 최근 7일간 AI 문제 제출 횟수 (마이페이지 활동 요약) */
    int countSubmissionsLast7Days(@Param("userId") String userId);
}
