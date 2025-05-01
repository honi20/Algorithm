-- 코드를 입력하세요
SELECT car_type, COUNT(*) AS cars
FROM CAR_RENTAL_COMPANY_CAR
WHERE car_id IN (
    SELECT car_id
    FROM CAR_RENTAL_COMPANY_CAR
    WHERE options LIKE '%통풍시트%'
    OR options LIKE '%열선시트%'
    OR options LIKE '%가죽시트%'
)
GROUP BY car_type
ORDER BY car_type;