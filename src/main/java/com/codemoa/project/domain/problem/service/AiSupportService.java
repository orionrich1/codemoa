package com.codemoa.project.domain.problem.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.codemoa.project.domain.problem.dto.request.CodeSubmitRequest;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

@Service
public class AiSupportService {

	@Value("${api.key}")
	private String apiKey;

	// Q-3: 요청마다 클라이언트 객체를 생성하지 않고 싱글턴으로 초기화
	private Client client;

	@PostConstruct
	private void init() {
		this.client = Client.builder().apiKey(apiKey).build();
	}

	public GenerateContentResponse apiRequest(CodeSubmitRequest request) {
		String geminiQuery = "1. 문제 설명\n"
				+ String.format("프로그래밍 언어 : %s\n", request.getProblem().getCategory())
				+ String.format("문제 제목 : %s\n", request.getProblem().getTitle())
				+ String.format("문제 내용 : %s\n", request.getProblem().getContent())
				+ "2. 모범 답안\n" + request.getProblem().getAnswer()
				+ "\n3. 사용자 제출 답안\n" + request.getAnswer()
				+ "\n4. 요청사항\n"
				+ " 1) 모범 답안 자체에 대한 부분은 절대 지적하지 말기\n"
				+ " 2) 모범 답안과 비교하여 적절하게 작성하였는지 여부를 점수로 환산해 100점 만점으로 출력하기\n"
				+ " 3) 어느 부분에서 부족했는지 피드백 작성하기, 핵심적인 부분만 적당히 작성할 것\n"
				+ " 4) 피드백을 반영한 예시 코드는 작성하지 말 것\n"
				+ " 5) 사용자가 취업을 준비하는 대학생 수준임을 인지할 것\n"
				+ " 6) 사용자 답변이 문제와 관련없는 내용이라면 주의를 줄 것\n";

		return client.models.generateContent("gemini-2.5-flash", geminiQuery, null);
	}

	public GenerateContentResponse apiQuestion(String question) {
		return client.models.generateContent("gemini-2.5-flash", question, null);
	}
}
