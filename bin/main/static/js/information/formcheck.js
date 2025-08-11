$(function() {
	
	
	// 강좌관련
	
	$("#lectureWriteForm, #lectureUpdateForm").on("submit", function() {
	    return validateLectureForm(this.id);
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
	
	$("#contestWriteForm, #contestUpdateForm").on("submit", function() {
	    return validateContestForm(this.id);
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
	
	$("#bookWriteForm, #bookUpdateForm").on("submit", function() {
	    return validateBookForm(this.id);
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

function validateLectureForm(formId) {
    const $form = $(`#${formId}`);
    
    if ($form.find("#userId").val().trim().length <= 0) {
        alert("작성자를 입력해 주세요");
        $form.find("#userId").focus();
        return false;
    }

    let ratingVal = parseFloat($form.find("#rating").val());
    if ($form.find("#rating").val().trim() === "" ||
        isNaN(ratingVal) || ratingVal < 0 || ratingVal > 5) {
        alert("평점을 0에서 5 사이의 숫자로 정확히 입력해 주세요");
        $form.find("#rating").focus();
        return false;
    }

    if ($form.find("#title").val().trim().length <= 0) {
        alert("제목을 입력해 주세요");
        $form.find("#title").focus();
        return false;
    }

    if ($form.find("#subtitle").val().trim().length <= 0) {
        alert("부제목을 입력해 주세요");
        $form.find("#subtitle").focus();
        return false;
    }

    if ($form.find("#category").val().trim().length <= 0) {
        alert("카테고리를 입력해 주세요");
        $form.find("#category").focus();
        return false;
    }

    if ($form.find("#content1").val().trim().length <= 0) {
        alert("내용을 입력해 주세요");
        $form.find("#content1").focus();
        return false;
    }

    if ($form.find("#content2").val().trim().length <= 0) {
        alert("내용을 입력해 주세요");
        $form.find("#content2").focus();
        return false;
    }

    return true;
}

function validateContestForm(formId) {
    const $form = $(`#${formId}`);

    if ($form.find("#userId").val().trim().length <= 0) {
        alert("작성자를 입력해 주세요");
        $form.find("#userId").focus();
        return false;
    }

    if ($form.find("#title").val().trim().length <= 0) {
        alert("제목을 입력해 주세요");
        $form.find("#title").focus();
        return false;
    }

    if ($form.find("#hostOrganization").val().trim().length <= 0) {
        alert("주최기관을 입력해 주세요");
        $form.find("#hostOrganization").focus();
        return false;
    }

    const startDate = $form.find("#start").val();
    const endDate = $form.find("#end").val();

    if (startDate.trim().length <= 0) {
        alert("시작일을 입력해 주세요");
        $form.find("#start").focus();
        return false;
    }

    if (endDate.trim().length <= 0) {
        alert("마감일을 입력해 주세요");
        $form.find("#end").focus();
        return false;
    }

    if (new Date(startDate) > new Date(endDate)) {
        alert("시작일은 마감일보다 이전이어야 합니다");
        $form.find("#start").focus();
        return false;
    }

    if ($form.find("#pass").val().trim().length <= 0) {
        alert("비밀번호를 입력해 주세요");
        $form.find("#pass").focus();
        return false;
    }

    if ($form.find("#content").val().trim().length <= 0) {
        alert("내용을 입력해 주세요");
        $form.find("#content").focus();
        return false;
    }

    return true;
}

function validateBookForm(formId) {
    const $form = $(`#${formId}`);

    if ($form.find("#userId").val().trim().length <= 0) {
        alert("작성자를 입력해 주세요");
        $form.find("#userId").focus();
        return false;
    }

    if ($form.find("#title").val().trim().length <= 0) {
        alert("제목을 입력해 주세요");
        $form.find("#title").focus();
        return false;
    }

    if ($form.find("#pass").val().trim().length <= 0) {
        alert("비밀번호를 입력해 주세요");
        $form.find("#pass").focus();
        return false;
    }

    const pubDate = $form.find("#pub").val();
    if (pubDate.trim().length <= 0) {
        alert("시작일을 입력해 주세요");
        $form.find("#pub").focus();
        return false;
    }

    if ($form.find("#content").val().trim().length <= 0) {
        alert("내용을 입력해 주세요");
        $form.find("#content").focus();
        return false;
    }

    let ratingVal = parseFloat($form.find("#rating").val());
    if ($form.find("#rating").val().trim() === "" ||
        isNaN(ratingVal) || ratingVal < 0 || ratingVal > 10) {
        alert("평점을 0에서 10 사이의 숫자로 정확히 입력해 주세요");
        $form.find("#rating").focus();
        return false;
    }

    if ($form.find("#isbn").val().trim().length <= 0) {
        alert("ISBN 번호를 입력해 주세요");
        $form.find("#isbn").focus();
        return false;
    }

    if ($form.find("#totalPageNum").val().trim().length <= 0) {
        alert("페이지 수를 입력해 주세요");
        $form.find("#totalPageNum").focus();
        return false;
    }

    return true;
}