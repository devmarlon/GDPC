package com.gardnerdenver.bean;

import com.gardnerdenver.facade.EquipamentoServicoFacade;
import com.gardnerdenver.facade.ModeloFacade;
import com.gardnerdenver.facade.MunicipioFacade;
import com.gardnerdenver.facade.ParceiroFacade;
import com.gardnerdenver.model.Configuracao;
import com.gardnerdenver.model.Equipamento;
import com.gardnerdenver.model.EquipamentoServico;
import com.gardnerdenver.model.Estado;
import com.gardnerdenver.model.Funcionario;
import com.gardnerdenver.model.Modelo;
import com.gardnerdenver.model.Municipio;
import com.gardnerdenver.model.Parceiro;
import com.gardnerdenver.model.PecaEqs;
import com.gardnerdenver.util.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@SessionScoped
@ManagedBean(name = "relBean")
public class RelatorioBean extends AbstractMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "br"));

//    private static final String SELECTED_PERSON = "selectedPerson";
//    private Dog dog;
//    private Person person;
//    private Person personWithDogs;
//    private Person personWithDogsForDetail;
//
//    private List<Dog> allDogs;
//    private List<Person> persons;
    private StreamedContent cabImagem;
    private StreamedContent logoImagem;

    private Date startDate;
    private Date endDate;
    private Parceiro parceiroDetail;
    private Parceiro filtroParceiro;
    private List<Parceiro> parceiros;
    private List<Parceiro> selectedParceiros;
    private List<Parceiro> filtroParceirosSelected;
    private List<Parceiro> parceirosAux;
    private List<Parceiro> parceirosAux2;
    private List<EquipamentoServico> eqs;
    private List<Equipamento> eqpAux;
    private List<EquipamentoServico> eqsAux;
    private List<EquipamentoServico> eqsAux2;
    private List<Municipio> listMunicipio;
    private List<PecaEqs> listPecas;
    private List<PecaEqs> listPecasEqs;
    private StringBuilder textoPecas;
    private boolean showCartaDlg;
//
    private EquipamentoServicoFacade eqsFacade;
    private ParceiroFacade parFacade;
