-- 코드를 입력하세요
SELECT half.flavor
FROM FIRST_HALF half
LEFT JOIN ICECREAM_INFO info ON half.flavor = info.flavor
WHERE half.total_order > 3000
AND info.ingredient_type = 'fruit_based'
ORDER BY half.total_order DESC;
;