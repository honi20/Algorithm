-- 코드를 입력하세요
SELECT category, price AS 'MAX_PRICE', product_name
FROM FOOD_PRODUCT
WHERE (category, price) IN (
    SELECT category, MAX(price)
    FROM FOOD_PRODUCT
    GROUP BY category
    HAVING category IN ('과자', '국', '김치', '식용유')
)
ORDER BY price DESC
;