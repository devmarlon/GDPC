select 
    *
from
    EquipamentoServico p 
where
    -- p.EQP_ID = 1 AND p.REALIZADO = TRUE AND
 (p.MANUTATUAL , p.MANUTATUALRHORAS) = (select 
            max(x.MANUTATUAL), max(x.MANUTATUALRHORAS)
        FROM
            EquipamentoServico x
        WHERE
            x.SRV_ID = p.SRV_ID
                AND x.EQP_ID = p.EQP_ID
--                AND x.REALIZADO = p.REALIZADO
)
ORDER BY p.SRV_ID