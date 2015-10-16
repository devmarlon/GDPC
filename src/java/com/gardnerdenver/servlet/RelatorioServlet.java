package com.gardnerdenver.servlet;

import com.gardnerdenver.util.Util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("nome") == null) {
            return;
        }
        response.setContentType("text/html;charset=UTF-8");

//        FacesContext context = FacesContext.getCurrentInstance();
//        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        HttpSession session = (HttpSession) request.getSession(true);

        try {

            String contextPath = request.getRealPath("");
            System.out.println("Context Path: " + contextPath);

//            Class myClass = RelatorioServlet.class;
            String jasperPathSub = request.getRealPath("") + "/jaspers/";
//            String jasperPathSub = myClass.getResource("RelatorioServlet.class").toString();
//            jasperPathSub = jasperPathSub.replace("file:/", "");
//            jasperPathSub = jasperPathSub.replace("build/web/WEB-INF/classes/com/varkon/servlet/RelatorioServlet.class", "web/jaspers/");
//            jasperPathSub = jasperPathSub.replace("home/munaiaco/appservers/glassfish-3x/domains/domain1/applications/GestorWeb/WEB-INF/classes/com/varkon/servlet/RelatorioServlet.class", "/home/munaiaco/appservers/glassfish-3x/domains/domain1/applications/GestorWeb/jaspers/");
//            jasperPathSub = jasperPathSub.replace("home/gestorwe/appservers/glassfish-3x/domains/domain1/applications/GestorWeb/WEB-INF/classes/com/varkon/servlet/RelatorioServlet.class", "/home/gestorwe/appservers/glassfish-3x/domains/domain1/applications/GestorWeb/jaspers/");
//            jasperPathSub = jasperPathSub.replace("opt/glassfish3/glassfish/domains/domain1/applications/GestorWeb/WEB-INF/classes/com/varkon/servlet/RelatorioServlet.class", "/opt/glassfish3/glassfish/domains/domain1/applications/GestorWeb/jaspers/");
//            jasperPathSub = jasperPathSub.replace("home/glassfish/glassfish/domains/domain1/applications/GestorWeb/WEB-INF/classes/com/varkon/servlet/RelatorioServlet.class", "/home/glassfish/glassfish/domains/domain1/applications/GestorWeb/jaspers/");
//            jasperPathSub = jasperPathSub.replace("var/www/clients/client1/web1/web/glassfish/domains/domain1/applications/GestorWeb/WEB-INF/classes/com/varkon/servlet/RelatorioServlet.class", "/var/www/clients/client1/web1/web/glassfish/domains/domain1/applications/GestorWeb/jaspers/");

            String jasperPath = request.getRealPath("") + "/jaspers/" + request.getParameter("nome") + ".jasper";
//            String jasperPath = myClass.getResource("RelatorioServlet.class").toString();
//            jasperPath = jasperPath.replace("file:/", "");
//            jasperPath = jasperPath.replace("build/web/WEB-INF/classes/com/varkon/servlet/RelatorioServlet.class", "web/jaspers/" + request.getParameter("nome") + ".jasper");
//            jasperPath = jasperPath.replace("home/munaiaco/appservers/glassfish-3x/domains/domain1/applications/GestorWeb/WEB-INF/classes/com/varkon/servlet/RelatorioServlet.class", "/home/munaiaco/appservers/glassfish-3x/domains/domain1/applications/GestorWeb/jaspers/" + request.getParameter("nome") + ".jasper");
//            jasperPath = jasperPath.replace("home/gestorwe/appservers/glassfish-3x/domains/domain1/applications/GestorWeb/WEB-INF/classes/com/varkon/servlet/RelatorioServlet.class", "/home/gestorwe/appservers/glassfish-3x/domains/domain1/applications/GestorWeb/jaspers/" + request.getParameter("nome") + ".jasper");
//            jasperPath = jasperPath.replace("opt/glassfish3/glassfish/domains/domain1/applications/GestorWeb/WEB-INF/classes/com/varkon/servlet/RelatorioServlet.class", "/opt/glassfish3/glassfish/domains/domain1/applications/GestorWeb/jaspers/" + request.getParameter("nome") + ".jasper");
//            jasperPath = jasperPath.replace("home/glassfish/glassfish/domains/domain1/applications/GestorWeb/WEB-INF/classes/com/varkon/servlet/RelatorioServlet.class", "/home/glassfish/glassfish/domains/domain1/applications/GestorWeb/jaspers/" + request.getParameter("nome") + ".jasper");
//            jasperPath = jasperPath.replace("var/www/clients/client1/web1/web/glassfish/domains/domain1/applications/GestorWeb/WEB-INF/classes/com/varkon/servlet/RelatorioServlet.class", "/var/www/clients/client1/web1/web/glassfish/domains/domain1/applications/GestorWeb/jaspers/" + request.getParameter("nome") + ".jasper");

            HashMap parametros = new HashMap();
//            RelatorioDao relDao;
            Connection conn;
            JRXmlDataSource xml;
            JasperPrint jp = null;

            if (request.getParameter("nome").equalsIgnoreCase("movimentoRes")
                    || request.getParameter("nome").equalsIgnoreCase("movimentoEntregueRes")
                    || request.getParameter("nome").equalsIgnoreCase("movimentoOds")
                    || request.getParameter("nome").equalsIgnoreCase("movimentoOdsBlank")
                    || request.getParameter("nome").equalsIgnoreCase("movimentoOdsEntregue")
                    || request.getParameter("nome").equalsIgnoreCase("movimentoOrc")
                    || request.getParameter("nome").equalsIgnoreCase("movimentoOrcD")
                    || request.getParameter("nome").equalsIgnoreCase("OrdemCompraRes")) {
                try {
//                    relDao = new RelatorioDao(request.getParameter("id"), "varkon", "qwert1234");

                    conn = Util.getConexao((String) session.getAttribute("database"));

                    String mov_id = request.getParameter("movId");
                    parametros.put("MOV_ID", mov_id);
                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);

                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    if (buffer != null) {
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("ParcDetail")) {
                try {
                    conn = Util.getConexao((String) session.getAttribute("database"));
                    parametros.put("CLIENTE", request.getParameter("cli"));
                    parametros.put("FORNECEDOR", request.getParameter("frn"));
                    parametros.put("TRANSPORTADOR", request.getParameter("tra"));
                    parametros.put("ORDEM", request.getParameter("ordem"));
                    if (request.getParameter("razao") != null) {
                        parametros.put("NOMERAZAO", request.getParameter("razao"));
                    } else {
                        parametros.put("NOMERAZAO", "");
                    }
                    parametros.put("CIDADE", request.getParameter("cidade"));
                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    if (buffer != null) {
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("ParcInativ")) {
                try {
                    conn = Util.getConexao((String) session.getAttribute("database"));
                    parametros.put("DATAINI", request.getParameter("dataIni"));
                    parametros.put("DATAFIM", request.getParameter("dataFim"));

                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    if (buffer != null) {
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("ParcNiver")) {
                try {
                    conn = Util.getConexao((String) session.getAttribute("database"));
                    parametros.put("CLIENTE", request.getParameter("cli"));
                    parametros.put("FORNECEDOR", request.getParameter("frn"));
                    parametros.put("TRANSPORTADOR", request.getParameter("tra"));
                    parametros.put("FISICA", request.getParameter("fis"));
                    parametros.put("JURIDICA", request.getParameter("jur"));
                    parametros.put("MESINI", Integer.valueOf(request.getParameter("mesIni")));
                    parametros.put("MESFIM", Integer.valueOf(request.getParameter("mesFim")));

                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    if (buffer != null) {
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("ProdPosEst")) {
                try {
                    conn = Util.getConexao((String) session.getAttribute("database"));
                    if (request.getParameter("grp") != null) {
                        parametros.put("GRUPO", request.getParameter("grp"));
                    } else {
                        parametros.put("GRUPO", 0);
                    }
                    if (request.getParameter("gri") != null) {
                        parametros.put("SUBGRUPO", request.getParameter("gri"));
                    } else {
                        parametros.put("SUBGRUPO", 0);
                    }
                    if (request.getParameter("pro") != null) {
                        parametros.put("NOME", request.getParameter("pro"));
                    } else {
                        parametros.put("NOME", "");
                    }

                    parametros.put("ORDEM", request.getParameter("ordem"));

                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    if (buffer != null) {
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("ProdMovEst")) {
                try {
                    conn = Util.getConexao((String) session.getAttribute("database"));
                    if (request.getParameter("pro") != null) {
                        parametros.put("PRO_ID", request.getParameter("pro"));
                    } else {
                        parametros.put("PRO_ID", 0);
                    }
                    parametros.put("DATAINI", request.getParameter("dataIni"));
                    parametros.put("DATAFIM", request.getParameter("dataFim"));

                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    if (buffer != null) {
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("ProdContEst")) {
                try {
                    conn = Util.getConexao((String) session.getAttribute("database"));
                    if (request.getParameter("pro") != null) {
                        parametros.put("PRO_ID", request.getParameter("pro"));
                    } else {
                        parametros.put("PRO_ID", 0);
                    }
                    if (request.getParameter("grp") != null) {
                        parametros.put("GRUPO", request.getParameter("grp"));
                    } else {
                        parametros.put("GRUPO", 0);
                    }
                    if (request.getParameter("gri") != null) {
                        parametros.put("SUBGRUPO", request.getParameter("gri"));
                    } else {
                        parametros.put("SUBGRUPO", 0);
                    }

                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    if (buffer != null) {
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("ProdEstMin")) {
                try {
                    conn = Util.getConexao((String) session.getAttribute("database"));

                    if (request.getParameter("grp") != null) {
                        parametros.put("GRUPO", request.getParameter("grp"));
                    } else {
                        parametros.put("GRUPO", 0);
                    }

                    if (request.getParameter("gri") != null) {
                        parametros.put("SUBGRUPO", request.getParameter("gri"));
                    } else {
                        parametros.put("SUBGRUPO", 0);
                    }

                    parametros.put("ESTIGUALMIN", request.getParameter("estIgualMin"));
                    parametros.put("ESTRESERVADO", request.getParameter("estReserv"));
                    parametros.put("ORDEM", request.getParameter("ordem"));

                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    if (buffer != null) {
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("ProdSugComp")) {

                try {
                    conn = Util.getConexao((String) session.getAttribute("database"));

                    if (request.getParameter("grp") != null) {
                        parametros.put("GRUPO", request.getParameter("grp"));
                    } else {
                        parametros.put("GRUPO", 0);
                    }

                    if (request.getParameter("gri") != null) {
                        parametros.put("SUBGRUPO", request.getParameter("gri"));
                    } else {
                        parametros.put("SUBGRUPO", 0);
                    }

                    parametros.put("APMENMIN", request.getParameter("apMenMin"));
                    parametros.put("USEESTRESERV", request.getParameter("estReserv"));
                    parametros.put("TEMPOPREVISAO", request.getParameter("durEst"));
                    parametros.put("TEMPOMEDIA", request.getParameter("tempoMedia"));
                    parametros.put("ORDEM", request.getParameter("ordem"));
                    //parametros.put("SUBREPORT_DIR", jasperPathSub + "cab.jasper");

                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    if (buffer != null) {
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("danfeR")) {

                System.setProperty("java.awt.headless", "true");
                System.setProperty("-Djava.awt.headless", "true");

                String pathXml;// = subDir + xmlNome + ".xml";
                pathXml = request.getParameter("xml").toString();// + ".xml";

                xml = new JRXmlDataSource(pathXml, "/nfeProc/NFe/infNFe/det");
                //System.out.println("LerXML Servlet: " + lerXML(pathXml));                //xml = new JRXmlDataSource(pathXml); /* String layout = jasperPath; layout = layout.replace(".jasper", ".jrxml"); JasperDesign desenho = JRXmlLoader.load(layout); JasperReport jasperReport = JasperCompileManager.compileReport(desenho); */ //String pathLogo = request.getParameter("pathLogo").toString(); //parametros.put("pathLogo", pathLogo);

                String caminhoImagem = request.getParameter("logo").toString();
                String www = request.getParameter("www").toString();
                String mail = request.getParameter("mail").toString();
                String statusNfe = request.getParameter("statusNfe").toString();
                parametros.put("logo", caminhoImagem);
                parametros.put("www", www);
                parametros.put("mail", mail);
                parametros.put("DIR_SUBREPORT", jasperPathSub);
                parametros.put("statusNfe", statusNfe);
                File jasperFile = new File(jasperPath);

                JasperReport jasperObj = (JasperReport) JRLoader.loadObject(jasperFile);
                jp = JasperFillManager.fillReport(jasperObj, parametros, xml);

            } else if (request.getParameter("nome").equalsIgnoreCase("NFS")) {

                System.setProperty("java.awt.headless", "true");
                System.setProperty("-Djava.awt.headless", "true");

                String pathXml;// = subDir + xmlNome + ".xml";
                pathXml = request.getParameter("xml").toString();// + ".xml";

                xml = new JRXmlDataSource(pathXml, "/CompNfse/Nfse/InfNfse");
                //System.out.println("LerXML Servlet: " + lerXML(pathXml));                //xml = new JRXmlDataSource(pathXml); /* String layout = jasperPath; layout = layout.replace(".jasper", ".jrxml"); JasperDesign desenho = JRXmlLoader.load(layout); JasperReport jasperReport = JasperCompileManager.compileReport(desenho); */ //String pathLogo = request.getParameter("pathLogo").toString(); //parametros.put("pathLogo", pathLogo);

                String caminhoImagemNfs = request.getParameter("logo").toString();
                String pathLogoGW = request.getParameter("logoGW").toString();
                String pathLogoCidade = request.getParameter("logoCidade").toString();
                String nomeCidade = request.getParameter("nomeCidade").toString();
                String nomeCidadePt = request.getParameter("nomeCidadePt").toString();
                String statusNfse = request.getParameter("statusNfse").toString();
                String ambienteNfse = request.getParameter("ambienteNfse").toString();
                parametros.put("ambienteNfse", ambienteNfse);
                parametros.put("statusNfse", statusNfse);
                parametros.put("logo", caminhoImagemNfs);
                parametros.put("logoGW", pathLogoGW);
                parametros.put("logoCidade", pathLogoCidade);
                parametros.put("nomeCidTm", nomeCidade);
                parametros.put("nomeCidPt", nomeCidadePt);
                File jasperFile = new File(jasperPath);
                JasperReport jasperObj = (JasperReport) JRLoader.loadObject(jasperFile);
                jp = JasperFillManager.fillReport(jasperObj, parametros, xml);

            } else if (request.getParameter("nome").equalsIgnoreCase("ParcDetail")) {
                try {

                    //parametros.put("dataIni", request.getParameter("dataIni"));
                    //parametros.put("dataFim", request.getParameter("dataFim"));
                    //parametros.put("usuario", usuLogado);
                    //parametros.put("dataStr", dataStr);
                    conn = Util.getConexao((String) session.getAttribute("database"));

                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);

                    //JasperViewer jv = new JasperViewer(jp, false);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    //byte[] buffer2 = JasperExportManager.exportReportToXml(jp);

                    //
                    if (buffer != null) {
                        //System.out.println("Buffer: " + buffer);
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("VendProdID")
                    || request.getParameter("nome").equalsIgnoreCase("VendProdNOME")
                    || request.getParameter("nome").equalsIgnoreCase("VendProdQTDE")) {
                try {

                    parametros.put("DATAINI", request.getParameter("dataIni"));
                    parametros.put("DATAFIM", request.getParameter("dataFim"));
                    parametros.put("PRODUTO", request.getParameter("produto"));
                    parametros.put("OS", request.getParameter("os"));
                    parametros.put("PED", request.getParameter("ped"));
                    parametros.put("GRP", request.getParameter("grp"));
                    parametros.put("GRI", request.getParameter("gri"));

                    conn = Util.getConexao((String) session.getAttribute("database"));;

                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);

                    //JasperViewer jv = new JasperViewer(jp, false);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    //byte[] buffer2 = JasperExportManager.exportReportToXml(jp);

                    if (buffer != null) {
                        //System.out.println("Buffer: " + buffer);
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("Vend")) {
                try {

                    parametros.put("DATAINI", request.getParameter("dataIni"));
                    parametros.put("DATAFIM", request.getParameter("dataFim"));
                    parametros.put("CLI", request.getParameter("cli"));
                    parametros.put("OS", request.getParameter("os"));
                    parametros.put("PED", request.getParameter("ped"));
                    parametros.put("VEND", request.getParameter("vend"));

                    conn = Util.getConexao((String) session.getAttribute("database"));

                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);

                    //JasperViewer jv = new JasperViewer(jp, false);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    //byte[] buffer2 = JasperExportManager.exportReportToXml(jp);

                    if (buffer != null) {
                        //System.out.println("Buffer: " + buffer);
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("VendClI")
                    || request.getParameter("nome").equalsIgnoreCase("VendCliDet")) {
                try {

                    parametros.put("DATAINI", request.getParameter("dataIni"));
                    parametros.put("DATAFIM", request.getParameter("dataFim"));
                    parametros.put("PARCEIRO", request.getParameter("cli"));
                    parametros.put("OS", request.getParameter("os"));
                    parametros.put("PED", request.getParameter("ped"));
                    parametros.put("ORDER", request.getParameter("order"));
                    parametros.put("CIDADE", request.getParameter("cidade"));

                    conn = Util.getConexao((String) session.getAttribute("database"));

                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);

                    //JasperViewer jv = new JasperViewer(jp, false);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    //byte[] buffer2 = JasperExportManager.exportReportToXml(jp);

                    //
                    if (buffer != null) {
                        //System.out.println("Buffer: " + buffer);
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("VendVenID")
                    || request.getParameter("nome").equalsIgnoreCase("VendVenNOME")
                    || request.getParameter("nome").equalsIgnoreCase("VendVenVALOR")
                    || request.getParameter("nome").equalsIgnoreCase("VendVenIDDet")
                    || request.getParameter("nome").equalsIgnoreCase("VendVenNOMEDet")
                    || request.getParameter("nome").equalsIgnoreCase("VendVenVALORDet")) {
                try {

                    parametros.put("DATAINI", request.getParameter("dataIni"));
                    parametros.put("DATAFIM", request.getParameter("dataFim"));
                    parametros.put("FUN_ID", request.getParameter("vend"));
                    parametros.put("OS", request.getParameter("os"));
                    parametros.put("PED", request.getParameter("ped"));
                    parametros.put("ORDER", request.getParameter("order"));
                    parametros.put("CIDADE", request.getParameter("cidade"));

                    conn = Util.getConexao((String) session.getAttribute("database"));

                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);

                    //JasperViewer jv = new JasperViewer(jp, false);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    //byte[] buffer2 = JasperExportManager.exportReportToXml(jp);

                    //
                    if (buffer != null) {
                        //System.out.println("Buffer: " + buffer);
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("VendGrpID")
                    || request.getParameter("nome").equalsIgnoreCase("VendGrpNOME")
                    || request.getParameter("nome").equalsIgnoreCase("VendGrpVALOR")
                    || request.getParameter("nome").equalsIgnoreCase("VendGrpIDDet")
                    || request.getParameter("nome").equalsIgnoreCase("VendGrpNOMEDet")
                    || request.getParameter("nome").equalsIgnoreCase("VendGrpVALORDet")) {
                try {

                    parametros.put("DATAINI", request.getParameter("dataIni"));
                    parametros.put("DATAFIM", request.getParameter("dataFim"));
                    parametros.put("OS", request.getParameter("os"));
                    parametros.put("PED", request.getParameter("ped"));

                    conn = Util.getConexao((String) session.getAttribute("database"));

                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);

                    //JasperViewer jv = new JasperViewer(jp, false);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    //byte[] buffer2 = JasperExportManager.exportReportToXml(jp);

                    //
                    if (buffer != null) {
                        //System.out.println("Buffer: " + buffer);
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("ABCProdLUC")
                    || request.getParameter("nome").equalsIgnoreCase("ABCProdQTDE")
                    || request.getParameter("nome").equalsIgnoreCase("ABCProdVAL")) {
                try {

                    parametros.put("DATAINI", request.getParameter("dataIni"));
                    parametros.put("DATAFIM", request.getParameter("dataFim"));
                    parametros.put("OS", request.getParameter("os"));
                    parametros.put("PED", request.getParameter("ped"));
                    conn = Util.getConexao((String) session.getAttribute("database"));
                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);
                    //JasperViewer jv = new JasperViewer(jp, false);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    //byte[] buffer2 = JasperExportManager.exportReportToXml(jp);
                    //
                    if (buffer != null) {
                        //System.out.println("Buffer: " + buffer);
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("ABCCliLUC")
                    || request.getParameter("nome").equalsIgnoreCase("ABCCliQTDE")
                    || request.getParameter("nome").equalsIgnoreCase("ABCCliVAL")) {
                try {

                    parametros.put("DATAINI", request.getParameter("dataIni"));
                    parametros.put("DATAFIM", request.getParameter("dataFim"));
                    parametros.put("OS", request.getParameter("os"));
                    parametros.put("PED", request.getParameter("ped"));

                    conn = Util.getConexao((String) session.getAttribute("database"));
                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);
                    //JasperViewer jv = new JasperViewer(jp, false);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    //byte[] buffer2 = JasperExportManager.exportReportToXml(jp);
                    //
                    if (buffer != null) {
                        //System.out.println("Buffer: " + buffer);
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("financLancCaixa")) {
                try {

                    parametros.put("DATAINI", request.getParameter("dataIni"));
                    parametros.put("DATAFIM", request.getParameter("dataFim"));
                    parametros.put("PLANOCONTA", request.getParameter("plc"));
                    parametros.put("CENTROCUSTO", request.getParameter("cc"));
                    parametros.put("TRANSF", request.getParameter("transf"));
                    parametros.put("CONTABANC", request.getParameter("cb"));

                    conn = Util.getConexao((String) session.getAttribute("database"));
                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);
                    //JasperViewer jv = new JasperViewer(jp, false);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    //byte[] buffer2 = JasperExportManager.exportReportToXml(jp);
                    //
                    if (buffer != null) {
                        //System.out.println("Buffer: " + buffer);
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("financPagRec")) {
                try {

                    parametros.put("DATAINI", request.getParameter("dataIni"));
                    parametros.put("DATAFIM", request.getParameter("dataFim"));
                    parametros.put("PLANOCONTA", request.getParameter("plc"));
                    parametros.put("CENTROCUSTO", request.getParameter("cc"));
                    parametros.put("CONTABANC", request.getParameter("cb"));
                    parametros.put("TIPO", request.getParameter("tipo"));
                    parametros.put("PAR", request.getParameter("par"));
                    parametros.put("DOC", request.getParameter("doc"));
                    parametros.put("STAT", request.getParameter("stat"));
                    conn = Util.getConexao((String) session.getAttribute("database"));
                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);
                    //JasperViewer jv = new JasperViewer(jp, false);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    //byte[] buffer2 = JasperExportManager.exportReportToXml(jp);
                    //
                    if (buffer != null) {
                        //System.out.println("Buffer: " + buffer);
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("financAnaliseLancPLC")
                    || request.getParameter("nome").equalsIgnoreCase("financAnaliseLancVAL")) {
                try {

                    parametros.put("DATAINI", request.getParameter("dataIni"));
                    parametros.put("DATAFIM", request.getParameter("dataFim"));
                    parametros.put("PLC", request.getParameter("plc"));
                    parametros.put("CCT", request.getParameter("cc"));
                    parametros.put("TRANSF", request.getParameter("transf"));
                    parametros.put("CB", request.getParameter("cb"));
                    parametros.put("REC", request.getParameter("rec"));

                    conn = Util.getConexao((String) session.getAttribute("database"));
                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);
                    //JasperViewer jv = new JasperViewer(jp, false);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    //byte[] buffer2 = JasperExportManager.exportReportToXml(jp);
                    //
                    if (buffer != null) {
                        //System.out.println("Buffer: " + buffer);
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("ProdListaPreco")
                    || request.getParameter("nome").equalsIgnoreCase("ProdListaPrecoGRP")) {
                try {

                    parametros.put("QUERY", request.getParameter("query"));
                    parametros.put("PRCCUSTO", request.getParameter("prccusto"));
                    parametros.put("PRCMIN", request.getParameter("prcmin"));
                    parametros.put("DESCR2", request.getParameter("descr2"));
                    parametros.put("DESCR3", request.getParameter("descr3"));
                    parametros.put("UTIL2", request.getParameter("util2"));
                    parametros.put("UTIL3", request.getParameter("util3"));
                    parametros.put("ACREDESC2", request.getParameter("acredesc2"));
                    parametros.put("ACREDESC3", request.getParameter("acredesc3"));
                    parametros.put("VAL2", request.getParameter("val2"));
                    parametros.put("VAL3", request.getParameter("val3"));

                    conn = Util.getConexao((String) session.getAttribute("database"));
                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);
                    //JasperViewer jv = new JasperViewer(jp, false);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    //byte[] buffer2 = JasperExportManager.exportReportToXml(jp);
                    //
                    if (buffer != null) {
                        //System.out.println("Buffer: " + buffer);
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("extRenovAlvara")) {
                try {

                    parametros.put("DATAINI", request.getParameter("dataIni"));
                    parametros.put("DATAFIM", request.getParameter("dataFim"));
                    parametros.put("FUN_ID", request.getParameter("vend"));
                    parametros.put("ORDER", request.getParameter("order"));

                    conn = Util.getConexao((String) session.getAttribute("database"));

                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);

                    //JasperViewer jv = new JasperViewer(jp, false);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    //byte[] buffer2 = JasperExportManager.exportReportToXml(jp);

                    //
                    if (buffer != null) {
                        //System.out.println("Buffer: " + buffer);
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("extRevisaoExt")) {
                try {

                    parametros.put("DATAINI", request.getParameter("dataIni"));
                    parametros.put("DATAFIM", request.getParameter("dataFim"));
                    parametros.put("FUN_ID", request.getParameter("vend"));
                    parametros.put("PAR_ID", request.getParameter("cli"));
                    parametros.put("ORDER", request.getParameter("order"));

                    System.out.println(parametros.toString());

                    conn = Util.getConexao((String) session.getAttribute("database"));

                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);

                    //JasperViewer jv = new JasperViewer(jp, false);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    //byte[] buffer2 = JasperExportManager.exportReportToXml(jp);

                    //
                    if (buffer != null) {
                        //System.out.println("Buffer: " + buffer);
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("clientes")) {
                try {

                    parametros.put("FUN_ID", request.getParameter("ven"));
                    parametros.put("CLI", request.getParameter("cli"));
                    parametros.put("UF", request.getParameter("uf"));
                    parametros.put("MUN", request.getParameter("mun"));
                    System.out.println(parametros.toString());

                    conn = Util.getConexao((String) session.getAttribute("database"));

                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);

                    //JasperViewer jv = new JasperViewer(jp, false);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    //byte[] buffer2 = JasperExportManager.exportReportToXml(jp);

                    //
                    if (buffer != null) {
                        //System.out.println("Buffer: " + buffer);
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("equipamentos")
                    || request.getParameter("nome").equalsIgnoreCase("equipamentosPecas")) {
                try {

                    parametros.put("MOD", request.getParameter("mod"));
                    parametros.put("CAT", request.getParameter("cat"));
                    parametros.put("FUN_ID", request.getParameter("ven"));
                    parametros.put("CLI", request.getParameter("cli"));
                    parametros.put("UF", request.getParameter("uf"));
                    parametros.put("MUN", request.getParameter("mun"));
                    parametros.put("MINHR", request.getParameter("minhr"));
                    parametros.put("MAXHR", request.getParameter("maxhr"));
                    System.out.println(parametros.toString());

                    conn = Util.getConexao((String) session.getAttribute("database"));

                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);

                    //JasperViewer jv = new JasperViewer(jp, false);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    //byte[] buffer2 = JasperExportManager.exportReportToXml(jp);

                    //
                    if (buffer != null) {
                        //System.out.println("Buffer: " + buffer);
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("historico")) {
                try {

                    parametros.put("MOD", request.getParameter("mod"));
                    parametros.put("CAT", request.getParameter("cat"));
                    parametros.put("FUN_ID", request.getParameter("ven"));
                    parametros.put("CLI", request.getParameter("cli"));
                    parametros.put("UF", request.getParameter("uf"));
                    parametros.put("MUN", request.getParameter("mun"));
                    parametros.put("MINHR", request.getParameter("minhr"));
                    parametros.put("MAXHR", request.getParameter("maxhr"));
                    System.out.println(parametros.toString());

                    conn = Util.getConexao((String) session.getAttribute("database"));

                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);

                    //JasperViewer jv = new JasperViewer(jp, false);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    //byte[] buffer2 = JasperExportManager.exportReportToXml(jp);

                    //
                    if (buffer != null) {
                        //System.out.println("Buffer: " + buffer);
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("rvt")) {
                try {

                    parametros.put("MOV_ID", request.getParameter("movId"));
                    System.out.println(parametros.toString());

                    conn = Util.getConexao((String) session.getAttribute("database"));

                    jp = JasperFillManager.fillReport(jasperPath, parametros, conn);

                    //JasperViewer jv = new JasperViewer(jp, false);
                    byte[] buffer = JasperExportManager.exportReportToPdf(jp);
                    //byte[] buffer2 = JasperExportManager.exportReportToXml(jp);

                    //
                    if (buffer != null) {
                        //System.out.println("Buffer: " + buffer);
                        response.setContentType("application/pdf");
                        response.setDateHeader("Expires", 0);
                        response.setContentLength(buffer.length);
                        response.setHeader("Content-Disposition", "inline; filename=\"\"");

                        OutputStream os = response.getOutputStream();
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        os.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //out.close();
                }

            } else if (request.getParameter("nome").equalsIgnoreCase("HelloWorld")) {

                /////   HELLO - WORLD
                //System.out.println("Dados Conexão: " + request.getParameter("database") + ", " + request.getParameter("usuarioBanco") + ", " + request.getParameter("senhaBanco"));
                conn = Util.getConexao((String) session.getAttribute("database"));
                JasperReport jasperReport = JasperCompileManager.compileReport("HelloWorld.jrxml");
                jp = JasperFillManager.fillReport(jasperReport, null, conn);

            }
            byte[] buffer = JasperExportManager.exportReportToPdf(jp);
            //byte[] buffer2 = JasperExportManager.exportReportToXml(jp);
            //
            if (buffer != null) {
                //System.out.println("Buffer: " + buffer);
                response.setContentType("application/pdf");
                response.setDateHeader("Expires", 0);
                response.setContentLength(buffer.length);
                response.setHeader("Content-Disposition", "inline; filename=\"nfe.pdf\"");

                OutputStream os = response.getOutputStream();
                os.write(buffer, 0, buffer.length);
                os.flush();
                os.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //out.close();
        }
    }

    private String lerXML(String fileXML) throws IOException {
        String linha = "";
        StringBuilder xml = new StringBuilder();

        BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileXML)));
        while ((linha = in.readLine()) != null) {
            xml.append(linha);
        }
        in.close();

        return xml.toString();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
