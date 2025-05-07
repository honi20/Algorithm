-- ONLINE_SALE
-- ONLINE_SALE_ID, USER_ID, PRODUCT_ID, SALES_AMOUNT, SALES_DATE

SELECT user_id, product_id
FROM ONLINE_SALE
GROUP BY user_id, product_id
HAVING COUNT(sales_date) > 1
ORDER BY user_id, product_id DESC
;
