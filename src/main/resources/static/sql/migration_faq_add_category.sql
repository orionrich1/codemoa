-- 기존 DB에 FAQ 카테고리 컬럼 추가 (한 번만 실행)
ALTER TABLE faq
    ADD COLUMN category VARCHAR(32) NOT NULL DEFAULT '일반' COMMENT 'FaqCategory 라벨과 동일'
    AFTER faq_id;
