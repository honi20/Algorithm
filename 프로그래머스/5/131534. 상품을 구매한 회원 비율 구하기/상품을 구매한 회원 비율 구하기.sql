-- 의류 쇼핑몰에 가입한 회원 정보를 담은 USER_INFO 테이블
-- USER_ID, GENDER, AGE, JOINED
-- 회원 ID, 성별(0/1), 나이, 가입일

-- 온라인 상품 판매 정보를 담은 ONLINE_SALE 테이블
-- ONLINE_SALE_ID, USER_ID, PRODUCT_ID, SALES_AMOUNT, SALES_DATE
-- 온라인 상품 판매 ID, 회원 ID, 상품 ID, 판매량, 판매일

-- (2021년에 가입한 회원 중 상품을 구매한 회원 수 / 2021년 가입한 전체 회원수)를 년, 월 별로ㅗ 출력
-- 소수점 2번째에서 반올림
-- 년 기준 오름차순 정렬 -> 월 기준 오름차순

SELECT year(sales_date) as 'YEAR', month(sales_date) as 'MONTH'
    , COUNT(DISTINCT sale.user_id) as 'PURCHASED_USERS'
    , ROUND(COUNT(DISTINCT sale.user_id) / (
        select COUNT(*) from user_info where year(joined) = '2021'
    ) , 1) as 'PUCHASED_RATIO'
    
FROM online_sale sale
left join user_info info on sale.user_id = info.user_id
where year(joined) = '2021'
group by year, month
order by year, month
;