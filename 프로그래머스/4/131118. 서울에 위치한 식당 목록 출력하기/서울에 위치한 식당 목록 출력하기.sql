-- 코드를 입력하세요
# 서울에 위치한 식당들의 식당 ID, 식당 이름, 음식 종류, 즐겨찾기 수, 주소, 리뷰 평균 점수
# 리뷰 평균점수는 소수점 3번째 자리엣 반올림
# 평균 점수 기준 내림차순 정렬 -> 즐겨찾기수 기준 내림차순 

SELECT info.rest_id, rest_name, food_type, favorites, address, ROUND(AVG(review_score), 2) AS 'score'
FROM REST_INFO info
INNER JOIN REST_REVIEW review ON info.rest_id = review.rest_id
WHERE address LIKE '서울%'
GROUP BY info.rest_id
ORDER BY score DESC, favorites DESC
;
