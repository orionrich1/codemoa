$(function() {
	// 유저 디테일 이동
	$(".users").on("click", function(){
		var id = $(this).data("id");
		location.href="users?userId="+ id;
	});
});