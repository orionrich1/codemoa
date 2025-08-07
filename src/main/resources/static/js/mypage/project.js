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
		if (!confirm('정말 삭제하시겠습니까?')) {
			e.stopPropagation();
			return false;
		}
		e.stopPropagation();
		var projectId = $(this).data('id');
		location.href = `deleteProject/${projectId}`;
	});
});

