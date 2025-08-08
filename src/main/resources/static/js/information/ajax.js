let swiperInstance = null;

$(function() {

	swiperInstance = initSwiper();
	
	$(document).on('change', "#typeSelector3", function(e) {
		const urlParams = new URLSearchParams(window.location.search);
		const pageNum = urlParams.get('pageNum') || 1;
		const order = $("#typeSelector3").val();
		const keyword = $("#bookSearch").val();
		let type = "";

		if (keyword !== null && keyword.trim() !== "") {
		    type = "title";
		}
		
		e.preventDefault();     
		e.stopPropagation();
		
		$.ajax({
			url: "/orderbook.ajax",
			type: "get",
			data: { order : order, keyword : keyword, pageNum : pageNum , type : type},
			dataType: "json",
			success: function (resData) {
				console.log(resData);

				renderBookList(resData.bList, pageNum, order, keyword, type);

				$("#Row").empty();
				renderPagination(resData);
			},
			error: function (xhr, status, error) {
				alert("error : " + xhr.statusText + ", " + status + ", " + error);
			}
		});
	});

	
	$(document).on('submit', "#bookSearchForm", function(e) {
		const keyword = $("#bookSearch").val();
		const pageNum = 1;
		const order = $("#typeSelector3").val();

		e.preventDefault();     
		e.stopPropagation();

		$.ajax({
			url: "/booksearch",
			type: "get",
			data: { keyword: keyword, order: order },
			dataType: "json",
			success: function (resData) {
				const type = "title";  // 성공 시에만 type을 설정
				renderBookList(resData.bList, pageNum, order, keyword, type);  // type 전달

				$("#Row").empty();
				renderPagination(resData);
			},
			error: function (xhr, status, error) {
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
			
			},
			error: function(xhr, status, error) {
				alert("error : " + xhr.statusText + ", " + status + ", " + error);
			}
		});
	});




	$(document).on('change', "#typeSelector1", function(e) {
		const urlParams = new URLSearchParams(window.location.search);
		const pageNum = urlParams.get('pageNum') || 1;
		const order = $("#typeSelector1").val();
		const keyword = $("#lectureSearch").val();
		let type = "";

		if (keyword !== null && keyword.trim() !== "") {
		    type = "title";
		}
		
		e.preventDefault();
		e.stopPropagation();

		$.ajax({
			url: "/orderlecture.ajax",
			type: "get",
			data: { order : order, keyword : keyword, pageNum : pageNum, type : type},
			dataType: "json",
			success: function(resData) {
				console.log(resData);

				renderLectureList(resData.lList, pageNum, order, keyword, type);
				$("#Row").empty();
				renderPagination(resData);
			},
			error: function(xhr, status, error) {
				alert("error : " + xhr.statusText + ", " + status + ", " + error);
			}
		});
	});


	$(document).on('submit', "#lectureSearchForm", function(e) {
		const keyword = $("#lectureSearch").val();
		const pageNum = 1;
		const order = $("#typeSelector1").val();

		e.preventDefault();
		e.stopPropagation();

		$.ajax({
			url: "/lecturesearch",
			type: "get",
			data: { keyword: keyword, order: order },
			dataType: "json",
			success: function(resData) {
				const type = "title";  // 성공 시에만 설정
				renderLectureList(resData.lList, pageNum, order, keyword, type);  // type 전달

				$("#Row").empty();
				renderPagination(resData);

				new Swiper(".mySwiper", {
					slidesPerView: 4,
					grid: { rows: 2, fill: "row" },
					spaceBetween: 20,
				});
			},
			error: function(xhr, status, error) {
				alert("error : " + xhr.statusText + ", " + status + ", " + error);
			}
		});
	});


	
});

function resetSwiper() {
    if (swiperInstance !== null) {
        swiperInstance.destroy(true, true);  // 기존 swiper 완전 제거
    }
    swiperInstance = initSwiper();  // 새로 만든 인스턴스를 저장
}

function initSwiper() {
    return new Swiper(".mySwiper", {
        spaceBetween: 30,
        slidesPerView: 4,
        grid: {
            rows: 2,
            fill: "row"
        },
        pagination: {
            el: ".swiper-pagination",
            clickable: true
        },
        breakpoints: {
            0: { slidesPerView: 1, grid: { rows: 8 } },
            576: { slidesPerView: 2, grid: { rows: 4 } },
            768: { slidesPerView: 3, grid: { rows: 3 } },
            992: { slidesPerView: 4, grid: { rows: 2 } }
        }
    });
}



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

function renderBookList(bList, pageNum, order, keyword, type) {
	let html = '';
	const slicedList = bList.slice(0, 8); // 항상 최대 8개만

	slicedList.forEach(book => {
		const detailUrl = `/information/bookDetail?no=${book.bookNo}&pageNum=${pageNum}&order=${order || ''}&keyword=${encodeURIComponent(keyword || '')}&type=${encodeURIComponent(type || '')}`;

		html += `
			<div class="swiper-slide">
				<div class="book-gallery-card">
					<a href="${detailUrl}">
						<img src="/files/information/${book.file1}" alt="이미지" class="book-cover-image">
						<div class="book-hover-overlay">
							<div class="book-hover-content">
								${book.content}
							</div>
						</div>
					</a>
					<div class="book-texts">
						<strong>${book.title}</strong>
						<div class="metadata">
							<span><b class="fw-bold">출판일:</b> ${book.pubDate.split("T")[0]}</span>
							<span><b class="fw-bold">평점:</b> ${book.rating}</span>
						</div>
					</div>
				</div>
			</div>
		`;
	});

	$("#content").empty().append(html);
	resetSwiper(); // Swiper 재초기화
}


function renderLectureList(lList, pageNum, order, keyword, type) {
	let html = '';

	lList.forEach(lecture => {
		const detailUrl = `/information/lectureDetail?no=${lecture.recommendNo}&pageNum=${pageNum}&order=${order || ''}&keyword=${encodeURIComponent(keyword || '')}&type=${encodeURIComponent(type || '')}`;

		html += `
			<div class="swiper-slide">
				<div class="item-gallery-card">
					<a href="${detailUrl}">
						<img src="/files/information/${lecture.file1}" alt="이미지" class="item-cover-image">
						<div class="item-hover-overlay">
							<div class="item-hover-content">
								${lecture.subtitle}
							</div>
						</div>
					</a>
					<div class="item-texts">
						<strong>${lecture.title}</strong>
						<div class="metadata">
							<span><b class="fw-bold">평점:</b> ${lecture.rating}</span>
							<span><b class="fw-bold">작성자:</b> ${lecture.userId}</span>
						</div>
					</div>
				</div>
			</div>
		`;
	});

	$("#content").empty().append(html);
	resetSwiper();
}


