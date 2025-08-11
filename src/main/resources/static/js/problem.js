let typeFilterSelected = [0, 0, 0];
let difficultyFilterSelected = [0, 0, 0];

$(function() {

	$(".typeFilter").on("change", function() {
		typeFilterSelected[$(this).val() - 1] = $(this).is(':checked') ? 1 : 0;
		requestList();
	});

	$(".difficultyFilter").on("change", function() {
		difficultyFilterSelected[$(this).val() - 1] = $(this).is(':checked') ? 1 : 0;
		requestList();
	});

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
			e.preventDefault();
			const start = this.selectionStart;
			const end = this.selectionEnd;
			this.value = this.value.substring(0, start) + "\t" + this.value.substring(end);
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

	$("#questionForm").submit(function(e) {
		e.preventDefault();
		questionApi();
	});
});

function requestList() {
	$.ajax({
		url: '/problems/listUpdate',
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify({
			'type': typeFilterSelected,
			'difficulty': difficultyFilterSelected
		}),
		success: function(res) {
			refreshList(res.result);
		},
		error: function(err) {
			console.error("게시글 로드 실패", err);
		}
	});
}

/**
 * AI 웹 퍼블리셔 (핵심 수정):
 * 서버에서 받은 문제 목록(JSON)을 사용하여, 세련된 디자인의 HTML을 생성하는 함수.
 */
function refreshList(problemList) {
	const listContainer = $('#problemTableBody');
	listContainer.empty(); // 기존 목록 초기화

	if (!problemList || problemList.length === 0) {
		listContainer.append(`
            <li class="list-group-item text-center p-5 text-secondary">
                해당 조건에 맞는 문제가 없습니다.
            </li>
        `);
		return;
	}

	problemList.forEach(problem => {
		const getDifficultyBadgeClass = (difficulty) => {
			switch (difficulty) {
				case '상': return 'bg-danger';
				case '중': return 'bg-warning text-dark';
				case '하': return 'bg-success';
				default: return 'bg-secondary';
			}
		};
		
		const formatDate = (dateString) => new Date(dateString).toISOString().split('T')[0];

		const problemHtml = `
			<li class="list-group-item list-group-item-action problem-list-item p-3" style="cursor: pointer;"
				onclick="location.href='problemDetail?problemId=${problem.problemId}'">
				<div class="d-flex justify-content-between align-items-center">
					<div class="flex-grow-1">
						<div class="d-flex align-items-center gap-3">
							<span class="badge text-bg-dark" style="width: 90px;">${problem.category}</span>
							<span class="problem-title">${problem.title}</span>
						</div>
						<div class="d-flex align-items-center gap-3 problem-meta mt-2">
							<div style="width: 90px;"></div>
							<span>${problem.userId}</span>
							<span>${formatDate(problem.regDate)}</span>
						</div>
					</div>
					<div class="flex-shrink-0">
						<span class="badge ${getDifficultyBadgeClass(problem.difficulty)} fs-6">${problem.difficulty}</span>
					</div>
				</div>
			</li>
		`;
		listContainer.append(problemHtml);
	});
}

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
	$("#result").text("💭 답변 생성 중입니다...");

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
			$("#result").text(data.result);
		});
}

function questionApi() {
	const questionText = $("#questionText").val();
	$(".code-block").text("💭 답변 생성 중입니다...");

	fetch('/problems/apiQuestion', {
		method: "POST",
		headers: {
			"Content-Type": "text/plain",
		},
		body: questionText
	})
		.then(res => res.json())
		.then(data => {
			$(".code-block").text(data.result);
		})
		.catch(err => {
			$(".code-block").text("❌ 오류가 발생했습니다: " + err.message);
		});;
}