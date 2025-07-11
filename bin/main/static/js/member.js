
$(function() { // 
	
	// 회원 정보 수정 폼 서브밋 이벤트 처리
	$("#memberUpdateForm").on("submit", function() {
		// 비밀번호 확인을 했는지 체크	
		if(!$("#btnPassCheck").is(":disabled")) {
			alert("비밀번호를 확인해 주세요");
			return false;
		}
		
		return joinFormCheck(false);
	});
	
	// 비밀번호 확인 버튼 이벤트 처리
	$("#btnPassCheck").on("click", function() {
		
		let oldPass = $("#oldPass").val();
		if($.trim(oldPass).length <= 0) {
			alert("기존 비밀번호를 입력하세요");
			return false;
		}
		
		let serverUrl = "passCheck";
		let data = "id=" + $("#id").val() + "&pass=" + oldPass;
		
		$.ajax({
			url: serverUrl,
			type: "get",
			data: data,
			dataType: "json",
			success: function(resData) {
				console.log(resData);
				if(resData.result) {
					alert("비밀번호가 확인됨");
					$("#btnPassCheck").attr("disabled", true);
					$("#oldPass").attr("readonly" ,true);
					$("#pass").focus();
					
				} else {
					alert("비밀번호가 틀림");
					$("#oldPass").focus();
				}
			},
			error: function(xhr, status) {
				console.log("error : " + status);
			}
		});
		
	});
	
	
	let selectDomain = $("#selectDomain").val();
	if(selectDomain != '직접입력') {
		$("#emailDomain").attr("readonly", true);
	}
	
	// 회원 가입 폼, 회원 정보 수정 폼 두군데서 사용하기 위해서 - 함수
	$("#joinForm").on("submit", function() {
		return joinFormCheck(true);
	})
	
	$("#selectDomain").on("change", function() {
		let str = $(this).val();
		console.log("jquery : ", str);
		if(str == '직접입력') {
			$("#emailDomain").val("");
			$("#emailDomain").prop("readonly", false);
			return;			
		}
		$("#emailDomain").val($(this).val());
		$("#emailDomain").prop("readonly", true);
		
		/*
		 else if(str == '네이버') {
			$("#emailDomain").val("naver.com");
			$("#emailDomain").prop("readonly", true);
			
		} else if(str == '다음') {
			$("#emailDomain").val("daum.net");
			$("#emailDomain").prop("readonly", true);
				
		} else if(str == '한메일') {
			$("#emailDomain").val("hanmail.net");
			$("#emailDomain").prop("readonly", true);
			
		} else if(str == '구글') {
			$("#emailDomain").val("gmail.com");
			$("#emailDomain").prop("readonly", true);	
		}
		*/
	});
	
	$("#btnZipcode").click(findZipcode);
	
	$("#btnIdCheckClose").on("click", function() {
		let id = $(this).attr("data-id-value");
		opener.document.joinForm.id.value = id;
		opener.document.joinForm.isIdCheck.value = true;
		window.close();
	});
	
	$("#btnOverlapId").on("click", function() {
		let id = $("#id").val();
		let url = "overlapIdCheck?id=" + id;
		
		if(id.length < 5) {
			alert("아이디는 5자 이상...");
			return false;
		}
		
		window.open(url, "idCheck", "toolbar=no, scrollbars=no, resizable=no,"
			+ "status=no, menubar=no, width=500, height=330");
	});
	
	// 회원 가입 폼 유효성 검사
	$("#id").on("keyup", function() {
		//   /[^A-Za-z0-9]/   ;   /^[A-Za-z0-9]{5, 15}$/
		let regExp = /[^A-Za-z0-9]/gi;
		if(regExp.test($(this).val())) {
			alert("영문 대소문자, 숫자만 입력할 수 있습니다.");
			$(this).val($(this).val().replace(regExp, ""));
		}
	});
	
	$("#pass1").on("keyup", inputCharReplace);
	$("#pass2").on("keyup", inputCharReplace);
	$("#emailId").on("keyup", inputCharReplace);
	$("#emailDomain").on("keyup", inputEmailDomainReplace);
		
	// 로그인 폼 유효성 검사
	$("#loginForm").submit(function() {
		let id = $("#userId").val();
		let pass = $("#userPass").val();
		
		if(id.length <= 0) {
			alert("아이디를 입력해 주세요");
			$("#userId").focus();			
			return false;
		}
		if(pass.length <= 0) {
			alert("비밀번호를 입력해 주세요")
			$("#userPass").focus();
			return false;			
		}
	});

	// 모달 로그인 폼 유효성 검사
	$("#modalLoginForm").submit(function() {
		let id = $("#modalUserId").val();
		let pass = $("#modalUserPass").val();
		
		if(id.length <= 0) {
			alert("아이디를 입력해 주세요");
			$("#modalUserId").focus();			
			return false;
		}
		if(pass.length <= 0) {
			alert("비밀번호를 입력해 주세요")
			$("#modalUserPass").focus();
			return false;			
		}
	});	
});

