-- 코드를 입력하세요
SELECT food_type, rest_id, rest_name, favorites
FROM REST_INFO
WHERE (food_type, favorites) IN (
    SELECT food_type, MAX(favorites)
    FROM REST_INFO
    GROUP BY food_type
)
GROUP BY food_type
ORDER BY food_type DESC;

# select food_type, favorites, rest_name from rest_info
# order by food_type;

# 분식	151	애플우스
# 양식	102	따띠따띠뜨
# 일식	230	스시사카우스
# 중식	20	만정
# 한식	734	은돼지식당
# 