//    private  personFacade;
    //relatorio de equipamentos
    private Municipio municipio;
    private Estado estado;
    private String cliente;
    private Funcionario vendedor;
    private DataModel<Parceiro> dmParcs;
    private int horaTotaisMin = 0;
    private int horaTotaisMax = 0;
    private int catId = 0;
    private Modelo modelo;
    private List<Modelo> modelos;
    private boolean mostraPecas;

    List<EquipamentoServico> esList = new ArrayList<>();

    public void imprimirRelCli(ActionEvent ae) {
        String cli = "&cli=";
        if (!cliente.trim().isEmpty()) {
            cli += cliente.trim();
        } else {
            cli += "";
        }

        String uf = "";
        if (estado != null) {
            uf = "&uf=" + estado.getJEST_ID();
        } else {
            uf = "&uf=0";
        }

        String mun = "";
        if (municipio != null) {
            mun = "&mun=" + municipio.getMUN_ID();
        } else {
            mun = "&mun=0";
        }

        String ven = "";
        if (vendedor != null) {
            ven = "&ven=" + vendedor.getFUN_ID();
        } else {
            ven = "&ven=0";
        }

        String link = "/gdpc";
//        if (Util.localhost) {
//            link = "/gdpc";
//        }
        FacesContext context = FacesContext.getCurrentInstance();

        link = link + "/RelatorioServlet?nome=clientes" + cli + uf + mun + ven;

        try {
            context.getExternalContext().redirect(link);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            context.responseComplete();
        }
    }

    public void imprimirRelHist(ActionEvent ae) {
        String cli = "&cli=";
        if (!cliente.trim().isEmpty()) {
            cli += cliente.trim();
        } else {
            cli += "";
        }

        String mod = "&mod=" + getModelo().getMOD_ID();
        String cat = "&cat=" + getCatId();

        String link = "/gdpc";
//        if (Util.localhost) {
//            link = "/gdpc";
//        }
        FacesContext context = FacesContext.getCurrentInstance();

        link = link + "/RelatorioServlet?nome=historico" + cli + mod + cat;

        try {
            context.getExternalContext().redirect(link);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            context.responseComplete();
        }
    }

    public void imprimirRelEqp(ActionEvent ae) {

        String mod = "&mod=" + getModelo().getMOD_ID();
        String cat = "&cat=" + getCatId();
//        if (getModelo().getMOD_ID() != 0) {
//            mod += getModelo().getMOD_ID();
//        } else {
//            mod += "0";
//        }
        String cli = "&cli=";
        if (!cliente.trim().isEmpty()) {
            cli += cliente.trim();
        } else {
            cli += "";
        }

        String uf = "";
        if (estado != null) {
            uf = "&uf=" + estado.getJEST_ID();
        } else {
            uf = "&uf=0";
        }

        String mun = "";
        if (municipio != null) {
            mun = "&mun=" + municipio.getMUN_ID();
        } else {
            mun = "&mun=0";
        }

        String ven = "";
        if (vendedor != null) {
            ven = "&ven=" + vendedor.getFUN_ID();
        } else {
            ven = "&ven=0";
        }
        String minhr = "";
        if (horaTotaisMin > 0) {
            minhr = "&minhr=" + horaTotaisMin;
        } else {
            minhr = "&minhr=0";
        }
        String maxhr = "";
        if (horaTotaisMax > 0) {
            maxhr = "&maxhr=" + horaTotaisMax;
        } else {
            maxhr = "&maxhr=0";
        }

        String link = "";

        FacesContext context = FacesContext.getCurrentInstance();

        if (mostraPecas) {
            link = "/gdpc/RelatorioServlet?nome=equipamentosPecas";
        } else {
            link = "/gdpc/RelatorioServlet?nome=equipamentos";
        }

        link += mod + cli + uf + mun + ven + minhr + maxhr + cat;

        try {
            context.getExternalContext().redirect(link);
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" + e.getCause());
        } finally {
            context.responseComplete();
        }
    }

    public void showRelatorio(ActionEvent ae) {
        String btnClicked = ae.getComponent().getId();

//        System.out.println("Oi");
//        parceiroDetail = null;
//        parceiros = null;
        startDate = null;
        endDate = null;

        parceiroDetail = null;
        filtroParceiro = null;
        parceiros = null;
        selectedParceiros = null;
        filtroParceirosSelected = null;
        parceirosAux = null;
        parceirosAux2 = null;
        eqs = null;
        eqsAux = null;
        listMunicipio = null;
        listPecas = null;
        listPecasEqs = null;
        textoPecas = null;

        municipio = null;
        estado = null;
        cliente = null;
        vendedor = null;
        dmParcs = null;

        horaTotaisMin = 0;
        horaTotaisMax = 0;
        catId = 0;

        modelo = null;

        modelos = new ModeloFacade().listAll();
        mostraPecas = false;

        if (btnClicked.contains("rcm")) {
            redirect("/pages/protected/distributor/cartamanutencao.xhtml");
        } else if (btnClicked.contains("rcli")) {
            redirect("/pages/protected/distributor/relatoriocli.xhtml");
        } else if (btnClicked.contains("reqp")) {
            redirect("/pages/protected/distributor/relatorioeqp.xhtml");
        } else if (btnClicked.contains("reqpPeca")) {
            redirect("/pages/protected/distributor/relatorioeqppecas.xhtml");
        } else if (btnClicked.contains("rhist")) {
            redirect("/pages/protected/distributor/relatoriohistorico.xhtml");
        }
    }

    public String busca() {
        boolean parTemEqs = false;
        boolean temEqs = false;

        List<EquipamentoServico> servicos = null;
        loadEqs();
        loadParceiros();

        List<EquipamentoServico> esAux = new ArrayList<>();
        esList = new ArrayList<>();
//        for (Parceiro p : getParFacade().listAll()) {
//            for (Equipamento eqp : p.getEquipamentos()) {
//                servicos = new EquipamentoServicoFacade().listByEqpIdCarta(eqp.getEQP_ID());
        try {
            servicos = getEqsFacade().listCarta();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (servicos != null) {
            for (EquipamentoServico es : servicos) {
//                esList.add(es);
                esAux.add(es);
            }
        }
//            }
//        }

        for (EquipamentoServico es : esAux) {
            while ((es.getMANUTPROXIMA().compareTo(startDate) >= 0)
                    || (es.getMANUTPROXIMAHORAS().compareTo(startDate) >= 0)
                    && (es.getMANUTPROXIMA().compareTo(endDate) <= 0
                    || es.getMANUTPROXIMAHORAS().compareTo(endDate) <= 0)) {
//            if (es.getMANUTPROXIMA().compareTo(new Date()) < 0) {
//                if (es.getMANUTPROXIMA().compareTo(startDate) >= 0 && es.getMANUTPROXIMA().compareTo(endDate) <= 0) {
//                if (es.getMANUTATUAL().compareTo(endDate) <= 0 || es.getMANUTATUAL().compareTo(endDate) <= 0) {
                es.setMANUTATUAL(es.getMANUTPROXIMA());
                es.setMANUTATUALRHORAS(es.getSRV_FREQUENCIAHORAS() + es.getMANUTATUALRHORAS());
                es.setREALIZADO(false);
                es.setID_EQS(0);
                es.setEquipamentosPecas(null);

                try {
                    if (es.getMANUTPROXIMA().compareTo(endDate) <= 0 || es.getMANUTPROXIMAHORAS().compareTo(endDate) <= 0) {
                        getEqsFacade().createEquipamentoServico(es);
//                        esList.add(es);
                        System.out.println("Criou eqs carta de manutenção " + es);
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    break;
                }
//                }
            }
        }

        loadEqs();
        loadParceiros();
        /*
         esAux = new ArrayList<>();
         esList = new ArrayList<>();
         try {
         servicos = getEqsFacade().listCarta();
         } catch (Exception e) {
         System.out.println(e.getMessage());
         }

         if (servicos != null) {
         for (EquipamentoServico es : servicos) {
         esList.add(es);
         //                esAux.add(es);
         }
         }
         */
        ///////////////////////////////////////////////////

        parceirosAux = new ArrayList<>();
        parceirosAux2 = new ArrayList<>();

        List<Equipamento> lstEq = new ArrayList<>();
        List<EquipamentoServico> lstEqs = new ArrayList<>();

//        for (EquipamentoServico eS : eqs) {
//            if ((eS.getMANUTPROXIMA().compareTo(endDate) <= 0) && (eS.getMANUTPROXIMA().compareTo(startDate) >= 0)
//                    || (eS.getMANUTPROXIMAHORAS().compareTo(endDate) <= 0) && (eS.getMANUTPROXIMAHORAS().compareTo(startDate) >= 0)) {
//                eqsAux.add(eS);
//            }
//        }
        for (Parceiro p : parceiros) {

            parTemEqs = false;
            lstEq = new ArrayList<>();
            for (Equipamento e : p.getEquipamentos()) {
                lstEqs = new ArrayList<>();
//                List<EquipamentoServico> esAux2 = new ArrayList<>();

//                for (EquipamentoServico es1 : e.getServicos()) {
//                    for (EquipamentoServico es2 : e.getServicos()) {
//                        if (!es1.equals(es2)) {
//                            if (es1.getServico().getSRV_ID() == es2.getServico().getSRV_ID()) {
//                                if ((es1.getMANUTATUALRHORAS() < es2.getMANUTATUALRHORAS()) && (es1.getMANUTATUAL().before(es2.getMANUTATUAL()))) {
//                                    esAux2.add(es1);
//                                }
//                            }
//                        }
//                    }
//                }
//                
//                
//                for (EquipamentoServico eqpServico : esAux2) {
//                    e.getServicos().remove(eqpServico);
//                }
                /////////
                /////////
                for (EquipamentoServico eS : e.getServicos()) {
                    eS.setMANUTPROXIMA(eS.getMANUTPROXIMA());
                    eS.setMANUTPROXIMAHORAS(eS.getMANUTPROXIMAHORAS());
                    temEqs = false;
                    if ((eS.getMANUTPROXIMA().compareTo(endDate) <= 0) && (eS.getMANUTPROXIMA().compareTo(startDate) >= 0)
                            || (eS.getMANUTPROXIMAHORAS().compareTo(endDate) <= 0) && (eS.getMANUTPROXIMAHORAS().compareTo(startDate) >= 0)) {
//                        parceirosAux.add(p);
                        parTemEqs = true;
                        temEqs = true;
                        lstEqs.add(eS);
                    }
                }
//                if (e.getServicos().isEmpty()) {
//                    temEqs = false;
//                }
                if (!lstEqs.isEmpty() && e.getAtivo()) {
                    e.setServicos(lstEqs);
                    lstEq.add(e);
                }
            }

//            if (parTemEqs && !lstEq.isEmpty()) {
            if (!lstEq.isEmpty()) {
                p.setEquipamentos(lstEq);
                if ((p.getPAR_EMAILCONTATO() != null && !p.getPAR_EMAILCONTATO().isEmpty()) && p.getPAR_EMAIL() != null) {
                    p.setPAR_MULTMAILS(p.getPAR_EMAIL() + "," + p.getPAR_EMAILCONTATO());
                } else if (p.getPAR_EMAIL() != null) {
                    p.setPAR_MULTMAILS(p.getPAR_EMAIL());
                } else if (p.getPAR_EMAILCONTATO() != null) {
                    p.setPAR_MULTMAILS(p.getPAR_EMAILCONTATO());
                }

                if (getFiltroParceirosSelected().isEmpty()) {
                    parceirosAux2.add(p);
                } else {
                    for (Parceiro parFiltro : getFiltroParceirosSelected()) {
                        if (parFiltro.equals(p)) {
                            parceirosAux2.add(p);
                            break;
                        }
                    }
                }
            }
        }

        //vendedor
        if (getVendedor().getFUN_ID() == 0) {
            parceirosAux = parceirosAux2;
        } else {
            for (Parceiro pv : parceirosAux2) {
                if (getVendedor().equals(pv.getVendedor())) {
                    parceirosAux.add(pv);
                }
            }
        }

        //monta texto Carta
        for (Parceiro par : parceirosAux) {
            for (Equipamento ePar : par.getEquipamentos()) {

                MunicipioFacade munFacade = new MunicipioFacade();
                par.setPAR_CIDADESTR(munFacade.findMunicipio(par.getPAR_CIDADE()).getMUN_NOME());
                ePar.setTextoEmail(new StringBuilder());
                String horimetro = "";
                if (ePar.getEqpMedicao() != null) {
                    horimetro = ePar.getEqpMedicao().getEQM_HORASESTIMADAS() + "";
                }
                String logoPath = Util.getConfiguracao().getEMP_LOGO();

                ePar.getTextoEmail().append((""
                        + "         <div style='width: 781px; '>\n"
                        + "            <div style='position:relative; top: 0px; left: 0px; margin: 0 auto;'>\n"
                        + "                <!--<h:graphicImage library=\"images\" name=\"gdhead.png\" />-->\n"
                        + "<p:graphicImage id=\"cabEmp\" value=\"#{relBean.cabImagem}\" style=\"height:100px;\" />"
                        //                        + "                <img src='/gdpc/resources/images/gdhead.png' />\n"
                        //                        + "                <img src='" + Util.getConfiguracao().getEMP_CAB() + "' />\n"
                        //                        + "                <img src='D:/Varkon/gdpc/GDPC/cab.png' />\n"
                        + "            </div>\n"
                        + "            <div style='text-align: center; width: 100%; '>\n"
                        + Util.getConfiguracao().getEMP_CABECALHO()//.replace("\n", "<br/>")
                        + "                \n"
                        + "            </div>\n"
                        //                        + "            <div style='position:absolute; top:40px; left:530px; text-align: center;  margin: 0 auto;'>\n"
                        //                        + "                <img src='/gdpc/logo.jpg' width=\"50\" />\n"
                        //                        + "            </div>\n"
                        + "        </div>"
                        //                        + "        <br/>"
                        + "\n"
                        + "        <div style=\"text-align: center; width: 791px\">\n"
                        + "            GD Preventive Control\n"
                        + "        </div>\n"
                        //                        + "        <br/>\n"
                        + "\n"
                        + "\n"
                        + "        <table width=\"791\" style=\"font-family:sans-serif; font-size:10pt;\"> \n"
                        + "            <!--<tbody>-->\n"
                        + "            <tr >\n"
                        + "                <td  style=\"font-weight: bold; text-align: right\" >Cliente</td>\n"
                        + "                <td colspan=\"5\">" + par.getPAR_RAZAO() + "</td>\n"
                        + "            </tr>\n"
                        + "            <tr>\n"
                        + "                <td  style=\"font-weight: bold; text-align: right\" >Responsável</td>\n"
                        + "                <td >" + par.getPAR_CONTATO() + "</td>\n"
                        + "                <td  style=\"font-weight: bold; text-align: right\" >Email</td>\n"
                        + "                <td colspan=\"3\" >" + par.getPAR_EMAIL() + "</td>\n"
                        + "\n"
                        + "            </tr>\n"
                        + "            <tr>\n"
                        + "                <td  style=\"font-weight: bold; text-align: right\" >Fone</td>\n"
                        + "                <td >" + par.getPAR_TELEFONE1() + "</td>\n"
                        + "                <td  style=\"font-weight: bold; text-align: right\" >Cel</td>\n"
                        + "                <td colspan=\"3\" >" + par.getPAR_TELEFONE2() + "</td>\n"
                        + "            </tr>\n"
                        + "            <tr>\n"
                        + "                <td style=\"font-weight: bold; text-align: right\" >Cidade</td>\n"
                        + "                <td >" + par.getPAR_CIDADESTR() + "</td>\n"
                        + "                <td style=\"font-weight: bold; text-align: right\" >UF</td>\n"
                        + "                <td >" + par.getPAR_UFSTR() + "</td>\n"
                        + "                <td colspan=\"2\" ></td>\n"
                        + "                <td colspan=\"2\" style=\"font-weight: bold; text-align: right\" >Data de Emissão:</td>\n"
                        + "                <td >" + sdf.format(new Date()) + "</td>\n"
                        + "            </tr>\n"
                        + "            <!--</tbody>-->\n"
                        + "        </table> \n"
                        + "        <p style=\"width: 751px; text-indent:10mm; text-align: justify; margin-left: 20px;\">\n"
                        + "            Este documento, apresenta as manutenções preventivas necessárias para realizar no Compressor de Ar Comprimido instalado na sua unidade fabril, Modelo: " + ePar.getModelo().getMOD_NOME() + " N/S: " + ePar.getEQP_SERIE() + "  entre o período de " + sdf.format(startDate) + " à " + sdf.format(endDate) + ". O acompanhamento correto das manutenções preventivas, conforme o manual do equipamento, proporcionam a vigência das garantias do fabricante e uma maior confiabilidade de operação.\n"
                        + "        </p>\n"
                        + "        <p style=\"width: 751px; text-indent:10mm; text-align: justify; margin-left: 20px;\">\n"
                        + "            Os campos do atual regime de trabalho, as datas dos serviços previstos e os respectivos horímetros dos compressores, deverão ser preenchidos e retornados via e-mail / fone ao nosso departamento técnico, para que seja realizado a atualização dos dados do equipamento no nosso sistema.\n"
                        + "        </p>\n"
                        + "\n"
                        + "        <p style=\"border:1px solid; border-color: black; width: 781px; margin-left: 4px;\">\n"
                        + "            <p style=\"font-weight: bold ; text-align: center; width: 791px\"> Atualização/Dúvidas :</p>\n"
                        + "            <p style=\"text-align: center; width: 791px\"> " + Util.getConfiguracao().getRESPONSAVELCARTAMANUTENCAO() + "</p>\n"
                        //                        + "            <table style=\"font-family:sans-serif; font-size:10pt; text-align: center; width: 791px\">\n"
                        //                        + "                <tbody>\n"
                        //                        + "                    <tr>\n"
                        //                        + "                        <td>"
                        //                        + Util.getConfiguracao().getRESPONSAVELCARTAMANUTENCAO()
                        //                        + "</td>\n"
                        //                        //                        + "                        <td>Att. Carlos Eduardo Maier                    </td>\n"
                        //                        //                        + "                        <td>Fone : (11)3109-1100                         </td>\n"
                        //                        //                        + "                        <td>Email: gdpreventivecontrol@gardnerdenver.com </td>\n"
                        //                        + "                    </tr>\n"
                        //                        + "                </tbody>\n"
                        //                        + "            </table>\n"
                        + "        </p> "
                        + "\n"
                        + "        <table style=\"font-family:sans-serif; font-size:10pt;\" border=\"1\" width=\"791\"> \n"
                        + "            <tbody>\n"
                        + "                <tr >\n"
                        + "                    <th colspan=\"4\" style=\"background-color: #d4d4d4\"  >Dados do Equipamento</th>\n"
                        + "                </tr>\n"
                        + "                <tr >\n"
                        + "                    <td colspan=\"4\">Dados do sistema (registrados no GD Preventive Control).</td>\n"
                        + "                </tr>\n"
                        + "                <tr>\n"
                        + "                    <td colspan=\"2\">" + ePar.getCategoria().getCAT_NOME() + " modelo " + ePar.getModelo().getMOD_NOME() + "</td>\n"
                        + "                    <td>Série: " + ePar.getEQP_SERIE() + "</td>\n"
                        + "                    <td>Data de Partida: " + sdf.format(ePar.getEQP_DATAPARTIDA()) + "</td>\n"
                        + "                </tr>\n"
                        + "                <tr>\n"
                        + "                    <td colspan=\"2\">Horímetro estimado na data da emissão: " + horimetro + "</td>\n"
                        + "                    <td colspan=\"2\">Regime de Trabalho: " + ePar.getEQP_REGIMEMEDIA() + " horas diárias médias</td>\n"
                        + "                </tr>\n"
                        + "            </tbody>\n"
                        + "        </table> \n"
                        //                        + "        <br/> \n"
                        + "        <table style=\"font-family:sans-serif; font-size:10pt;\" border=\"1\" width=\"791\"> \n"
                        + "            <tbody>\n"
                        + "                <tr >\n"
                        + "                    <th colspan=\"3\" style=\"background-color: #d4d4d4\">Leituras do Equipamento</th>\n"
                        + "                    <!--<td></td>-->\n"
                        + "                </tr> \n"
                        + "                <tr >\n"
                        + "                    <td colspan=\"3\" >Dados a serem informados pelo responsável (favor, preencher).</td>\n"
                        + "                </tr> \n"
                        + "                <tr >\n"
                        + "                    <td>Horas Totais: ________</td>\n"
                        + "                    <td>Horas em Carga: ________</td>\n"
                        + "                    <td>Data de Leitura: ____/____/______</td>\n"
                        + "                </tr> \n"
                        + "                <tr >\n"
                        + "                    <td colspan=\"3\">Regime de trabalho: Operação semanal de ____ horas, em ____ dias de funcionamento efetivo.</td>\n"
                        + "                </tr> \n"
                        + "            </tbody>\n"
                        + "        </table> \n"));

                ePar.getTextoEmail().append(""
                        + "        <table style=\"font-family:sans-serif; font-size:10pt;\" border=\"1\" width=\"791\"> \n"
                        + "            <tbody>\n"
                        + "                <tr>\n"
                        + "                    <th colspan=\"6\" style=\"background-color: #d4d4d4\" >Manutenções a Realizar</th>\n"
                        + "                </tr>\n"
                        + "                <tr align=\"center\" >\n"
                        + "                    <td rowspan=\"2\" >Serviços</td>\n"
                        + "                    <!--                </tr>\n"
                        + "                                    <tr>-->\n"
                        + "                    <td colspan=\"2\" >Manutenção</td>\n"
                        + "                    <td rowspan=\"2\" >Data de Execução</td>\n"
                        + "                    <td rowspan=\"2\" >Horas Totais</td>\n"
                        + "                    <td rowspan=\"2\" >Horas de Carga</td>\n"
                        + "                </tr>\n"
                        + "                <tr align=\"center\">\n"
                        + "                    <td>Última</td>\n"
                        + "                    <td>Próxima</td>\n"
                        + "                </tr>");
                for (EquipamentoServico es : ePar.getServicos()) {
                    String dtManAnterior = "";
                    String dtManProx = "";
                    if (es.getMANUTANTERIOR() != null) {
                        dtManAnterior = sdf.format(es.getMANUTANTERIOR());
                    }
                    if (es.getMANUTPROXIMA() != null && es.getMANUTPROXIMAHORAS() != null) {
//                        if (es.getMANUTPROXIMA().compareTo(es.getMANUTPROXIMAHORAS()) > 0) {
//                            dtManProx = sdf.format(es.getMANUTPROXIMA());
//                        } else if (es.getMANUTPROXIMA().compareTo(es.getMANUTPROXIMAHORAS()) < 0) {
//                            dtManProx = sdf.format(es.getMANUTPROXIMAHORAS());
//                        } else {
//                            dtManProx = sdf.format(es.getMANUTPROXIMA());
//                        }

                        if ((es.getMANUTPROXIMA().compareTo(startDate) >= 0) && (es.getMANUTPROXIMA().compareTo(endDate) <= 0)) {
                            dtManProx = sdf.format(es.getMANUTPROXIMA());
                        }
                        if ((es.getMANUTPROXIMAHORAS().compareTo(startDate) >= 0) && (es.getMANUTPROXIMAHORAS().compareTo(endDate) <= 0)) {
                            dtManProx = sdf.format(es.getMANUTPROXIMAHORAS());
                        }

                    }

                    ePar.getTextoEmail().append("<tr>\n"
                            + "                    <td>" + es.getServico().getSRV_DESCRICAO() + "</td>\n"
                            + "                    <td style=\"text-align: center\">" + dtManAnterior + "</td>\n" //ultima
                            + "                    <td style=\"text-align: center\">" + dtManProx + "</td>\n" //proxima
                            + "                    <td style=\"text-align: center\">____/____/______</td>\n" //execução
                            + "                    <td style=\"text-align: center\">______</td>\n" //hrs totais
                            + "                    <td style=\"text-align: center\">______</td>\n" //hrs em carga
                            + "                </tr>");
                }
                ePar.getTextoEmail().append("</tbody>\n"
                        + "        </table> "
                        + "        <table style=\"font-family:sans-serif; font-size:10pt;\" border=\"1\" width=\"791\"> \n"
                        + "            <tbody>\n"
                        + "                <tr >\n"
                        + "                    <td style=\"text-indent:10mm; text-align: justify;\">\n"
                        + "                        Alerta: a manutenção preventiva é muito importante para a confiabilidade, durabilidade e eficiência de operação do equipamento. A não realização da mesma, poderá causar danos à unidade compressora, sistema elétrico, válvulas, motores e, consequentemente, a parada do equipamento.\n"
                        + "                    </td>\n"
                        + "                </tr>\n"
                        + "            </tbody>\n"
                        + "        </table> \n"
                        + "        <table style=\"font-family:sans-serif; font-size:10pt;\" border=\"1\" width=\"791\"> \n"
                        + "            <tbody>\n"
                        + "                <tr >\n"
                        + "                    <td style=\"text-indent:10mm; text-align: justify;\">\n"
                        + "                        Nota: falhas de operação do equipamento decorrentes da falta de manutenções preventivas, ou mau uso, não são cobertas pela garantia.\n"
                        + "                    </td>\n"
                        + "                </tr>\n"
                        + "            </tbody>\n"
                        + "        </table> "
                );
            }
        }

        parceiros = parceirosAux;

        listPecas = new ArrayList<>();
        for (Parceiro parceiro : parceiros) {
            for (Equipamento equipamento : parceiro.getEquipamentos()) {
                for (EquipamentoServico equipamentoServico : equipamento.getServicos()) {
                    for (PecaEqs pecaEqs : equipamentoServico.getEquipamentosPecas()) {
                        listPecas.add(pecaEqs);
                    }
                }
            }
        }

//        Collections.sort(listPecas, new PecaEqs());
        Map<String, PecaEqs> map = new HashMap<String, PecaEqs>();
        for (PecaEqs c : listPecas) {
            String key = c.getPeca().toString();
            if (!map.containsKey(key)) {
                map.put(c.getPeca().toString(), c);
            } else {
                PecaEqs comp = map.get(key);
                double quantity = comp.getQuantidade() + c.getQuantidade();
                comp.setQuantidade(quantity);
            }
        }
        listPecasEqs = new ArrayList<>();
        for (String key : map.keySet()) {
            PecaEqs c = map.get(key);
            listPecasEqs.add(c);
//            System.out.println("Id: " + c.getPES_ID());
//            System.out.println("PartNumber: " + c.getPeca());
//            System.out.println("Quantity: " + c.getQuantidade());
//            System.out.println("##########################################");
        }

        if (!listPecasEqs.isEmpty()) {

            setTextoPecas(new StringBuilder());
            textoPecas.append("<table border=\"1\" width=\"791\">");
            textoPecas.append("<tr>"
                    + "<th colspan=\"3\" style=\"background-color: #d4d4d4\"  >Peças a serem utilizadas entre " + sdf.format(startDate) + " e " + sdf.format(endDate) + " </th>\n"
                    + "</tr>"
                    + "<tr>"
                    + "<th>Código</th>"
                    + "<th>Descrição</th>"
                    + "<th>Quantidade</th>"
                    + "</tr>"
            );

            for (PecaEqs p : listPecasEqs) {
                textoPecas.append("<tr>");

                textoPecas.append("<td>");
                textoPecas.append(p.getPeca().getCodigo());
                textoPecas.append("</td>");
                textoPecas.append("<td>");
                textoPecas.append(p.getPeca().getDescricao());
                textoPecas.append("</td>");
                textoPecas.append("<td align=\"right\">");
                textoPecas.append(p.getQuantidade());
                textoPecas.append("</td>");

                textoPecas.append("</tr>");
            }

            textoPecas.append("</table>");
        }

        dmParcs = new ListDataModel<>(parceiros);
        return null;
    }

    public String gerarCartaManut() {
        boolean parTemEqs = false;
        boolean temEqs = false;

        List<EquipamentoServico> servicos = null;
//        loadEqs();
//        loadParceiros();

        List<EquipamentoServico> esAux = new ArrayList<>();
        esList = new ArrayList<>();
//        for (Parceiro p : getParFacade().listAll()) {
//            for (Equipamento eqp : p.getEquipamentos()) {
//                servicos = new EquipamentoServicoFacade().listByEqpIdCarta(eqp.getEQP_ID());
        try {
            servicos = getEqsFacade().listCarta();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (servicos != null) {
            for (EquipamentoServico es : servicos) {
//                esList.add(es);
                esAux.add(es);
            }
        }
//            }
//        }

        for (EquipamentoServico es : esAux) {
            while ((es.getMANUTPROXIMA().compareTo(startDate) >= 0)
                    || (es.getMANUTPROXIMAHORAS().compareTo(startDate) >= 0)
                    && (es.getMANUTPROXIMA().compareTo(endDate) <= 0
                    || es.getMANUTPROXIMAHORAS().compareTo(endDate) <= 0)) {
//            if (es.getMANUTPROXIMA().compareTo(new Date()) < 0) {
//                if (es.getMANUTPROXIMA().compareTo(startDate) >= 0 && es.getMANUTPROXIMA().compareTo(endDate) <= 0) {
//                if (es.getMANUTATUAL().compareTo(endDate) <= 0 || es.getMANUTATUAL().compareTo(endDate) <= 0) {
                es.setMANUTATUAL(es.getMANUTPROXIMA());
                es.setMANUTATUALRHORAS(es.getSRV_FREQUENCIAHORAS() + es.getMANUTATUALRHORAS());
                es.setREALIZADO(false);
                es.setID_EQS(0);
                es.setEquipamentosPecas(null);

                try {
                    if (es.getMANUTATUAL().compareTo(startDate) < 0) {
                        getEqsFacade().createEquipamentoServico(es);
//                        esList.add(es);
                        System.out.println("Criou eqs carta de manutenção " + es);
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    break;
                }
//                }
            }
        }

        esAux = new ArrayList<>();
        esList = new ArrayList<>();
        try {
            servicos = getEqsFacade().listCarta();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (servicos != null) {
            for (EquipamentoServico es : servicos) {
                esAux.add(es);
//                esList.add(es);
            }
        }

        parceirosAux = new ArrayList<>();
        parceirosAux2 = new ArrayList<>();

        for (EquipamentoServico esList1 : esAux) {
            if ((esList1.getMANUTPROXIMA().compareTo(endDate) <= 0) && (esList1.getMANUTPROXIMA().compareTo(startDate) >= 0)
                    || (esList1.getMANUTPROXIMAHORAS().compareTo(endDate) <= 0) && (esList1.getMANUTPROXIMAHORAS().compareTo(startDate) >= 0)) {
                esList.add(esList1);
                if (getFiltroParceirosSelected().isEmpty()) {
                    parceirosAux2.add(esList1.getEquipamento().getParceiro());
                } else {
                    for (Parceiro parFiltro : getFiltroParceirosSelected()) {
                        if (parFiltro.equals(esList1.getEquipamento().getParceiro())) {
                            parceirosAux2.add(esList1.getEquipamento().getParceiro());
                            break;
                        }
                    }
                }
            }
        }
        for (Parceiro p : parceirosAux2) {
            if (!parceirosAux.contains(p)) {
                //vendedor
                if (getVendedor().getFUN_ID() == 0) {
                    parceirosAux.add(p);
                } else {
                    if (getVendedor().equals(p.getVendedor())) {
                        parceirosAux.add(p);
                    }
                }

            }
        }

        for (Parceiro p : parceirosAux) {
            System.out.println(p.getPAR_RAZAO());
            p.setEquipamentos(new ArrayList<>());
            for (EquipamentoServico esAux1 : esList) {
                if (!p.getEquipamentos().contains(esAux1.getEquipamento())) {
                    p.getEquipamentos().add(esAux1.getEquipamento());
                }
            }
        }

        for (Parceiro p : parceirosAux) {
            for (Equipamento eqp : p.getEquipamentos()) {
                eqp.setServicos(new ArrayList<>());
                for (EquipamentoServico esAux1 : esList) {
                    if (!eqp.getServicos().contains(esAux1)) {
                        eqp.getServicos().add(esAux1);
                    }
                }
            }
        }

        eqsAux = new ArrayList<>();
        for (EquipamentoServico es : esList) {
            if (!eqsAux.contains(es)) {
                eqsAux.add(es);
            }
        }

        eqpAux = new ArrayList<>();
        for (EquipamentoServico es : esList) {
            if (!eqpAux.contains(es.getEquipamento())) {
                eqpAux.add(es.getEquipamento());
            }
        }

        //monta texto Carta
        for (Parceiro par : parceirosAux) {
            for (Equipamento ePar1 : par.getEquipamentos()) {
                for (Equipamento ePar : eqpAux) {
                    if (ePar.getEQP_ID() == ePar1.getEQP_ID()) {

                        MunicipioFacade munFacade = new MunicipioFacade();
                        par.setPAR_CIDADESTR(munFacade.findMunicipio(par.getPAR_CIDADE()).getMUN_NOME());
                        ePar.setTextoEmail(new StringBuilder());
                        String horimetro = "";
                        if (ePar.getEqpMedicao() != null) {
                            horimetro = ePar.getEqpMedicao().getEQM_HORASESTIMADAS() + "";
                        }
                        String logoPath = Util.getConfiguracao().getEMP_LOGO();

                        ePar.getTextoEmail().append((""
                                + "         <div style='width: 781px; '>\n"
                                + "            <div style='position:relative; top: 0px; left: 0px; margin: 0 auto;'>\n"
                                + "                <!--<h:graphicImage library=\"images\" name=\"gdhead.png\" />-->\n"
                                + "<p:graphicImage id=\"cabEmp\" value=\"#{relBean.cabImagem}\" style=\"height:100px;\" />"
                                //                        + "                <img src='/gdpc/resources/images/gdhead.png' />\n"
                                //                        + "                <img src='" + Util.getConfiguracao().getEMP_CAB() + "' />\n"
                                //                        + "                <img src='D:/Varkon/gdpc/GDPC/cab.png' />\n"
                                + "            </div>\n"
                                + "            <div style='text-align: center; width: 100%; '>\n"
                                + Util.getConfiguracao().getEMP_CABECALHO()//.replace("\n", "<br/>")
                                + "                \n"
                                + "            </div>\n"
                                //                        + "            <div style='position:absolute; top:40px; left:530px; text-align: center;  margin: 0 auto;'>\n"
                                //                        + "                <img src='/gdpc/logo.jpg' width=\"50\" />\n"
                                //                        + "            </div>\n"
                                + "        </div>"
                                //                        + "        <br/>"
                                + "\n"
                                + "        <div style=\"text-align: center; width: 791px\">\n"
                                + "            GD Preventive Control\n"
                                + "        </div>\n"
                                //                        + "        <br/>\n"
                                + "\n"
                                + "\n"
                                + "        <table width=\"791\" style=\"font-family:sans-serif; font-size:10pt;\"> \n"
                                + "            <!--<tbody>-->\n"
                                + "            <tr >\n"
                                + "                <td  style=\"font-weight: bold; text-align: right\" >Cliente</td>\n"
                                + "                <td colspan=\"5\">" + par.getPAR_RAZAO() + "</td>\n"
                                + "            </tr>\n"
                                + "            <tr>\n"
                                + "                <td  style=\"font-weight: bold; text-align: right\" >Responsável</td>\n"
                                + "                <td >" + par.getPAR_CONTATO() + "</td>\n"
                                + "                <td  style=\"font-weight: bold; text-align: right\" >Email</td>\n"
                                + "                <td colspan=\"3\" >" + par.getPAR_EMAIL() + "</td>\n"
                                + "\n"
                                + "            </tr>\n"
                                + "            <tr>\n"
                                + "                <td  style=\"font-weight: bold; text-align: right\" >Fone</td>\n"
                                + "                <td >" + par.getPAR_TELEFONE1() + "</td>\n"
                                + "                <td  style=\"font-weight: bold; text-align: right\" >Cel</td>\n"
                                + "                <td colspan=\"3\" >" + par.getPAR_TELEFONE2() + "</td>\n"
                                + "            </tr>\n"
                                + "            <tr>\n"
                                + "                <td style=\"font-weight: bold; text-align: right\" >Cidade</td>\n"
                                + "                <td >" + par.getPAR_CIDADESTR() + "</td>\n"
                                + "                <td style=\"font-weight: bold; text-align: right\" >UF</td>\n"
                                + "                <td >" + par.getPAR_UFSTR() + "</td>\n"
                                + "                <td colspan=\"2\" ></td>\n"
                                + "                <td colspan=\"2\" style=\"font-weight: bold; text-align: right\" >Data de Emissão:</td>\n"
                                + "                <td >" + sdf.format(new Date()) + "</td>\n"
                                + "            </tr>\n"
                                + "            <!--</tbody>-->\n"
                                + "        </table> \n"
                                + "        <p style=\"width: 751px; text-indent:10mm; text-align: justify; margin-left: 20px;\">\n"
                                + "            Este documento, apresenta as manutenções preventivas necessárias para realizar no Compressor de Ar Comprimido instalado na sua unidade fabril, Modelo: " + ePar.getModelo().getMOD_NOME() + " N/S: " + ePar.getEQP_SERIE() + "  entre o período de " + sdf.format(startDate) + " à " + sdf.format(endDate) + ". O acompanhamento correto das manutenções preventivas, conforme o manual do equipamento, proporcionam a vigência das garantias do fabricante e uma maior confiabilidade de operação.\n"
                                + "        </p>\n"
                                + "        <p style=\"width: 751px; text-indent:10mm; text-align: justify; margin-left: 20px;\">\n"
                                + "            Os campos do atual regime de trabalho, as datas dos serviços previstos e os respectivos horímetros dos compressores, deverão ser preenchidos e retornados via e-mail / fone ao nosso departamento técnico, para que seja realizado a atualização dos dados do equipamento no nosso sistema.\n"
                                + "        </p>\n"
                                + "\n"
                                + "        <p style=\"border:1px solid; border-color: black; width: 781px; margin-left: 4px;\">\n"
                                + "            <p style=\"font-weight: bold ; text-align: center; width: 791px\"> Atualização/Dúvidas :</p>\n"
                                + "            <p style=\"text-align: center; width: 791px\"> " + Util.getConfiguracao().getRESPONSAVELCARTAMANUTENCAO() + "</p>\n"
                                //                        + "            <table style=\"font-family:sans-serif; font-size:10pt; text-align: center; width: 791px\">\n"
                                //                        + "                <tbody>\n"
                                //                        + "                    <tr>\n"
                                //                        + "                        <td>"
                                //                        + Util.getConfiguracao().getRESPONSAVELCARTAMANUTENCAO()
                                //                        + "</td>\n"
                                //                        //                        + "                        <td>Att. Carlos Eduardo Maier                    </td>\n"
                                //                        //                        + "                        <td>Fone : (11)3109-1100                         </td>\n"
                                //                        //                        + "                        <td>Email: gdpreventivecontrol@gardnerdenver.com </td>\n"
                                //                        + "                    </tr>\n"
                                //                        + "                </tbody>\n"
                                //                        + "            </table>\n"
                                + "        </p> "
                                + "\n"
                                + "        <table style=\"font-family:sans-serif; font-size:10pt;\" border=\"1\" width=\"791\"> \n"
                                + "            <tbody>\n"
                                + "                <tr >\n"
                                + "                    <th colspan=\"4\" style=\"background-color: #d4d4d4\"  >Dados do Equipamento</th>\n"
                                + "                </tr>\n"
                                + "                <tr >\n"
                                + "                    <td colspan=\"4\">Dados do sistema (registrados no GD Preventive Control).</td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td colspan=\"2\">" + ePar.getCategoria().getCAT_NOME() + " modelo " + ePar.getModelo().getMOD_NOME() + "</td>\n"
                                + "                    <td>Série: " + ePar.getEQP_SERIE() + "</td>\n"
                                + "                    <td>Data de Partida: " + sdf.format(ePar.getEQP_DATAPARTIDA()) + "</td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td colspan=\"2\">Horímetro estimado na data da emissão: " + horimetro + "</td>\n"
                                + "                    <td colspan=\"2\">Regime de Trabalho: " + ePar.getEQP_REGIMEMEDIA() + " horas diárias médias</td>\n"
                                + "                </tr>\n"
                                + "            </tbody>\n"
                                + "        </table> \n"
                                //                        + "        <br/> \n"
                                + "        <table style=\"font-family:sans-serif; font-size:10pt;\" border=\"1\" width=\"791\"> \n"
                                + "            <tbody>\n"
                                + "                <tr >\n"
                                + "                    <th colspan=\"3\" style=\"background-color: #d4d4d4\">Leituras do Equipamento</th>\n"
                                + "                    <!--<td></td>-->\n"
                                + "                </tr> \n"
                                + "                <tr >\n"
                                + "                    <td colspan=\"3\" >Dados a serem informados pelo responsável (favor, preencher).</td>\n"
                                + "                </tr> \n"
                                + "                <tr >\n"
                                + "                    <td>Horas Totais: ________</td>\n"
                                + "                    <td>Horas em Carga: ________</td>\n"
                                + "                    <td>Data de Leitura: ____/____/______</td>\n"
                                + "                </tr> \n"
                                + "                <tr >\n"
                                + "                    <td colspan=\"3\">Regime de trabalho: Operação semanal de ____ horas, em ____ dias de funcionamento efetivo.</td>\n"
                                + "                </tr> \n"
                                + "            </tbody>\n"
                                + "        </table> \n"));

                        ePar.getTextoEmail().append(""
                                + "        <table style=\"font-family:sans-serif; font-size:10pt;\" border=\"1\" width=\"791\"> \n"
                                + "            <tbody>\n"
                                + "                <tr>\n"
                                + "                    <th colspan=\"6\" style=\"background-color: #d4d4d4\" >Manutenções a Realizar</th>\n"
                                + "                </tr>\n"
                                + "                <tr align=\"center\" >\n"
                                + "                    <td rowspan=\"2\" >Serviços</td>\n"
                                + "                    <!--                </tr>\n"
                                + "                                    <tr>-->\n"
                                + "                    <td colspan=\"2\" >Manutenção</td>\n"
                                + "                    <td rowspan=\"2\" >Data de Execução</td>\n"
                                + "                    <td rowspan=\"2\" >Horas Totais</td>\n"
                                + "                    <td rowspan=\"2\" >Horas de Carga</td>\n"
                                + "                </tr>\n"
                                + "                <tr align=\"center\">\n"
                                + "                    <td>Última</td>\n"
                                + "                    <td>Próxima</td>\n"
                                + "                </tr>");
                        for (EquipamentoServico es0 : ePar.getServicos()) {
                            for (EquipamentoServico es : eqsAux) {
                                if (es.equals(es0)) {
                                    String dtManAnterior = "";
                                    String dtManProx = "";
                                    if (es.getMANUTANTERIOR() != null) {
                                        dtManAnterior = sdf.format(es.getMANUTANTERIOR());
                                    }
                                    if (es.getMANUTPROXIMA() != null && es.getMANUTPROXIMAHORAS() != null) {
//                        if (es.getMANUTPROXIMA().compareTo(es.getMANUTPROXIMAHORAS()) > 0) {
//                            dtManProx = sdf.format(es.getMANUTPROXIMA());
//                        } else if (es.getMANUTPROXIMA().compareTo(es.getMANUTPROXIMAHORAS()) < 0) {
//                            dtManProx = sdf.format(es.getMANUTPROXIMAHORAS());
//                        } else {
//                            dtManProx = sdf.format(es.getMANUTPROXIMA());
//                        }

                                        if ((es.getMANUTPROXIMA().compareTo(startDate) >= 0) && (es.getMANUTPROXIMA().compareTo(endDate) <= 0)) {
                                            dtManProx = sdf.format(es.getMANUTPROXIMA());
                                        }
                                        if ((es.getMANUTPROXIMAHORAS().compareTo(startDate) >= 0) && (es.getMANUTPROXIMAHORAS().compareTo(endDate) <= 0)) {
                                            dtManProx = sdf.format(es.getMANUTPROXIMAHORAS());
                                        }

                                    }

                                    ePar.getTextoEmail().append("<tr>\n"
                                            + "                    <td>" + es.getServico().getSRV_DESCRICAO() + "</td>\n"
                                            + "                    <td style=\"text-align: center\">" + dtManAnterior + "</td>\n" //ultima
                                            + "                    <td style=\"text-align: center\">" + dtManProx + "</td>\n" //proxima
                                            + "                    <td style=\"text-align: center\">____/____/______</td>\n" //execução
                                            + "                    <td style=\"text-align: center\">______</td>\n" //hrs totais
                                            + "                    <td style=\"text-align: center\">______</td>\n" //hrs em carga
                                            + "                </tr>");
                                }
                            }
                        }

                        ePar.getTextoEmail().append("</tbody>\n"
                                + "        </table> "
                                + "        <table style=\"font-family:sans-serif; font-size:10pt;\" border=\"1\" width=\"791\"> \n"
                                + "            <tbody>\n"
                                + "                <tr >\n"
                                + "                    <td style=\"text-indent:10mm; text-align: justify;\">\n"
                                + "                        Alerta: a manutenção preventiva é muito importante para a confiabilidade, durabilidade e eficiência de operação do equipamento. A não realização da mesma, poderá causar danos à unidade compressora, sistema elétrico, válvulas, motores e, consequentemente, a parada do equipamento.\n"
                                + "                    </td>\n"
                                + "                </tr>\n"
                                + "            </tbody>\n"
                                + "        </table> \n"
                                + "        <table style=\"font-family:sans-serif; font-size:10pt;\" border=\"1\" width=\"791\"> \n"
                                + "            <tbody>\n"
                                + "                <tr >\n"
                                + "                    <td style=\"text-indent:10mm; text-align: justify;\">\n"
                                + "                        Nota: falhas de operação do equipamento decorrentes da falta de manutenções preventivas, ou mau uso, não são cobertas pela garantia.\n"
                                + "                    </td>\n"
                                + "                </tr>\n"
                                + "            </tbody>\n"
                                + "        </table> "
                        );
                    }
                }
            }
        }

        parceiros = parceirosAux;

        listPecas = new ArrayList<>();
        for (Parceiro parceiro : parceiros) {
            for (Equipamento equipamento : parceiro.getEquipamentos()) {
                for (EquipamentoServico equipamentoServico : equipamento.getServicos()) {
                    for (PecaEqs pecaEqs : equipamentoServico.getEquipamentosPecas()) {
                        listPecas.add(pecaEqs);
                    }
                }
            }
        }

//        Collections.sort(listPecas, new PecaEqs());
        Map<String, PecaEqs> map = new HashMap<String, PecaEqs>();
        for (PecaEqs c : listPecas) {
            String key = c.getPeca().toString();
            if (!map.containsKey(key)) {
                map.put(c.getPeca().toString(), c);
            } else {
                PecaEqs comp = map.get(key);
                double quantity = comp.getQuantidade() + c.getQuantidade();
                comp.setQuantidade(quantity);
            }
        }
        listPecasEqs = new ArrayList<>();
        for (String key : map.keySet()) {
            PecaEqs c = map.get(key);
            listPecasEqs.add(c);
//            System.out.println("Id: " + c.getPES_ID());
//            System.out.println("PartNumber: " + c.getPeca());
//            System.out.println("Quantity: " + c.getQuantidade());
//            System.out.println("##########################################");
        }

        if (!listPecasEqs.isEmpty()) {

            setTextoPecas(new StringBuilder());
            textoPecas.append("<table border=\"1\" width=\"791\">");
            textoPecas.append("<tr>"
                    + "<th colspan=\"3\" style=\"background-color: #d4d4d4\"  >Peças a serem utilizadas entre " + sdf.format(startDate) + " e " + sdf.format(endDate) + " </th>\n"
                    + "</tr>"
                    + "<tr>"
                    + "<th>Código</th>"
                    + "<th>Descrição</th>"
                    + "<th>Quantidade</th>"
                    + "</tr>"
            );

            for (PecaEqs p : listPecasEqs) {
                textoPecas.append("<tr>");

                textoPecas.append("<td>");
                textoPecas.append(p.getPeca().getCodigo());
                textoPecas.append("</td>");
                textoPecas.append("<td>");
                textoPecas.append(p.getPeca().getDescricao());
                textoPecas.append("</td>");
                textoPecas.append("<td align=\"right\">");
                textoPecas.append(p.getQuantidade());
                textoPecas.append("</td>");

                textoPecas.append("</tr>");
            }

            textoPecas.append("</table>");
        }

//        dmParcs = new ListDataModel<>(parceiros);
        dmParcs = new ListDataModel<>(parceirosAux);
        return null;
    }

    public void detalheCarta() {
        Parceiro par = (Parceiro) dmParcs.getRowData();
        displayInfoMessageToUser(par.getPAR_RAZAO());
    }

    public void sendEmail() {
        if (selectedParceiros.isEmpty()) {
            displayInfoMessageToUser("Selecione algum cliente na lista");
            return;
        }
        Configuracao configuracao = Util.getConfiguracao();
        String email;
        String senha;
        String smtp;
        String porta;
        int seguranca;
        if (configuracao.isEMP_EMAILPROPRIO()) {
            email = configuracao.getEMP_EMAILENVIO();
            senha = configuracao.getEMP_SENHAEMAIL();
            smtp = configuracao.getEMP_SMTP();
            porta = configuracao.getEMP_PORTA();
            seguranca = configuracao.getEMP_SEGURANCA();
        } else {
            email = "compressor.compair@gmail.com";
            senha = "compair2014";
            smtp = "smtp.gmail.com";
            porta = "587";
            seguranca = 1;
        }
        final String Mail = email;
        final String Password = senha;

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");//define protocolo de envio como SMTP
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtp);
        props.put("mail.smtp.auth", true);//ativa autenticacao
        props.put("mail.smtp.user", Mail);//usuario ou seja, a conta que esta enviando o email
        props.put("mail.debug", true);
        props.put("mail.smtp.port", porta);//porta
        props.put("mail.smtp.socketFactory.port", porta);//mesma porta para o socket
        props.put("mail.smtp.ssl.trust", smtp);

        // | Automático "1" | SSL "2" | TLS 3 |
        if (seguranca == 2) {
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        }
        props.put("mail.smtp.socketFactory.fallback", false);

        Authenticator autenticador = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Mail, Password);
            }
        };

        try {
            for (Parceiro parceiro : selectedParceiros) {

                for (Equipamento eq : parceiro.getEquipamentos()) {
                    StringBuilder textoEmail = eq.getTextoEmail();
                    String temp = textoEmail.toString();
                    temp = temp.replace("<img src='/gdpc/logo.jpg' width=\"50\" />", "");
                    temp = temp.replace("<img src='/gdpc/resources/images/gdhead.png' />", "");
                    textoEmail = new StringBuilder();
                    textoEmail.append(temp);

//                  textoPecas.toString().replace(porta, textoPecas).
                    MimeBodyPart mbp1 = new MimeBodyPart();
                    mbp1.setContent(textoEmail.toString(), "text/html; charset=utf-8");
                    Multipart mp = new MimeMultipart();
                    mp.addBodyPart(mbp1);
                    Session session = Session.getInstance(props, autenticador);
                    Message msg = new MimeMessage(session);
                    msg.setFrom(new InternetAddress(email));
//                    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(parceiro.getPAR_EMAILCONTATO()));
//                    msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(parceiro.getPAR_EMAILCONTATO()));
                    if (parceiro.getPAR_MULTMAILS().indexOf(',') > 0) {
                        msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(parceiro.getPAR_MULTMAILS()));
                    } else {
                        msg.setRecipient(Message.RecipientType.CC, new InternetAddress(parceiro.getPAR_MULTMAILS()));
                    }

                    if (!configuracao.getEMP_EMAILRESPOSTACM().isEmpty()) {
                        msg.setReplyTo(new InternetAddress[]{new InternetAddress(configuracao.getEMP_EMAILRESPOSTACM())});
                    } else if (!configuracao.isEMP_EMAILPROPRIO()) {
                        msg.setReplyTo(new InternetAddress[]{new InternetAddress(configuracao.getEMP_EMAIL())});
                    }

                    msg.setSentDate(new Date());
                    msg.setSubject("GD Preventive Control " + eq.getModelo().getMOD_NOME() + " N/S: " + eq.getEQP_SERIE());
//            msg.setContent(String.valueOf(textoPecas), "text/html");

                    msg.setContent(mp);

                    Transport.send(msg);
                    System.out.println("Ok email enviado");
                    keepDialogOpen();

                }
                displayInfoMessageToUser("Email enviado com sucesso!");

            }

//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Email Enviado com sucesso!", "");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (MessagingException mex) {
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao enviar Email!", "");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            mex.printStackTrace();
            System.out.println("Erro: " + mex.getMessage() + " .. " + mex.getCause());
            displayErrorMessageToUser("Falha ao enviar Email");
        }

//        return null;
    }

    private void loadEqs() {
        eqs = getEqsFacade().listAll();
    }

    private void loadParceiros() {
        List<Parceiro> ls = getParFacade().listAll();
        List<Parceiro> lstemp = getParFacade().listAll();

        for (Parceiro parceiro : ls) {
            if (getEstado() != null && getEstado().getJEST_ID() != parceiro.getPAR_UF()) {
                lstemp.remove(parceiro);
            }
            if (getMunicipio() != null && getMunicipio().getMUN_ID() != parceiro.getPAR_CIDADE()) {
                lstemp.remove(parceiro);
            }
        }

        parceiros = lstemp;

    }

    public void resetParceiroCartaDetail() {
        parceiroDetail = new Parceiro();
        setShowCartaDlg(false);
    }

