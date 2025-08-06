/*
const regionData = {
	서울 : ["강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구",
		 "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구",
		  "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구",
		  "종로구", "중구", "중랑구"],
		  
	경기 : ["수원시", "성남시", "고양시", "용인시", "부천시", "안산시", "남양주시",
		"화성시", "평택시", "의정부시", "파주시", "시흥시", "김포시", "광주시", "군포시", 
		"이천시", "안양시", "오산시", "하남시", "양주시", "구리시","안성시", "포천시", 
		"의왕시", "여주시", "동두천시", "과천시", "가평군", "양평군", "연천군"],
		
	부산 : ["중구", "서구", "동구", "영도구", "부산진구", "동래구", "남구", "북구", "해운대구",
		"사하구", "금정구", "강서구", "연제구", "수영구", "사상구", "기장군"],
									 
	인천 : ["중구", "동구", "미추홀구", "연수구", "남동구", "부평구", "계양구", "서구", "강화군", "옹진군"],
	
	대구 : ["중구", "동구", "서구", "남구", "북구", "수성구", "달서구", "달성군", "군위군"],
	
	광주 : ["동구", "서구", "남구", "북구", "광산구"],
	
	대전 : ["동구", "중구", "서구", "유성구", "대덕구"],
	
	울산 : ["중구", "남구", "동구", "북구", "울주군"]
};

//지역 선택시 하위 지역 동적 로딩
function updateSubRegion(selectedRegion) {
	console.log('updateSubRegion called with:', selectedRegion);
	const subRegionSelect = document.getElementById("subRegion");

	subRegionSelect.innerHTML = '<option value="">구/군 선택</option>';

	if(regionData[selectedRegion]) {
		regionData[selectedRegion].forEach(subRegion => {
			const option = document.createElement("option");
			option.value = subRegion;
			option.textContent = subRegion;
			subRegionSelect.appendChild(option);
		});
	}	else {
	        console.log('No region data found for:', selectedRegion);
	}
	const selectedSubRegion = [[${param.subRegion}]] "";
	if(selectedSubRegion){
		subRegionSelect.value = selectedSubRegion;
	}
}
*/

// 카드 클릭 이벤트
function setupCardClickEvent(){
	const cards = document.querySelectorAll(".employment-card");
	cards.forEach(card => {
		const url = card.getAttribute("data-url");
		card.addEventListener("click", () => {
			if(url){
				window.open(url,"_blank");
			}
		});
	});
}

//수동 크롤링폼 전송 함수 추가
function submitCrawlForm(){
	const crawlForm = document.getElementById("crawlForm");
	if(crawlForm){
		crawlForm.submit();
	}
}


window.onload = function() {
	const typeSelect = document.getElementById("type");
	const selectedType = /*[[${type}]]*/ ""; // Thymeleaf가 서버에서 넣어줌
	if(selectedType) {
	  typeSelect.value = selectedType;
	}
	setupCardClickEvent();
	};