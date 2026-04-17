/* AI 코딩 문제 풀이 — problem.js */

function getCsrfHeadersForProblem() {
    var headers = {};
    var tokenMeta = document.querySelector("meta[name='_csrf']");
    var headerMeta = document.querySelector("meta[name='_csrf_header']");
    if (tokenMeta && headerMeta && tokenMeta.content && headerMeta.content) {
        headers[headerMeta.content] = tokenMeta.content;
    }
    var match = document.cookie.match(/(?:^|;\s*)XSRF-TOKEN=([^;]+)/);
    var xsrf = match ? decodeURIComponent(match[1]) : '';
    if (xsrf) {
        if (!headers['X-XSRF-TOKEN']) {
            headers['X-XSRF-TOKEN'] = xsrf;
        }
    }
    return headers;
}

let typeFilterSelected = [0, 0, 0, 0];      // JAVA, Javascript, Python, Kotlin
let difficultyFilterSelected = [0, 0, 0];   // 상, 중, 하

$(function () {

    /* ── 문제 목록: 언어·난이도 필터 ── */
    $(".typeFilter").on("change", function () {
        typeFilterSelected[$(this).val() - 1] = $(this).is(':checked') ? 1 : 0;
        requestList();
    });

    $(".difficultyFilter").on("change", function () {
        difficultyFilterSelected[$(this).val() - 1] = $(this).is(':checked') ? 1 : 0;
        requestList();
    });

    /* ── 문제 목록: 키워드 검색 (클라이언트 사이드) ── */
    $("#problemSearch").on("input", function () {
        const kw = $(this).val().trim().toLowerCase();
        $("#problemTableBody .ai-list-item").each(function () {
            const title = $(this).find(".ai-problem-title").text().toLowerCase();
            $(this).toggle(kw === "" || title.includes(kw));
        });
        // 검색 후 빈 결과 처리
        const visible = $("#problemTableBody .ai-list-item:visible").length;
        $("#problemSearchEmpty").toggle(visible === 0);
    });

    /* ── 문제 풀기: 제출 전 유효성 검사 ── */
    $("#problemForm").on("submit", function () {
        const answer = $("#answer").val().trim();
        if (answer.length === 0) {
            showAlert("답안을 입력해 주세요.", "warning");
            return false;
        }
        // 제출 버튼 중복 클릭 방지
        $(this).find('[type=submit]').prop('disabled', true).text('제출 중...');
    });

    /* ── 문제 풀기: 힌트 보기 ── */
    $("#showHintBtn").click(function () {
        const hint = $("#hint").text().trim();
        const msg = hint.length > 0 ? hint : "힌트가 없습니다.";
        showAlert(msg, "info", "💡 힌트");
    });

    /* ── 공통: Tab 키 → 4칸 스페이스 ── */
    $(".useTabkey").on("keydown", function (e) {
        if (e.key === "Tab") {
            e.preventDefault();
            const start = this.selectionStart;
            const end = this.selectionEnd;
            const spaces = "    ";
            this.value = this.value.substring(0, start) + spaces + this.value.substring(end);
            this.selectionStart = this.selectionEnd = start + spaces.length;
            syncLineNumbers(this);
        }
    });

    /* ── 줄 번호 표시 동기화 (기능 3) ── */
    const answerTextarea = document.getElementById('answer');
    const lineNumbersEl  = document.getElementById('lineNumbers');

    function syncLineNumbers(ta) {
        if (!lineNumbersEl || !ta) return;
        const lines = ta.value ? ta.value.split('\n').length : 1;
        let html = '';
        for (let i = 1; i <= lines; i++) html += i + '\n';
        lineNumbersEl.textContent = html;
        lineNumbersEl.scrollTop = ta.scrollTop;
    }

    if (answerTextarea) {
        $(answerTextarea).on('input scroll', function () { syncLineNumbers(this); });
        syncLineNumbers(answerTextarea);
    }

    /* ── 작성/수정 폼: 유효성 검사 ── */
    $("#writeForm, #updateForm").on("submit", function () {
        const title    = $("#title");
        const content  = $("#content");
        const category = $("#category");

        if (!title.val().trim()) {
            showAlert("문제 제목을 입력해 주세요.", "warning");
            title.focus();
            return false;
        }
        if (!content.val().trim()) {
            showAlert("문제 내용을 입력해 주세요.", "warning");
            content.focus();
            return false;
        }
        if (!category.val()) {
            showAlert("프로그래밍 언어를 선택해 주세요.", "warning");
            category.focus();
            return false;
        }
    });

    /* ── AI 자유 질문 폼 ── */
    $("#questionForm").on("submit", function (e) {
        e.preventDefault();
        questionApi();
    });

    /* ── 인라인 AI 질문 패널 (문제 풀기 화면) ── */
    // 모달 열기는 data-bs-toggle/target 으로 Bootstrap이 직접 처리

    $("#inlineAiSendBtn").on("click", function () {
        sendInlineAiQuestion();
    });

    $("#inlineAiQuestion").on("keydown", function (e) {
        if (e.key === "Enter" && (e.ctrlKey || e.metaKey)) {
            sendInlineAiQuestion();
        }
    });

    /* ── 문제 작성 폼: 언어별 답안 플레이스홀더 ── */
    $("#category").on("change", function () {
        updateAnswerPlaceholder($(this).val());
    });
    // 초기화
    if ($("#category").length) updateAnswerPlaceholder($("#category").val());
});

