# Bootstrap Dependency Audit

## Scope
- Templates: `src/main/resources/templates/views/**` (49 files)
- Styles: `src/main/resources/static/css/**`

## Findings
- All view templates use shared layout (`layout:decorate`) and therefore inherit Bootstrap through `default_layout.html`.
- Bootstrap-oriented classes appear across almost every domain (`btn*`, `card*`, `form-*`, `table*`, `badge*`, `alert*`, `modal*`, `pagination*`).
- Highest-risk UI patterns for redesign compatibility:
  - `table` and responsive table blocks in list pages
  - `form-control`, `form-select`, `input-group` in write/update forms
  - `navbar`, `dropdown`, `nav-link` in global header
  - `btn-*` variants used in CTA and actions
- Custom CSS files already override Bootstrap in several areas:
  - `common.css`
  - `information/layout.css`, `information/layout2.css`
  - `mypage/diary.css`
  - `problem.css`

## Migration Strategy
- Keep Bootstrap JS behavior (`dropdown`, `collapse`, `modal`) intact.
- Shift visual identity via tokenized global CSS and Bootstrap bridge styles.
- Add `ui-view` wrapper class to every view root (`layout:fragment="content"`) for consistent page shell styling.
