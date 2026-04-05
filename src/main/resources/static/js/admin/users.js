function getCsrfToken() {
	var match = document.cookie.match(/(?:^|;\s*)XSRF-TOKEN=([^;]+)/);
	return match ? decodeURIComponent(match[1]) : '';
}

function postAction(url, params) {
	var form = document.createElement('form');
	form.method = 'POST';
	form.action = url;

	// CSRF 토큰 hidden input
	var csrfInput = document.createElement('input');
	csrfInput.type = 'hidden';
	csrfInput.name = '_csrf';
	csrfInput.value = getCsrfToken();
	form.appendChild(csrfInput);

	for (var key in params) {
		var input = document.createElement('input');
		input.type = 'hidden';
		input.name = key;
		input.value = params[key];
		form.appendChild(input);
	}
	document.body.appendChild(form);
	form.submit();
}

$(function() {
	// 유저 디테일 이동
	$(".users").on("click", function() {
		var id = $(this).data("id");
		location.href = "users?userId=" + id;
	});

	// 유저 상세 페이지 - 차단 해제 버튼 (C-4: GET → POST)
	$("#unBanBtn").click(function() {
		var isBan = $("#isBan").text();

		if (isBan == "true") {
			var userId = $("#userId").val();
			var check = confirm("유저 " + userId + "의 차단을 해제하시겠습니까?");
			if (check) {
				postAction("unbanUser", { userId: userId });
			}
		} else {
			alert("차단되지 않은 사용자입니다.");
		}
	});

	// 유저 상세 페이지 - 강제탈퇴 버튼 (C-4: GET → POST)
	$("#deleteUserBtn").click(function() {
		var userId = $("#userId").val();
		var check1 = confirm("유저 " + userId + "를 강제로 탈퇴시키시겠습니까?");

		if (check1) {
			var check2 = confirm("이 행동은 되돌릴 수 없습니다.\n정말로 " + userId + "를 강제로 탈퇴시키시겠습니까?");
			if (check2) {
				postAction("deleteUser", { userId: userId });
			}
		}
	});
});