function joinFormCheck(isJoinForm) {
	
	if($("#name").val().length == 0) {
		alert('이름을 입력하세요');		
		return false;		
	}
	
	if($("#id").val().length == 0) {
		alert('아이디를 입력하세요');
		return false;		
	}
	
	if(isJoinForm && $("#isIdCheck").val() == 'false') {
		alert("아이디 중복 체크를 하세요");
		return false;
	}
	
	if($("#pass1").val().length == 0) {
		alert('비밀번호를 입력하세요');
		return false;		
	}
	
	if($("#pass2").val().length == 0) {
		alert('비밀번호 확인을 입력하세요');
		return false;		
	}
	
	if($("#pass1").val() != $("#pass2").val()) {
		alert("비밀번호와 비밀번호 확인이 다릅니다.");
		return false;	
	}
	
	if($("#zipcode").val().length == 0) {
		alert('우편번호를 입력하세요');
		return false;		
	}
	
	if($("#address1").val().length == 0) {
		alert('자택주소를 입력하세요');
		return false;		
	}
	
	if($("#emailId").val().length == 0) {
		alert('이메일 아이디를 입력하세요');
		return false;		
	}
	
	if($("#emailDomain").val().length == 0) {
		alert('이메일 도메인을 입력하세요');
		return false;		
	}
	
	if($("#mobile2").val().length == 0 || $("#mobile3").val().length == 0) {
		alert('휴대폰 번호를 입력하세요');
		return false;		
	}
	
}


function inputCharReplace() {
	let regExp = /[^A-Za-z0-9]/gi;
	if(regExp.test($(this).val())) {
		alert("영문 대소문자, 숫자만 입력할 수 있습니다.");
		$(this).val($(this).val().replace(regExp, ""));
	}
}

function inputEmailDomainReplace() {
	let regExp = /[^a-z0-9\.]/gi;
	if(regExp.test($(this).val())) {
		alert("영문 소문자, 숫자, 점(.)만 입력할 수 있습니다.");
		$(this).val($(this).val().replace(regExp, ""));
	}
}

// 카카오 우편번호 AIP를 이용해서 우편번호를 찾는 함수
function findZipcode() {
	new daum.Postcode({
	     oncomplete: function(data) {
	        
	         // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	         // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	         var addr = data.roadAddress; // 도로명 주소 변수
	         var extraAddr = ''; // 참고 항목 변수

	         // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	         // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	         if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	             extraAddr += data.bname;
	         }
	         // 건물명이 있고, 공동주택일 경우 추가한다.
	         if(data.buildingName !== '' && data.apartment === 'Y'){
	            extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	         }
	         // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	         if(extraAddr !== ''){
	             extraAddr = ' (' + extraAddr + ')';
	         }
			 
			addr += extraAddr;

	        // 우편번호와 주소 정보를 해당 필드에 넣는다.
	        $("#zipcode").val(data.zonecode);
			$("#address1").val(addr);
	         
			$("#address2").focus();

	     } // end oncomplte
	 }).open();	
}






