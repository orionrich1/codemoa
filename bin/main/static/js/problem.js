$(function() {
	// ProblemDetail 페이지 유효성 검사
	$("#problemForm").on("submit", function() {
		var answer = $("#answer").val();
		if (answer.length <= 0) {
			alert("답안을 작성해주세요.");
			return false;
		}
	});

	// ProblemDetail 페이지 힌트 보기
	$("#showHintBtn").click(function() {
		var hint = $("#hint").text();
		if (hint.length <= 0) {
			hint = "힌트가 없습니다.";
		}
		alert(hint);
	});

	// Tab 누르면 \t 입력되는 함수
	$(".useTabkey").on("keydown", function(e) {
		if (e.key == "Tab") {
			// 기본 Tab 이벤트 정지
			e.preventDefault();

			const start = this.selectionStart;
			const end = this.selectionEnd;

			// 현재 커서 위치에 \t 삽입
			this.value = this.value.substring(0, start) + "\t" + this.value.substring(end);

			// 커서를 탭 문자 뒤로 이동
			this.selectionStart = this.selectionEnd = start + 1;
		}
	});

	// ProblemWriteForm, ProblemUpdateForm 페이지 유효성 검사
	$("#writeForm, #updateForm").on("submit", function() {
		var title = $("#title");
		var content = $("#content");
		var category = $("#category");

		if (title.val().length <= 0) {
			alert("문제 제목을 작성해주세요.");
			title.focus();
			return false;
		}

		if (content.val().length <= 0) {
			alert("문제 내용을 작성해주세요.");
			content.focus();
			return false;
		}

		if (category.val().length <= 0) {
			alert("프로그래밍 언어를 작성해주세요.");
			category.focus();
			return false;
		}
	});
});

function requestApi() {
	var problemCategory = $("#problemCategory").text();
	var problemTitle = $("#problemTitle").text();
	var problemContent = $("#problemContent").text();
	var problemAnswer = $("#problemAnswer").text();
	var userAnswer = $("#userAnswer").text();

	var test = {
		"problem": {
			"category": problemCategory,
			"title": problemTitle,
			"content": problemContent,
			"answer": problemAnswer
		},
		"answer": userAnswer
	}

	console.log(test);

	fetch('/problems/apiRequest', {
		method: 'POST',
		headers: { 'Content-Type': 'application/json'},
		body: JSON.stringify({
			"problem": {
				"category": problemCategory,
				"title": problemTitle,
				"content": problemContent,
				"answer": problemAnswer
			},
			"answer": userAnswer
		})
	})
		.then(res => res.json())
		.then(data => {
			document.getElementById('result').innerText = data.result;
		});
} 