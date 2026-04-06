(function () {
	const previewWrapper = document.getElementById('contestPreviewWrapper');
	const previewImg = document.getElementById('contestPreviewImage');
	const previewT = document.getElementById('contestPreviewTitle');
	const previewD = document.getElementById('contestPreviewDesc');
	const previewL = document.getElementById('contestPreviewLink');
	const hiddenUrlEl = document.getElementById('contestUrlValue');

	let targetUrl = '';
	if (hiddenUrlEl && hiddenUrlEl.value) {
		targetUrl = hiddenUrlEl.value.trim();
	}
	if (!targetUrl && previewL && previewL.tagName === 'A') {
		targetUrl = (previewL.getAttribute('href') || '').trim();
	}

	const isExternal = (u) => /^https?:\/\//i.test(u);

	async function loadOgPreview(url) {
		if (!isExternal(url)) {
			if (previewT) {
				previewT.textContent = '(외부 URL 없음)';
			}
			return;
		}
		if (previewT) {
			previewT.textContent = '불러오는 중...';
		}
		if (previewD) {
			previewD.textContent = '';
		}
		if (previewImg) {
			previewImg.classList.add('d-none');
			previewImg.removeAttribute('src');
		}
		if (previewWrapper) {
			previewWrapper.classList.remove('d-none');
		}

		try {
			const resp = await fetch('/recruit/og-preview?url=' + encodeURIComponent(url));
			if (!resp.ok) {
				throw new Error('응답 오류');
			}
			const data = await resp.json();
			if (data.success) {
				if (previewT) {
					previewT.textContent = data.title || url;
				}
				if (previewD) {
					previewD.textContent = data.description || '';
				}
				if (previewImg) {
					if (data.imageUrl) {
						previewImg.src = data.imageUrl;
						previewImg.alt = (data.title || '공모전') + ' 미리보기 이미지';
						previewImg.classList.remove('d-none');
					} else {
						previewImg.classList.add('d-none');
					}
				}
			} else {
				if (previewT) {
					previewT.textContent = url;
				}
				if (previewD) {
					previewD.textContent = '미리보기 정보를 불러올 수 없습니다.';
				}
			}
		} catch (err) {
			if (previewT) {
				previewT.textContent = url || '(URL 없음)';
			}
			if (previewD) {
				previewD.textContent = '미리보기 로드 실패.';
			}
			if (previewImg) {
				previewImg.classList.add('d-none');
			}
		}

		if (previewL && previewL.tagName === 'A' && url) {
			previewL.href = url;
		}
	}

	const recruitIdEl = document.getElementById('no');
	const btnUpdate = document.getElementById('detailUpdate');
	const confirmDelete = document.getElementById('confirmDeleteRecruit');

	if (btnUpdate && recruitIdEl) {
		btnUpdate.addEventListener('click', () => {
			const id = recruitIdEl.value;
			if (!id) {
				return;
			}
			window.location.href = '/recruit/updateForm?recruitId=' + encodeURIComponent(id);
		});
	}

	if (confirmDelete && recruitIdEl) {
		confirmDelete.addEventListener('click', async () => {
			const id = recruitIdEl.value;
			if (!id) {
				return;
			}
			const token = document.querySelector("meta[name='_csrf']")?.content;
			const headerName = document.querySelector("meta[name='_csrf_header']")?.content;
			const headers = { 'X-Requested-With': 'XMLHttpRequest' };
			if (token && headerName) {
				headers[headerName] = token;
			}
			confirmDelete.disabled = true;
			try {
				const resp = await fetch('/recruit/' + encodeURIComponent(id), {
					method: 'DELETE',
					headers: headers
				});
				const modalEl = document.getElementById('deleteRecruitModal');
				if (modalEl && window.bootstrap) {
					bootstrap.Modal.getInstance(modalEl)?.hide();
				}
				if (resp.ok) {
					window.location.href = '/teamRecruitList';
				} else {
					const text = await resp.text();
					alert('삭제 실패: ' + (text || resp.status));
				}
			} catch (e) {
				console.error(e);
				alert('삭제 중 오류가 발생했습니다.');
			} finally {
				confirmDelete.disabled = false;
			}
		});
	}

	loadOgPreview(targetUrl);
})();