//    public String chamaCarta() {
//        return
//    }
    public EquipamentoServicoFacade getEqsFacade() {
        if (eqsFacade == null) {
            eqsFacade = new EquipamentoServicoFacade();
        }

        return eqsFacade;
    }

    public List<Parceiro> getParceiros() {
        return parceiros;
    }

    public void setParceiros(List<Parceiro> parceiros) {
        this.parceiros = parceiros;
    }

    public List<Parceiro> getSelectedParceiros() {
        return selectedParceiros;
    }

    public void setSelectedParceiros(List<Parceiro> selectedParceiros) {
        this.selectedParceiros = selectedParceiros;
    }

    public List<Parceiro> getFiltroParceirosSelected() {
        if (filtroParceirosSelected == null || filtroParceirosSelected.isEmpty()) {
            filtroParceirosSelected = new ArrayList<>();
        }
        return filtroParceirosSelected;
    }

    public void setFiltroParceirosSelected(List<Parceiro> filtroParceirosSelected) {
        this.filtroParceirosSelected = filtroParceirosSelected;
    }

    public List<Parceiro> getParceirosAux() {
        return parceirosAux;
    }

    public void setParceirosAux(List<Parceiro> parceirosAux) {
        this.parceirosAux = parceirosAux;
    }

    public ParceiroFacade getParFacade() {
//        if (parFacade == null) {
        parFacade = new ParceiroFacade();
//        }

        return parFacade;
    }

    public void setParFacade(ParceiroFacade parFacade) {
        this.parFacade = parFacade;
    }

    public void showDetailCarta() {
        setShowCartaDlg(true);
    }
