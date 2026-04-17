//도영
package com.codemoa.project.domain.problem.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import com.codemoa.project.domain.problem.dto.request.SaveSubmissionRequest;
import com.codemoa.project.domain.problem.dto.request.SearchListRequest;
import com.codemoa.project.domain.problem.dto.request.UpdateProblemRequest;
import com.codemoa.project.domain.problem.dto.request.WriteProblemRequest;
import com.codemoa.project.domain.problem.dto.response.MyHistoryItem;
import com.codemoa.project.domain.problem.dto.response.ProblemListResponse;
import com.codemoa.project.domain.problem.dto.response.SaveSubmissionResponse;
import com.codemoa.project.domain.problem.dto.response.StatsResponse;
import com.codemoa.project.domain.problem.entity.Problem;
import com.codemoa.project.domain.problem.entity.ProblemSubmission;
import com.codemoa.project.domain.problem.mapper.ProblemMapper;
import com.codemoa.project.domain.problem.mapper.SubmissionMapper;
import com.codemoa.project.domain.user.entity.PointEventType;
import com.codemoa.project.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProblemService {
	private final ProblemMapper problemMapper;
	private final SubmissionMapper submissionMapper;
	private final UserService userService;

	public List<Problem> getProblemList() {
		return problemMapper.getProblemList();
	}

	public List<ProblemListResponse> getProblemListAsDto() {
		return problemMapper.getProblemList().stream()
				.map(ProblemListResponse::new)
				.toList();
	}

	public List<Problem> searchProblemList(SearchListRequest request) {
		List<String> selectedTypes = new ArrayList<>();
		int[] typeFilter = request.getType();
		for (int i = 0; i < typeFilter.length; i++) {
			if (typeFilter[i] == 1) {
				String type = switch (i) {
				case 0 -> "JAVA";
				case 1 -> "JavaScript";
				case 2 -> "Python";
				case 3 -> "Kotlin";
				default -> "";
				};
				if (!type.isEmpty()) selectedTypes.add(type);
			}
		}

		List<String> selectedDifficulties = new ArrayList<>();
		int[] difficultyFilter = request.getDifficulty();
		for (int i = 0; i < difficultyFilter.length; i++) {
			if (difficultyFilter[i] == 1) {
				String type = switch (i) {
				case 0 -> "상";
				case 1 -> "중";
				case 2 -> "하";
				default -> "";
				};
				selectedDifficulties.add(type);
			}
		}

		return problemMapper.searchProblemList(selectedTypes, selectedDifficulties);
	}

	public List<ProblemListResponse> searchProblemListAsDto(SearchListRequest request) {
		return searchProblemList(request).stream()
				.map(ProblemListResponse::new)
				.toList();
	}

	public Problem getProblemDetail(int no) {
		return problemMapper.getProblemDetail(no);
	}

	public void addProblem(WriteProblemRequest request, String authorId) {
		problemMapper.addProblem(request.toEntity(authorId));
	}

	/** 문제를 추가하고 생성된 PK를 반환 — 작성 완료 후 상세 페이지로 리다이렉트하기 위해 사용 */
	public int addProblemAndGetId(WriteProblemRequest request, String authorId) {
		Problem entity = request.toEntity(authorId);
		problemMapper.addProblem(entity);
		return entity.getProblemId();
	}

	public void updateProblem(UpdateProblemRequest request) {
		problemMapper.updateProblem(request.toEntity());
	}

	/** @deprecated 직접 Problem 엔티티를 받는 이전 메서드 — Mapper 내부에서만 사용 */
	@Deprecated
	public void addProblem(Problem problem) {
		problemMapper.addProblem(problem);
	}

	/** @deprecated 직접 Problem 엔티티를 받는 이전 메서드 — Mapper 내부에서만 사용 */
	@Deprecated
	public void updateProblem(Problem problem) {
		problemMapper.updateProblem(problem);
	}

	public void deleteProblem(int no) {
		problemMapper.deleteProblem(no);
	}

	public Problem getDailyProblem() {
		int total = problemMapper.countProblems();
		if (total == 0) return null;
		int offset = LocalDate.now().getDayOfYear() % total;
		return problemMapper.findDailyProblem(offset);
	}

	public int countProblems() {
		return problemMapper.countProblems();
	}

	// ─── 기능 1: 풀이 이력 저장 + 기능 8: 포인트 지급 ─────────────────────

	public SaveSubmissionResponse saveSubmission(SaveSubmissionRequest request, String userId) {
		ProblemSubmission submission = new ProblemSubmission();
		submission.setUserId(userId);
		submission.setProblemId(request.getProblemId());
		submission.setSubmittedAnswer(request.getSubmittedAnswer());
		submission.setAiScore(request.getAiScore());
		submission.setAiFeedback(request.getAiFeedback());
		submissionMapper.insertSubmission(submission);

		boolean alreadyToday = submissionMapper.existsTodayPointForProblem(userId, request.getProblemId());
		int points = 0;
		String message;

		if (alreadyToday) {
			message = "오늘 이미 이 문제로 포인트를 받으셨습니다.";
		} else {
			points = calcPoints(request.getAiScore());
			if (points > 0) {
				String desc = String.format("AI 코딩 문제 제출 (문제 #%d, %d점)", request.getProblemId(), request.getAiScore());
				userService.addCustomPoints(userId, points, desc, PointEventType.PROBLEM_SUBMIT);
				submissionMapper.updatePointAwarded(submission.getSubmissionId(), points);
			}
			message = points > 0 ? "+" + points + " 포인트 획득!" : "다음엔 더 높은 점수에 도전해 보세요!";
		}

		return new SaveSubmissionResponse(submission.getSubmissionId(), points, alreadyToday, message);
	}

	/** AI 점수 구간별 포인트 계산 (기획서 기능 8) */
	private int calcPoints(int score) {
		if (score >= 90) return 100;
		if (score >= 70) return 60;
		if (score >= 50) return 30;
		return 10;
	}

	// ─── 기능 6: 내 풀이 이력 ───────────────────────────────────────────────

	public List<MyHistoryItem> getMyHistory(String userId) {
		return submissionMapper.findMyHistory(userId).stream()
				.map(MyHistoryItem::new)
				.toList();
	}

	/** 내가 푼 문제 수 */
	public int countSolvedProblems(String userId) {
		return submissionMapper.countSolvedProblems(userId);
	}

	/** 문제 목록용 — 로그인 사용자의 문제별 최고 점수 맵 {problemId → bestScore} */
	public Map<Integer, Integer> getBestScoreMap(String userId) {
		Map<Integer, Integer> map = new HashMap<>();
		submissionMapper.findBestScorePerProblem(userId)
				.forEach(s -> map.put(s.getProblemId(), s.getAiScore()));
		return map;
	}

	// ─── 기능 7: 학습 통계 ──────────────────────────────────────────────

	public int countSubmissionsLast7Days(String userId) {
		return submissionMapper.countSubmissionsLast7Days(userId);
	}

	public StatsResponse getMyStats(String userId) {
		int total   = submissionMapper.countTotalSubmissions(userId);
		Double avg  = submissionMapper.avgScore(userId);
		Integer max = submissionMapper.maxScore(userId);
		int solved  = submissionMapper.countSolvedProblems(userId);
		return new StatsResponse(
				total,
				avg  != null ? Math.round(avg * 10.0) / 10.0 : 0.0,
				max  != null ? max : 0,
				solved,
				submissionMapper.avgScoreByLang(userId),
				submissionMapper.statsByDifficulty(userId),
				submissionMapper.recentDailyCount(userId));
	}

}