/* ─── 언어별 플레이스홀더 ─── */
function updateAnswerPlaceholder(lang) {
    const placeholders = {
        JAVA:       "Java 코드를 입력하세요...",
        JavaScript: "JavaScript 코드를 입력하세요...",
        Python:     "Python 코드를 입력하세요...",
        Kotlin:     "Kotlin 코드를 입력하세요..."
    };
    const ph = placeholders[lang] || "답안을 여기에 입력하세요...";
    $("#answer").attr("placeholder", ph);
}

/* ─── 문제 목록 AJAX 갱신 ─── */
function requestList() {
    $.ajax({
        url: '/problems/listUpdate',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            type:       typeFilterSelected,
            difficulty: difficultyFilterSelected
        }),
        success: function (res) { refreshList(res.result); },
        error:   function (err) { console.error("문제 목록 로드 실패", err); }
    });
}

function refreshList(problemList) {
    const container = $('#problemTableBody');
    container.empty();
    $('#problemSearchEmpty').hide();

    if (!problemList || problemList.length === 0) {
        container.append(`
            <li class="list-group-item text-center p-5 text-secondary">
                <i class="bi bi-inbox me-2"></i>해당 조건에 맞는 문제가 없습니다. 필터를 초기화해 보세요.
            </li>`);
        return;
    }

    const bestScores = window.BEST_SCORES || {};
    problemList.forEach(p => {
        const diffClass = p.difficulty === '상' ? 'bg-danger'
                        : p.difficulty === '중' ? 'bg-warning text-dark'
                        : 'bg-success';
        const date  = p.regDate || '';
        const best  = bestScores[p.problemId];
        const bestBadgeClass = best >= 90 ? 'text-bg-success'
                             : best >= 70 ? 'text-bg-primary'
                             : best >= 50 ? 'text-bg-warning'
                             : 'text-bg-danger';
        const bestBadge = best != null
            ? `<span class="badge ${bestBadgeClass}">✓ ${best}점</span>`
            : '';
        container.append(`
            <li class="list-group-item list-group-item-action ai-list-item p-3 ds-cursor-pointer"
                onclick="location.href='problemDetail?problemId=${p.problemId}'">
                <div class="d-flex justify-content-between align-items-center">
                    <div class="flex-grow-1">
                        <div class="d-flex align-items-center gap-3">
                            <span class="badge text-bg-dark ds-problem-cat-badge">${escHtml(p.category)}</span>
                            <span class="ai-problem-title">${escHtml(p.title)}</span>
                        </div>
                        <div class="d-flex align-items-center gap-3 ai-problem-meta mt-2">
                            <div class="ds-problem-meta-spacer" aria-hidden="true"></div>
                            <span>${escHtml(p.userId)}</span>
                            <span>${date}</span>
                        </div>
                    </div>
                    <div class="d-flex align-items-center gap-2 flex-shrink-0">
                        ${bestBadge}
                        <span class="badge ${diffClass} fs-6">${escHtml(p.difficulty)}</span>
                    </div>
                </div>
            </li>`);
    });
}

/* ─── AI 채점 요청 (결과 페이지) ─── */
function requestApi() {
    const category = $("#resultCategory").text();
    const title    = $("#resultProblemTitle").text();
    const content  = $("#resultContent").text();
    const answer   = $("#resultAnswer").text();
    const userAns  = $("#resultUserAnswer").text();

    fetch('/problems/apiRequest', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            problem: { category, title, content, answer },
            answer: userAns
        })
    })
    .then(res => res.json())
    .then(data => renderResult(data.result))
    .catch(() => renderResult(null));
}

