function getMyPageBase() {
	var el = document.querySelector('[data-mypage-base]');
	var base = el ? el.getAttribute('data-mypage-base') : '';
	if (!base) {
		base = '/my-pages/';
	}
	if (!base.endsWith('/')) {
		base += '/';
	}
	return base;
}

function getCsrfHeaders() {
	var tokenMeta = document.querySelector("meta[name='_csrf']");
	var headerMeta = document.querySelector("meta[name='_csrf_header']");
	if (tokenMeta && headerMeta && tokenMeta.content && headerMeta.content) {
		var h = {};
		h[headerMeta.content] = tokenMeta.content;
		return h;
	}
	var match = document.cookie.match(/(?:^|;\s*)XSRF-TOKEN=([^;]+)/);
	var xsrf = match ? decodeURIComponent(match[1]) : '';
	return xsrf ? { 'X-XSRF-TOKEN': xsrf } : {};
}

$(function() {
	var base = getMyPageBase();

	$(".project-card").click(function() {
		var id = $(this).data("id");
		location.href = base + "projects?id=" + id;
	});

	$(".projectUpdateBtn").click(function(e) {
		e.stopPropagation();
		var projectId = $(this).data('id');
		location.href = base + "projectForm/" + projectId;
	});

	var pendingDeleteId = null;
	var deleteModalEl = document.getElementById('projectDeleteModal');
	var deleteModal = deleteModalEl && typeof bootstrap !== 'undefined' ? bootstrap.Modal.getOrCreateInstance(deleteModalEl) : null;

	$(".projectDeleteBtn").click(function(e) {
		e.stopPropagation();
		pendingDeleteId = $(this).data('id');
		if (deleteModal) {
			deleteModal.show();
		} else if (confirm('정말 삭제하시겠습니까?')) {
			postDeleteProject(pendingDeleteId, base);
		}
	});

	$("#confirmProjectDeleteBtn").on('click', function() {
		if (pendingDeleteId == null) {
			return;
		}
		postDeleteProject(pendingDeleteId, base);
		if (deleteModal) {
			deleteModal.hide();
		}
		pendingDeleteId = null;
	});

	function postDeleteProject(projectId, pageBase) {
		var headers = Object.assign({ 'X-Requested-With': 'XMLHttpRequest' }, getCsrfHeaders());
		fetch(pageBase + "deleteProject/" + projectId, {
			method: 'POST',
			headers: headers,
			credentials: 'same-origin'
		}).then(function(res) {
			if (res.ok || res.redirected) {
				location.href = pageBase;
			} else {
				alert('삭제 중 오류가 발생했습니다.');
			}
		}).catch(function() {
			alert('삭제 중 오류가 발생했습니다.');
		});
	}
});
