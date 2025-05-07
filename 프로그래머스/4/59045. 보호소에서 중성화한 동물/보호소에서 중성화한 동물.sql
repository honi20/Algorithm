-- ANIMAL_INS 테이블은 동물 보호소에 들어온 동물의 정보
-- ANIMAL_ID, ANIMAL_TYPE, DATETIME, INTAKE_CONDITION, NAME, SEX_UPON_INTAKE
-- 동물의 아이디, 생물 종, 보호 시작일, 보호 시작 시 상태, 이름, 성별 및 중성화 여부

-- ANIMAL_OUTS 테이블은 동물 보호소에서 입양 보낸 동물의 정보를 담은 테이블
-- ANIMAL_ID, ANIMAL_TYPE, DATETIME, NAME, SEX_UPON_OUTCOME
-- 동물의 아이디, 생물 종, 입양일, 이름, 성별 및 중성화 여부

-- 보호소에 들어올 당시에는 중성화되지 않았지만, 보호소를 나갈 당시에는 중성화된
-- 동물의 아이디와 생물 종, 이름
-- 아이디 순 정렬

SELECT outs.animal_id, outs.animal_type, outs.name
FROM animal_outs outs
left join animal_ins ins on outs.animal_id = ins.animal_id
where outs.sex_upon_outcome IN ('Spayed Female', 'Neutered Male')
and ins.SEX_UPON_INTAKE LIKE 'INTACT%'
ORDER BY outs.animal_id
;
