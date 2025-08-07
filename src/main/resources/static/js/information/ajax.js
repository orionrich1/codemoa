// DOM(Document Object Model)이 준비되면
$(function() {
	
	$("#typeSelector3").on('change', function() {
		$.ajax({
			url: "/orderbook.ajax",
			type: "get",
			data : {order : $("#typeSelector3").val()},
			dataType: "json",
			success: function(resData) {	
				console.log(resData);	
				
				// 1. 컨텐츠 및 페이지네이션 비우기
			   $("#content").empty();
			   $("#Row").empty();

			   // 2. 새로운 리스트 HTML 구성
			   let html = `
			       <div class="layout">
			           <div class="bod-list-body">
			               <ul class="bod-gallery-list">
			                   <div class="swiper mySwiper">
			                       <div class="swiper-wrapper">
			   `;

			   resData.bList.forEach(book => {
			       html += `
			           <div class="swiper-slide">
			               <div class="swiper-area">
			                   <div class="cover">
			                       <div class="cover-content">
			                           <img src="/files/information/${book.file1}" alt="이미지를 찾을 수 없습니다.">
			                           <div class="texts">
			                               <strong>${book.title}</strong>
			                               <span>
			                                   <i data-color="2">출판일</i> ${book.pubDate.split("T")[0]}
			                               </span>
			                               <span>
			                                   <i data-color="1">평점</i> ${book.rating}
			                               </span>
			                           </div>
			                           <a href="/information/bookDetail?no=${book.bookNo}&pageNum=1">
			                               <div class="cover-item">
			                                   <div class="cover-text">
			                                       <span class="cover-label"></span>
			                                       ${book.content}
			                                   </div>
			                               </div>
			                           </a>
			                       </div>
			                   </div>
			               </div>
			           </div>
			       `;
			   });

			   html += `
			                       </div>
			                   </div>
			               </ul>
			           </div>
			       </div>
			   `;

			   $("#content").append(html);
				
			   // 3. 페이지네이션도 필요하다면 추가 
			   renderPagination(resData);
			
			   new Swiper(".mySwiper", {
			   		slidesPerView: 4, // (가로)한 줄에 4개의 슬라이드가 보임
			   	  	grid: {
			   	    	rows: 2,
			   	   		fill: "row",
			   	  	},
			   	  	spaceBetween: 20,
			 	});
			},
			error: function(xhr, status, error) {
				alert("error : " + xhr.statusText + ", " + status + ", " + error);
			}
		});
	});
	
	
	$("#typeSelector2").on('change', function() {
		$.ajax({
			url: "/ordercontest.ajax",
			type: "get",
			data : {order : $("#typeSelector2").val()},
			dataType: "json",
			success: function(resData) {	
				console.log(resData);	
				
				// 1. 컨텐츠 및 페이지네이션 비우기
			   $("#content").empty();
			   $("#Row").empty();

			   // 2. 새로운 리스트 HTML 구성
			   let html = `
			       <div class="layout">
			           <div class="bod-list-body">
			               <ul class="bod-gallery-list">
			                   <div class="swiper mySwiper">
			                       <div class="swiper-wrapper">
			   `;

			   resData.cList.forEach(contest => {
			       html += `
			           <div class="swiper-slide">
			               <div class="swiper-area">
			                   <div class="cover">
			                       <div class="cover-content">
			                           <img src="/files/information/${contest.file1}" alt="이미지를 찾을 수 없습니다.">
			                           <div class="texts">
			                               <strong>${contest.title}</strong>
			                               <span>
			                                   <i data-color="2">기한</i> ${contest.startDate.split("T")[0]} ~ ${contest.endDate}
			                               </span>
			                               <span>
			                                   <i data-color="1">주최기관</i> ${contest.hostOrganization}
			                               </span>
			                           </div>
			                           <a href="/information/contestDetail?no=${contest.contestNo}&pageNum=1">
			                               <div class="cover-item">
			                                   <div class="cover-text">
			                                       <span class="cover-label"></span>
			                                       <p>작성자 : ${contest.content}</p>
												   <p>등록일 : ${contest.regDate.split("T")[0]}</p>
			                                   </div>
			                               </div>
			                           </a>
			                       </div>
			                   </div>
			               </div>
			           </div>
			       `;
			   });

			   html += `
			                       </div>
			                   </div>
			               </ul>
			           </div>
			       </div>
			   `;

			   $("#content").append(html);
				
			   renderPagination(resData);
			
			   new Swiper(".mySwiper", {
			   		slidesPerView: 4, 
			   	  	grid: {
			   	    	rows: 2,
			   	   		fill: "row",
			   	  	},
			   	  	spaceBetween: 20,
			 	});
			},
			error: function(xhr, status, error) {
				alert("error : " + xhr.statusText + ", " + status + ", " + error);
			}
		});
	});
	
	
	$("#typeSelector1").on('change', function() {
		$.ajax({
			url: "/orderlecture.ajax",
			type: "get",
			data : {order : $("#typeSelector1").val()},
			dataType: "json",
			success: function(resData) {	
				console.log(resData);	
				
			   $("#content").empty();
			   $("#Row").empty();

			   let html = `
			       <div class="layout">
			           <div class="bod-list-body">
			               <ul class="bod-gallery-list">
			                   <div class="swiper mySwiper">
			                       <div class="swiper-wrapper">
			   `;

			   resData.lList.forEach(lecture => {
			       html += `
			           <div class="swiper-slide">
			               <div class="swiper-area">
			                   <div class="cover">
			                       <div class="cover-content">
			                           <img src="/files/information/${lecture.file1}" alt="이미지를 찾을 수 없습니다.">
			                           <div class="texts">
			                               <strong>${lecture.title}</strong>
			                               <span>
			                                   <i data-color="2">등록일</i> ${lecture.regDate.split("T")[0]}
			                               </span>
			                               <span>
			                                   <i data-color="1">작성자</i> ${lecture.userId}
			                               </span>
			                           </div>
			                           <a href="/information/lectureDetail?no=${lecture.recommendNo}&pageNum=1">
			                               <div class="cover-item">
			                                   <div class="cover-text">
			                                       <span class="cover-label"></span>
			                                       ${lecture.subtitle}
			                                   </div>
			                               </div>
			                           </a>
			                       </div>
			                   </div>
			               </div>
			           </div>
			       `;
			   });

			   html += `
			                       </div>
			                   </div>
			               </ul>
			           </div>
			       </div>
			   `;

			   $("#content").append(html);
				
			   renderPagination(resData);
			
			   new Swiper(".mySwiper", {
			   		slidesPerView: 4, 
			   	  	grid: {
			   	    	rows: 2,
			   	   		fill: "row",
			   	  	},
			   	  	spaceBetween: 20,
			 	});
			},
			error: function(xhr, status, error) {
				alert("error : " + xhr.statusText + ", " + status + ", " + error);
			}
		});
	});
});

function renderPagination(data) {
    const {
        currentPage,
        startPage,
        endPage,
        pageCount,
        pageGroup,
        order
    } = data;

    let paginationHtml = `
        <div class="col">
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
    `;

    // 이전 페이지 버튼
    if (startPage > pageGroup) {
        const prevPage = startPage - 1;
        paginationHtml += `<li class="page-item"><a class="page-link" href="?pageNum=${prevPage}&order=${order}">Pre</a></li>`;
    }

    // 페이지 번호들
    for (let i = startPage; i <= endPage; i++) {
        const activeClass = (i === currentPage) ? "active" : "";
        paginationHtml += `
            <li class="page-item ${activeClass}">
                <a class="page-link" href="?pageNum=${i}&order=${order}">${i}</a>
            </li>`;
    }

    // 다음 페이지 버튼
    if (endPage < pageCount) {
        const nextPage = startPage + pageGroup;
        paginationHtml += `<li class="page-item"><a class="page-link" href="?pageNum=${nextPage}&order=${order}">Next</a></li>`;
    }

    paginationHtml += `
                </ul>
            </nav>
        </div>
    `;

    document.querySelector("#Row").innerHTML = paginationHtml;
}

