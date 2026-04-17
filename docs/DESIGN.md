# Design System: CodeMoa (ElevenLabs 리듬 + **현행 다크 DS**)

## 0. CodeMoa 통일 원칙 (Single Source of Truth)

CodeMoa UI는 **하나의 시각 언어**만 사용한다. **현재 라이브 구현(`default_layout` + `body.site-ds`)**은 **다크 캔버스 + 민트 악센트**이며, 권위 있는 토큰·오버라이드는 **`site-ds.css`** 한곳에 둔다. 아래 §1·§2.1 등 ElevenLabs 기술은 **타이포 리듬·간격·그림자 철학 참고**용이고, **색·캔버스·CTA 색상은 §2.3·§9.1을 따른다.**

| 규칙 | 설명 |
|------|------|
| 토큰 | 새 스타일·페이지 CSS는 **`var(--ds-*)`** 및 `site-ds.css` 별칭(`--surface-default`, `--text-secondary` 등)을 우선한다. 동일 의미의 색·그림자를 **페이지 CSS에 16진으로 재정의하지 않는다.** |
| 레거시 `common.css` | `:root` 틸/슬레이트 등은 **`body.site-ds` 밖** 또는 미개편 화면에만 한정. 신규·개편은 **`site-ds` 토큰**으로 통일한다. |
| Bootstrap | 그리드·유틸은 Bootstrap 5. **`nav-pills` 활성·`btn-primary`·표·리스트 그룹** 등은 BS 기본 **파랑 primary**가 나오지 않도록 **`site-ds.css`에서 이미 덮어쓴 규칙을 유지**하고, 템플릿에서 **`text-dark`로 다크 배경 가독성을 깨지 않는다.** |
| 금지 | **Bootstrap 기본 파랑(#0d6efd 계열)** 을 탭·필터 활성·브랜드 강조에 새로 도입하지 않는다. **이중 Primary**(서브 페이지만 다른 브랜드색)도 금지. |

---

## 1. Visual Theme & Atmosphere

ElevenLabs' website is a study in restrained elegance — a near-white canvas (`#ffffff`, `#f5f5f5`) where typography and subtle shadows do all the heavy lifting. The design feels like a premium audio product brochure: clean, spacious, and confident enough to let the content speak (literally, given ElevenLabs makes voice AI). There's an almost Apple-like quality to the whitespace strategy, but warmer — the occasional warm stone tint (`#f5f2ef`, `#777169`) prevents the purity from feeling clinical.

**CodeMoa 전역 정책:** 표제·본문·내비·버튼·폼 등 UI 전역에서 **명조·세리프 계열을 사용하지 않는다.** (웹폰트·`font-family` 폴백에 `Georgia`, `Times` 등 세리프 금지.) 대형 제목도 **Inter / Pretendard** 고딕 스택에서 가중치 300·자간으로 위계를 만든다. 본문·UI는 Inter(또는 Pretendard)에 양(+) 자간(0.14px–0.18px)으로 가독성을 유지한다. 코드·터미널은 **JetBrains Mono** 또는 `ui-monospace` 계열만 사용한다.

What makes ElevenLabs distinctive is its multi-layered shadow system. Rather than simple box-shadows, elements use complex stacks: inset border-shadows (`rgba(0,0,0,0.075) 0px 0px 0px 0.5px inset`), outline shadows (`rgba(0,0,0,0.06) 0px 0px 0px 1px`), and soft elevation shadows (`rgba(0,0,0,0.04) 0px 4px 4px`) — all at remarkably low opacities. The result is a design where surfaces seem to barely exist, floating just above the page with the lightest possible touch. Pill-shaped buttons (9999px) with warm-tinted backgrounds (`rgba(245,242,239,0.8)`) and warm shadows (`rgba(78,50,23,0.04)`) add a tactile, physical quality.

**Key Characteristics:**
- Near-white canvas with warm undertones (`#f5f5f5`, `#f5f2ef`)
- **Sans-serif only** — Inter / Pretendard for display + body; no serif display faces
- Inter weight 300 (light) for large display headings — airy hierarchy without serifs
- Inter with positive letter-spacing (0.14–0.18px) for body — airy readability
- Multi-layered shadow stacks at sub-0.1 opacity — surfaces barely exist
- Pill buttons (9999px) with warm stone-tinted backgrounds
- Inter weight 700 + uppercase for specific CTA labels (when needed)
- Warm shadow tints: `rgba(78, 50, 23, 0.04)` — shadows have color, not just darkness
- JetBrains Mono / ui-monospace for code snippets

---

## 2. Color Palette & Roles

### 2.1 시맨틱 팔레트 (문서·픽셀 기준)

#### Primary
- **Pure White** (`#ffffff`): Primary background, card surfaces, button backgrounds
- **Light Gray** (`#f5f5f5`): Secondary surface, subtle section differentiation (`--ds-surface-subtle`)
- **Warm Stone** (`#f5f2ef`): Button background (at 80% opacity) — the warm signature (`--ds-warm`)
- **Black** (`#000000`): Primary text, headings, dark buttons (`--ds-text`, 브랜드 primary)

#### Neutral Scale
- **Dark Gray** (`#4e4e4e`): Secondary text, descriptions (`--ds-text-secondary`)
- **Warm Gray** (`#777169`): Tertiary text, muted links, decorative underlines (`--ds-text-muted`)
- **Near White** (`#f6f6f6`): Alternate light surface

#### Interactive
- **Grid Cyan** (`#7fffff`): `--grid-column-bg`, at 25% opacity — decorative grid overlay
- **Ring Blue** (`rgb(147 197 253 / 0.5)`): focus ring (`--ds-ring`)
- **Border Light** (`#e5e5e5`): Explicit borders (`--ds-border`)
- **Border Subtle** (`rgba(0, 0, 0, 0.05)`): Ultra-subtle bottom borders (`--ds-border-subtle`)

#### 상태·피드백 (최소 사용)

브랜드 색을 늘리지 않되, **폼 검증·알림·배지**에는 의미 전달이 필요하다. 아래는 **기능 영역에 한정**하고, **메인 CTA·내비 강조에는 사용하지 않는다.**

| 용도 | 방향 | 비고 |
|------|------|------|
| 성공 | 낮은 채도 그린 계열 | 배지·토스트·인라인 검증 성공 |
| 경고 | 앰버/오렌지 계열 | 삭제 전 확인 등 |
| 위험·오류 | 레드 계열 | 폼 에러·파괴적 액션 |

구현 시 가능하면 **DS에 추가된 시맨틱 토큰**을 쓰고, 임의의 새 브랜드 컬러를 도입하지 않는다.

### 2.2 CSS 토큰 매핑 (`site-ds.css`)

| 토큰 | 용도 |
|------|------|
| `--ds-text` | 본문·제목 기본색 |
| `--ds-text-secondary` | 보조 설명 |
| `--ds-text-muted` | 메타·캡션·힌트 |
| `--ds-surface` | 카드·패널 배경 |
| `--ds-surface-subtle` | 교대 섹션·호버 배경 |
| `--ds-warm` | 시그니처 웜 CTA 배경 |
| `--ds-border` / `--ds-border-subtle` | 테두리 |
| `--ds-ring` | 포커스 링 |
| `--ds-shadow-outline` / `--ds-shadow-elev` / `--ds-shadow-card` / `--ds-shadow-warm` | 카드·버튼 깊이 |

도메인 전용 CSS(`community.css`, `information/*.css` 등)는 **위 토큰으로 치환**하는 것을 표준으로 한다.

### 2.3 현행 `body.site-ds` 팔레트 · 토큰 (`site-ds.css` — 다크 테크 + 민트)

**라이브 사이트 기준.** 새 화면·수정 시 이 표와 `site-ds.css` 선언을 맞춘다. §2.1의 화이트·웜스톤 수치는 **라이트 모드 복귀 시 참고**용이며, 지금은 아래가 단일 진실원천이다.

| 토큰 | 역할 |
|------|------|
| `--ds-canvas` | 페이지 배경 |
| `--ds-text` / `--ds-text-secondary` / `--ds-text-muted` | 본문·보조·캡션 |
| `--ds-surface` / `--ds-surface-subtle` / `--ds-surface-elevated` | 카드·호버·입력 배경 |
| `--ds-accent` | 주요 CTA·활성 탭·링크 호버 강조 (민트) |
| `--ds-text-on-accent` | 악센트 배경 위 텍스트 (진한 캔버스 톤) |
| `--ds-accent-soft` / `--ds-accent-line` / `--ds-shadow-warm` | 칩·테두리·은은한 글로우 |
| `--ds-border` / `--ds-border-subtle` | 구분선 |
| `--ds-ring` | 포커스 링 (민트 틴트) |
| `--ds-shadow-card` / `--shadow-sm` / `--shadow-md` | 카드·상승 그림자 |
| `--ds-site-header-h` | 고정 헤더 높이 참조 (레이아웃·JS와 맞출 때) |
| `--ds-gallery-overlay-grad` / `--ds-gallery-ease` / `--ds-gallery-duration` | IT 정보 Swiper 카드 호버 오버레이 전용 |

**별칭(레거시 템플릿 호환):** `--surface-default`, `--text-primary`, `--brand-primary` 등은 `site-ds.css`에서 위 토큰을 가리킨다.

### Shadows (참고)

- **Inset Border** (`rgba(0,0,0,0.075) 0px 0px 0px 0.5px inset`): Internal edge definition
- **Inset Dark** (`rgba(0,0,0,0.1) 0px 0px 0px 0.5px inset`): Stronger inset variant
- **Outline Ring** (`rgba(0,0,0,0.06) 0px 0px 0px 1px`): Shadow-as-border
- **Soft Elevation** (`rgba(0,0,0,0.04) 0px 4px 4px`): Gentle lift
- **Card Shadow** (`rgba(0,0,0,0.4) 0px 0px 1px, rgba(0,0,0,0.04) 0px 4px 4px`): Button/card elevation
- **Warm Shadow** (`rgba(78,50,23,0.04) 0px 6px 16px`): Warm-tinted button shadow
- **Edge Shadow** (`rgba(0,0,0,0.08) 0px 0px 0px 0.5px`): Subtle edge definition
- **Inset Ring** (`rgba(0,0,0,0.1) 0px 0px 0px 1px inset`): Strong inset border

---

## 3. Typography Rules

### 전역 규칙 (필수)

- **세리프·명조 미사용:** `font-family`에 세리프 계열(예: Georgia, Times, 명조 계열 웹폰트)을 넣지 않는다. CSS 변수 `--font-display`는 본문과 동일한 **고딕 스택**으로 정의한다 (`site-ds.css`, `common.css`).
- **구현 참조:** `default_layout.html`에서 세리프 웹폰트(Cormorant, DM Serif 등)를 로드하지 않는다. Inter(300–600) + JetBrains Mono만 로드.

### Font Families

- **Display / Body / UI (통합 고딕 스택)**: `"Inter", "Pretendard", -apple-system, BlinkMacSystemFont, "Segoe UI", system-ui, sans-serif`
- **Monospace**: `JetBrains Mono`, `ui-monospace`, SFMono-Regular, Menlo, Monaco, Consolas

### Hierarchy

| Role | Font | Size | Weight | Line Height | Letter Spacing | Notes |
|------|------|------|--------|-------------|----------------|-------|
| Display Hero | Inter | 48px (3.00rem) | 300 | 1.08 (tight) | -0.96px | Light sans display |
| Section Heading | Inter | 36px (2.25rem) | 300 | 1.17 (tight) | normal | Same stack as body |
| Card Heading | Inter | 32px (2.00rem) | 300 | 1.13 (tight) | normal | Light card titles |
| Page Hub Title | Inter | ~1.5–2rem | 300 | tight | align with section | `body.site-ds`에서 `h1`–`h6`는 기본 300 |
| Body Large | Inter | 20px (1.25rem) | 400 | 1.35 | normal | Introductions |
| Body | Inter | 18px (1.13rem) | 400 | 1.44–1.60 | 0.18px | Standard reading text |
| Body Standard | Inter | 16px (1.00rem) | 400 | 1.50 | 0.16px | UI text |
| Body Medium | Inter | 16px (1.00rem) | 500 | 1.50 | 0.16px | Emphasized body |
| Nav / UI | Inter | 15px (0.94rem) | 500 | 1.33–1.47 | 0.15px | Navigation links |
| Button | Inter | 15px (0.94rem) | 500 | 1.47 | normal | Button labels |
| Button Uppercase | Inter | 14px (0.88rem) | 700 | 1.10 (tight) | 0.7px | `text-transform: uppercase` |
| Caption | Inter | 14px (0.88rem) | 400–500 | 1.43–1.50 | 0.14px | Metadata |
| Small | Inter | 13px (0.81rem) | 500 | 1.38 | normal | Tags, badges |
| Code | JetBrains Mono | 13px (0.81rem) | 400 | 1.85 (relaxed) | normal | Code blocks |
| Micro | Inter | 12px (0.75rem) | 500 | 1.33 | normal | Tiny labels |
| Tiny | Inter | 10px (0.63rem) | 400 | 1.60 (relaxed) | normal | Fine print |

### Principles

- **Light sans for display**: Inter at 300 for large headings keeps the “whisper” rhythm without introducing serifs.
- **Positive letter-spacing on body**: Inter uses +0.14px to +0.18px tracking on body text; display can use tighter negative tracking for large sizes.
- **Inter 700 + uppercase for rare CTAs**: Only where a bold uppercase label is required.
- **Monospace for code**: JetBrains Mono (or system mono stack) at relaxed line-height for code blocks.

### CodeMoa — 페이지 머리글·내비 통일

- **`body.site-ds` 안에서** 히어로·허브·목록 상단의 **메인 제목(`h1`/`h2`)에 `fw-bold`(700)를 기본으로 쓰지 않는다.** DS가 제목에 300을 적용한다. 특수한 데이터 테이블 헤더 등 예외는 **소형 제목(`h5`/`h6`)·라벨**로 한정한다.
- **내비 1depth 링크**: Inter **500**, `letter-spacing` **0.15px** 근처. `fw-bold` 남발 대신 동일 굵기로 통일한다.
- **키커(eyebrow)**: 상위 섹션 라벨은 **Caption / Small** 스케일, 색은 `--ds-text-muted` 계열.

---

## 4. Component Stylings

### Buttons

**Primary Black Pill**
- Background: `#000000`
- Text: `#ffffff`
- Padding: 0px 14px
- Radius: 9999px (full pill)
- Use: Primary CTA (전 사이트 동일)

**White Pill (Shadow-bordered)**
- Background: `#ffffff`
- Text: `#000000`
- Radius: 9999px
- Shadow: `rgba(0,0,0,0.4) 0px 0px 1px, rgba(0,0,0,0.04) 0px 4px 4px`
- Use: Secondary CTA on white

**Warm Stone Pill**
- Background: `rgba(245, 242, 239, 0.8)` (warm translucent)
- Text: `#000000`
- Padding: 12px 20px 12px 14px (asymmetric)
- Radius: 30px
- Shadow: `rgba(78, 50, 23, 0.04) 0px 6px 16px` (warm-tinted)
- Use: Featured CTA, hero action — the signature warm button

**Uppercase CTA Label**
- Font: Inter 14px weight 700
- Text-transform: uppercase
- Letter-spacing: 0.7px
- Use: Specific bold CTA labels

### Bootstrap 매핑 (CodeMoa · `site-ds`)

| 의도 | 구현 |
|------|------|
| 주요 제출·이동 CTA | `body.site-ds`에서 **`btn-primary` → 민트 배경 + `--ds-text-on-accent`** (`site-ds.css`). 별도 “블랙 필만이 CTA” 규칙은 **다크 DS에서는 민트가 메인 CTA**로 통일됨. |
| 보조 CTA | `btn-outline-secondary` 등은 DS 테두리·텍스트 토큰에 맞게 오버라이드됨. |
| **탭·필터 (`nav-pills`)** | **활성:** 배경 `--ds-accent`, 글자 `--ds-text-on-accent`. **비활성:** `--ds-text-secondary`, 호버 시 `surface-subtle`. Bootstrap 기본 파랑 **`--bs-nav-pills-link-active-*`가 노출되면 안 됨** — `site-ds.css` 유지. |
| **표 (`table`, `table-light`)** | `--bs-table-bg` / `--bs-table-color` 등을 캔버스·서피스 토큰으로 통일. **`tbody` 링크는 `var(--ds-text)`, 호버 `var(--ds-accent)`**. |
| **목록 (`list-group`)** | `--bs-list-group-*`를 DS 서피스·텍스트로 맞춤. |
| 테이블·상태 배지 | `badge`·`text-bg-*`는 가능하나 **본문 링크 가독성을 `text-dark` 등으로 깨지 말 것** (§7·§9.1). |

**금지:** 서브 페이지만 Bootstrap 기본 **파랑 primary** 또는 **`text-dark` 링크**가 튀어나오는 패턴.

### Cards & Containers

- Background: `#ffffff` / `var(--ds-surface)`
- Border: `1px solid #e5e5e5` or shadow-as-border
- Radius: 16px–24px
- Shadow: multi-layer stack (inset + outline + elevation)
- 허브·목록 카드도 **동일 카드 그림자 토큰**을 사용해 메인과 리듬을 맞춘다.

### Inputs & Forms

- Textarea: padding 12px 20px, transparent text at default
- Select: white background, standard styling
- Radio: standard with tw-ring focus
- Focus: `var(--ds-ring)` 계열 링

### Navigation

- **현행 (`site-ds`):** 헤더는 **`position: fixed`** + 본문 상단 패딩(`main.ui-page-shell` 등)으로 겹침 방지. 스크롤 시 글래스/그림자는 **`site-header.js`의 `.is-scrolled`** 등으로 토글. 앵커 이동 시 `html` **`scroll-padding-top`** 으로 고정 헤더에 가리지 않게 맞춤.
- Inter 15px weight **500** for nav links (not bold by default)
- **CTA:** 다크 DS에서는 **민트 primary** 필 우측 정렬이 기본.
- Mobile: Bootstrap `navbar-toggler` — 브레이크포인트 **lg(992px)**.
- **탭·필터:** `nav-pills`는 §4 Bootstrap 표와 동일하게 **민트 활성**; 정보 허브 등은 `.filter-pills`로 스코프 오버라이드 가능하나 **토큰은 동일 계열**을 쓴다.

### Image Treatment

- Product screenshots and audio waveform visualizations
- Warm gradient backgrounds in feature sections
- 20px–24px radius on image containers
- Full-width sections alternating white and light gray
- **아이콘·등급 뱃지 이미지**는 배경이 `#f5f5f5` 등과 맞닿을 때 **흰 사각 캔버스(매트)** 가 보이지 않도록 **투명 배경 PNG 또는 SVG**를 사용한다. (CSS만으로는 완전 제거가 어려운 경우가 많다.)

### Distinctive Components

**Audio Waveform Sections** (레퍼런스)
- Colorful gradient backgrounds showcasing voice AI capabilities
- Warm amber, blue, and green gradients behind product demos
- Screenshots of the ElevenLabs product interface

**Warm Stone CTA Block**
- `rgba(245,242,239,0.8)` background with warm shadow
- Asymmetric padding (more right padding)
- Creates a physical, tactile quality unique to ElevenLabs

---

## 5. Layout Principles

### Spacing System

- Base unit: 8px
- Scale: 1px, 3px, 4px, 8px, 9px, 10px, 11px, 12px, 16px, 18px, 20px, 24px, 28px, 32px, 40px

### Grid & Container

- Centered content with generous max-width
- Single-column hero, expanding to feature grids
- Full-width gradient sections for product showcases
- White card grids on light gray backgrounds

### Alignment & Distribution (정렬·분배)

가로 여백이 넓게 남을 때 **콘텐츠가 한쪽(주로 왼쪽)에만 몰려 “반쪽만 채운” 느낌**이 나지 않게 한다. 정렬은 **의도한 축(선)에 정확히 맞추는 것**을 우선한다.

#### 축(선)의 규칙

- **왼쪽 정렬:** 블록 안에서 맞출 **기준 세로선**을 하나 정한다. 제목·탭·카드·목록의 **왼쪽 패딩/마진을 동일 컨테이너 기준으로 통일**해, 눈에 보이는 시작선이 한 줄로 이어지게 한다. (섹션은 가운데 제목이어도, 그 아래 **콘텐츠 블록**은 같은 `container` 안에서 탭과 본문의 왼쪽 끝이 서로 어긋나지 않게 한다.)
- **오른쪽 정렬:** 오른쪽 끝을 기준으로 할 때는 **컨테이너의 오른쪽 안쪽 여백**에 맞춘다. “대략 오른쪽”이 아니라 **끝선이 닿도록** 배치한다. (예: 푸터 3열 중 마지막 열을 정책상 오른쪽 앵커로 쓸 경우 `text-align: end` + 동일 그리드/간격.)
- **중앙 정렬:** 제목·탭 그룹·카드 묶음 등을 중앙에 둘 때는 **부모 안에서 수학적으로 중앙**(flex/grid `justify-content: center`, 또는 명시적 그리드 트랙)에 둔다. 위는 중앙·아래는 왼쪽만 붙어 있는 식의 **축 혼합은 의도가 없으면 피한다.**

#### 여백이 남을 때의 분배

- **다열(푸터, 카드 헤더+액션 등):** 한 덩어리로 보이게 **열 간 간격을 의도적으로 분배**한다. 예: `justify-content: space-between`, `grid-template-columns: repeat(3, 1fr)` + 일정한 `gap`, 또는 `1fr auto 1fr` 패턴으로 **시각적 무게가 좌우로 균형** 잡히게 한다. 왼쪽 열만 두껍게 쓰고 오른쪽이 텅 빈 느낌이면 기준 미달.
- **가로 스크롤 탭·칩 트레이:** 트랙 전체가 한 블록이면 **그 블록의 시작선**은 위의 본문/카드와 맞추고, 오른쪽 빈 공간은 “정렬 실패”가 아니라 **의도된 스크롤 영역**임을 유지한다.

#### 아이콘 + 본문 (멀티라인)

- 아이콘 옆에 문장이 **두 줄 이상**으로 갈 때, **둘째 줄부터는 아이콘 왼쪽이 아니라 첫 줄 텍스트의 시작 위치**에 맞춘다(행잉 인덴트). flex만으로 깨지면 `padding-left` + 아이콘 `position` 또는 전용 래퍼로 **텍스트 블록 기준선**을 맞춘다.
- 옆에 **충분한 가로 공간**이 있는데도 불필요하게 좁은 `max-width` 때문에만 줄이 바뀌지 않게 한다. 빈 공간이 크면 **텍스트 영역 `min-width`/`flex`를 키워** 자연스러운 줄바꿈만 허용한다.

#### 구현 시 참고

- 같은 화면에서 **제목만 `text-center`**, 바로 아래 **탭·패널만 왼쪽 시작**이면 “선”이 끊긴다. 제목 블록을 중앙에 두더라도, **아래 모듈은 동일 수평 인셋**을 쓰거나 제목+탭을 하나의 정렬 그룹으로 묶어 기준을 통일한다.
- 장식용 여백과 **실수로 남은 여백**을 구분한다. 후자는 분배·그리드·열 너비 조정으로 줄인다.

### Whitespace Philosophy

- **Apple-like generosity**: Massive vertical spacing between sections creates a premium, unhurried pace. Each section is an exhibit.
- **Warm emptiness**: The whitespace isn't cold — the warm stone undertones and warm shadows give empty space a tactile, physical quality.
- **Typography-led rhythm**: Light-weight Inter (300) headings create visual "whispers" that draw the eye through vast white space — without serifs.

### Border Radius Scale

- Minimal (2px): Small links, inline elements
- Subtle (4px): Nav items, tab panels, tags
- Standard (8px): Small containers
- Comfortable (10px–12px): Medium cards, dropdowns
- Card (16px): Standard cards, articles
- Large (18px–20px): Featured cards, code panels
- Section (24px): Large panels, section containers
- Warm Button (30px): Warm stone CTA
- Pill (9999px): Primary buttons, navigation pills

---

## 6. Depth & Elevation

| Level | Treatment | Use |
|-------|-----------|-----|
| Flat (Level 0) | No shadow | Page background, text blocks |
| Inset Edge (Level 0.5) | `rgba(0,0,0,0.075) 0px 0px 0px 0.5px inset, #fff 0px 0px 0px 0px inset` | Internal border definition |
| Outline Ring (Level 1) | `rgba(0,0,0,0.06) 0px 0px 0px 1px` + `rgba(0,0,0,0.04) 0px 1px 2px` + `rgba(0,0,0,0.04) 0px 2px 4px` | Shadow-as-border for cards |
| Card (Level 2) | `rgba(0,0,0,0.4) 0px 0px 1px, rgba(0,0,0,0.04) 0px 4px 4px` | Button elevation, prominent cards |
| Warm Lift (Level 3) | `rgba(78,50,23,0.04) 0px 6px 16px` | Featured CTAs — warm-tinted |
| Focus (Accessibility) | 민트 틴트 링 (`--ds-ring`) | Keyboard focus (`site-ds`) |

**Shadow Philosophy**: ElevenLabs uses the most refined shadow system of any design system analyzed. Every shadow is at sub-0.1 opacity, many include both outward cast AND inward inset components, and the warm CTA shadows use an actual warm color (`rgba(78,50,23,...)`) rather than neutral black. The inset half-pixel borders (`0px 0px 0px 0.5px inset`) create edges so subtle they're felt rather than seen — surfaces define themselves through the lightest possible touch.

---

## 7. Do's and Don'ts

### Do

- Use **Inter / Pretendard only** for UI and headings; keep `--font-display` as the same sans-serif stack
- Use Inter weight 300 for large display headings where the spec calls for light display type
- Apply multi-layer shadows (inset + outline + elevation) at sub-0.1 opacity
- Use warm stone tints (`#f5f2ef`, `rgba(245,242,239,0.8)`) for featured elements
- Apply positive letter-spacing (+0.14px to +0.18px) on Inter body text
- Use 9999px radius for primary buttons — pill shape is standard
- Use warm-tinted shadows (`rgba(78,50,23,0.04)`) on featured CTAs
- **`body.site-ds`:** 캔버스·서피스 대비로 섹션 구분; 라이트 전용 “대부분 화이트” 문장은 비적용
- Use Inter 700 + uppercase only for specific CTA labels when needed
- **Prefer `var(--ds-*)` in new and refactored CSS** for colors and shadows
- **Align to a clear axis:** 같은 블록에서는 왼쪽·오른쪽·중앙 중 의도를 하나로 잡고, 다열 레이아웃은 `space-between`·균등 그리드 등으로 **빈 공간이 한쪽으로만 몰리지 않게** 분배한다

### Don't

- **Don't use serif / 명조** fonts anywhere (including Georgia/Times fallbacks on headings)
- Don't default display headings to bold (700) — prefer 300 for hero/section scale unless a page explicitly needs heavier weight
- Don't use heavy shadows (>0.1 opacity) — the ethereal quality requires whisper-level depth
- Don't use cool gray borders — the system is warm-tinted throughout
- Don't skip the inset shadow component — half-pixel inset borders define edges
- Don't apply negative letter-spacing to body text — Inter uses positive tracking
- Don't use sharp corners (<8px) on cards — the generous radius is structural
- **Don't use Bootstrap default blue** for `nav-pills` active, stray links, or “extra” primary — `site-ds` 오버라이드와 충돌나게 두지 않는다
- **Don't use `text-dark`** on links inside dark tables or dark main — 가독성 붕괴
- Don't make buttons opaque and heavy — the warm translucent stone treatment is the signature
- **Don't scatter raw hex** for DS colors in domain CSS — map to tokens
- **Don't mix alignment without intent:** 위는 중앙·아래는 임의 왼쪽 시작처럼 **기준선이 끊기게** 두지 않는다. 중앙이면 블록 전체를 중앙에, 왼쪽이면 탭·카드·본문의 시작선을 맞춘다
- **Don't let icon+text second lines align to the icon edge** — 본문은 **첫 줄 텍스트** 기준으로 이어진다

---

## 8. Responsive Behavior

### Breakpoints (Bootstrap 5 정렬)

| Name | Width | Key Changes |
|------|-------|-------------|
| sm | ≥576px | 점진적 여백 |
| md | ≥768px | 2열 가능, **모바일 레이아웃 상한** 검증 |
| lg | ≥992px | **내비 전개**, 데스크톱 헤더 |
| xl | ≥1200px | 넓은 컨테이너 |

레거시 문서의 1024px 표현은 **lg(992px)** 로 이해한다.

### Touch Targets

- Pill buttons with generous padding (12px–20px)
- Navigation links at 15px with adequate spacing
- Select dropdowns maintain comfortable sizing

### Collapsing Strategy

- Navigation: horizontal → collapse at **lg**
- Feature grids: multi-column → stacked
- Hero: maintains centered layout, font scales proportionally
- Gradient sections: full-width maintained, content stacks
- Spacing compresses proportionally

### Image Behavior

- Product screenshots scale responsively
- Gradient backgrounds simplify on mobile
- Audio waveform previews maintain aspect ratio
- Rounded corners maintained across breakpoints

---

## 9. CodeMoa 페이지 패턴 (템플릿)

### 허브·섹션 랜딩 (고객센터 홈, IT 정보 허브 등)

1. 상단 여백: `my-4`–`my-5` 수준으로 메인과 유사 리듬
2. 키커 1줄 (영문 라벨 또는 카테고리) + **라이트 제목** + 리드 문단(`--ds-text-secondary`)
3. 카드 그리드: **동일 반경·`--ds-shadow-card`**; 호버 시 `surface-subtle` 정도의 미세 피드백

### 목록 페이지

- 필터·검색·정렬을 **한 덩어리 카드** 또는 명확한 섹션으로 묶는다
- **빈 목록**: 안내 문구 + **CTA 1개** (작성/이동)

### 정보(IT) 다중 레이아웃

- `information/*.css`가 여럿이어도 **색·그림자·제목 굵기**는 본 절과 `site-ds` 토큰을 공유한다. 레이아웃 전용(그리드 열 수 등)만 파일별로 분리한다.

### 9.1 `site-ds` 개편 누적 패턴 (유지보수 시 그대로 적용)

아래는 이미 코드에 반영된 규칙이다. **같은 종류 UI를 새로 만들 때 복사·재현**하고, `site-ds.css`를 깨지 않도록 한다.

| 영역 | 규칙 | 구현 메모 |
|------|------|-----------|
| **고정 헤더** | 캔버스와 겹치지 않게 본문 패딩·스크롤 보정 | `default_layout` / `header` / `site-header.js`, `--ds-site-header-h` |
| **`nav-pills`** | 활성 = 민트, 비활성 = 그레이 톤 | `site-ds.css` — BS 변수 `--bs-nav-pills-link-active-*` |
| **표 + 링크** | 제목 셀은 **DS 텍스트색**; Bootstrap `text-dark` 금지 | `site-ds.css` `.table tbody a`; 필요 시 `a.text-dark` 폴백 오버라이드 |
| **IT 정보 Swiper 카드** | `.item-gallery-card` / `.book-gallery-card` | 카드 **리프트는 `:has(> a:hover)`** 일 때만(제목만 호버 시 덜컥 움직임 방지). 오버레이는 **`--ds-gallery-overlay-grad`**, blur, `cubic-bezier(0.22,1,0.36,1)`. 썸네일 약한 `scale`. **`prefers-reduced-motion: reduce`** 에서 애니메이션 완화 |
| **리스트·브레드크럼·아코디언** | 다크 대비 | `site-ds`에서 `--bs-list-group-*`, breadcrumb, accordion 버튼 토큰 정렬 |
| **커뮤니티 표** | `community-ds` 스코프 | `community.css` — `community-table` 등 DS 토큰 유지 |
| **폼·회원가입** | `body.site-ds`에 **`--bs-body-color*` / `--bs-secondary-color*` / `--bs-tertiary-color*`** 설정으로 BS가 라이트용 보조색을 쓰지 않게 함 | `.form-text`, `.form-floating > label`, `caret-color`, `invalid-feedback`/`valid-feedback` 토큰, **카드 본문 링크**(`a`만) 민트 |
| **`alert-light`** | 비로그인 안내 등 | 배경·글자를 서피스·본문 토큰으로 (라이트 회색 박스 금지) |

**템플릿 체크:** 목록 제목 링크에 `class="text-dark"` 를 붙이지 않는다. Q&A 목록 등은 **`text-decoration-none`만** 또는 DS용 유틸.

---

## 10. Agent Prompt Guide

### Quick Color Reference

**현행 (`body.site-ds`):** Canvas `#0a0a0a` → `--ds-canvas` · Text `#f0f0f0` → `--ds-text` · Accent mint `#6ef0dc` → `--ds-accent` · Surfaces `#151515` / `#1a1a1a` / `#212121`. 구현은 항상 **`var(--ds-*)`**.

**레거시 라이트 참고 (ElevenLabs 리듬):** White / `#f5f5f5` / warm stone 등 — 라이트 테마 복귀 시에만 픽셀 기준으로 사용.

### Example Component Prompts

- "Create a hero on white background. Headline at 48px **Inter** weight 300, line-height 1.08, letter-spacing -0.96px, black text (sans-serif only). Subtitle at 18px Inter weight 400, line-height 1.60, letter-spacing 0.18px, #4e4e4e text. Two pill buttons: black (9999px, 0px 14px padding) and warm stone (rgba(245,242,239,0.8), 30px radius, 12px 20px padding, warm shadow rgba(78,50,23,0.04) 0px 6px 16px)."
- "Design a card: white background, 20px radius. Shadow: rgba(0,0,0,0.06) 0px 0px 0px 1px, rgba(0,0,0,0.04) 0px 1px 2px, rgba(0,0,0,0.04) 0px 2px 4px. Title at 32px **Inter** weight 300, body at 16px Inter weight 400 letter-spacing 0.16px, #4e4e4e."
- "Build a white pill button: white bg, 9999px radius. Shadow: rgba(0,0,0,0.4) 0px 0px 1px, rgba(0,0,0,0.04) 0px 4px 4px. Text at 15px Inter weight 500."
- "Create an uppercase CTA label: 14px Inter weight 700, text-transform uppercase, letter-spacing 0.7px."
- "Design navigation: white sticky header. Inter 15px weight 500. Black pill CTA right-aligned. Border-bottom: rgba(0,0,0,0.05)."

### Iteration Guide

1. Start with white — the warm undertone comes from shadows and stone surfaces, not backgrounds
2. **Inter 300** for large display headings — sans-serif only; no serif faces or fallbacks
3. Multi-layer shadows: always include inset + outline + elevation at sub-0.1 opacity
4. Positive letter-spacing on Inter body (+0.14px to +0.18px) — the airy reading quality
5. Warm stone CTA is the signature — `rgba(245,242,239,0.8)` with `rgba(78,50,23,0.04)` shadow
6. Pill (9999px) for buttons, generous radius (16px–24px) for cards
7. **One accent story on `site-ds`** — 민트 CTA + 토큰; 파랑 BS primary 노출 금지
8. **Tables in dark** — no `text-dark` on title links; use DS link colors

---

## 11. 구현 체크리스트 (통일 검수)

- [ ] 새·개편 화면이 `layout:decorate="~{layouts/default_layout}"` 및 `body.site-ds` 아래에 있는가
- [ ] 주요 CTA·탭이 **`site-ds` 민트/토큰**과 충돌하는 **Bootstrap 파랑 primary**를 쓰지 않는가
- [ ] 표·목록 **제목 링크**에 `text-dark` 등 **라이트 전용 유틸**이 없는가
- [ ] `nav-pills` 활성 상태가 **민트 배경 + 어두운 글자**인가 (Q&A·유사 필터)
- [ ] IT 정보 갤러리 카드가 **썸네일 링크 호버와 카드 리프트**가 어긋나지 않게 (`:has(> a:hover)` 패턴) 유지되는가
- [ ] `h1`–`h4`에 불필요한 `fw-bold`가 없는가 (`site-ds` 제목 리듬)
- [ ] 도메인 CSS에 DS 색이 하드코딩되지 않았는가 (`var(--ds-*)`)
- [ ] 빈 목록에 안내 + CTA가 있는가
- [ ] 모바일 **768px**에서 레이아웃·터치 타깃이 유지되는가
- [ ] 같은 섹션 안에서 **정렬 축이 일관**한가 (왼쪽/오른쪽/중앙 중 하나의 의도가 한 블록에 통일되는가, 다열 시 여백이 한쪽으로만 쏠리지 않는가)
- [ ] 아이콘+멀티라인 텍스트에서 **둘째 줄이 첫 줄 텍스트 시작**에 맞는가

---

*문서 버전: 다크·민트 `site-ds` 반영, §2.3 토큰 표, §4·§9.1 개편 패턴(nav-pills·표·갤러리·헤더), §7·§10·§11 금지·체크리스트 갱신. ElevenLabs 본문은 타이포·간격 참고용.*
