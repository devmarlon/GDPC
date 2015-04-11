package com.gardnerdenver.dao;


import com.gardnerdenver.bean.UserItemFactoryBean;
import com.gardnerdenver.model.Funcionario;
import com.gardnerdenver.model.Generico;
import com.gardnerdenver.model.Movimento;
import com.gardnerdenver.model.MovimentoItem;
import com.gardnerdenver.model.StatusOS;
import com.gardnerdenver.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

public class MovimentoDao extends GenericDAO<Movimento> implements Serializable {

    public MovimentoDao() {
        super(UserItemFactoryBean.banco, Movimento.class, false);
    }

    public void delete(Movimento movimento) {
        super.delete(movimento.getMOV_ID());
    }

    public int nextId() {
        Object id;
        super.createEntityManager();
        Query query = super.em.createQuery("SELECT MAX(m.MOV_ID) FROM Movimento m ");
//        query.setParameter("movTipo", movTipo);
        id = query.getSingleResult();
        super.closeEntityManager();
        if (id == null) {
            return 0;
        } else {
            return Integer.valueOf(id.toString());
        }
    }

    public boolean deleteMov(Movimento mov) {

        try {
            super.beginTransaction();
            

            Query query = em.createQuery("DELETE FROM MovimentoItem m WHERE m.movimento.MOV_ID = :movId AND m.movimento.MOV_STATUS = :movStatus");
            query.setParameter("movId", mov.getMOV_ID());
            query.setParameter("movStatus", mov.getMOV_STATUS());
            query.executeUpdate();
//            mov = em.merge(mov);
//            em.remove(mov);

            super.commitAndCloseTransaction();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
            //em = null;
        }
    }

