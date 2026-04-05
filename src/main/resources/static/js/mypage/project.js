function getCsrfToken() {
	var match = document.cookie.match(/(?:^|;\s*)XSRF-TOKEN=([^;]+)/);
	return match ? decodeURIComponent(match[1]) : '';
}

$(function() {

	// 프로젝트 리스트에서 클릭 시 Detail 로 이동
	$(".project-card").click(function() {
		var id = $(this).data("id");
		location.href = "projects?id=" + id;
	});

	$(".projectUpdateBtn").click(function(e) {
		e.stopPropagation();
		var projectId = $(this).data('id');
		location.href = `projectForm/${projectId}`;
	});

	$(".projectDeleteBtn").click(function(e) {
		e.stopPropagation();
		if (!confirm('정말 삭제하시겠습니까?')) return false;

		var projectId = $(this).data('id');
		// C-4: GET → POST 변경. CSRF 쿠키에서 토큰을 읽어 헤더에 포함
		var csrfToken = getCsrfToken();
		fetch(`deleteProject/${projectId}`, {
			method: 'POST',
			headers: { 'X-XSRF-TOKEN': csrfToken }
		}).then(function(res) {
			if (res.ok || res.redirected) {
				location.href = '/my-pages/';
			} else {
				alert('삭제 중 오류가 발생했습니다.');
			}
		});
	});
});

