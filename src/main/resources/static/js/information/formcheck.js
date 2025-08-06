$(function() {
	
	
	// 강좌관련
	
	// 게시글 쓰기 폼 유효성 검사
	$("#lectureWriteForm").on("submit", function() {
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
		
		if($("#subtitle").val().length <= 0) {
			alert("부제목를 입력해 주세요");
			$("#subtitle").focus();
			return false;
		}
				
		if($("#category").val().length <= 0) {
			alert("카테고리를 입력해 주세요");
			$("#category").focus();
			return false;
		}
		
		if($("#content2").val().length <= 0) {
			alert("내용을 입력해 주세요");
			$("#content2").focus();
			return false;
		}
		
		let ratingVal = parseFloat($("#rating").val());
		if ($("#rating").val().trim() === "" ||          
		    isNaN(ratingVal) || ratingVal < 0 || ratingVal > 5) {
		    alert("평점을 0에서 5 사이의 숫자로 정확히 입력해 주세요");
		    $("#rating").focus();
		    return false;
		}
	});
	
	
	// 게시글 수정 폼 유효성 검사
	$("#lectureUpdateForm").on("submit", function() {
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
		
		if($("#subtitle").val().length <= 0) {
			alert("부제목를 입력해 주세요");
			$("#subtitle").focus();
			return false;
		}
				
		if($("#category").val().length <= 0) {
			alert("카테고리를 입력해 주세요");
			$("#category").focus();
			return false;
		}
		
		if($("#content2").val().length <= 0) {
			alert("내용을 입력해 주세요");
			$("#content2").focus();
			return false;
		}
		
		let ratingVal = parseFloat($("#rating").val());
		if ($("#rating").val().trim() === "" ||          
		    isNaN(ratingVal) || ratingVal < 0 || ratingVal > 5) {
		    alert("평점을 0에서 5 사이의 숫자로 정확히 입력해 주세요");
		    $("#rating").focus();
		    return false;
		}
	});
	
	// 게시 글 상세보기 "삭제하기" 버튼 이벤트
	$("#lectureDetailDelete").on("click", function() {
		
		let pass = $("#pass").val();
		if(pass.length <= 0) {
			alert("비밀번호를 입력해 주세요");
			return false;
		}

		$("#rPass").val(pass);
		$("#checkForm").attr("action","lectureDelete");
		$("#checkForm").attr("method","post");
		$("#checkForm").submit();
	});
	
	
	// 게시 글 상세보기 "수정버튼" 이벤트
	$("#detailUpdate").on("click", function() {
		
		let pass = $("#pass").val();
		if(pass.length <= 0) {
			alert("비밀번호를 입력해 주세요");
			return false;
		}

		$("#rPass").val(pass);
		$("#checkForm").attr("action","lectureUpdateForm");
		$("#checkForm").attr("method","post");
		$("#checkForm").submit();
	});
	
	
	// 공모전 관련 
	
	// 게시글 쓰기 폼 유효성 검사
	$("#contestWriteForm").on("submit", function() {
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
	$("#contestUpdateForm").on("submit", function() {
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
		
		if($("#category").val().length <= 0) {
			alert("카테고리를 입력해 주세요");
			$("#category").focus();
			return false;
		}
		
		if($("#content2").val().length <= 0) {
			alert("내용을 입력해 주세요");
			$("#content2").focus();
			return false;
		}
		
	});
	
	// 게시 글 상세보기 "삭제하기" 버튼 이벤트
	$("#contestDetailDelete").on("click", function() {
		
		let pass = $("#pass").val();
		if(pass.length <= 0) { 
			alert("비밀번호를 입력해 주세요");
			return false;
		}

		$("#rPass").val(pass);
		$("#checkForm").attr("action","contestDelete");
		$("#checkForm").attr("method","post");
		$("#checkForm").submit();
	});
	
	// 게시 글 상세보기 "수정버튼" 이벤트
	$("#contestDetailUpdate").on("click", function() {
		
		let pass = $("#pass").val();
		if(pass.length <= 0) {
			alert("비밀번호를 입력해 주세요");
			return false;
		}
		
		$("#rPass").val(pass);
		$("#checkForm").attr("action","contestUpdateForm");
		$("#checkForm").attr("method","post");
		$("#checkForm").submit();
	});
	
	
	// 도서 관련
	
	// 게시글 쓰기 폼 유효성 검사
	$("#bookWriteForm").on("submit", function() {
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
		
		let ratingVal = parseFloat($("#rating").val());
		if ($("#rating").val().trim() === "" ||          
		    isNaN(ratingVal) || ratingVal < 0 || ratingVal > 10) {
		    alert("평점을 0에서 10 사이의 숫자로 정확히 입력해 주세요");
		    $("#rating").focus();
		    return false;
		}
	});
	
	
	// 게시글 수정 폼 유효성 검사
	$("#bookUpdateForm").on("submit", function() {
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
		
		if($("#category").val().length <= 0) {
			alert("카테고리를 입력해 주세요");
			$("#category").focus();
			return false;
		}
		
		if($("#content2").val().length <= 0) {
			alert("내용을 입력해 주세요");
			$("#content2").focus();
			return false;
		}
		
		let ratingVal = parseFloat($("#rating").val());
		if ($("#rating").val().trim() === "" ||          
		    isNaN(ratingVal) || ratingVal < 0 || ratingVal > 10) {
		    alert("평점을 0에서 10 사이의 숫자로 정확히 입력해 주세요");
		    $("#rating").focus();
		    return false;
		}
	});
		
	$("#bookDetailDelete").on("click", function() {
		
		let pass = $("#pass").val();
		if(pass.length <= 0) { 
			alert("비밀번호를 입력해 주세요");
			return false;
		}
		
		$("#rPass").val(pass);
		$("#checkForm").attr("action","bookDelete");
		$("#checkForm").attr("method","post");
		$("#checkForm").submit();
	});
	
	// 게시 글 상세보기 "수정버튼" 이벤트
	$("#bookDetailUpdate").on("click", function() {
		
		let pass = $("#pass").val();
		if(pass.length <= 0) {
			alert("비밀번호를 입력해 주세요");
			return false;
		}

		$("#rPass").val(pass);
		$("#checkForm").attr("action","bookUpdateForm");
		$("#checkForm").attr("method","post");
		$("#checkForm").submit();
	});
	
	$(".searchbox").on("submit", function() {
		if($("#hiddenType").val() == "") {
			alert("검색 옵션을 선택해주세요.");
			return false;
		}
	});
	
});