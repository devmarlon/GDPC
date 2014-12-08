SELECT 
    p.codigo, p.descricao, sum(ps.quantidade) as quantidade
FROM
    pecaeqs ps
        inner join
    equipamentoservico es ON ps.ID_EQS = es.ID_EQS
        inner join
    peca p ON ps.PEC_ID = p.PEC_ID
WHERE
    EQP_ID = 34
group by ps.PEC_ID;