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


	
	
	
	
	$(document).on('change', "#typeSelector2", function () {
		const urlParams = new URLSearchParams(window.location.search);
		const pageNum = urlParams.get('pageNum') || 1;
		const order = $("#typeSelector2").val();
		const keyword = $("#contestSearch").val();
		let type = "";

		if (keyword !== null && keyword.trim() !== "") {
		    type = "title";
		}

	    $.ajax({
	        url: "/ordercontest.ajax",
	        type: "get",
	        data: { order: order },
	        dataType: "json",
	        success: function (resData) {
	            console.log(resData);
	            renderContestList(resData); 
	        },
	        error: function (xhr, status, error) {
	            alert("error : " + xhr.statusText + ", " + status + ", " + error);
	        }
	    });
	});


	$(document).on('submit', "#contestSearchForm", function(e) {
	    e.preventDefault();
	    e.stopPropagation();

	    const keyword = $("#contestSearch").val();
	    const order = $("#typeSelector2").val();
	    const type = keyword.trim() !== "" ? "title" : "";
	    const pageNum = 1;

	    $.ajax({
	        url: "/ordercontest.ajax",
	        type: "get",
	        data: {
	            order: order,
	            keyword: keyword,
	            pageNum: pageNum,
	            type: type
	        },
	        dataType: "json",
	        success: function (resData) {
	            console.log(resData);
	            renderContestList(resData, pageNum); 
	        },
	        error: function (xhr, status, error) {
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
				<div class="item-gallery-card">
					<a href="${detailUrl}">
						<img src="/files/information/${book.file1}" alt="이미지" class="item-cover-image">
						<div class="item-hover-overlay">
							<div class="item-hover-content">
								${book.content}
							</div>
						</div>
					</a>
					<div class="item-texts">
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

function renderContestList(resData, pageNum = 1, order = '', keyword = '', type = '') {
    $("#content").empty();
    $("#Row").empty();

    let html = '';

    resData.cList.slice(0, 8).forEach(contest => {
        const detailUrl = `/information/contestDetail?no=${contest.contestNo}&pageNum=${pageNum}&order=${order || ''}&keyword=${encodeURIComponent(keyword || '')}&type=${encodeURIComponent(type || '')}`;
        const imgSrc = `/files/information/${contest.file1}`;
        const regDate = contest.regDate.split("T")[0];
        const startDate = contest.startDate.split("T")[0];
        const endDate = contest.endDate;

        html += `
            <div class="swiper-slide">
                <div class="book-gallery-card">
                    <a href="${detailUrl}">
                        <img src="${imgSrc}" alt="이미지" class="book-cover-image">
                        <div class="book-hover-overlay">
                            <div class="book-hover-content">
                                <p>작성자: ${contest.userId}</p>
                                <p>등록일: ${regDate}</p>
                            </div>
                        </div>
                    </a>
                    <div class="book-texts">
                        <strong>${contest.title}</strong>
                        <div class="metadata">
                            <span><b>기한:</b> ${startDate} ~ ${endDate}</span>
                            <span><b>주최기관:</b> ${contest.hostOrganization}</span>
                        </div>
                    </div>
                </div>
            </div>
        `;
    });

    $("#content").empty().append(html);
    renderPagination(resData);
    resetSwiper();
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


