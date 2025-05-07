-- ANIMAL_INS 테이블은 동물 보호소에 들어온 동물의 정보
-- ANIMAL_ID, ANIMAL_TYPE, DATETIME, INTAKE_CONDITION, NAME, SEX_UPON_INTAKE
-- 동물의 아이디, 생물 종, 보호 시작일, 보호 시작 시 상태, 이름, 성별 및 중성화 여부

-- ANIMAL_OUTS 테이블은 동물 보호소에서 입양 보낸 동물의 정보를 담은 테이블
-- ANIMAL_ID, ANIMAL_TYPE, DATETIME, NAME, SEX_UPON_OUTCOME
-- 동물의 아이디, 생물 종, 입양일, 이름, 성별 및 중성화 여부

-- 보호 시작일보다 입양일이 더 빠른 동물의 아이디와 이름 조회
-- 보호 시작일이 빠른 순으로 정렬

SELECT outs.animal_id, outs.name
FROM ANIMAL_OUTS outs
LEFT JOIN ANIMAL_INS ins ON outs.animal_id = ins.animal_id
WHERE outs.datetime < ins.datetime
ORDER BY ins.datetime;