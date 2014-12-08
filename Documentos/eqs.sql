select 
    *
from
    EquipamentoServico p;

select 
    *
from
    EquipamentoServico p
where
    p.EQP_ID = 1 AND p.REALIZADO = TRUE
        AND (p.MANUTATUAL = (select 
            max(x.MANUTATUAL)
        FROM
            EquipamentoServico x
        WHERE
            x.SRV_ID = p.SRV_ID
                AND x.EQP_ID = p.EQP_ID
                AND x.REALIZADO = TRUE)
        AND p.MANUTATUALRHORAS = (select 
            max(y.MANUTATUALRHORAS)
        FROM
            EquipamentoServico y
        WHERE
            y.SRV_ID = p.SRV_ID
                AND y.EQP_ID = p.EQP_ID
                AND y.REALIZADO = TRUE))
ORDER BY p.SRV_ID