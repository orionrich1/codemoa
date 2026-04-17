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
				const bList = resData.bookList || resData.bList || [];
				renderBookList(bList, pageNum, order, keyword, type);

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
				const type = "title";
				const bList = resData.bookList || resData.bList || [];
				renderBookList(bList, pageNum, order, keyword, type);

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
		const contestFilter = $("#contestFilterState").val() || "all";
		let type = "";

		if (keyword !== null && keyword.trim() !== "") {
		    type = "title";
		}

	    $.ajax({
	        url: "/ordercontest.ajax",
	        type: "get",
	        data: { order: order, keyword: keyword, pageNum: pageNum, type: type, contestFilter: contestFilter },
	        dataType: "json",
	        success: function (resData) {
	            renderContestList(resData, pageNum, order, keyword, type, contestFilter); 
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
	    const contestFilter = $("#contestFilterState").val() || "all";
	    const type = keyword.trim() !== "" ? "title" : "";
	    const pageNum = 1;

	    $.ajax({
	        url: "/ordercontest.ajax",
	        type: "get",
	        data: {
	            order: order,
	            keyword: keyword,
	            pageNum: pageNum,
	            type: type,
	            contestFilter: contestFilter
	        },
	        dataType: "json",
	        success: function (resData) {
	            renderContestList(resData, pageNum, order, keyword, type, contestFilter); 
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
				const lList = resData.lectureList || resData.lList || [];
				renderLectureList(lList, pageNum, order, keyword, type);
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
				const type = "title";
				const lList = resData.lectureList || resData.lList || [];
				renderLectureList(lList, pageNum, order, keyword, type);

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
        swiperInstance.destroy(true, true);
    }
    swiperInstance = initSwiper();
}

function initSwiper() {
	const el = document.querySelector(".mySwiper");
	if (!el) {
		return null;
	}
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

function paginationQuery(pageNum, data) {
	const order = encodeURIComponent(data.order || "");
	const kw = encodeURIComponent(data.keyword || "");
	const ty = encodeURIComponent(data.type || "");
	const cList = data.contestList || data.cList;
	if (cList !== undefined) {
		const cf = encodeURIComponent(data.contestFilter || "all");
		return `?pageNum=${pageNum}&order=${order}&keyword=${kw}&type=${ty}&contestFilter=${cf}`;
	}
	return `?pageNum=${pageNum}&order=${order}&keyword=${kw}&type=${ty}`;
}

function renderPagination(data) {
    const {
        currentPage,
        startPage,
        endPage,
        pageCount,
        pageGroup,
    } = data;

    let paginationHtml = `
        <div class="col">
            <nav aria-label="페이지">
                <ul class="pagination justify-content-center flex-wrap">
    `;

    if (startPage > pageGroup) {
        const prevPage = startPage - 1;
        paginationHtml += `<li class="page-item"><a class="page-link" href="${paginationQuery(prevPage, data)}">이전</a></li>`;
    }

    for (let i = startPage; i <= endPage; i++) {
        const activeClass = (i === currentPage) ? "active" : "";
        paginationHtml += `
            <li class="page-item ${activeClass}">
                <a class="page-link" href="${paginationQuery(i, data)}">${i}</a>
            </li>`;
    }

    if (endPage < pageCount) {
        const nextPage = startPage + pageGroup;
        paginationHtml += `<li class="page-item"><a class="page-link" href="${paginationQuery(nextPage, data)}">다음</a></li>`;
    }

    paginationHtml += `
                </ul>
            </nav>
        </div>
    `;

    const row = document.querySelector("#Row");
    if (row) {
    	row.innerHTML = paginationHtml;
    }
}

function escapeHtml(s) {
	if (s == null) return "";
	return String(s)
		.replace(/&/g, "&amp;")
		.replace(/</g, "&lt;")
		.replace(/>/g, "&gt;")
		.replace(/"/g, "&quot;");
}

function formatDatePart(v) {
	if (!v) return "";
	const str = String(v);
	return str.includes("T") ? str.split("T")[0] : str;
}

function renderBookList(bList, pageNum, order, keyword, type) {
	let html = '';
	const slicedList = (bList || []).slice(0, 8);

	slicedList.forEach(book => {
		const detailUrl = `/information/bookDetail?no=${book.bookNo}&pageNum=${pageNum}&order=${encodeURIComponent(order || '')}&keyword=${encodeURIComponent(keyword || '')}&type=${encodeURIComponent(type || '')}`;
		const img = book.file1 ? `/files/information/${encodeURIComponent(book.file1)}` : "";
		const pub = formatDatePart(book.pubDate);
		const cover = img
			? `<img src="${img}" alt="도서 표지: ${escapeHtml(book.title)}" class="item-cover-image" loading="lazy" width="280" height="360">`
			: `<div class="item-cover-image d-flex align-items-center justify-content-center bg-light text-secondary"><i class="bi bi-book fs-1" aria-hidden="true"></i></div>`;

		html += `
			<div class="swiper-slide">
				<div class="item-gallery-card">
					<a href="${detailUrl}">
						${cover}
						<div class="item-hover-overlay">
							<div class="item-hover-content">
								${escapeHtml(book.content)}
							</div>
						</div>
					</a>
					<div class="item-texts">
						<strong>${escapeHtml(book.title)}</strong>
						<div class="metadata">
							<span><strong class="fw-medium">출판일:</strong> ${escapeHtml(pub)}</span>
							<span><strong class="fw-medium">평점:</strong> ${escapeHtml(book.rating)}</span>
						</div>
					</div>
				</div>
			</div>
		`;
	});

	$("#content").empty().append(html);
	resetSwiper();
}

function renderContestList(resData, pageNum = 1, order = '', keyword = '', type = '', contestFilter = 'all') {
    $("#content").empty();
    $("#Row").empty();

    const cList = resData.contestList || resData.cList || [];
    let html = '';

    cList.slice(0, 8).forEach(contest => {
    	const cf = encodeURIComponent(resData.contestFilter || contestFilter || 'all');
        const detailUrl = `/information/contestDetail?no=${contest.contestNo}&pageNum=${pageNum}&order=${encodeURIComponent(order || '')}&keyword=${encodeURIComponent(keyword || '')}&type=${encodeURIComponent(type || '')}&contestFilter=${cf}`;
        const imgSrc = contest.file1 ? `/files/information/${encodeURIComponent(contest.file1)}` : "";
        const regDate = formatDatePart(contest.regDate);
        const startDate = formatDatePart(contest.startDate);
        const endDate = formatDatePart(contest.endDate);
        const cover = imgSrc
        	? `<img src="${imgSrc}" alt="공모전 포스터: ${escapeHtml(contest.title)}" class="book-cover-image" loading="lazy" width="280" height="360">`
        	: `<div class="book-cover-image d-flex align-items-center justify-content-center bg-light text-secondary"><i class="bi bi-trophy fs-1" aria-hidden="true"></i></div>`;

        html += `
            <div class="swiper-slide">
                <div class="book-gallery-card">
                    <a href="${detailUrl}">
                        ${cover}
                        <div class="book-hover-overlay">
                            <div class="book-hover-content">
                                <p>작성자: ${escapeHtml(contest.userId)}</p>
                                <p>등록일: ${escapeHtml(regDate)}</p>
                            </div>
                        </div>
                    </a>
                    <div class="book-texts">
                        <strong>${escapeHtml(contest.title)}</strong>
                        <div class="metadata">
                            <span><strong class="fw-medium">기한:</strong> ${escapeHtml(startDate)} ~ ${escapeHtml(endDate)}</span>
                            <span><strong class="fw-medium">주최기관:</strong> ${escapeHtml(contest.hostOrganization)}</span>
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

	(lList || []).forEach(lecture => {
		const detailUrl = `/information/lectureDetail?no=${lecture.recommendNo}&pageNum=${pageNum}&order=${encodeURIComponent(order || '')}&keyword=${encodeURIComponent(keyword || '')}&type=${encodeURIComponent(type || '')}`;
		const imgSrc = lecture.file1 ? `/files/information/${encodeURIComponent(lecture.file1)}` : "";
		const cover = imgSrc
			? `<img src="${imgSrc}" alt="강좌 썸네일: ${escapeHtml(lecture.title)}" class="item-cover-image" loading="lazy" width="280" height="360">`
			: `<div class="item-cover-image d-flex align-items-center justify-content-center bg-light text-secondary"><i class="bi bi-collection-play fs-1" aria-hidden="true"></i></div>`;

		html += `
			<div class="swiper-slide">
				<div class="item-gallery-card">
					<a href="${detailUrl}">
						${cover}
						<div class="item-hover-overlay">
							<div class="item-hover-content">
								${escapeHtml(lecture.subtitle)}
							</div>
						</div>
					</a>
					<div class="item-texts">
						<strong>${escapeHtml(lecture.title)}</strong>
						<div class="metadata">
							<span><strong class="fw-medium">평점:</strong> ${escapeHtml(lecture.rating)}</span>
							<span><strong class="fw-medium">작성자:</strong> ${escapeHtml(lecture.userId)}</span>
						</div>
					</div>
				</div>
			</div>
		`;
	});

	$("#content").empty().append(html);
	resetSwiper();
}
