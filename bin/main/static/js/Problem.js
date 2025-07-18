$(function() {
	$("#problemForm").on("submit", function() {
		var answer = $("#answer").val();
		if (answer.length <= 0) {
			alert("답안을 작성해주세요.");
			return false;
		}
	});

	// Tab 누르면 \t 입력되는 함수
	$("#answer").on("keydown", function(e) {
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
		headers: { 'Content-Type': 'application/json' },
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
			//document.getElementById('loading').style.display = 'none';
			document.getElementById('result').innerText = data.result;
		});
} 