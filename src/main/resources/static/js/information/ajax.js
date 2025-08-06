// DOM(Document Object Model)이 준비되면
$(function() {
	
	
	$("#typeSelector").on('change', function() {
		
		
		$.ajax({
			url: "/orderbook.ajax",
			type: "get",
			data : {order : $("#typeSelector").val()},
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
	
	

});

function renderPagination(data) {
    const {
        currentPage,
        startPage,
        endPage,
        pageCount,
        pageGroup,
        searchOption
    } = data;

    let paginationHtml = `
        <div class="col">
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
    `;

    // 이전 페이지 버튼
    if (startPage > pageGroup) {
        const prevPage = startPage - 1;
        paginationHtml += `<li class="page-item"><a class="page-link" href="?pageNum=${prevPage}&order=${$("#typeSelector").val()}">Pre</a></li>`;
    }

    // 페이지 번호들
    for (let i = startPage; i <= endPage; i++) {
        const activeClass = (i === currentPage) ? "active" : "";
        paginationHtml += `
            <li class="page-item ${activeClass}">
                <a class="page-link" href="?pageNum=${i}&order=${$("#typeSelector").val()}">${i}</a>
            </li>`;
    }

    // 다음 페이지 버튼
    if (endPage < pageCount) {
        const nextPage = startPage + pageGroup;
        paginationHtml += `<li class="page-item"><a class="page-link" href="?pageNum=${nextPage}&order=${$("#typeSelector").val()}">Next</a></li>`;
    }

    paginationHtml += `
                </ul>
            </nav>
        </div>
    `;

    document.querySelector("#Row").innerHTML = paginationHtml;
}