function renderResult(resultText) {
    // 스피너 숨기고 점수 표시
    $('#scoreSpinner').addClass('d-none');
    $('#scoreSection').removeClass('d-none');

    if (!resultText) {
        $('#scoreDisplay').text('?');
        $('#gradeBadge').text('오류').addClass('text-bg-secondary');
        $('#aiFeedbackContent').html('<p class="text-danger"><i class="bi bi-exclamation-triangle me-2"></i>AI 피드백을 받아오는 중 오류가 발생했습니다. 잠시 후 다시 시도해 주세요.</p>');
        return;
    }

    // 점수 파싱 (100점 만점 기준 숫자 추출)
    const score = parseScore(resultText);

    // 점수 표시 애니메이션
    animateScore(score);

    // 등급 배지
    const { label, cls } = getGrade(score);
    $('#gradeBadge').text(label).addClass(cls);

    // AI 피드백 텍스트 렌더링 (코드 블록 처리)
    $('#aiFeedbackContent').html(renderMarkdown(resultText));

    // 풀이 이력 저장 + 포인트 지급 (기능 1, 8)
    const problemId = parseInt($('#resultProblemId').text()) || 0;
    const userAnswer = $('#resultUserAnswer').text();
    if (problemId > 0 && score !== null) {
        saveSubmission(problemId, userAnswer, score, resultText);
    }
}

function saveSubmission(problemId, submittedAnswer, aiScore, aiFeedback) {
    var csrfPart = getCsrfHeadersForProblem();
    var hasCsrf = Object.keys(csrfPart).length > 0;
    var headers = Object.assign(
        { 'Content-Type': 'application/json' },
        csrfPart
    );
    fetch('/problems/saveSubmission', {
        method: 'POST',
        headers: headers,
        credentials: 'include',
        body: JSON.stringify({ problemId, submittedAnswer, aiScore, aiFeedback })
    })
    .then(function (res) {
        if (!res.ok) {
            if (res.status === 403 && !hasCsrf) {
                throw new Error('CSRF');
            }
            return res.json().catch(function () { return null; }).then(function (body) {
                throw new Error((body && body.message) || ('HTTP ' + res.status));
            });
        }
        return res.json();
    })
    .then(function (data) {
        if (data.pointAwarded > 0) {
            $('#pointMessageText').text(data.message);
            $('#pointMessage').removeClass('d-none');
        } else if (data.alreadyReceivedToday) {
            $('#pointMessageText').text('오늘 이미 이 문제로 포인트를 받으셨습니다.');
            $('#pointMessage').removeClass('d-none');
        } else if (data.message && String(data.message).indexOf('로그인') !== -1) {
            $('#pointMessageText').text(data.message);
            $('#pointMessage').removeClass('d-none');
        }
    })
    .catch(function (err) {
        var msg = '풀이 이력 저장에 실패했습니다. 로그인 상태를 확인한 뒤 다시 시도해 주세요.';
        if (err && String(err.message) === 'CSRF') {
            msg = '보안 토큰이 없어 저장되지 않았습니다. 페이지를 새로고침한 뒤 다시 제출해 주세요.';
        } else if (err && String(err.message).indexOf('403') !== -1) {
            msg = '요청이 거부되었습니다. 페이지를 새로고침한 뒤 다시 시도하거나 로그인 상태를 확인해 주세요.';
        }
        $('#pointMessageText').text(msg);
        $('#pointMessage').removeClass('d-none');
    });
}

/* 점수 파싱 — 여러 형식 대응 */
function parseScore(text) {
    const patterns = [
        /점수[:\s]*(\d+)/,
        /(\d+)\s*\/\s*100/,
        /(\d+)\s*점/,
        /score[:\s]*(\d+)/i
    ];
    for (const re of patterns) {
        const m = text.match(re);
        if (m) {
            const n = parseInt(m[1], 10);
            if (n >= 0 && n <= 100) return n;
        }
    }
    return null;
}

/* 점수 카운트업 애니메이션 */
function animateScore(score) {
    if (score === null) {
        $('#scoreDisplay').text('?');
        return;
    }
    let current = 0;
    const step = Math.max(1, Math.floor(score / 40));
    const timer = setInterval(() => {
        current = Math.min(current + step, score);
        $('#scoreDisplay').text(current);
        if (current >= score) clearInterval(timer);
    }, 30);
}

/* 등급 결정 */
function getGrade(score) {
    if (score === null) return { label: '채점 오류', cls: 'text-bg-secondary' };
    if (score >= 90)    return { label: '우수 🏆', cls: 'text-bg-success' };
    if (score >= 70)    return { label: '양호 👍', cls: 'text-bg-primary' };
    if (score >= 50)    return { label: '보통 📝', cls: 'text-bg-warning text-dark' };
    return              { label: '재도전 💪', cls: 'text-bg-danger' };
}

