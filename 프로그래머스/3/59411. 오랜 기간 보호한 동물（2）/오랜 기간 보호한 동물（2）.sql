-- 코드를 입력하세요
SELECT outs.animal_id, outs.name
from animal_outs outs
left join animal_ins ins on outs.animal_id = ins.animal_id
order by timestampdiff(day, ins.datetime, outs.datetime) desc
limit 2
;