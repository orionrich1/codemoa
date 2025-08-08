(function(){
	const $ =(sel) => document.querySelector(sel);
	const $$ = (sel) => document.querySelectorAll(sel);
	
	const form =$('#recruitWriteForm') || $('#recruitUpdateForm');
	if(! form){
		console.warn(`recruitWrite.js: no valid form found. Script aborted.`);
		return;
	}
	
	//-----------------------------
	//모집 분류 (TEAM_RECRUIT / TEAM_JOIN)
	// 	//TEAM_JOIN을 선택시 인원 입력 잠금
	//-------------------------------
	
	const recruitTypeBoxes = $$('.recruitTypeChk');
	const recruitTypeHidden = $('#recruitTypeHidden');
	const totalMembersInput =$('#totalMembers');
	const remainingMembersInput =$('#remainingMembers');
	
	function singleCheckHandler(boxes, hiddenField, extraCallback){
		boxes.forEach(cb => {
			cb.addEventListener('change', function(){
				if(this.checked) {
					boxes.forEach(other => {
						if (other !== this) other.checked = false;			
					});		
					hiddenField.value = this.value;
					} else {
						hiddenField.value = '';
					}
					if(extraCallback) extraCallback();
				});
			});		
		}
		
		
		//TEAM_JOIN 선택시 인원 입력 기능 잠금 적용 함수
		function toggleMemberInputs(){
			const hasJoin = recruitTypeHidden.value === 'TEAM_JOIN';
			
			remainingMembersInput.disabled = hasJoin;
			if(hasJoin){
			
						remainingMembersInput.value = '';
			}
		}
		
	singleCheckHandler(recruitTypeBoxes, recruitTypeHidden, toggleMemberInputs);
	
	
	//진행 방식(ONLINE / OFFLINE / MIXED) 다중 선택 허용
	const progressTypeBoxes = $$('.progressTypeChk');
	const progressTypeHidden = $('#progressTypeHidden');
	singleCheckHandler(progressTypeBoxes, progressTypeHidden);
	
	// 모집 / 활동 기간 hidden 문자열 조합
	// 저장 형식 : "YYYY-MM-DD ~ YYYY-MM-DD" (둘다 있을때만) / 없다면 공백
	const recruitStart = $('#recruitStartDate');
	const recruitEnd = $('#recruitEndDate');
	const recruitHidden = $('#recruitperiodHidden');
	
	const activityStart = $('#activityStartDate');
	const activityEnd = $('#activityEndDate');
	const activityHidden = $('#activityPeriodHidden');
	
	function joinDateRange(startE1, endE1){
		const s = (startE1 && startE1.value) ? startE1.value : '';
		const e = (endE1 && endE1.value) ? endE1.value : '';
		if(! s && !e) return '';
		return s + ` ~ ` + e ;		
	}
	
	function syncPeriods(){
		if(recruitHidden) recruitHidden.value = joinDateRange(recruitStart, recruitEnd);
		if(activityHidden) activityHidden.value = joinDateRange(activityStart, activityEnd);
	}
	function validateDateRange(startInput, endInput){
		if(!startInput || !endInput) return;
		
		endInput.addEventListener('mousedown', function(e){
			if(!startInput.value){
				e.preventDefault();
				alert("시작 날짜를 먼저 선택해주세요");
			}
		});
		
		endInput.addEventListener('change', function(){
			if(!startInput.value){
				e.preventDefault();
				alert("먼저 시작 날짜를 선택하세요");
				endInput.value='';
				return;
			}
			if(startInput.value&& endInput.value && startInput.value > endInput.value){
							alert("종료 날짜를 시작 날짜보다 이전으로 선택할수 없습니다.");
							endInput.value='';
						}
			syncPeriods();
		});

		startInput.addEventListener('change', syncPeriods);
	}
	
	validateDateRange(recruitStart, recruitEnd);
	validateDateRange(activityStart, activityEnd);

	//--모집 인원 / 남은 인원 (음수입력 방지)
	function preventNegative(input){
		if(!input) return;
		input.addEventListener('input', function(){
			//음수 방지
			if(this.value < 0) this.value=0;
			//2자리 숫자 제한
			if(this.value.length > 2){
				this.value = this.value.slice(0, 2);
			}
		});
	}	
	
	function validateMemberCounts(){
		if(!totalMembersInput || !remainingMembersInput) return;
		
		function checkCounts(){
			const total = parseInt(totalMembersInput.value || 0, 10);
			const remaining = parseInt(remainingMembersInput.value || 0, 10);
			
			if(remaining > total){
				alert("남은 모집 인원은 전체 모집 인원보다 많을 수 없습니다.");
				remainingMembersInput.value = total;
			}
		}
		totalMembersInput.addEventListener('input', checkCounts);
		remainingMembersInput.addEventListener('input', checkCounts);
	}
	
	preventNegative(totalMembersInput);
	preventNegative(remainingMembersInput);
	
	validateMemberCounts();
	
	//기술 스택 태그 입력 (콤마 / Enter / blur)
	const tagInput = $('#techStackInput');			 // 입력 텍스트 박스
	const tagHidden = $('#techStackHidden');		// 숨겨진 실제 폼 데이터 저장용
	const tagContainer = $('#techTagContainer');	// 태그들이 시각적으로 보여질 컨테이너
	let tags = [];
	
	// 태그를 화면에 랜더링 하고 숨겨진 필드값을 업데이트
	function renderTags(){
		if(!tagContainer) return;
		tagContainer.innerHTML='';		//기존 태그를 제거 하는 역활
		tags.forEach((t,idx)=>{
			const span = document.createElement('span');
			span.className = 'badge bg-secondary me-1 mb-1';
			span.textContent = '#' +t;			//태그 형식 표시
			span.style.cursor = 'pointer';			//클릭 가능 커서 표시
			span.title = '클릭하면 삭제';				//툴팁 안내용
			//클릭시 태그가 삭제되기 위한 코드
			span.addEventListener('click',() => {removeTag(idx);}); 
			tagContainer.appendChild(span);		//태그 컨테이너에 추가
		});
		//태그 배열을 콤마 구분을 통해 문자열로 숨겨진 필드에 저장하기 위한 용도
		if(tagHidden) tagHidden.value = tags.join(','); 
	}
	
	//새로운 태그 추가시 중복 및 빈값 검사용 함수
	function addTag(raw){
		const trimmed = raw.trim();
		if(! trimmed) return;			// 빈 문자열 무시
		if(tags.includes(trimmed)) return;	// 중복 태그 무시
		tags.push(trimmed);		//태그 배열에 추가
		renderTags();					//태그 화면에 출력
	}
	// 인덱스에 해당되는 태그 삭제
	function removeTag(i){	
		tags.splice(i,1); 				//배열에서 제거
		renderTags();					// 화면 갱신
	}
	
	// 입력 문자열을 공백, 콤마 단위로 따로 분리하여 태그로 변환
	function parseInputTags(str){
		str.split(/[\s,]+/)					// 공백 또는 콤마로 분리
			.map(s => s.replace(/#/g, '').trim().toLowerCase())	// '#' 제거, 소문자 변환
			.filter(s => s.length > 0 && !tags.includes('#' + s))		// 빈값과 중복 제외	
			.forEach(s => addTag(s));				 // 각 단어를 태그로 추가
	}
	
	if(tagHidden && tagHidden.value){
		tags=[];
		parseInputTags(tagHidden.value);
	}
	
	
	if(tagInput){
		tagInput.addEventListener('keypress', function(e){
			if(e.key === '#'){			//'#' 직접 입력 차단
				e.preventDefault();
			}
		});
		
		tagInput.addEventListener('keydown', function(e){
			if(e.key==='Enter'){			// 엔터 시 입력값을 태그로 변환
				e.preventDefault();
				parseInputTags(tagInput.value);
				tagInput.value='';
			}
		});
		tagInput.addEventListener('blur', function(){  // 입력란 포커스 해제 시 태그 변환
			parseInputTags(tagInput.value);
			tagInput.value='';
		});
	}
	function addTag(raw){
		let trimmed = raw.trim().toLowerCase();
		if(!trimmed) return;
		if(!trimmed.startsWith('#')){
			trimmed = '#' + trimmed;
		}
		if(tags.includes(trimmed)) return;
		tags.push(trimmed);
		renderTags();
	}
	
	//----------------------
	//공모전 URL 미리보기
	//-----------------------
	const btnPreview = $('#btnContestPreview');
	const contestUrlInput =$('#contestTitle');
	const previewBox = $('#contestPreviewBox');
	const previewImg = $('#contestPreviewImage');
	const previewT = $('#contestPreviewTitle');
	const previewD = $('#contestPreviewDesc');
	const previewL = $('#contestPreviewLink');
	
	async function loadOgPreview(){
		const url = contestUrlInput ? contestUrlInput.value.trim() : '';
		if(! url){
			alert(" 공모전 URL 주소를 입력하세요");
			return;
		}
		if(previewT) previewT.textContent = '불러오는 중...';
		if(previewD) previewD.textContent = '';
		if(previewImg) previewImg.style.display='none';
		if(previewBox) previewBox.classList.remove('d-none');
		
		try{
			const resp = await fetch('/recruit/og-preview?url=' + encodeURIComponent(url));
			if(!resp.ok) throw new Error('응답 오류');
			const data = await resp.json();
			if(data.success){
				if(previewT) previewT.textContent = data.title || url;
				if(previewD) previewD.textContent = data.description || '';
				if(previewImg) {
					if(data.imageUrl){
					previewImg.src = data.imageUrl;
					previewImg.style.display='inline-block';
				} else {
					previewImg.style.display='none';
				}
			}
		} else {
			if(previewT) previewT.textContent = url;
			if(previewD) previewD.textContent = '미리보기 정보를 불러올 수 없습니다.';
		}		
	}	catch(err){
		if(previewT) previewT.textContent = url || '(URL 없음)';
		if(previewD) previewD.textContent = '미리보기 로드 실패.';
		if(previewImg) previewImg.style.display='none';
	}
	if(previewL) previewL.href = url || '#';
	}
	if(btnPreview) btnPreview.addEventListener('click', loadOgPreview);
	if(contestUrlInput) contestUrlInput.addEventListener('blur', function(){
		if(contestUrlInput.value.trim()) loadOgPreview();
	});
	
	//-------------------------------
	// 작성 폼 제출전 유효성 검증
	//---------------------------------
	
	form.addEventListener('submit', function(e){
		syncPeriods();
		
		const total = parseInt(totalMembersInput.value || 0,10);
		const remaining = parseInt(remainingMembersInput.value || 0, 10);
		if(remaining > total){
			e.preventDefault();
			alert("남은 인원이 현재 모집예정 인원보다 많습니다.");
			return;
		}
		
		if(recruitTypeHidden && !recruitTypeHidden.value){
			e.preventDefault();
			alert('모집 분류를 선택하세요');
			return;
		}
		if(progressTypeHidden && !progressTypeHidden.value){
			e.preventDefault();
			alert("진행 방식을 선택하세요");
			return;
		}
		//추가 검증 : 모집 기간, 활동 기간 미입력시 경고 표시후 그대로 진행할지 여부 확인
		if(recruitHidden && !recruitHidden.value){
			const go = confirm('모집 기간이 지정되지 않았습니다. 이대로 계속 하시겠습니까?');
			if(!go){e.preventDefault(); 
			return;	
			}
		}
		if(activityHidden && !activityHidden.value){
			const go = confirm('활동 기간이 지정되지 않았습니다. 이대로 계속 하시겠습니까?');
			if(!go){e.preventDefault(); 
			return;
			}
		}
	});
	
	//진행 여부에서 아니요를 누르면 한번 초기화
	toggleMemberInputs();
	syncPeriods();
}) ();









