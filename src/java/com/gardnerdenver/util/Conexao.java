/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gardnerdenver.util;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author master
 */
public class Conexao {

    public static Connection getConexao(String database, String local, String usuario, String senha) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            //local = "184.107.31.195";  //  comentar   BANCO NO AR
            //local = "192.168.1.10";    //  comentar   SERVIDOR
            String url = "jdbc:mysql://" + local + ":3306/" + database;

            /*
             * String database = "gestorwe_brinqmannia"; String url =
             * "jdbc:mysql://localhost:3306/" + database; String usuario =
             * "gestorwe_mannia"; String senha = "qwert1234";
             */
            Class.forName("com.mysql.jdbc.Driver");
            Connection con;
            con = (Connection) DriverManager.getConnection(url, usuario, senha);

            return con;

        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        try { //testar a conexao com o banco

            String database = "gestorwe_demo";
            String local = "localhost";
            String usuario = "gestorwe_demo";
            String senha = "qwert1234";

//            String database = "varkon";
//            String local = "174.142.136.186";
//            String usuario = "munaiaco";
//            String senha = "qwert1234";
            Connection conn = Conexao.getConexao(database, local, usuario, senha);
            System.out.println("Conectado com sucesso");
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("Erro ao conectar!");
            System.out.println(ex);
        }
    }
}
