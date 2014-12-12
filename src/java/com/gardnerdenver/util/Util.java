package com.gardnerdenver.util;

import com.gardnerdenver.facade.ConfiguracaoFacade;
import com.gardnerdenver.facade.ConfiguracaoGdpcFacade;
import com.gardnerdenver.model.Configuracao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class Util {

//    public static String local = "localhost";
    public static String local = "server.gestorweb.com.br";

    public static Connection getConexao(String db) throws SQLException {
//        String database = UserItemFactoryBean.banco;

        String database = db;

        String usuario = "varkon";
        String senha = "qwert1234";
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            Connection conn = Conexao.getConexao(database, local, usuario, senha);

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

    public static String dateToStrMySql(Date aData) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
        return fmt.format(aData);
    }

    public static String dateToStr(Date aData) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        return fmt.format(aData);
    }

    public static String getFactoryDB() {
        return "gdpc";
    }

    public static double convertDoubleMoeda(double precoDouble) {
        DecimalFormat fmt = new DecimalFormat("0.00");
        String string = fmt.format(precoDouble);
        String[] part;
        part = string.split("[.]");
        if (part.length < 2) {
            part = string.split("[,]");
        }
        String string2 = part[0] + "." + part[1];
        double preco = Double.parseDouble(string2);
        return preco;
    }

    public static void gravarCookie(String nome, String valor, int tempo) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        Cookie cookie = new Cookie(nome, valor);
        cookie.setMaxAge(tempo);
        cookie.setComment("Cookie GestorWeb");
        httpServletResponse.addCookie(cookie);
    }

    public static void gravarCookie(String nome, String valor) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        Cookie cookie = new Cookie(nome, valor);
        cookie.setMaxAge(-1);
        cookie.setComment("Cookie GestorWeb");
        httpServletResponse.addCookie(cookie);
    }

    public static void excluirCookie(String nome) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        Cookie cookie = new Cookie(nome, "");
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
    }

    public static boolean isInt(String s) {
        if (s == null || s.equalsIgnoreCase("")) {
            return false;
        }
        // cria um array de char
        char[] c = s.toCharArray();
        boolean d = true;
        for (int i = 0; i < c.length; i++) // verifica se o char não é um dígito
        {
            if (!Character.isDigit(c[i])) {
                d = false;
                break;
            }
        }
        return d;
    }

    public static String getCaminho() {
//        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
//        FacesContext aFacesContext = FacesContext.getCurrentInstance();
//        ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();
//        String realPath = context.getRealPath("");
//
//        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
//        System.out.println("path = " + path);

        String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
        if (!caminho.startsWith("/")) {
            return caminho + "../../../GDPC/";
        }
        return caminho + "/../../../../../../web/GDPC/";

//        return realPath;
    }

    public static Configuracao getConfiguracao() {
        Configuracao cfg;
        ConfiguracaoFacade cfgFacade = new ConfiguracaoFacade();
        cfg = cfgFacade.findConfig(1);
        return cfg;
    }

    public static Configuracao getGdpcConfiguracao() {
        Configuracao cfg;
        ConfiguracaoGdpcFacade cfgFacade = new ConfiguracaoGdpcFacade();
        cfg = cfgFacade.findConfig(1);
        return cfg;
    }

    public static int difEntreDias(Date d1, Date d2) {
        int MILLIS_IN_DAY = 86400000;

        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        c1.set(Calendar.MILLISECOND, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.HOUR_OF_DAY, 0);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        c2.set(Calendar.MILLISECOND, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.HOUR_OF_DAY, 0);
        return (int) ((c1.getTimeInMillis() - c2.getTimeInMillis()) / MILLIS_IN_DAY);
    }

    public static String somenteNumeroLetra(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        str = str.replace("|", "");
        return str.replaceAll("[^a-z|^A-Z|^0-9]", "");
    }

    public static boolean validaCPF_CNPJ(String s_aux) {//------- Rotina para CPF 
        if (s_aux.length() == 11) {
            int d1, d2;
            int digito1, digito2, resto;
            int digitoCPF;
            String nDigResult;
            d1 = d2 = 0;
            digito1 = digito2 = resto = 0;
            for (int n_Count = 1; n_Count < s_aux.length() - 1; n_Count++) {
                digitoCPF = Integer.valueOf(s_aux.substring(n_Count - 1, n_Count)).intValue();
//--------- Multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante. 
                d1 = d1 + (11 - n_Count) * digitoCPF;
//--------- Para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior. 
                d2 = d2 + (12 - n_Count) * digitoCPF;
            }
//--------- Primeiro resto da divisão por 11. 
            resto = (d1 % 11);
//--------- Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior. 
            if (resto < 2) {
                digito1 = 0;
            } else {
                digito1 = 11 - resto;
            }
            d2 += 2 * digito1;
//--------- Segundo resto da divisão por 11. 
            resto = (d2 % 11);
//--------- Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior. 
            if (resto < 2) {
                digito2 = 0;
            } else {
                digito2 = 11 - resto;
            }
//--------- Digito verificador do CPF que está sendo validado. 
            String nDigVerific = s_aux.substring(s_aux.length() - 2, s_aux.length());
//--------- Concatenando o primeiro resto com o segundo. 
            nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
//--------- Comparar o digito verificador do cpf com o primeiro resto + o segundo resto. 
            return nDigVerific.equals(nDigResult);
        } //-------- Rotina para CNPJ 
        else if (s_aux.length() == 14) {
            return isCnpjValido(s_aux);
            /*
             int soma = 0, aux, dig;
             String cnpj_calc = s_aux.substring(0, 12);
             char[] chr_cnpj = s_aux.toCharArray();
             //--------- Primeira parte 
             for (int i = 0; i < 4; i++) {
             if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
             soma += (chr_cnpj[i] - 4 * (6 - (i + 1)));
             }
             }
             for (int i = 0; i < 8; i++) {
             if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
             soma += (chr_cnpj[i + 4] - 4 * (10 - (i + 1)));
             }
             }
             dig = 11 - (soma % 11);
             cnpj_calc += (dig == 10 || dig == 11)
             ? "0" : Integer.toString(dig);
             //--------- Segunda parte 
             soma = 0;
             for (int i = 0; i < 5; i++) {
             if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
             soma += (chr_cnpj[i] - 4 * (7 - (i + 1)));
             }
             }
             for (int i = 0; i < 8; i++) {
             if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
             soma += (chr_cnpj[i + 5] - 4 * (10 - (i + 1)));
             }
             }
             dig = 11 - (soma % 11);
             cnpj_calc += (dig == 10 || dig == 11)
             ? "0" : Integer.toString(dig);
             return s_aux.equals(cnpj_calc);
             * */
        } else {
            return false;
        }
    }

    public static boolean isCnpjValido(String cnpj) {
        if (!cnpj.substring(0, 1).equals("")) {
            try {
                cnpj = cnpj.replace('.', ' ');//onde há ponto coloca espaço
                cnpj = cnpj.replace('/', ' ');//onde há barra coloca espaço
                cnpj = cnpj.replace('-', ' ');//onde há traço coloca espaço
                cnpj = cnpj.replaceAll(" ", "");//retira espaço
                int soma = 0, dig;
                String cnpj_calc = cnpj.substring(0, 12);

                if (cnpj.length() != 14) {
                    return false;
                }
                char[] chr_cnpj = cnpj.toCharArray();
                /* Primeira parte */
                for (int i = 0; i < 4; i++) {
                    if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                        soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
                    }
                }
                for (int i = 0; i < 8; i++) {
                    if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
                        soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
                    }
                }
                dig = 11 - (soma % 11);
                cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(
                        dig);
                /* Segunda parte */
                soma = 0;
                for (int i = 0; i < 5; i++) {
                    if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                        soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
                    }
                }
                for (int i = 0; i < 8; i++) {
                    if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
                        soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
                    }
                }
                dig = 11 - (soma % 11);
                cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(
                        dig);
                return cnpj.equals(cnpj_calc);
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }
}
