$(function() {

	// 회원 정보 수정 페이지에서, 패스워드도 변경하겠다고 체크하는 경우
	$("#changePasswordCheck").on("change", function() {
		if ($(this).is(":checked")) {
			$("#passwordSection").show();
			$("#isPassChange").val("1");
		} else {
			$("#passwordSection").hide();
			$("#isPassChange").val("0");
		}
	});

	// 수정 폼에서 비밀번호 유효성 검사 
	$("#userUpdateForm").submit(function() {
		var passCheck = $("#isPassChange").val();
		if (passCheck == "1") {
			var userId = $("#userId").val();
			var currentPass = $("#currentPass").val();

			// 현재 비밀번호 검사
			$.ajax({
				url: 'checkPass',       // 요청 보낼 URL
				type: 'POST',            // HTTP 메소드 (GET, POST, PUT, DELETE 등)
				dataType: 'json',       // 서버에서 받을 데이터 타입
				data: { userId: userId, pass: currentPass },
				success: function(res) {
					if (res.result == 'UnMatched') {
						alert("기존 비밀번호가 일치하지 않습니다.");
						$("#currentPass").focus();
						return false;
					}
				},
				error: function(xhr, status, error) {
					console.error('에러 발생 : ', error);
				}
			});

			// 새 비밀번호 검사
			if ($("#newPass").val() != $("#passConfirm").val() || $("#newPass").val() == "") {
				alert("새 비밀번호가 일치하지 않습니다.");
				$("#newPass").focus();
				return false;
			}
		}
	});

	// 전화번호 정규식 패턴
	$('#mobile').on('input', function() {
		this.value = this.value.replace(/[^0-9]/g, '');
	});

});
