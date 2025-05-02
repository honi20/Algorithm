SELECT u.user_id, nickname, SUM(price) AS 'total_sales'
FROM USED_GOODS_USER u
LEFT JOIN USED_GOODS_BOARD b ON u.user_id = b.writer_id
WHERE b.status = 'DONE'
GROUP BY user_id
HAVING SUM(price) >= 700000
ORDER BY total_sales
;