-- BOOK: 각 도서의 정보
-- AUTHOR: 도서의 저자 정보
-- 경제 카테고리에 속하는 도서들의 도서 Id, 

SELECT book_id, author_name, DATE_FORMAT(published_date, '%Y-%m-%d') as 'published_date'
FROM BOOK book
LEFT JOIN AUTHOR author ON book.author_id = author.author_id
WHERE category = '경제'
ORDER BY published_date;