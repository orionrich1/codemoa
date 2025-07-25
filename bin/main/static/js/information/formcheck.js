$(function() {
	// 게시 글 상세보기 "삭제하기" 버튼 이벤트
	$("#detailDelete").on("click", function() {
		
		// 사용자가 입력한 비번을 읽어온다.
		let pass = $("#pass").val();
		if(pass.length <= 0) { // 비번이 입력되지 않았으면
			alert("비밀번호를 입력해 주세요");
			return false;
		}
		
		// 비번이 입력되었으면 - 데이터와 함께 서버로 요청을 보냄
		$("#rPass").val(pass);
		$("#checkForm").attr("th:action","lectureDelete");
		$("#checkForm").attr("method","post");
		$("#checkForm").submit();
	});
	
	// 게시 글 상세보기 "수정버튼" 이벤트
	$("#detailUpdate").on("click", function() {
		
		// 사용자가 입력한 비번을 읽어온다.
		let pass = $("#pass").val();
		if(pass.length <= 0) { // 비번이 입력되지 않았으면
			alert("비밀번호를 입력해 주세요");
			return false;
		}
		
		// 비번이 입력되었으면 - 데이터와 함께 서버로 요청을 보냄
		$("#rPass").val(pass);
		$("#checkForm").attr("th:action","lectureUpdateForm");
		$("#checkForm").attr("method","post");
		$("#checkForm").submit();
	});
	
	// 게시글 쓰기 폼 유효성 검사
	$("#writeForm").on("submit", function() {
		if($("#writer").val().length <= 0) {
			alert("작성자를 입력해 주세요");
			$("#writer").focus();
			return false;
		}
		
		if($("#title").val().length <= 0) {
			alert("제목를 입력해 주세요");
			$("#title").focus();
			return false;
		}
		
		if($("#pass").val().length <= 0) {
			alert("비밀번호를 입력해 주세요");
			$("#pass").focus();
			return false;
		}
		
		if($("#content").val().length <= 0) {
			alert("내용을 입력해 주세요");
			$("#content").focus();
			return false;
		}
	});
	
	// 게시글 수정 폼 유효성 검사
	$("#updateForm").on("submit", function() {
		if($("#writer").val().length <= 0) {
			alert("작성자를 입력해 주세요");
			$("#writer").focus();
			return false;
		}
		
		if($("#title").val().length <= 0) {
			alert("제목를 입력해 주세요");
			$("#title").focus();
			return false;
		}
		
		if($("#pass").val().length <= 0) {
			alert("비밀번호를 입력해 주세요");
			$("#pass").focus();
			return false;
		}
		
		if($("#content").val().length <= 0) {
			alert("내용을 입력해 주세요");
			$("#content").focus();
			return false;
		}
	});
});