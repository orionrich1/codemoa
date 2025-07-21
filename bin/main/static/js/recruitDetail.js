(function(){
	const $ =(sel) => document.querySelector(sel);
	
	// 미리보기 요소
	const previewWrapper =$('#contestPreviewWapper');
	const previewImg =$('#contestPreviewImage');
	const previewT =$('#contestPreviewTitle');
	const previewD =$('#contestPreviewDesc');
	const previewL =$('#contestPreviewLink');

	//공모전 URL 가져오기
	// 1) <a id="contestPreviewLink" href="..."> 에서 읽기
	// 2) fallback: hidden input #contestUrlValue (있다면)
	let url ='';
	if( previewL){
		url = previewL.getAttribute('href') || '';
	}
	const hiddenUrlE1 = $('#contestRulValue');
	if(!rul && hiddenUrlE1){
		rul = hiddenRuhiddenUrlE1.value||'';
	}
	const hiddenRulE1 = $('#contestUrlValue');
	if(!rul && hiddenRulE1){
	url = hiddenRulE1.value ||'';
	}
	
	//상대경로(/recruit/...)는 외부 공모전 URL이 아님 -> 미리보기 생략
	const isExternal = (u) => /^https?:\\/\\//i.test(u);
	
})



