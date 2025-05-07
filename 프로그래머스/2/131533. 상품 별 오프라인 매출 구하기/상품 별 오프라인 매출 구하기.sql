-- 판매중인 상품들의 상품 정보를 담은 PRODUCT 테이블
-- PRODUCT_ID, PRODUCT_CODE, PRICE
-- 상품 ID, 상품코드, 판매가

-- 오프라인 상품 판매 정보를 담은 OFFLINE_SALE 테이블
-- OFFLINE_SALE_ID, PRODUCT_ID, SALES_AMOUNT, SALES_DATE
-- 오프라인 상품 판매 ID, 상품 ID, 판매량, 판매일

-- 상품코드 별 매출액(판매가*판매량) 합계 출력
-- 매출액 기준 내림차순 정렬 -> 상품코드 기준 오름차순 정렬

# # 1. 각 상품id 별 매출액
# select product_code, sales_amount*price as 'sales'
# from offline_sale sale
# left join product on sale.product_id = product.product_id
# ;

select product_code, SUM(sales) as 'sales'
from (
    select product_code, sales_amount*price as 'sales'
    from offline_sale sale
    left join product on sale.product_id = product.product_id
) sales_of_product
group by product_code
ORDER BY sales DESC, product_code;