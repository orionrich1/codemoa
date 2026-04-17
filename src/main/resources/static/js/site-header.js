/**
 * 스크롤 시 헤더에 반투명 글래스 바(.is-scrolled) 적용 (fixed 헤더 + 본문 오프셋은 CSS)
 */
(function () {
    function init() {
        var nav = document.getElementById("site-header-nav");
        if (!nav) {
            return;
        }

        var thresholdPx = 8;
        var ticking = false;

        function update() {
            var y = window.scrollY || document.documentElement.scrollTop;
            nav.classList.toggle("is-scrolled", y > thresholdPx);
            ticking = false;
        }

        function onScroll() {
            if (!ticking) {
                window.requestAnimationFrame(update);
                ticking = true;
            }
        }

        window.addEventListener("scroll", onScroll, { passive: true });
        window.addEventListener("resize", onScroll, { passive: true });
        update();
    }

    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", init);
    } else {
        init();
    }
})();
