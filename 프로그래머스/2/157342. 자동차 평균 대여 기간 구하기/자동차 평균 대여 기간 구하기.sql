-- 코드를 입력하세요
SELECT car_id, 
    ROUND(AVG(TIMESTAMPDIFF(DAY, start_date, end_date) + 1), 1) as 'AVERAGE_DURATION'
from car_rental_company_rental_history
group by car_id
having AVG(TIMESTAMPDIFF(DAY, start_date, end_date) + 1) >= 7
order by average_duration desc, car_id desc
;