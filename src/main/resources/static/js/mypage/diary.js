$(function() {
	$(".project-card").click(function() {
		var id = $(this).data("id");
		location.href = "projects?id=" + id;
	});
});