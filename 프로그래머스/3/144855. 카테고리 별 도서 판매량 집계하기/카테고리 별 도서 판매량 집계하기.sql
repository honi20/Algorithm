# 1. BOOK_SALES 테이블을 기준으로 BOOK 테이블과의 left join
# 2. 판매일이 2022-01인 데이터만 필터링 (WHERE)
# 3. 카테고리 기준 그룹화
# 4. 집계함수 SUM을 통해 총 판매량

SELECT category, SUM(sales) AS 'TOTAL_SALES'
FROM BOOK_SALES sales
LEFT JOIN BOOK book ON sales.book_id = book.book_id
WHERE DATE_FORMAT(sales.sales_date, '%Y-%m') = '2022-01'
GROUP BY category
ORDER BY category
;
