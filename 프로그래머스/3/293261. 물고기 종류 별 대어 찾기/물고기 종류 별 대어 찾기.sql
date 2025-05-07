-- 잡은 물고기들의 정보 FISH_INFO
-- ID, FISH_TYPE, LENGTH, TIME
-- 잡은 물고기의 ID, 물고기의 종류(숫자), 잡은 물고기의 길이(cm), 물고기를 잡은 날짜
-- 잡은 물고기의 길이가 10cm 이하일 경우 LENGTH는 NULL

-- FISH_NAME_INFO 테이블: 물고기의 이름에 대한 정보
-- FISH_TYPE, FISH_NAME
-- 물고기의 종류(숫자), 물고기의 이름(문자)

-- 물고기 종류 별로 가장 큰 물고기의 Id, 이름, 길이
SELECT id, fish_name, length AS 'length'
FROM FISH_INFO info
LEFT JOIN FISH_NAME_INFO name ON info.fish_type = name.fish_type
WHERE length is not null
AND (info.fish_type, length) IN (
    SELECT fish_type, MAX(length)
    FROM FISH_INFO
    GROUP BY fish_type
)
ORDER BY id
;