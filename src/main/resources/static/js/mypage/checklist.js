$(function() {
	
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
					.addClass('d-flex align-items-center gap-2');

				const $spanRightInner = $('<span>')
					.addClass('badge rounded-pill ');

				const $spanpriority = $('<span>')
					.addClass(priorityClass)
					.text('우선순위');

				const buttonsHtml = `
				    <!-- 수정 버튼 -->
				    <button type="button" class="updateChecklistBtn btn btn-outline-primary btn-sm"
				            data-no="${data.checklistId}">
				        <i class="bi bi-pencil-square"></i>
				    </button>

				    <!-- 삭제 버튼 -->
				    <button type="button" class="deleteChecklistBtn btn btn-outline-danger btn-sm"
				            data-no="${data.checklistId}">
				        <i class="bi bi-trash3"></i>
				    </button>
				`;
				$spanRightInner.append($spanpriority);
				$spanRight.append($spanRightInner, buttonsHtml);
				$li.append($spanLeft).append($spanRight);

				// + 버튼 li 앞에 삽입
				const $addBtn = $('.checklist-add');
				$li.insertBefore($addBtn);

				// 폼 초기화 및 닫기
				$form[0].reset();
				const collapseInstance = bootstrap.Collapse.getInstance($('#checklistForm')[0]);
				if (collapseInstance) collapseInstance.hide();
			},
			error: function() {
				alert('항목 추가 중 오류가 발생했습니다.');
			}
		});
	});

	// 각 체크리스트 업데이트 버튼 (폼 요청)
	$(document).on('click', '.updateChecklistBtn', function() {
		const $li = $(this).closest('li');
		const $checkbox = $li.find('.checkListBox');
		const $textSpan = $checkbox.next('span');
		const $priorityBadge = $li.find('.badge');

		if ($checkbox.is(':hidden')) {
			return false;
		}

		var currentText = $textSpan.text();
		const currentPriority = 
		    $priorityBadge.children('span').hasClass('priority-high') ? 1 :
		    $priorityBadge.children('span').hasClass('priority-medium') ? 2 : 3;

		$checkbox.hide();
		$textSpan.hide();
		$priorityBadge.hide();

		// 입력창 + 전송 버튼 생성 (한 줄 배치)
		const $inputGroup = $(`
		    <span class="edit-inline d-flex align-items-center gap-2">
		        <input type="text" class="form-control form-control-sm edit-input" style="width:auto;" />
				<select class="form-select form-select-sm edit-priority" style="width:auto;">
				    <option value="1">높음</option>
				    <option value="2">중간</option>
				    <option value="3">낮음</option>
				</select>
		        <button type="button" class="btn btn-success btn-sm submit-edit">전송</button>
				<button type="button" class="btn btn-secondary btn-sm cancel-edit">취소</button>
		    </span>
		`);
		
		// 버튼 위치에 삽입 (체크박스 뒤에)
		$checkbox.after($inputGroup);

		$inputGroup.find('.edit-input').val(currentText);
		$inputGroup.find('.edit-priority').val(String(currentPriority));

	});


	// 각 체크리스트 업데이트 폼 전송 함수
	$(document).on('click', '.submit-edit', function() {
		const $li = $(this).closest('li');
		const newText = $li.find('.edit-input').val();
		const newPriority = parseInt($li.find('.edit-priority').val(), 10);
		const no = $li.find('.checkListBox').data('no');
	
		$.ajax({
		    url: 'updateChecklist',
		    type: 'POST',
		    contentType: 'application/json',
		    data: JSON.stringify({ checklistId: no, content: newText, priority: newPriority }),
		    success: function() {
		        // UI 복원
		        $li.find('.edit-inline').remove();
		        $li.find('.checkListBox').show();
		        $li.find('.checkListBox').next('span').text(newText).show();
	
		        const $badge = $li.find('.badge');
				const $priority =$badge.children('span');
		        $priority.removeClass('priority-high priority-medium priority-low');
		        if (newPriority === 1) {
		            $priority.addClass('priority-high');
		        } else if (newPriority === 2) {
		            $priority.addClass('priority-medium');
		        } else {
		            $priority.addClass('priority-low');
		        }
		        $badge.show();
		    },
		    error: function() {
		        alert('수정 실패');
		    }
		});
	});

	// 업데이트 취소 버튼 클릭
	$(document).on('click', '.cancel-edit', function() {
	    const $li = $(this).closest('li');
	
	    $li.find('.edit-inline').remove();
	    $li.find('.checkListBox').show();
	    $li.find('.checkListBox').next('span').show();
	    $li.find('.badge').show();
	
	    $li.removeClass('editing');
	});

	// 각 체크리스트 삭제 버튼
	$(document).on('click', '.deleteChecklistBtn', function() {
		var no = $(this).data("no");
		if (!confirm('정말 삭제하시겠습니까?')) return;

		$.ajax({
			url: "deleteChecklist",
			type: "POST",
			contentType: "application/json; charset=UTF-8",
			data: JSON.stringify({ 'no': no }),
			success: function() {
				$(`[data-no="${no}"]`).closest('li').remove();
			},
			error: function() {
				alert('삭제 실패');
			}
		});
	});
});

