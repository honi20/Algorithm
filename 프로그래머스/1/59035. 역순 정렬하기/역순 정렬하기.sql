-- 동물 보호소에 들어온 동물의 정보
-- 각 동물의 아이디, 생물 종, 보호 시작일, 보호 시작 시 상태, 이름, 성별 및 중성화 여부
SELECT name, datetime
FROM ANIMAL_INS
ORDER BY animal_id DESC;