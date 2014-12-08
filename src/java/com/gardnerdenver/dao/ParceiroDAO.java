package com.gardnerdenver.dao;

import com.gardnerdenver.bean.UserItemFactoryBean;
import com.gardnerdenver.model.Estado;
import com.gardnerdenver.model.Municipio;
import com.gardnerdenver.model.Parceiro;
import com.gardnerdenver.util.Util;
import java.util.List;
import javax.persistence.Query;

public class ParceiroDAO extends GenericDAO<Parceiro> {

    private static final long serialVersionUID = 1L;

    public ParceiroDAO() {
        super(UserItemFactoryBean.banco, Parceiro.class, false);
    }

//    public Parceiro findPersonWithAllDogs(int personId) {
//        Map<String, Object> parameters = new HashMap<String, Object>();
//        parameters.put("personId", personId);
//
//        return super.findOneResult(Parceiro.FIND_USER_BY_ID_WITH_DOGS, parameters);
//    }
    public List<Parceiro> findParceiros() {
//        Map<String, Object> parameters = new HashMap<String, Object>();
//        parameters.put("personId", personId);

        return super.findListResult(Parceiro.FIND_PARCEIRO, null);
    }

    public List<Parceiro> findParceirosBusca(String NOME, String CPFCNPJ, String ENDERECO, String BAIRRO, String OUTRO, Estado ESTADO, Municipio CIDADE) {
//        
        List<Parceiro> Parceiros = null;

        String sql = "";
        String where = "";
        String nome = "";
        String cpfcnpj = "";
        String endereco = "";
        String bairro = "";
        String outro = "";
        String estado = "";
        String cidade = "";
        String and = "";
        String estcid = "";

        where = " p.PAR_CLI = true ";

        if (!"".equals(NOME) && !" ".equals(NOME)) {
            if (Util.isInt(NOME)) {
                nome = " (p.PAR_ID = " + NOME + " OR p.PAR_NOME LIKE '%" + NOME + "%' "
                        + "OR p.PAR_RAZAO LIKE '%" + NOME + "%') ";
            } else {
                nome = " (p.PAR_NOME LIKE '%" + NOME + "%' "
                        + "OR p.PAR_RAZAO LIKE '%" + NOME + "%') ";
            }
        }
        if (!"".equals(CPFCNPJ) && !" ".equals(CPFCNPJ)) {
            cpfcnpj = " (p. PAR_CNPJCPF LIKE '%" + CPFCNPJ + "%' "
                    + "OR p. PAR_CNPJCPFCOB LIKE '%" + CPFCNPJ + "%' "
                    + "OR p. PAR_CNPJCPFENT LIKE '%" + CPFCNPJ + "%') ";
        }
        if (ESTADO != null) {
            estado = " (p.PAR_UF =" + ESTADO.getJEST_ID() + " "
                    + "OR p.PAR_UFCOB =" + ESTADO.getJEST_ID() + " "
                    + "OR p.PAR_UFENT =" + ESTADO.getJEST_ID() + ") ";
        }
        if (CIDADE != null) {
            cidade = " (p.PAR_CIDADE = " + CIDADE.getMUN_ID() + " "
                    + "OR p.PAR_CIDADECOB = " + CIDADE.getMUN_ID() + " "
                    + "OR p.PAR_CIDADEENT = " + CIDADE.getMUN_ID() + ") ";
        }
        if (!"".equals(BAIRRO) && !" ".equals(BAIRRO)) {
            bairro = " ( p.PAR_BAIRRO LIKE '%" + BAIRRO + "%' "
                    + "OR p.PAR_BAIRROCOB LIKE '%" + BAIRRO + "%' "
                    + "OR p.PAR_BAIRROENT LIKE '%" + BAIRRO + "%') ";
        }

        if (!"".equals(ENDERECO) && !" ".equals(ENDERECO)) {
            endereco = " (p.PAR_CEP LIKE '%" + ENDERECO + "%' "
                    + "OR p.PAR_ENDERECO LIKE '%" + ENDERECO + "%' "
                    + "OR p.PAR_NUMERO LIKE '%" + ENDERECO + "%' "
                    + "OR p.PAR_CEPCOB LIKE '%" + ENDERECO + "%' "
                    + "OR p.PAR_ENDERECOCOB LIKE '%" + ENDERECO + "%' "
                    + "OR p.PAR_NUMEROCOB LIKE '%" + ENDERECO + "%' "
                    + "OR p.PAR_CEPENT LIKE '%" + ENDERECO + "%' "
                    + "OR p.PAR_ENDERECOENT LIKE '%" + ENDERECO + "%' "
                    + "OR p.PAR_NUMEROENT LIKE '%" + ENDERECO + "%') ";
        }
        if (!"".equals(OUTRO) && !" ".equals(OUTRO)) {
            outro = " (p.PAR_EMAIL LIKE '%" + OUTRO + "%' "
                    + "OR p.PAR_SITE LIKE '%" + OUTRO + "%' "
                    + "OR p.PAR_TELEFONE1 LIKE '%" + OUTRO + "%' "
                    + "OR p.PAR_TELEFONE2 LIKE '%" + OUTRO + "%') ";
        }

        if ("".equals(nome)
                && "".equals(cpfcnpj)
                && ESTADO == null
                && CIDADE == null
                && "".equals(endereco)
                && "".equals(bairro)
                && "".equals(outro)) {
            and = "";
        } else {
            and = "AND";
            if (!"".equals(nome)) {
                sql = and + nome;
            }

            if (!"".equals(cpfcnpj)) {
                if (!"".equals(sql)) {
                    sql = sql + and + cpfcnpj;
                } else {
                    sql = and + cpfcnpj;
                }
            }

            if (!"".equals(estado)) {
                if (!"".equals(sql)) {
                    sql = sql + and + estado;
                } else {
                    sql = and + estado;
                }
            }

            if (!"".equals(cidade)) {
                if (!"".equals(sql)) {
                    sql = sql + and + cidade;
                } else {
                    sql = and + cidade;
                }
            }

            if (!"".equals(bairro)) {
                if (!"".equals(sql)) {
                    sql = sql + and + bairro;
                } else {
                    sql = and + bairro;
                }
            }

            if (!"".equals(endereco)) {
                if (!"".equals(sql)) {
                    sql = sql + and + endereco;
                } else {
                    sql = and + endereco;
                }
            }

            if (!"".equals(outro)) {
                if (!"".equals(sql)) {
                    sql = sql + and + outro;
                } else {
                    sql = and + outro;
                }
            }

        }

        try {
//            em = emf.createEntityManager();
//            em.getTransaction().begin();
            super.begin();

            Query query = super.em.createQuery("SELECT p FROM Parceiro p WHERE " + where + sql);
//            System.out.println("SELECT p FROM Parceiro p WHERE " + where + sql);
//                    + "ORDER BY p." + CAMPO + " " + ORDEM);
            Parceiros = query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + e.getCause() + "\n" + e.getClass());
        } finally {
//            em.close();
            super.close();
        }

        return Parceiros;
    }

    public List<Parceiro> findParceirosByEqsAVencer() {
//        super.begin();

//        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//        cq.select(cq.from(entityClass));
//        return em.createQuery(cq).getResultList();
//        super.closeTransaction();

        return super.findListResult(Parceiro.FIND_PARCEIRO, null);
    }

    public void delete(Parceiro parceiro) {
        super.delete(parceiro.getPAR_ID(), Parceiro.class);
    }
}