    public boolean delete2(Movimento obj) {
        //EntityManager em = getEntityManager();
        //EntityManager em = null;
        try {
           super.beginTransaction();
            em.getTransaction().begin();
            obj = em.merge(obj);
            em.remove(obj);
            super.commitAndCloseTransaction();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

//    public boolean cancelDeleteMov(boolean delete, Movimento mov) {
//
//        try {
//            em = emf.createEntityManager();
//            em.getTransaction().begin();
//
//            //////////  DELETE CAIXAS
////            Query query = em.createQuery("DELETE FROM Caixa c WHERE c.titulo.fatura.movimento.MOV_ID = " + mov.getMOV_ID()
////                    + " AND c.titulo.fatura.movimento.MOV_TIPO = '" + mov.getMOV_TIPO() + "'");
////            query.executeUpdate();
//            //////////  DELETE TÍTULOS
//            Query query = em.createQuery("DELETE FROM Titulo t WHERE t.fatura.movimento.MOV_ID = " + mov.getMOV_ID()
//                    + " AND t.fatura.movimento.MOV_TIPO = '" + mov.getMOV_TIPO() + "'");
//            query.executeUpdate();
//
//            //////////  DELETE FATURAS
//            query = em.createQuery("DELETE FROM Fatura f WHERE f.movimento.MOV_ID = " + mov.getMOV_ID()
//                    + " AND f.movimento.MOV_TIPO = '" + mov.getMOV_TIPO() + "'");
//            query.executeUpdate();
//
//            /////////// delete CondicaoPagamentoMovimento
//            query = em.createQuery("DELETE FROM CondicaoPagamentoMovimento f WHERE f.MOV_ID = " + mov.getMOV_ID()
//                    + " AND f.MOV_TIPO = '" + mov.getMOV_TIPO() + "'");
//            query.executeUpdate();
//
//            //////////  DELETE KARDEX
//            query = em.createQuery("DELETE FROM Kardex k WHERE k.movimentoItem.movimento.MOV_ID = " + mov.getMOV_ID()
//                    + " AND k.movimentoItem.movimento.MOV_TIPO = '" + mov.getMOV_TIPO() + "'");
//            query.executeUpdate();
//
//            if (delete) {
//                query = em.createQuery("DELETE FROM MovimentoItem m WHERE m.movimento.MOV_ID = " + mov.getMOV_ID()
//                        + " AND m.movimento.MOV_TIPO = '" + mov.getMOV_TIPO() + "'");
//                query.executeUpdate();
//                mov = em.merge(mov);
//                em.remove(mov);
//            } else {
//                em.merge(mov);
//            }
//
//            em.getTransaction().commit();
//            return true;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            em.getTransaction().rollback();
//            return false;
//        } finally {
//            em.close();
//            //em = null;
//        }
//
//    }
    public double getTotFatMesIndex(int mes, int ano) {
        double total = 0;

        List<Movimento> movs = new ArrayList<>();
        String sql = "SELECT m FROM Movimento m WHERE m.MOV_COBCTO = FALSE AND (m.MOV_TIPO = 'PED' AND m.MOV_STATUS <> 2 AND m.MOV_STATUS <> 254) OR (m.MOV_TIPO = 'ODS' AND m.MOV_STATUS = 7) ";

        try {
            super.createEntityManager();
            Query query = em.createQuery(sql);
            //query = em.createQuery(" SELECT m FROM MOVIMENTO m WHERE (m.MOV_TIPO = 'PED' AND m.MOV_STATUS <> 252 AND m.MOV_STATUS <> 254) OR (m.MOV_TIPO = 'ODS' AND m.MOV_STATUS = 167) ");
            movs = query.getResultList();
            super.closeEntityManager();
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + e.getCause() + "\n" + e.getClass());
        } finally {
            em.close();
        }

        if (movs != null && !movs.isEmpty()) {
            for (Movimento movAux : movs) {
                if (movAux.getMOV_EMISSAO() != null && movAux.getMOV_EMISSAO().getMonth() == (mes - 1)
                        && movAux.getMOV_EMISSAO().getYear() == (ano - 1900)) {
                    total += movAux.getMOV_TOTAL();
                }
            }
        }

        return total;
    }

//    public boolean estornarMov(Movimento mov) throws SQLException {
//
//        List<MovimentoItem> itens = new MovimentoItemDao().getListaByMov(mov);
//
//        try {
//            em = emf.createEntityManager();
//            em.getTransaction().begin();
//
//            for (MovimentoItem ite : itens) {
//                ite = em.merge(ite);
//                em.remove(ite);
//            }
//            em.getTransaction().commit();
//
//            em.getTransaction().begin();
//            mov = em.merge(mov);
//            em.remove(mov);
//            em.getTransaction().commit();
//            return true;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            em.getTransaction().rollback();
//            return false;
//        } finally {
//            em.close();
//        }
//
//    }
    public List< Movimento> getListaVEN(int fun, Date ini, Date fim) {
        List< Movimento> lista = null;
        try {
            super.createEntityManager();
            String auxFunc = " ";
            if (fun > 0) {
                auxFunc = " AND m.FUN_ID = " + fun + " ";
            }
            String dataIni = Util.dateToStrMySql(ini);
            dataIni = dataIni + " 00:00:00";
            String dataFim = Util.dateToStrMySql(fim);
            dataFim = dataFim + " 23:59:59";
            String sql = "SELECT m FROM Movimento m WHERE m.MOV_TIPO = 'VEN' "
                    + auxFunc + " AND m.MOV_EMISSAO >= '" + dataIni + "' AND m.MOV_EMISSAO <= '" + dataFim
                    + "' ORDER BY m.MOV_EMISSAO ";
            //System.out.println("sql: " + sql);
            Query query = em.createQuery(sql);
            lista = query.getResultList();
            super.closeEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return lista;
    }

    public Movimento getObjByID(String TIPO, int ID) {

        if (ID == 0) {
            return null;
        }
        List<Movimento> Movs = null;
        try {
            super.createEntityManager();
            Query query = em.createQuery("SELECT m FROM Movimento m WHERE m.MOV_TIPO = '" + TIPO + "' AND m.MOV_ID = " + ID);
            Movs = query.getResultList();
            super.closeEntityManager();
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + e.getCause() + "\n" + e.getClass());
        } finally {
            em.close();
        }

        if (!Movs.isEmpty()) {
            return Movs.get(0);
        } else {
            return null;
        }
    }

    public List< Movimento> getListaVendas(Date ini, Date fim) {
        String dataIni = Util.dateToStrMySql(ini);
        dataIni = dataIni + " 00:00:00";
        String dataFim = Util.dateToStrMySql(fim);
        dataFim = dataFim + " 23:59:59";

        List<Movimento> vendas = null;

        Query query = em.createQuery("select m from Movimento m "
                + "join m.Parceiro p  "
                + "where m.MOV_EMISSAO >= :d1 and m.MOV_EMISSAO <= :d2 ");

        query.setParameter("d1", dataIni);
        query.setParameter("d2", dataFim);

        vendas = query.getResultList();
        return vendas;
    }

    public String getLastID(String TIPO) {

        if ("".equals(TIPO)) {
            return "";
        }
        Object mov = 0;
        try {
           super.createEntityManager();
            Query query = em.createQuery("SELECT MAX(m.MOV_ID) FROM Movimento m WHERE m.MOV_TIPO = '" + TIPO + "'");
            mov = query.getSingleResult();
            super.closeEntityManager();
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + e.getCause() + "\n" + e.getClass());
        } finally {
            em.close();
        }
        if (mov != null) {
            return mov.toString();
        } else {
            return "0";
        }

    }

