// DOM이 준비되면
$(function() {
	
	
	// 댓글 삭제하기 버튼 클릭 이벤트 처리
	$(document).on("click", ".deleteReply", function() { 
		
		// 댓글 리스트를 갱신하면 모두 삭제되기 때문에 폼도 같이 삭제됨 - 폼을 백업
		$("#global-content > div").append($("#replyForm").slideUp(300));
		$("replyContent").val("");
		
		// 작성자 읽어오기
		let writer = $(this).parent().prev().find("span").text();
		
		// 댓글을 삭제할 때 필요한 데이터 - 댓글 번호
		// 댓글을 갱신 - 게시 글 번호
		console.log("data : ", $(this).data("no"));
		let no = $(this).attr("data-no");
		let bbsNo = $("#replyForm input[name='bbsNo']").val();
		
		let params = "no=" + no + "&bbsNo=" + bbsNo;
		console.log(params);
		
		const result = confirm(writer + "님이 작성한 댓글을 삭제할까요?");
		if(result) {
			console.log(result);
			// ajax로 댓글 삭제 요청을 전송
			$.ajax({
				url: "replyDelete.ajax",
				data: params,
				type: "delete",
				dataType: "json",
				success: function(resData) {
					console.log(resData);
					// 기본에 화면에 보이는 리스트 지우기
					$("#replyList").empty();
									  	
					// 응답 받은 데이터를 이용해 화면을 갱신
					$.each(resData, function(index, value) {					
						// 2025-06-16 13:37:42   <= 2025. 6. 16. 오후 1:38:26
						let regDate = new Date(value.regDate);
						
						// 2025-06-16 13:37:42 
						let strDate = regDate.getFullYear() + "-" + ((regDate.getMonth() + 1 < 10) 
							? "0" + (regDate.getMonth() + 1) : (regDate.getMonth() + 1)) + "-"
							+ (regDate.getDate() < 10 ? "0" + regDate.getDate() : regDate.getDate()) + " " 
							+ (regDate.getHours() < 10 ? "0" + regDate.getHours() : regDate.getHours()) + ":"
							+ (regDate.getMinutes() < 10 ? "0" + regDate.getMinutes() : regDate.getMinutes()) + ":" 
							+ (regDate.getSeconds() < 10 ? "0" + regDate.getSeconds() : regDate.getSeconds());					
						
						let result =				
							'<div class="row replyRow border border-top-0" >'
							+ '<div class="col">'
							+ '	<div class="row bg-light p-2">'
							+ '		<div class="col-4">'
							+ '				<span>' + value.replyWriter + '</span>'
							+ '		</div>'
							+ '		<div class="col-8 text-end">'
							+ '			<span>' + strDate + '</span>'
							+ '			<button class="modifyReply btn btn-outline-success btn-sm" data-no="' + value.no + '">'
							+ '				<i class="bi bi-journal-text">수정</i>'										
							+ '			</button>'
							+ '			<button class="deleteReply btn btn-outline-warning btn-sm" data-no="' + value.no + '">'
							+ '				<i class="bi bi-trash">삭제</i>'
							+ '			</button>'
							+ '			<button class="btn btn-outline-danger btn-sm" onclick="reportReply(' + value.no + ')">'
							+ '				<i class="bi bi-telephone-outbound-fill">신고</i>'
							+ '			</button>'
							+ '		</div>'
							+ '	</div>'
							+ '	<div class="row">'
							+ '		<div class="col p-3">'
							+ '			<pre>' + value.replyContent+ '</pre>'
							+ '		</div>'
							+ '	</div>'								
							+ '</div>'
						+ '</div>';
						
						$("#replyList").append(result);
						
					});
										
				}	,
				error: function() {
					console.log("error...");
				}			
			});
		}		
	});
	
	
	// 댓글 수정 폼이 서브밋 이벤트 처리
	$(document).on("submit", "#replyUpdateForm", function() {
		
		if($("#replyContent").val().length <= 5) {
			alert("댓글은 5자 이상 입력해야 합니다.");
			return false;
		}
		// bbsNo=20&replyWriter=midas&no=40
		let params = $(this).serialize() + "&no=" + $(this).attr("data-no");
		console.log("replyUpdate : ", params)
		
		// 댓글 리스트를 갱신하면 모두 삭제되기 때문에 폼도 같이 삭제됨 - 폼을 백업
		$("#global-content > div").append($("#replyForm").slideUp(300));
						
		$.ajax({
			url: "replyUpdate.ajax",
			type: "patch", // post, get, put, patch
			data: params,
			dataType: "json",
			success: function(resData) {
				console.log(resData);
				
				// 기본에 화면에 보이는 리스트 지우기
				$("#replyList").empty();
								  	
				// 응답 받은 데이터를 이용해 화면을 갱신
				$.each(resData, function(index, value) {					
					// 2025-06-16 13:37:42   <= 2025. 6. 16. 오후 1:38:26
					let regDate = new Date(value.regDate);
					
					// 2025-06-16 13:37:42 
					let strDate = regDate.getFullYear() + "-" + ((regDate.getMonth() + 1 < 10) 
						? "0" + (regDate.getMonth() + 1) : (regDate.getMonth() + 1)) + "-"
						+ (regDate.getDate() < 10 ? "0" + regDate.getDate() : regDate.getDate()) + " " 
						+ (regDate.getHours() < 10 ? "0" + regDate.getHours() : regDate.getHours()) + ":"
						+ (regDate.getMinutes() < 10 ? "0" + regDate.getMinutes() : regDate.getMinutes()) + ":" 
						+ (regDate.getSeconds() < 10 ? "0" + regDate.getSeconds() : regDate.getSeconds());					
					
					let result =				
						'<div class="row replyRow border border-top-0" >'
						+ '<div class="col">'
						+ '	<div class="row bg-light p-2">'
						+ '		<div class="col-4">'
						+ '				<span>' + value.replyWriter + '</span>'
						+ '		</div>'
						+ '		<div class="col-8 text-end">'
						+ '			<span>' + strDate + '</span>'
						+ '			<button class="modifyReply btn btn-outline-success btn-sm" data-no="' + value.no + '">'
						+ '				<i class="bi bi-journal-text">수정</i>'										
						+ '			</button>'
						+ '			<button class="deleteReply btn btn-outline-warning btn-sm" data-no="' + value.no + '">'
						+ '				<i class="bi bi-trash">삭제</i>'
						+ '			</button>'
						+ '			<button class="btn btn-outline-danger btn-sm" onclick="reportReply(' + value.no + ')">'
						+ '				<i class="bi bi-telephone-outbound-fill">신고</i>'
						+ '			</button>'
						+ '		</div>'
						+ '	</div>'
						+ '	<div class="row">'
						+ '		<div class="col p-3">'
						+ '			<pre>' + value.replyContent+ '</pre>'
						+ '		</div>'
						+ '	</div>'								
						+ '</div>'
					+ '</div>';
					
					$("#replyList").append(result);
					
				});

				$("#replyContent").val("");								
				
			},
			error: function() {
				console.log("error...");
			}			
		});
		
		return false;
	});
	
	
	// 댓글 수정 버튼이 클릭되면
	$(document).on("click", ".modifyReply", function(e) {
		
		let $replyRow = $(this).parents(".replyRow");
		
		if($("#replyForm").is(":visible"))	{ // 현재 폼이 보인다면
			
			let $next = $replyRow.next();
			if(! $next.is("#replyForm")) {

				$("#replyForm").slideUp(300);
			}
			setTimeout(function() {
				$("#replyForm").insertAfter($replyRow ).slideDown(300);	
			}, 300);			
			
		} else {		// 현재 폼이 보이지 않는다면				
			$("#replyForm").removeClass("d-none").css("display", "none")
				.insertAfter($replyRow ).slideDown(300);	
		}
		
		$("#replyForm").find("form")
			.attr({"id": "replyUpdateForm", "data-no": $(this).attr("data-no")});		
		$("#replyWriteButton").val("댓글수정");
		
		let reply = $(this).parent().parent().next().find("pre").text();
		$("#replyContent").val(reply);		
		
	});
	
	// 댓글 쓰기 폼이 서브밋 될 때 
	$(document).on("submit", "#replyWriteForm", function(e) {
				
		if($("#replyContent").val().length < 5) {
			alert("댓글은 5자 이상 입력해야 합니다.");
			return false;
		}
		
		let params = $(this).serialize();
		console.log("replyWrite : ", params);
		
		$.ajax({
			url: "replyWrite.ajax",
			type: "post",
			data: params,
			dataType: "json",
			success: function(resData) {
			    console.log(resData);
				$("#replyList").empty();
			    	
				// 응답 받은 데이터를 이용해 화면을 갱신
				$.each(resData, function(index, value) {					
					// 2025-06-16 13:37:42   <= 2025. 6. 16. 오후 1:38:26
					let regDate = new Date(value.regDate);
					
					// 2025-06-16 13:37:42 
					let strDate = regDate.getFullYear() + "-" + ((regDate.getMonth() + 1 < 10) 
						? "0" + (regDate.getMonth() + 1) : (regDate.getMonth() + 1)) + "-"
						+ (regDate.getDate() < 10 ? "0" + regDate.getDate() : regDate.getDate()) + " " 
						+ (regDate.getHours() < 10 ? "0" + regDate.getHours() : regDate.getHours()) + ":"
						+ (regDate.getMinutes() < 10 ? "0" + regDate.getMinutes() : regDate.getMinutes()) + ":" 
						+ (regDate.getSeconds() < 10 ? "0" + regDate.getSeconds() : regDate.getSeconds());					
					
					let result =				
						'<div class="row replyRow border border-top-0" >'
						+ '<div class="col">'
						+ '	<div class="row bg-light p-2">'
						+ '		<div class="col-4">'
						+ '				<span>' + value.replyWriter + '</span>'
						+ '		</div>'
						+ '		<div class="col-8 text-end">'
						+ '			<span>' + strDate + '</span>'
						+ '			<button class="modifyReply btn btn-outline-success btn-sm" data-no="' + value.no + '">'
						+ '				<i class="bi bi-journal-text">수정</i>'										
						+ '			</button>'
						+ '			<button class="deleteReply btn btn-outline-warning btn-sm" data-no="' + value.no + '">'
						+ '				<i class="bi bi-trash">삭제</i>'
						+ '			</button>'
						+ '			<button class="btn btn-outline-danger btn-sm" onclick="reportReply(' + value.no + ')">'
						+ '				<i class="bi bi-telephone-outbound-fill">신고</i>'
						+ '			</button>'
						+ '		</div>'
						+ '	</div>'
						+ '	<div class="row">'
						+ '		<div class="col p-3">'
						+ '			<pre>' + value.replyContent+ '</pre>'
						+ '		</div>'
						+ '	</div>'								
						+ '</div>'
					+ '</div>';
					
					$("#replyList").append(result);
					
				});
				
				$("#replyForm").slideUp(300).add("#replyContent").val("");

			},
			error: function() {
				console.log("ajax error...");
			}
		});
		
		//return false;
		e.preventDefault();				
	});

	
	// 댓글쓰기 버튼이 클릭되면
	$("#replyWrite").on("click", function() {			
		
		console.log($("#replyForm").css("display"));
		if($("#replyForm").is(":visible"))	{ // 현재 폼이 보인다면
			
			let $prev = $("#replyTitle").prev();
			if(! $prev.is("#replyForm")) {
				$("#replyForm").slideUp(300);
			}
			
			setTimeout(function() {
				$("#replyForm").insertBefore("#replyTitle").slideDown(300);	
			}, 300);
			
			
		} else {		// 현재 폼이 보이지 않는다면				
			$("#replyForm").removeClass("d-none").css("display", "none")
				.insertBefore("#replyTitle").slideDown(300);	
		}
		
		$("#replyForm").find("form")
			.attr("id", "replyWriteForm").removeAttr("data-no");
		$("#replyContent").val("");
		$("#replyWriteButton").val("댓글쓰기");
		
	});
	
	$(".btnCommend").click(function() {
		let com = $(this).attr("id");
		console.log("com : ", com);
		
		$.ajax({
			url: "recommend.ajax",
			data: {recommend: com, no: $("#no").val()},
			dataType: "json",
			type: "post",
			success: function(resData) {
				console.log(resData);
				let msg = com == 'commend' ? "추천이" : "땡큐가";
				alert(msg + " 반영 되었습니다.");
				$("#commend > .recommend").text(" (" + resData.recommend + ")")
				$("#thank > .recommend").text(" (" + resData.thank + ")")
			}, 
			error: function(xhr, satus, error) {
				console.log("error : " + xhr.statusText + ", " + status + ", " + error); 
			}
		});
		
	});
	
});

function reportReply(replyNo) {
	alert(replyNo + "번 댓글 신고");
}




