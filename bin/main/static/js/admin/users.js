$(function() {
	// 유저 디테일 이동
	$(".users").on("click", function() {
		var id = $(this).data("id");
		location.href = "users?userId=" + id;
	});

	// 유저 상세 페이지 - 차단 해제 버튼
	$("#unBanBtn").click(function() {
		var isBan = $("#isBan").text();

		if (isBan == "true") {
			var userId = $("#userId").val();
			var check = confirm("유저 " + userId + "의 차단을 해제하시겠습니까?")

			if (check) {
				location.href = "unbanUser?userId=" + userId;
			}
		}
		else {
			alert("차단되지 않은 사용자입니다.");
		}
	});
});