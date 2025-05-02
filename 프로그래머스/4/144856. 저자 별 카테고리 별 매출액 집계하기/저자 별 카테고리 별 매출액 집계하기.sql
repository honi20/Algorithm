-- 코드를 입력하세요
SELECT a.author_id, a.author_name, b.category
    , SUM(b.price * bs.sales) AS 'SALES'
FROM BOOK_SALES bs
LEFT JOIN BOOK b ON bs.book_id = b.book_id
LEFT JOIN AUTHOR a ON b.author_id = a.author_id
WHERE DATE_FORMAT(sales_date, '%Y-%m') = '2022-01'
GROUP BY a.author_id, category
ORDER BY a.author_id, b.category DESC
;