function openExternalEmployment(url) {
	if (!url) {
		return;
	}
	const a = document.createElement('a');
	a.href = url;
	a.target = '_blank';
	a.rel = 'noopener noreferrer';
	a.style.display = 'none';
	document.body.appendChild(a);
	a.click();
	document.body.removeChild(a);
}

function setupCardClickEvent() {
	const cards = document.querySelectorAll('.employment-card');
	cards.forEach(card => {
		const url = card.getAttribute('data-url');
		const go = () => {
			if (url) {
				openExternalEmployment(url);
			}
		};
		card.addEventListener('click', go);
		card.addEventListener('keydown', ev => {
			if (ev.key === 'Enter' || ev.key === ' ') {
				ev.preventDefault();
				go();
			}
		});
	});
}

function submitCrawlForm() {
	const crawlForm = document.getElementById('crawlForm');
	if (crawlForm) {
		crawlForm.submit();
	}
}

window.onload = function () {
	const typeSelect = document.getElementById('type');
	const state = document.getElementById('employmentTypeState');
	const selectedType = state && state.dataset.type ? state.dataset.type : '';
	if (typeSelect && selectedType) {
		typeSelect.value = selectedType;
	}
	setupCardClickEvent();
};