//    public void resetPerson() {
//        person = new Person();
//    }
//    public Dog getDog() {
//        if (dog == null) {
//            dog = new Dog();
//        }
//
//        return dog;
//    }
//
//    public void setDog(Dog dog) {
//        this.dog = dog;
//    }
//
//    public void resetDog() {
//        dog = new Dog();
//    }
//    private void reloadPersonWithDogs() {
//        personWithDogs = getPersonFacade().findPersonWithAllDogs(person.getId());
//    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Parceiro getParceiroDetail() {

        return parceiroDetail;
    }

    public void setParceiroDetail(Parceiro parceiroDetail) {
        this.parceiroDetail = parceiroDetail;
    }

    public Parceiro getFiltroParceiro() {
        return filtroParceiro;
    }

    public void setFiltroParceiro(Parceiro filtroParceiro) {
        this.filtroParceiro = filtroParceiro;
    }

    public List<Parceiro> getParceirosAux2() {
        return parceirosAux2;
    }

    public void setParceirosAux2(List<Parceiro> parceirosAux2) {
        this.parceirosAux2 = parceirosAux2;
    }

    public List<EquipamentoServico> getEqs() {
        return eqs;
    }

    public void setEqs(List<EquipamentoServico> eqs) {
        this.eqs = eqs;
    }

    public List<EquipamentoServico> getEqsAux() {
        return eqsAux;
    }

    public void setEqsAux(List<EquipamentoServico> eqsAux) {
        this.eqsAux = eqsAux;
    }

    public boolean isShowCartaDlg() {
        return showCartaDlg;
    }

    public void setShowCartaDlg(boolean showCartaDlg) {
        this.showCartaDlg = showCartaDlg;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Funcionario getVendedor() {
        if (vendedor == null) {
            vendedor = new Funcionario();
        }
        return vendedor;
    }

    public void setVendedor(Funcionario vendedor) {
        this.vendedor = vendedor;
    }

    public List<Municipio> getListMunicipio() {
        if (estado == null) {
//            estado = new Estado(12, "AC", "Acre");
            estado = new Estado();
        }
        MunicipioFacade munFacade = new MunicipioFacade();
        listMunicipio = munFacade.findListMunicipioByUF(estado.getJEST_ID());
        if (listMunicipio == null) {
            listMunicipio = new ArrayList<>();
        }
        return listMunicipio;
    }

    public void setListMunicipio(List<Municipio> listMunicipio) {
        this.listMunicipio = listMunicipio;
    }

    public DataModel<Parceiro> getDmParcs() {
        if (dmParcs == null) {
            dmParcs = new ArrayDataModel<>();
        }
        return dmParcs;
    }

    public void setDmParcs(DataModel<Parceiro> dmParcs) {
        this.dmParcs = dmParcs;
    }

    public int getHoraTotaisMin() {
        return horaTotaisMin;
    }

    public void setHoraTotaisMin(int horaTotaisMin) {
        this.horaTotaisMin = horaTotaisMin;
    }

    public int getHoraTotaisMax() {
        return horaTotaisMax;
    }

    public void setHoraTotaisMax(int horaTotaisMax) {
        this.horaTotaisMax = horaTotaisMax;
    }

    public Modelo getModelo() {
        if (modelo == null) {
            modelo = new Modelo();
        }
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public List<Modelo> getModelos() {
        if (modelos == null) {
            modelos = new ArrayList<>();
        }
        return modelos;
    }

    public void setModelos(List<Modelo> modelos) {
        this.modelos = modelos;
    }

    public boolean isMostraPecas() {
        return mostraPecas;
    }

    public void setMostraPecas(boolean mostraPecas) {
        this.mostraPecas = mostraPecas;
    }

    public List<PecaEqs> getListPecas() {
        if (listPecas == null) {
            listPecas = new ArrayList<>();
        }
        return listPecas;
    }

    public void setListPecas(List<PecaEqs> listPecas) {
        this.listPecas = listPecas;
    }

    public List<PecaEqs> getListPecasEqs() {
        return listPecasEqs;
    }

    public void setListPecasEqs(List<PecaEqs> listPecasEqs) {
        this.listPecasEqs = listPecasEqs;
    }

    public StringBuilder getTextoPecas() {
        if (textoPecas == null) {
            textoPecas = new StringBuilder();
        }
        return textoPecas;
    }

    public void setTextoPecas(StringBuilder textoPecas) {
        this.textoPecas = textoPecas;
    }

    public StreamedContent getCabImagem() {

        Configuracao configuracao = Util.getConfiguracao();
        if (configuracao.getEMP_CAB() != null && !"".equals(configuracao.getEMP_CAB())) {
            File file = new File(configuracao.getEMP_CAB());
            String ext = configuracao.getEMP_CAB().substring((configuracao.getEMP_CAB().length() - 3));
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(file);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(RelatorioBean.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            cabImagem = new DefaultStreamedContent(inputStream, "image/" + ext, configuracao.getEMP_LOGO());
        }
        return cabImagem;
    }

    public void setCabImagem(StreamedContent cabImagem) {
        this.cabImagem = cabImagem;
    }

    public StreamedContent getLogoImagem() {
        return logoImagem;
    }

    public void setLogoImagem(StreamedContent logoImagem) {
        this.logoImagem = logoImagem;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }
}
