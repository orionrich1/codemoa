$(function() {

	// 모달 지정
	const diaryModal = new bootstrap.Modal($("#diaryModal")[0]);

	// 작성 버튼 클릭 → 빈 폼
	$(".writeDiaryBtn").on("click", function() {
		$("#diaryModalLabel").text("작업 일지 작성");
		$("#diaryForm")[0].reset();
		$("#diaryId").val("");
		diaryModal.show();
	});

	// 수정 버튼 클릭 → 데이터 채우기
	$(document).on("click", ".updateDiaryBtn", function() {
		const diaryId = $(this).data("id");
		const card = $(this).closest(".card-body");
		const workDate = card.find(".card-title").text().trim();
		const content = card.find(".card-text").attr("title");

		$("#diaryModalLabel").text("작업 일지 수정");
		$("#workDate").val(workDate);
		$("#content").val(content);
		$("#diaryId").val(diaryId);

		diaryModal.show();
	});

	// 폼 제출, Ajax 요청
	$("#diaryForm").on("submit", function(e) {
		e.preventDefault();
		if ($("#diaryId").val() == "") {
			$("#diaryId").val("0");
		}
		$.ajax({
			url: "saveDiary",
			type: "POST",
			contentType: 'application/json',
			data: JSON.stringify({
				diaryId: $("#diaryId").val(),
				content: $("#content").val(),
				workDate: $("#workDate").val(),
				projectId: $("#projectId").val()
			}),
			success: function(res) {
				// 작성시엔 목록에 새 카드 추가
				if ($("#diaryId").val() == 0) {
					const newCard = `
			        <div class="card mb-2 position-relative diary-card">
			            <div class="card-body d-flex justify-content-between align-items-start">
			                <div>
			                    <h6 class="card-title mb-1">${res.workDate}</h6>
			                    <p class="card-text ellipsis mb-0" title="${res.content}">
			                        ${res.content.length > 100 ? res.content.substring(0, 100) + "..." : res.content}
			                    </p>
			                </div>
			                <div class="d-flex align-items-center gap-2">
			                    <button type="button" class="updateDiaryBtn diaryBtn btn btn-outline-primary btn-sm" data-id="${res.diaryId}">
			                        <i class="bi bi-pencil-square"></i>
			                    </button>
			                    <button type="button" class="deleteDiaryBtn diaryBtn btn btn-outline-danger btn-sm" data-id="${res.diaryId}">
			                        <i class="bi bi-trash3"></i>
			                    </button>
			                </div>
			            </div>
			        </div>
			    `;
					$("#diaryHeader").after(newCard); // 작업 일지 영역에 추가
				}
				// 수정시엔 해당 카드 내용 교체
				else {
					const card = $(`.updateDiaryBtn[data-id='${$("#diaryId").val()}']`).closest(".card-body");
					card.find(".card-title").text(res.workDate);
					card.find(".card-text").attr("title", res.content)
						.text(res.content.length > 100 ? res.content.substring(0, 100) + "..." : res.content);
				}
				diaryModal.hide();
			},
			error: function(err) {
				console.error("저장 실패:", err);
				alert("저장 중 오류가 발생했습니다.");
			}
		});
	});

	// 삭제 버튼 클릭
	$(document).on("click", ".deleteDiaryBtn", function() {
		const diaryId = $(this).data("id");
		const card = $(this).closest(".card");

		if (!confirm('정말 삭제하시겠습니까?')) return;
		$.ajax({
			url: `deleteDiary/${diaryId}`,
			type: "DELETE",
			success: function() {
				card.fadeOut(300, function() { $(this).remove(); });
			},
			error: function() {
				alert("삭제 중 오류가 발생했습니다.");
			}
		});
	});
});

