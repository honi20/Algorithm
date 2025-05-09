-- 코드를 입력하세요
SELECT animal_id, name, 
    case
        when SEX_UPON_INTAKE like '%Neutered%' then 'O'
        when SEX_UPON_INTAKE like '%spayed%' then 'O'
        else 'X'
    end as '중성화'
from animal_ins
order by animal_id;