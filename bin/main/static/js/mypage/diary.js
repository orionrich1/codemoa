$(function() {

	// 프로젝트 리스트에서 클릭 시 Detail 로 이동
	$(".project-card").click(function() {
		var id = $(this).data("id");
		location.href = "projects?id=" + id;
	});

	// 체크박스 클릭 시 DB에 반영되게 하는 함수
	$(document).on('change', '.checkListBox', function() {
		var done = $(this).is(":checked");
		var no = $(this).data("no");
		$.ajax({
			url: "updateCheckBox",
			type: "POST",
			contentType: "application/json; charset=UTF-8",
			data: JSON.stringify({ 'done': done, 'no': no }),
			success: function(res) {
				console.log(res);
			}
		});
	});

	// 체크리스트 추가 버튼 클릭 시 작동
	$("#addChecklistBtn").click(function() {
		$("#checklistForm").show();
		$(this).hide();
	});

	// 추가 버튼 폼 제출 시 작동
	$('#checklistAddForm').on('submit', function(e) {
		e.preventDefault();

		const $form = $(this);
		const projectId = $(this).data("id");
		const content = $.trim($form.find('input[name="content"]').val());
		const priority = $form.find('select[name="priority"]').val();

		if (!content) {
			alert('할 일을 입력하세요.');
			return;
		}

		$.ajax({
			url: 'addChecklist',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ projectId: projectId, content: content, priority: priority }),
			success: function(data) {
				// 새 항목 li 생성
				const priorityClass = data.priority == 1 ? 'priority-high' :
					data.priority == 2 ? 'priority-medium' : 'priority-low';

				const $li = $('<li>')
					.addClass('list-group-item d-flex justify-content-between align-items-center')

				const $spanLeft = $('<span>');
				const $checkbox = $('<input>')
					.addClass('checkListBox form-check-input me-2')
					.attr({ type: 'checkbox', 'data-no': data.checklistId })
				$spanLeft.append($checkbox).append(('<span>' + data.content + '</span>'));

				const $spanRight = $('<span>')
					.addClass('badge rounded-pill ');

				const $spanInner = $('<span>')
					.addClass(priorityClass)
					.text('우선순위');
				$spanRight.append($spanInner);


				$li.append($spanLeft).append($spanRight);

				// + 버튼 li 앞에 삽입
				const $addBtn = $('.checklist-add');
				$li.insertBefore($addBtn);

				// 폼 초기화 및 닫기
				$form[0].reset();
				const collapseInstance = bootstrap.Collapse.getInstance($('#checklistForm')[0]);
				if (collapseInstance) collapseInstance.hide();
			},
			error: function(xhr) {
				alert('항목 추가 중 오류가 발생했습니다.');
			}
		});
	});
});

