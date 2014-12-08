package com.gardnerdenver.dao;

import com.gardnerdenver.util.Conexao;
import com.gardnerdenver.util.Util;
import java.io.Serializable;
import java.sql.*;

public class RelatorioDao<T> extends GenericDAO<Object> implements Serializable {

    private String local = Util.local;

    public RelatorioDao(String bancoExe, Class<Object> entityClass) {
        super(bancoExe, entityClass, false);
    }

//    public RelatorioDao(String bancoExe, String usuarioExe, String senhaExe) {
//        super(bancoExe, usuarioExe, senhaExe);
//    }
    public String getDataBase() {
        return database;
    }

    //Método para conexão com o banco de dados
    public Connection getConexao() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = Conexao.getConexao(database, local, usuario, senha);

            String url = "jdbc:mysql://" + local + ":3306/" + database; // MySQL
            //String url = "jdbc:oracle:thin:@" + local + ":1521:" + database;

            Class.forName("com.mysql.jdbc.Driver");
            Connection con;

            con = (Connection) DriverManager.getConnection(url, usuario, senha);
            //con = (Connection) DriverManager.getConnection(url, usuario, "ipiranga");
            //Statement st = con.createStatement();
            //st.execute("ALTER SESSION SET nls_date_format='dd.mm.yy hh24:mi:ss'");
            //st.execute("ALTER SESSION SET nls_language='BRAZILIAN PORTUGUESE'");
            //st.execute("ALTER SESSION SET nls_TERRITORY='BRAZIL'");
            return con;

        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public Connection getConexao(String dataD, String usuA, String senH) throws SQLException {
        try {
            database = dataD;
            usuario = usuA;
            senha = senH;
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://" + local + ":3306/" + database; // MySQL
            //String url = "jdbc:oracle:thin:@" + local + ":1521:" + database;

            Class.forName("com.mysql.jdbc.Driver");
            Connection con;

            con = (Connection) DriverManager.getConnection(url, usuario, senha);
            //con = (Connection) DriverManager.getConnection(url, usuario, "ipiranga");
            Statement st = con.createStatement();
            st.execute("ALTER SESSION SET nls_date_format='dd.mm.yy hh24:mi:ss'");
            st.execute("ALTER SESSION SET nls_language='BRAZILIAN PORTUGUESE'");
            st.execute("ALTER SESSION SET nls_TERRITORY='BRAZIL'");
            return con;

        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }

    //Método para preencher a data do cabeçalho dos relatórios
    public String getDataRelatorio() {
        String dataStr = "";

        try {
            String sql = " SELECT 'RELATÓRIO GERADO ÀS '||To_Char(SYSDATE, 'HH24:MI, DAY, DD MONTH')||'DE '||To_Char(SYSDATE,'YYYY') AS DATA FROM DUAL ";
            Connection conn = Conexao.getConexao(database, local, usuario, senha);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                dataStr = rs.getString("DATA");
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + e.getCause() + "\n" + e.getClass());
        }

        return dataStr;
    }

    //Método para verificar se existem registros no relatório selecionado
    public boolean haRegistro(String sql) {

        try {
            Connection conn = getConexao();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                return true;
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + e.getCause() + "\n" + e.getClass());
        }
        return false;
    }
}
