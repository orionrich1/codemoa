(function(){
	const $ =(sel) => document.querySelector(sel);
	
	// 미리보기 요소
	const previewWrapper =$('#contestPreviewWrapper') || $('#contestPreviewBox');
	const previewImg =$('#contestPreviewImage');
	const previewT =$('#contestPreviewTitle');
	const previewD =$('#contestPreviewDesc');
	const previewL =$('#contestPreviewLink');

	//공모전 URL 가져오기
	// 1) <a id="contestPreviewLink" href="..."> 에서 읽기
	// 2) fallback: hidden input #contestUrlValue (있다면)
	let targetUrl ='';
	if(previewL){
		targetUrl = previewL.getAttribute('href') || '';
	}

	if(!targetUrl && hiddenUrlE1){
		targetUrl = hiddenUrlE1.value||'';
	}
	
	//상대경로(/recruit/...)는 외부 공모전 URL이 아님 -> 미리보기 생략
	const isExternal = (u) => /^https?:\/\//i.test(u);
	
	async function loadOgPreview(url){
		if(!isExternal(url)){
			if(previewT) previewT.textContent = '(외부 URL 없음)';
			return;
		}
		if(previewT) previewT.textContent ='불러오는 중...';
		if(previewD) previewD.textContent = '';
		if(previewImg) previewImg.style.display='none';
		if(previewWrapper) previewWrapper.classList.remove('d-none');
		
		try{
			const resp = await fetch('/recruit/og-preview?url=' + encodeURIComponent(url));
			if(!resp.ok) throw new Error('응답 오류');
			
			const data = await resp.json();
			if(data.success){
				if(previewT) previewT.textContent = data.title || targetUrl;
				if(previewD) previewD.textContent = data.description || '';
				if(previewImg){
					if(data.imageUrl){
						previewImg.src = data.imageUrl;
						previewImg.style.display = 'inline-block';
					} else {
						previewImg.style.display='none';
					}
				}			
			}	else{
				if(previewT) previewT.textContent = url;
				if(previewD) previewD.textContent = '미리보기 정보를 불러올수 없습니다.';
				}
		} 	catch (err){
			if(previewT) previewT.textContent = url ||'(URL 없음)';
			if(previewD) previewD.textContent = '미리보기 로드 실패.';
			if(previewImg) previewImg.style.display = 'none';
		}
			
		//링크 href 보정(DB에 저장된 값이 잘못되어 비어있을경우)
		if(previewL && url){
			previewL.href = url;
		}
	}
		
		//-- 수정 / 삭제 버튼
		const btnUpdate = document.querySelector('#detailUpdate');
		const btnDelete = document.querySelector('#detailDelete');
		const recruitIdE1 = document.querySelector("#no");	// hidden input (teamRecruit.recruitId)
		
		if(btnUpdate && recruitIdE1){
			btnUpdate.addEventListener('click', ()=> {
				const id = recruitIdE1.value;
				if(!id) return;
				window.location.href = '/recruit/updateForm?recruitId=' + id;
			});
		}
		
		if(btnDelete && recruitIdE1){
			btnDelete.addEventListener('click', async ()=> {
				const id = recruitIdE1.value;
				if(!id) return;
				if(!confirm('정말 삭제 하시겠습니까?')) return;
				
				//간단 REST 호출
				const resp = await fetch('/recruit/' + id, {
					method: 'DELETE',
					headers: {'X-Requested-With': 'XMLHttpRequest'}
				});
				if(resp.ok){
					alert('삭제가 완료되었습니다.');
					window.location.href="/teamRecruitList";
				} else{
					alert('삭제 실패');
				}
			});
		}
		//-- 초기 실행
		loadOgPreview(targetUrl);
	
})();
