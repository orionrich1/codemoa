import { GoogleGenerativeAI } from "@google/generative-ai";

const API_KEY = "AIzaSyDP0H846HhbiL1mEtCaVsE4n702YoQyqGg";
const genAI = new GoogleGenerativeAI(API_KEY);

async function run() {
	const model = genAI.getGenerativeModel({ model: "gemini-1.5-flash" });

	var prompt = `1. 문제 설명`;
	prompt += `프로그래밍 언어 : Java
	문제 내용 : 사용자로부터 3개의 점수를 입력받아 합산과 평균을 구하시오.`;
	
	prompt +=`\n2. 모범 답안`;
	prompt += `Scanner scanner = new Scanner(System.in);

	int[] scores = new int[3];
	int sum = 0;

	for (int i = 0; i < 3; i++) {
	    System.out.print("점수 " + (i + 1) + " 입력: ");
	    scores[i] = scanner.nextInt();
	    sum += scores[i];
	}

	double average = sum / 3.0;

	System.out.println("합계: " + sum);
	System.out.printf("평균: %.2f\n", average);

	scanner.close();`;
	
	prompt += `\n3. 사용자 제출 답안`;
	prompt += `Scanner sc = new Scanner(System.in);

	int score1, score2, score3;
	int total = 0;
	double avg = 0;

	System.out.println("첫번째 점수 입력하세요:");
	score1 = sc.nextInt();

	System.out.println("두번째 점수를 입력하시오:");
	score2 = sc.nextInt();

	System.out.println("마지막 점수 입력:");
	score3 = sc.nextInt();

	total = score1 + score2 + score3;

	avg = total / 3; 

	System.out.println("총합은 " + total + " 입니다.");
	System.out.println("평균은 " + avg + " 입니다.");         
	sc.close();`;
	
	prompt += `\n4. 요청사항
	1) 모범 답안 자체에 대한 부분은 절대 지적하지 말기
	2) 모범 답안과 비교하여 적절하게 작성하였는지 여부를 점수로 환산해 100점 만점으로 출력하기
	3) 어느 부분에서 부족했는지 피드백 작성하기, 너무 길게 작성할 필요 없이 핵심적인 부분만 적당히 작성할 것
	4) 너무 길어질 것을 대비하여, 피드백을 반영한 예시 코드는 작성하지 말 것
	5) 반드시, 사용자가 취업을 준비하는 대학생 정도의 수준임을 인지할 것`;

	const result = await model.generateContent(prompt);
	const response = await result.response;
	const text = response.text();
	console.log(text);
}

run();	