$(function() {
	
	
	// 강좌관련
	
	$("#lectureWriteForm, #lectureUpdateForm").on("submit", function(e) {
		e.preventDefault();
		e.stopPropagation();
		const formId = this.id;
		
		if (validateLectureForm(formId)) {
	       document.getElementById(formId).submit();
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
	
	$("#contestWriteForm, #contestUpdateForm").on("submit", function(e) {
		e.preventDefault();
		e.stopPropagation();
		const formId = this.id;
		
		if (validateContestForm(formId)) {
	       document.getElementById(formId).submit();
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
	
	$("#bookWriteForm, #bookUpdateForm").on("submit", function(e) {
		e.preventDefault();
		e.stopPropagation();
		const formId = this.id;
		
		if (validateBookForm(formId)) {
	       document.getElementById(formId).submit();
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

function validateLectureForm(formId) {
    const $form = $(`#${formId}`);

    const $userId = $form.find("#userId");
    if ($userId.length === 0 || $userId.val()?.trim().length <= 0) {
        alert("작성자를 입력해 주세요");
        $userId.focus();
        return false;
    }

    const $rating = $form.find("#rating");
    let ratingVal = parseFloat($rating.val());
    if ($rating.length === 0 || $rating.val()?.trim() === "" || isNaN(ratingVal) || ratingVal < 0 || ratingVal > 5) {
        alert("평점을 0에서 5 사이의 숫자로 정확히 입력해 주세요");
        $rating.focus();
        return false;
    }

    const $title = $form.find("#title");
    if ($title.length === 0 || $title.val()?.trim().length <= 0) {
        alert("제목을 입력해 주세요");
        $title.focus();
        return false;
    }

    const $subtitle = $form.find("#subtitle");
    if ($subtitle.length === 0 || $subtitle.val()?.trim().length <= 0) {
        alert("부제목을 입력해 주세요");
        $subtitle.focus();
        return false;
    }

    const $category = $form.find("#category");
    if ($category.length === 0 || $category.val()?.trim().length <= 0) {
        alert("카테고리를 입력해 주세요");
        $category.focus();
        return false;
    }

    const $content1 = $form.find("#content1");
    if ($content1.length === 0 || $content1.val()?.trim().length <= 0) {
        alert("내용을 입력해 주세요");
        $content1.focus();
        return false;
    }

    const $content2 = $form.find("#content2");
    if ($content2.length === 0 || $content2.val()?.trim().length <= 0) {
        alert("내용을 입력해 주세요");
        $content2.focus();
        return false;
    }

    return true;
}


function validateContestForm(formId) {
    const $form = $(`#${formId}`);

    const $userId = $form.find("#userId");
    if ($userId.length === 0 || $userId.val()?.trim().length <= 0) {
        alert("작성자를 입력해 주세요");
        $userId.focus();
        return false;
    }

    const $title = $form.find("#title");
    if ($title.length === 0 || $title.val()?.trim().length <= 0) {
        alert("제목을 입력해 주세요");
        $title.focus();
        return false;
    }

    const $hostOrg = $form.find("#hostOrganization");
    if ($hostOrg.length === 0 || $hostOrg.val()?.trim().length <= 0) {
        alert("주최기관을 입력해 주세요");
        $hostOrg.focus();
        return false;
    }

    const $start = $form.find("#start");
    const $end = $form.find("#end");
    const startDate = $start.val()?.trim();
    const endDate = $end.val()?.trim();

    if ($start.length === 0 || !startDate) {
        alert("시작일을 입력해 주세요");
        $start.focus();
        return false;
    }

    if ($end.length === 0 || !endDate) {
        alert("마감일을 입력해 주세요");
        $end.focus();
        return false;
    }

    if (new Date(startDate) > new Date(endDate)) {
        alert("시작일은 마감일보다 이전이어야 합니다");
        $start.focus();
        return false;
    }

    const $content = $form.find("#content");
    if ($content.length === 0 || $content.val()?.trim().length <= 0) {
        alert("내용을 입력해 주세요");
        $content.focus();
        return false;
    }

    return true;
}


function validateBookForm(formId) {
    const $form = $(`#${formId}`);

    const $userId = $form.find("#userId");
    if ($userId.length === 0 || $userId.val()?.trim().length <= 0) {
        alert("작성자를 입력해 주세요");
        $userId.focus();
        return false;
    }

    const $title = $form.find("#title");
    if ($title.length === 0 || $title.val()?.trim().length <= 0) {
        alert("제목을 입력해 주세요");
        $title.focus();
        return false;
    }

    const $pub = $form.find("#pub");
    const pubDate = $pub.val()?.trim();
    if ($pub.length === 0 || !pubDate) {
        alert("시작일을 입력해 주세요");
        $pub.focus();
        return false;
    }

    const $content = $form.find("#content");
    if ($content.length === 0 || $content.val()?.trim().length <= 0) {
        alert("내용을 입력해 주세요");
        $content.focus();
        return false;
    }

    const $rating = $form.find("#rating");
    let ratingVal = parseFloat($rating.val());
    if ($rating.length === 0 || $rating.val()?.trim() === "" || isNaN(ratingVal) || ratingVal < 0 || ratingVal > 10) {
        alert("평점을 0에서 10 사이의 숫자로 정확히 입력해 주세요");
        $rating.focus();
        return false;
    }

    const $isbn = $form.find("#isbn");
    if ($isbn.length === 0 || $isbn.val()?.trim().length <= 0) {
        alert("ISBN 번호를 입력해 주세요");
        $isbn.focus();
        return false;
    }

    const $totalPageNum = $form.find("#totalPageNum");
    if ($totalPageNum.length === 0 || $totalPageNum.val()?.trim().length <= 0) {
        alert("페이지 수를 입력해 주세요");
        $totalPageNum.focus();
        return false;
    }

    return true;
}