    public List<Movimento> getLista(String TIPO) {
        List<Movimento> movs = new ArrayList<>();
        if ("".equals(TIPO)) {
            return movs;
        }
        try {
            super.createEntityManager();
            Query query = em.createQuery("SELECT m FROM Movimento m WHERE m.MOV_TIPO = '" + TIPO + "'");
            movs = query.getResultList();
            super.closeEntityManager();
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + e.getCause() + "\n" + e.getClass());
        } finally {
            em.close();
        }
        if (movs == null || movs.isEmpty()) {
            return movs = new ArrayList<>();
        } else {
            return movs;
        }
    }

    public List<Movimento> getListaAddCto(int par) {
        List<Movimento> movs = new ArrayList<>();

        try {
           super.createEntityManager();
            Query query = em.createQuery("SELECT m FROM Movimento m WHERE m.parceiro.PAR_ID = :par AND m.MOV_STATUS <> 8 AND m.MOV_COBCTO = FALSE AND ((m.MOV_TIPO = 'PED' AND m.MOV_STATUS = 3) OR (m.MOV_TIPO = 'ODS' AND m.MOV_STATUS = 7))");
            query.setParameter("par", par);
            movs = query.getResultList();
            super.closeEntityManager();
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + e.getCause() + "\n" + e.getClass());
        } finally {
            em.close();
        }
        if (movs == null || movs.isEmpty()) {
            return movs = new ArrayList<>();
        } else {
            return movs;
        }
    }

    public List<Movimento> getListaBuscaODS(String CAMPO, String ORDEM, String TIPO, String CLICOD,
            StatusOS STATUS, Generico TIPODATA, Date INICIAL, Date FINAL) {

        List<Movimento> movs = new ArrayList<>();

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");

        String tipo = "";
        String ini = "";
        String fim = "";
        String where = "";
        String and = "";
        String sql = "";
        String clicod = "";
        String stat = "";
        String data = "";

        if ("".equals(TIPO)) {
            return movs;
        } else {
            where = " m.MOV_STATUS <> NULL";
        }

        if (!"".equals(CLICOD) && !" ".equals(CLICOD)) {
            if (Util.isInt(CLICOD)) {
                clicod = " (m.MOV_ID =" + CLICOD + " "
                        + "OR m.parceiro.PAR_NOME LIKE '%" + CLICOD + "%') ";
            } else {
                clicod = " (m.parceiro.PAR_NOME LIKE '%" + CLICOD + "%') ";
            }
        }

//        if (STATUS != null) {
//            if (STATUS.getGEN_ID() == 170) { //170 = abertas
//                stat = " (m.MOV_STATUS BETWEEN 0 AND 6) ";
//            } else if (STATUS.getGEN_ID() == 171) { // 171 = todas menos as canceladas
//                stat = " (m.MOV_STATUS BETWEEN 0 AND 8) ";
//            } else {
//                stat = " (m.MOV_STATUS = " + (STATUS.getGEN_ID() - 160) + ") "; //GUARDA 0,1,... mas na clase o id é 160,161,...
//            }
//        }
        if (TIPODATA != null) {
            if (INICIAL != null) {
                ini = sdf.format(INICIAL);
            }
            if (FINAL != null) {
                fim = sdf.format(FINAL);
            }

            if (TIPODATA.getGEN_ID() == 231) {

                if (INICIAL != null && FINAL != null) {
                    data = " (m.MOV_EMISSAO BETWEEN '" + ini + "' AND '" + fim + "') ";
                } else if (FINAL != null) {
                    data = " (m.MOV_EMISSAO <= '" + fim + "') ";
                } else {
                    data = " (m.MOV_EMISSAO >= '" + ini + "') ";
                }

            } else if (TIPODATA.getGEN_ID() == 232) {

                if (INICIAL != null && FINAL != null) {
                    data = " (m.MOV_PREVISAO BETWEEN '" + ini + "' AND '" + fim + "') ";
                } else if (FINAL != null) {
                    data = " (m.MOV_PREVISAO <= '" + fim + "') ";
                } else {
                    data = " (m.MOV_PREVISAO >= '" + ini + "') ";
                }

            } else if (TIPODATA.getGEN_ID() == 233) {

                if (INICIAL != null && FINAL != null) {
                    data = " (m.MOV_FINALIZACAO BETWEEN '" + ini + "' AND '" + fim + "') ";
                } else if (FINAL != null) {
                    data = " (m.MOV_FINALIZACAO <= '" + fim + "') ";
                } else {
                    data = " (m.MOV_FINALIZACAO >= '" + ini + "') ";
                }

            }
        }

        if ("".equals(clicod)
                && STATUS == null
                && "".equals(data)) {
            and = "";
        } else {
            and = "AND";
            if (!"".equals(clicod)) {
                sql = and + clicod;
            }
            if (!"".equals(stat)) {
                if (!"".equals(sql)) {
                    sql = sql + and + stat;
                } else {
                    sql = and + stat;
                }
            }

            if (!"".equals(data)) {
                if (!"".equals(sql)) {
                    sql = sql + and + data;
                } else {
                    sql = and + data;
                }
            }
        }

        try {
            super.createEntityManager();
            Query query = em.createQuery("SELECT m FROM Movimento m WHERE" + where + sql
                    + " ORDER BY m." + CAMPO + " " + ORDEM);
            movs = query.getResultList();
            super.closeEntityManager();
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + e.getCause() + "\n" + e.getClass());
        } finally {
            em.close();
        }
        if (movs == null || movs.isEmpty()) {
            return movs = new ArrayList<>();
        } else {
            return movs;
        }
    }

    public List<Movimento> getListaBuscaPed(String CAMPO, String ORDEM, String TIPO, String CLICOD, Generico STATUS, Funcionario FUN, Generico TIPODATA, Date INICIAL, Date FINAL) {

        List<Movimento> movs = new ArrayList<>();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");

        String tipo = "";
        String ini = "";
        String fim = "";
        String where = "";
        String and = "";
        String sql = "";
        String clicod = "";
        String stat = "";
        String data = "";
        String fun = "";

        switch (TIPO) {
            case "":
                return movs;
            case "OP": //ORÇAMENTO E PEDIDO
                where = " ( m.MOV_TIPO = 'PED' OR m.MOV_TIPO = 'ORC' )";

                if (STATUS != null) {
                    if (STATUS.getGEN_ID() == 261) { //abertas
                        stat = " (m.MOV_STATUS = 1) ";
                    } else if (STATUS.getGEN_ID() == 262) { //cancelados
                        stat = " (m.MOV_STATUS = 2) ";
                    } else if (STATUS.getGEN_ID() == 263) { //ped. entregues
                        where = " m.MOV_TIPO = 'PED' ";
                        stat = " (m.MOV_STATUS = 3) ";
                    } else if (STATUS.getGEN_ID() == 264) {//ped. reprovados
                        where = " m.MOV_TIPO = 'PED' ";
                        stat = " (m.MOV_STATUS = 4) ";
                    } else if (STATUS.getGEN_ID() == 265) {//orc. transf. pedido
                        where = " m.MOV_TIPO = 'ORC' ";
                        stat = " (m.MOV_STATUS = 3) ";
                    }
                }
                break;
            case "PED": //PEDIDO
                where = " m.MOV_TIPO = 'PED' ";
                if (STATUS != null) {
                    if (STATUS.getGEN_ID() == 251) { //abert0
                        stat = " (m.MOV_STATUS = 1) ";
                    } else if (STATUS.getGEN_ID() == 252) { //cancelado
                        stat = " (m.MOV_STATUS = 2) ";
                    } else if (STATUS.getGEN_ID() == 253) { //ped. entregue
                        stat = " (m.MOV_STATUS = 3) ";
                    } else if (STATUS.getGEN_ID() == 254) {//ped. reprovado   
                        stat = " (m.MOV_STATUS = 4) ";
                    }
                }
                break;
            case "ORC": //ORÇAMENTO
                where = " m.MOV_TIPO = 'ORC' ";
                if (STATUS != null) {
                    if (STATUS.getGEN_ID() == 241) { //abert0
                        stat = " (m.MOV_STATUS = 1) ";
                    } else if (STATUS.getGEN_ID() == 242) { //cancelado
                        stat = " (m.MOV_STATUS = 2) ";
                    } else if (STATUS.getGEN_ID() == 243) { //orc. transf. pedido
                        stat = " (m.MOV_STATUS = 3) ";
                    }
                }
                break;
            case "ODC": //ORDEMCOMPRA
                where = " m.MOV_TIPO = 'ODC' ";
                if (STATUS != null) {
                    if (STATUS.getGEN_ID() == 281) { //abertA
                        stat = " (m.MOV_STATUS = 1) ";
                    } else if (STATUS.getGEN_ID() == 282) { //canceladA
                        stat = " (m.MOV_STATUS = 2) ";
                    } else if (STATUS.getGEN_ID() == 283) { //recebida
                        stat = " (m.MOV_STATUS = 3) ";
                    } else if (STATUS.getGEN_ID() == 284) { //aberta e recebida
                        stat = " (m.MOV_STATUS <> 2) ";
                    }
                }
                break;
        }

        if (!"".equals(CLICOD) && !" ".equals(CLICOD)) {
            if (Util.isInt(CLICOD)) {
                clicod = " (m.MOV_ID =" + CLICOD + " "
                        + "OR m.parceiro.PAR_RAZAO LIKE '%" + CLICOD + "%') ";
            } else {
                clicod = " (m.parceiro.PAR_RAZAO LIKE '%" + CLICOD + "%') ";
            }
        }

        if (FUN != null && FUN.getFUN_ID() > 0) {
            fun = " (m.funcionario.FUN_ID = " + String.valueOf(FUN.getFUN_ID()) + ") ";
        }

        if (TIPODATA != null) {
            if (INICIAL != null) {
                ini = sdf.format(INICIAL);
            }
            if (FINAL != null) {
                fim = sdf.format(FINAL);
            }

            if (TIPODATA.getGEN_ID() == 231) {
                if (INICIAL != null && FINAL != null) {
                    data = " (m.MOV_EMISSAO BETWEEN '" + ini + "' AND '" + fim + "') ";
                } else if (FINAL != null) {
                    data = " (m.MOV_EMISSAO <= '" + fim + "') ";
                } else {
                    data = " (m.MOV_EMISSAO >= '" + ini + "') ";
                }

            } else if (TIPODATA.getGEN_ID() == 232) {
                if (INICIAL != null && FINAL != null) {
                    data = " (m.MOV_PREVISAO BETWEEN '" + ini + "' AND '" + fim + "') ";
                } else if (FINAL != null) {
                    data = " (m.MOV_PREVISAO <= '" + fim + "') ";
                } else {
                    data = " (m.MOV_PREVISAO >= '" + ini + "') ";
                }

            } else if (TIPODATA.getGEN_ID() == 233) {
                if (INICIAL != null && FINAL != null) {
                    data = " (m.MOV_FINALIZACAO BETWEEN '" + ini + "' AND '" + fim + "') ";
                } else if (FINAL != null) {
                    data = " (m.MOV_FINALIZACAO <= '" + fim + "') ";
                } else {
                    data = " (m.MOV_FINALIZACAO >= '" + ini + "') ";
                }

            }
        }

        if ("".equals(clicod)
                && FUN == null
                && STATUS == null
                && "".equals(data)) {
            and = "";
        } else {
            and = "AND";
            if (!"".equals(clicod)) {
                sql = and + clicod;
            }
            if (!"".equals(stat)) {
                if (!"".equals(sql)) {
                    sql = sql + and + stat;
                } else {
                    sql = and + stat;
                }
            }
            if (!"".equals(fun)) {
                if (!"".equals(sql)) {
                    sql = sql + and + fun;
                } else {
                    sql = and + fun;
                }
            }

            if (!"".equals(data)) {
                if (!"".equals(sql)) {
                    sql = sql + and + data;
                } else {
                    sql = and + data;
                }
            }
        }

        try {
            super.createEntityManager();
            String sqlQry = "SELECT m FROM Movimento m WHERE" + where + sql
                    + " ORDER BY m." + CAMPO + " " + ORDEM;
            Query query = em.createQuery(sqlQry);
            movs = query.getResultList();
            super.closeEntityManager();
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + e.getCause() + "\n" + e.getClass());
        } finally {
            em.close();
        }
        if (movs == null || movs.isEmpty()) {
            return movs = new ArrayList<>();
        } else {
            return movs;
        }
    }

    public List<Movimento> getListaBuscaOrc(String CAMPO, String ORDEM, String TIPO, String CLICOD, Generico STATUS, Funcionario FUN, Generico TIPODATA, Date INICIAL, Date FINAL) {

        List<Movimento> movs = new ArrayList<>();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");

        String tipo = "";
        String ini = "";
        String fim = "";
        String where = "";
        String and = "";
        String sql = "";
        String clicod = "";
        String stat = "";
        String data = "";
        String fun = "";

        where = " m.MOV_TIPO = 'ORC' ";
        if (STATUS != null) {
            if (STATUS.getGEN_ID() == 241) { //abert0
                stat = " (m.MOV_STATUS = 1) ";
            } else if (STATUS.getGEN_ID() == 242) { //cancelado
                stat = " (m.MOV_STATUS = 2) ";
            } else if (STATUS.getGEN_ID() == 243) { //transformado em venda
                stat = " (m.MOV_STATUS = 3) ";
            }
        }

        if (!"".equals(CLICOD) && !" ".equals(CLICOD)) {
            if (Util.isInt(CLICOD)) {
                clicod = " (m.MOV_ID =" + CLICOD + " "
                        + "OR m.parceiro.PAR_NOME LIKE '%" + CLICOD + "%') ";
            } else {
                clicod = " (m.parceiro.PAR_NOME LIKE '%" + CLICOD + "%') ";
            }
        }

        if (FUN != null && FUN.getFUN_ID() > 0) {
            fun = " (m.funcionario.FUN_ID = " + String.valueOf(FUN.getFUN_ID()) + ") ";
        }

        if (TIPODATA != null) {
            if (INICIAL != null) {
                ini = sdf.format(INICIAL);
            }
            if (FINAL != null) {
                fim = sdf.format(FINAL);
            }

            if (TIPODATA.getGEN_ID() == 231) {

                if (INICIAL != null && FINAL != null) {
                    data = " (m.MOV_EMISSAO BETWEEN '" + ini + "' AND '" + fim + "') ";
                } else if (FINAL != null) {
                    data = " (m.MOV_EMISSAO <= '" + fim + "') ";
                } else {
                    data = " (m.MOV_EMISSAO >= '" + ini + "') ";
                }

            } else if (TIPODATA.getGEN_ID() == 234) {

                if (INICIAL != null && FINAL != null) {
                    data = " (m.MOV_PREVISAO BETWEEN '" + ini + "' AND '" + fim + "') ";
                } else if (FINAL != null) {
                    data = " (m.MOV_PREVISAO <= '" + fim + "') ";
                } else {
                    data = " (m.MOV_PREVISAO >= '" + ini + "') ";
                }

            } else if (TIPODATA.getGEN_ID() == 233) {

                if (INICIAL != null && FINAL != null) {
                    data = " (m.MOV_FINALIZACAO BETWEEN '" + ini + "' AND '" + fim + "') ";
                } else if (FINAL != null) {
                    data = " (m.MOV_FINALIZACAO <= '" + fim + "') ";
                } else {
                    data = " (m.MOV_FINALIZACAO >= '" + ini + "') ";
                }
            }
        }

        if ("".equals(clicod)
                && FUN == null
                && STATUS == null
                && "".equals(data)) {
            and = "";
        } else {
            and = "AND";
            if (!"".equals(clicod)) {
                sql = and + clicod;
            }
            if (!"".equals(stat)) {
                if (!"".equals(sql)) {
                    sql = sql + and + stat;
                } else {
                    sql = and + stat;
                }
            }
            if (!"".equals(fun)) {
                if (!"".equals(sql)) {
                    sql = sql + and + fun;
                } else {
                    sql = and + fun;
                }
            }

            if (!"".equals(data)) {
                if (!"".equals(sql)) {
                    sql = sql + and + data;
                } else {
                    sql = and + data;
                }
            }
        }

        try {
            super.createEntityManager();
            Query query = em.createQuery("SELECT m FROM Movimento m WHERE" + where + sql
                    + " ORDER BY m." + CAMPO + " " + ORDEM);
            movs = query.getResultList();
            super.closeEntityManager();
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + e.getCause() + "\n" + e.getClass());
        } finally {
            em.close();
        }
        if (movs == null || movs.isEmpty()) {
            return movs = new ArrayList<>();
        } else {
            return movs;
        }
    }

    public List<Movimento> getListaCliHist(int par, String tipo) {
        List<Movimento> mov = null;
        try {
            super.createEntityManager();
            Query query = em.createQuery("SELECT m FROM Movimento m WHERE m.parceiro.PAR_ID = :par AND m.MOV_TIPO = :tipo ");
            query.setParameter("par", par);
            query.setParameter("tipo", tipo);
            mov = query.getResultList();
            super.closeEntityManager();
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + e.getCause() + "\n" + e.getClass());
        } finally {
            em.close();
        }
        return mov;
    }

    public Movimento getMovByTipoId(int id) {

        if (id == 0) {
            return null;
        }
        List<Movimento> Movs = null;
        try {
            super.beginTransaction();
            Query query = em.createQuery("SELECT m FROM Movimento m WHERE m.MOV_ID = :id");
//            query.setParameter("tipo", tipo);
            query.setParameter("id", id);
            Movs = query.getResultList();
            super.closeEntityManager();
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + e.getCause() + "\n" + e.getClass());
        } finally {
            em.close();
        }

        if (!Movs.isEmpty()) {
            return Movs.get(0);
        } else {
            return null;
        }
    }

}