/* 마크다운 코드블록 렌더링 */
function renderMarkdown(text) {
    // 코드 블록 (```lang ... ```)
    let html = text.replace(/```(\w*)\n?([\s\S]*?)```/g, (_, lang, code) =>
        `<pre class="code-block rounded-3 p-3 mt-2 mb-2"><code>${escHtml(code.trim())}</code></pre>`
    );
    // 인라인 코드 (`code`)
    html = html.replace(/`([^`]+)`/g, '<code class="bg-light px-1 rounded">$1</code>');
    // 줄바꿈
    html = html.replace(/\n/g, '<br>');
    return html;
}

/* ─── AI 자유 질문 (aiAnswer 페이지) ─── */
function questionApi() {
    const questionText = $("#questionText").val().trim();
    if (!questionText) return;

    // 빈 상태 숨기고 답변 블록 표시
    $('#aiAnswerEmpty').addClass('d-none');
    const answerBlock = $('#aiAnswerBlock').removeClass('d-none');

    answerBlock.text("💭 답변 생성 중입니다...");
    const sendBtn = $('#questionForm [type=submit]');
    sendBtn.prop('disabled', true);

    fetch('/problems/apiQuestion', {
        method: "POST",
        headers: { "Content-Type": "text/plain" },
        body: questionText
    })
    .then(res => res.json())
    .then(data => {
        answerBlock.html(renderMarkdown(data.result));
        sendBtn.prop('disabled', false);
    })
    .catch(err => {
        answerBlock.text("❌ 오류가 발생했습니다: " + err.message);
        sendBtn.prop('disabled', false);
    });
}

/* ─── 인라인 AI 질문 패널 (문제 풀기 화면) ─── */
let inlineAiCount = 0;
const INLINE_AI_LIMIT = 5;

function sendInlineAiQuestion() {
    if (inlineAiCount >= INLINE_AI_LIMIT) {
        $('#inlineAiAnswer').html('<p class="text-warning"><i class="bi bi-exclamation-circle me-1"></i>AI 질문 횟수를 모두 사용했습니다. 내일 다시 이용해 주세요.</p>');
        return;
    }

    const question  = $('#inlineAiQuestion').val().trim();
    if (!question) return;

    const category  = $('#problemCategoryForAi').text().trim();
    const title     = $('#problemTitleForAi').text().trim();
    const fullQuery = `문제: ${title} (${category})\n\n질문: ${question}`;

    $('#inlineAiAnswer').html('<div class="d-flex align-items-center gap-2 text-secondary"><div class="spinner-border spinner-border-sm"></div><span>답변 생성 중...</span></div>');
    $('#inlineAiSendBtn').prop('disabled', true);

    fetch('/problems/apiQuestion', {
        method: "POST",
        headers: { "Content-Type": "text/plain" },
        body: fullQuery
    })
    .then(res => res.json())
    .then(data => {
        inlineAiCount++;
        const remain = INLINE_AI_LIMIT - inlineAiCount;
        $('#inlineAiCountBadge').text(remain);
        $('#inlineAiCountModal').text(remain);
        $('#inlineAiAnswer').removeClass('d-none').html(renderMarkdown(data.result));
        $('#inlineAiSendBtn').prop('disabled', false);
    })
    .catch(() => {
        $('#inlineAiAnswer').removeClass('d-none').html('<p class="text-danger">오류가 발생했습니다. 잠시 후 다시 시도해 주세요.</p>');
        $('#inlineAiSendBtn').prop('disabled', false);
    });
}

/* ─── 유틸 ─── */
function escHtml(str) {
    return String(str ?? '')
        .replace(/&/g, '&amp;')
        .replace(/</g, '&lt;')
        .replace(/>/g, '&gt;')
        .replace(/"/g, '&quot;');
}

function showAlert(message, type = 'info', title = '') {
    const toastId = 'dynamicToast_' + Date.now();
    const icons   = { info: 'bi-info-circle', warning: 'bi-exclamation-triangle', danger: 'bi-x-circle', success: 'bi-check-circle' };
    const icon    = icons[type] || 'bi-info-circle';
    const header  = title ? `<strong class="me-auto">${escHtml(title)}</strong>` : '';

    const toastHtml = `
        <div id="${toastId}" class="toast align-items-center" role="alert" aria-live="assertive" aria-atomic="true"
             style="position:fixed;bottom:1.5rem;right:1.5rem;z-index:9999;min-width:280px;">
            <div class="d-flex">
                <div class="toast-body">
                    <i class="bi ${icon} me-2 text-${type}"></i>${escHtml(message)}
                </div>
                <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="닫기"></button>
            </div>
        </div>`;
    $('body').append(toastHtml);
    const toast = new bootstrap.Toast(document.getElementById(toastId), { delay: 4000 });
    toast.show();
    document.getElementById(toastId).addEventListener('hidden.bs.toast', () => document.getElementById(toastId)?.remove());
}
