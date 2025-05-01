# -- 코드를 입력하세요
SELECT MONTH(start_date) as MONTH, car_id, COUNT(*) as RECORDS
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE car_id IN (
    SELECT car_id
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE start_date >= "2022-08-01"
    AND start_date <= "2022-10-31"
    GROUP BY car_id
    HAVING COUNT(*) >= 5
)
AND start_date >= '2022-08-01'
AND start_date <= '2022-10-31'
GROUP BY month, car_id
HAVING records > 0
ORDER BY month, car_id desc;
