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

	// 유저 상세 페이지 - 강제탈퇴 버튼
	$("#deleteUserBtn").click(function() {
		var userId = $("#userId").val();
		var check1 = confirm("유저 " + userId + "를 강제로 탈퇴시키시겠습니까?");

		if (check1) {
			var check2 = confirm("이 행동은 되돌릴 수 없습니다.\n정말로 " + userId + "를 강제로 탈퇴시키시겠습니까?");
			if (check2) {
				location.href = "deleteUser?userId=" + userId;
				alert(userId + "가 탈퇴처리 되었습니다.");
			}
		}
	});
});