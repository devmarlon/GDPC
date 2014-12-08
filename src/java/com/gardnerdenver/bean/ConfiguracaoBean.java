package com.gardnerdenver.bean;

import com.gardnerdenver.facade.ConfiguracaoFacade;
import com.gardnerdenver.facade.MunicipioFacade;
import com.gardnerdenver.model.Configuracao;
import com.gardnerdenver.model.Municipio;
import com.gardnerdenver.util.Util;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@SessionScoped
@ManagedBean(name = "configuracaoBean")
public class ConfiguracaoBean extends AbstractMB implements Serializable {

//    public static final String INJECTION_NAME = "#{configuracaoBean}";
    private static final long serialVersionUID = 1L;

    private String tipoPessoa = "F";
    private String maskCPFCNPJ = "999.999.999-99";
    private String labelCPFCNPJ = "CPF";
    private String logoLabel = "";
    private String cabLabel = "";

    private UploadedFile logo;
    private StreamedContent logoImagem;
    private InputStream logoIS = null;
    private UploadedFile cab;
    private StreamedContent cabImagem;
    private InputStream cabIS = null;

    private boolean disCredSimples = false;
//    private boolean mailProprio;

    private ConfiguracaoFacade cfgFacade;

    private Configuracao configuracao;

    private List<Municipio> listMunicipioEMP = new ArrayList<>();

    public ConfiguracaoBean() {
        if (UserItemFactoryBean.banco.equalsIgnoreCase("gdpc")) {
            configuracao = Util.getGdpcConfiguracao();
        } else {
            configuracao = Util.getConfiguracao();
        }
    }

//    @PostConstruct
    public void showConf() throws FileNotFoundException {
//        if (configuracao == null || configuracao.getCFG_ID() == 0) {
        if (UserItemFactoryBean.banco.equalsIgnoreCase("gdpc")) {
            configuracao = Util.getGdpcConfiguracao();
        } else {
            configuracao = Util.getConfiguracao();
        }
        MunicipioFacade munFacade = new MunicipioFacade();
        configuracao.setMunicipio(munFacade.findMunicipio(configuracao.getCID_IDEMP()));
        tipoPessoa = configuracao.getEMP_TIPOPESSOA();
        getLogoImagem();
        getCabImagem();
//        mailProprio = configuracao.isEMP_EMAILPROPRIO();
//        }
        redirect("/pages/protected/distributor/configuracao.xhtml");
    }

    public void testeEmail() {
        salvar(null);
//        String retorno = "erro";

        final String usuario = configuracao.getEMP_USUARIO();
        final String email = configuracao.getEMP_EMAILENVIO();
        final String senha = configuracao.getEMP_SENHAEMAIL();
        final String smtp = configuracao.getEMP_SMTP();
        final String porta = configuracao.getEMP_PORTA();
        final int seguranca = configuracao.getEMP_SEGURANCA();

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");//define protocolo de envio como SMTP
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtp);
        props.put("mail.smtp.auth", true);//ativa autenticacao
        props.put("mail.smtp.user", email);//usuario ou seja, a conta que esta enviando o email
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
                return new PasswordAuthentication(email, senha);
            }
        };

        StringBuilder textoEmail = new StringBuilder();
        textoEmail.append(("<p style='font-family: Arial'>"
                + "<small style='color:#999999;'>*** Configuração de Email realizada com sucesso ***</small>"
                + "<br/><br/><br/>"
                + "Prezado(a), " + configuracao.getEMP_FANTASIA()
                + "<br/>"
                + "Parabéns você configurou com sucesso seu e-mail nas Configurações de Gestorweb.com.br!<br/><br/>"
                + "<a href='http://www.gestorweb.com.br' alt='Sistema de Gestão Online'><img src='http://gestorweb.com.br/images/gw-email.png' /></a>"
                + "</p>"));

        try {
            Session session = Session.getInstance(props, autenticador);
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(email));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            msg.setSentDate(new Date());
            msg.setSubject("Email de Teste");
            msg.setContent(String.valueOf(textoEmail), "text/html");
            Transport.send(msg);

//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Email Enviado com sucesso!", "");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            displayInfoMessageToUser("Email Enviado com sucesso!");
        } catch (MessagingException mex) {
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao enviar Email!", "");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            displayErrorMessageToUser("Erro ao enviar Email!");
            //e.printStackTrace();
            mex.printStackTrace();
            System.out.println("Erro: " + mex.getMessage() + " .. " + mex.getCause());
        }
//        return retorno;
        redirect("/pages/protected/distributor/configuracao.xhtml");
    }

    public void salvar(ActionEvent ae) {
//        configuracao.setEMP_EMAILPROPRIO(mailProprio);
        configuracao.setCID_IDEMP(configuracao.getMunicipio().getMUN_ID());

        configuracao.setEMP_SMTP(configuracao.getEMP_SMTP().trim());
        configuracao.setEMP_TIPOPESSOA(tipoPessoa);

        try {
            getCfgFacade().updateConfig(configuracao);
            displayInfoMessageToUser("Configuração salva com sucesso.");
//            showConf();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            displayErrorMessageToUser("Não foi possível salvar as conficurações.");
        }
        if (ae != null) {
            redirect("/pages/protected/distributor/configuracao.xhtml");
        }
//        return null;
    }

    public void isCPFCNPJ() {
        switch (tipoPessoa) {
            case "F":
                maskCPFCNPJ = "999.999.999-99";
                labelCPFCNPJ = "CPF";
                break;
            case "J":
                maskCPFCNPJ = "99.999.999/9999-99";
                labelCPFCNPJ = "CNPJ";
                break;
        }
    }

    public void uploadLogo() throws IOException {
        if (logo != null && !logo.getFileName().trim().isEmpty()) {
            logoIS = logo.getInputstream();
            if (configuracao.getEMP_LOGO() != null && !"".equals(configuracao.getEMP_LOGO())) {
                logoImagem = null;//logoImagem usa o caminho;
                File destino = new File(configuracao.getEMP_LOGO());
                String dest = destino.getAbsolutePath();
                try {
                    if (!dest.contains("GDPC\\logo.png")) {
                        destino.delete();
                    }
//                        cfgFacade.updateConfig(configuracao);

                } catch (Exception e) {
                    throw new RuntimeException("Erro ao deletar Logotipo", e);
                }
                if (salvaLogo()) {

                }
            }
            getLogoImagem();
            salvar(null);
        }
    }

    public void uploadCab() throws IOException {
        if (cab != null) {
            cabIS = cab.getInputstream();
            if (configuracao.getEMP_CAB() != null && !"".equals(configuracao.getEMP_CAB())) {
                cabImagem = null;//logoImagem usa o caminho;;;
                File destino = new File(configuracao.getEMP_CAB());
                String dest = destino.getAbsolutePath();
                try {
                    if (!dest.contains("GDPC\\cab.png")) {
                        cabImagem = null;
                        destino.delete();
                    }
//                        cfgFacade.updateConfig(configuracao);

                } catch (Exception e) {
                    throw new RuntimeException("Erro ao deletar cabeçalho", e);
                }
                if (salvaCab()) {

                }
            }
            getCabImagem();
            salvar(null);

        }
    }

    public boolean salvaLogo() throws IOException {
        boolean save = false;
        //int width = 0;
        //int height = 0;
        String caminho = "";
        String nome = "";
        String ext = "";
        long tam = 0;

        BufferedImage imagem = ImageIO.read(logoIS);
        //List<Integer> widHeig = redimensionarLogo60(imagem);
        //width = widHeig.get(0);
        //height = widHeig.get(1);
        nome = logo.getFileName();
        ext = logo.getFileName().substring(logo.getFileName().length() - 3);

        caminho = Util.getCaminho() + UserItemFactoryBean.banco + "/logo/" + nome;

        File dirNfeE = new File(caminho);
        if (dirNfeE.mkdirs()) {
            System.out.println("Diretorio criado com sucesso!");
        } else {
            System.out.println("Erro ao criar diretorio! " + dirNfeE);
        }

        tam = logo.getSize();
        System.out.println("tamanho da imagem: " + tam);//256322
        if (tam > 500000) {
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na Imagem!", "Não foi possível adicionar o logo! Imagem maior que 500 Kb");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return false;
        }

        try {
// BufferedImage new_logo = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
// Graphics2D g = new_logo.createGraphics();
// g.drawImage(imagem, 0, 0, width, height, null);
            ImageIO.write(imagem, ext, new File(caminho));
            save = true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Imagem!", "Não foi possível adicionar o logo!");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            save = false;
        }

        if (save) {
            configuracao.setEMP_LOGO(caminho);
        }
        return save;
    }

    public boolean salvaCab() throws IOException {
        boolean save = false;
        //int width = 0;
        //int height = 0;
        String caminho = "";
        String nome = "";
        String ext = "";
        long tam = 0;

        BufferedImage imagem = ImageIO.read(cabIS);
        //List<Integer> widHeig = redimensionarLogo60(imagem);
        //width = widHeig.get(0);
        //height = widHeig.get(1);
        nome = cab.getFileName();
        ext = cab.getFileName().substring(cab.getFileName().length() - 3);

        caminho = Util.getCaminho() + UserItemFactoryBean.banco + "/cab/" + nome;

        File dirNfeE = new File(caminho);
        if (dirNfeE.mkdirs()) {
            System.out.println("Diretorio criado com sucesso!");
        } else {
            System.out.println("Erro ao criar diretorio! " + dirNfeE);
        }

        tam = cab.getSize();
        System.out.println("tamanho da imagem: " + tam);//256322
        if (tam > 500000) {
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na Imagem!", "Não foi possível adicionar o logo! Imagem maior que 500 Kb");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return false;
        }

        try {
// BufferedImage new_logo = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
// Graphics2D g = new_logo.createGraphics();
// g.drawImage(imagem, 0, 0, width, height, null);
            ImageIO.write(imagem, ext, new File(caminho));
            save = true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Imagem!", "Não foi possível adicionar o logo!");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            save = false;
        }

        if (save) {
            configuracao.setEMP_CAB(caminho);
        }
        return save;
    }

    public StreamedContent getLogoImagem() throws FileNotFoundException {
        logoImagem = null;
        if (configuracao.getEMP_LOGO() != null && !"".equals(configuracao.getEMP_LOGO())) {
            File file = new File(configuracao.getEMP_LOGO());
            String ext = configuracao.getEMP_LOGO().substring((configuracao.getEMP_LOGO().length() - 3));
            if (file.exists()) {
                InputStream inputStream = new FileInputStream(file);
                logoImagem = new DefaultStreamedContent(inputStream, "image/" + ext, configuracao.getEMP_LOGO());
            }
////            logoImagem = new DefaultStreamedContent(inputStream, "image/jpeg", configuracao.getEMP_LOGO());
//            logoImagem = new DefaultStreamedContent(inputStream, "image/" + ext);
        }

        getLogoLabel();
        return logoImagem;
    }

    public void setLogoImagem(StreamedContent logoImagem) {
        this.logoImagem = logoImagem;
    }

    public String getLogoLabel() {

        if (logoImagem != null) {
            StringBuilder cert = new StringBuilder(logoImagem.getName());
            String nomeCert = new String(cert.reverse());
            if (nomeCert.indexOf("/") > 0) {
                nomeCert = nomeCert.substring(0, nomeCert.indexOf("/"));
                cert = new StringBuilder(nomeCert);
                nomeCert = new String(cert.reverse());
                logoLabel = nomeCert;
            } else {
                logoLabel = "Não há nenhum logo para a empresa!";
            }
        } else {
            logoLabel = "Não há nenhum logo para a empresa!";
        }

        return logoLabel;
    }

    public void setLogoLabel(String logoLabel) {
        this.logoLabel = logoLabel;
    }

    public String getCabLabel() {

        if (cabImagem != null) {
            StringBuilder cert = new StringBuilder(cabImagem.getName());
            String nomeCert = new String(cert.reverse());
            if (nomeCert.indexOf("/") > 0) {
                nomeCert = nomeCert.substring(0, nomeCert.indexOf("/"));
                cert = new StringBuilder(nomeCert);
                nomeCert = new String(cert.reverse());
                cabLabel = nomeCert;
            } else {
                cabLabel = "Não há nenhum cabeçalho para a empresa!";
            }
        } else {
            cabLabel = "Não há nenhum cabeçalho para a empresa!";
        }

        return cabLabel;
    }

    public void setCabLabel(String cabLabel) {
        this.cabLabel = cabLabel;
    }

    public Configuracao getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(Configuracao configuracao) {
        this.configuracao = configuracao;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getMaskCPFCNPJ() {
        return maskCPFCNPJ;
    }

    public void setMaskCPFCNPJ(String maskCPFCNPJ) {
        this.maskCPFCNPJ = maskCPFCNPJ;
    }

    public String getLabelCPFCNPJ() {
        return labelCPFCNPJ;
    }

    public void setLabelCPFCNPJ(String labelCPFCNPJ) {
        this.labelCPFCNPJ = labelCPFCNPJ;
    }

    public ConfiguracaoFacade getCfgFacade() {
        cfgFacade = new ConfiguracaoFacade();
        return cfgFacade;
    }

    public void setCfgFacade(ConfiguracaoFacade cfgFacade) {
        this.cfgFacade = cfgFacade;
    }

    public UploadedFile getLogo() {
        return logo;
    }

    public void setLogo(UploadedFile logo) {
        this.logo = logo;
    }

    public UploadedFile getCab() {
        return cab;
    }

    public void setCab(UploadedFile cab) {
        this.cab = cab;
    }

    public InputStream getLogoIS() {
        return logoIS;
    }

    public void setLogoIS(InputStream logoIS) {
        this.logoIS = logoIS;
    }

    public List<Municipio> getListMunicipioEMP() {
        MunicipioFacade munFacade = new MunicipioFacade();
        if (configuracao.getEstado() != null) {
            listMunicipioEMP = munFacade.findListMunicipioByUF(configuracao.getEstado().getJEST_ID());
        }
        return listMunicipioEMP;
    }

    public void setListMunicipioEMP(List<Municipio> listMunicipioEMP) {
        this.listMunicipioEMP = listMunicipioEMP;
    }

    public boolean isDisCredSimples() {
        disCredSimples = true;
        if (configuracao.getEMP_REGIME() < 3) {
            disCredSimples = false;
        }
        return disCredSimples;
    }

    public void setDisCredSimples(boolean disCredSimples) {
        this.disCredSimples = disCredSimples;
    }

    public StreamedContent getCabImagem() throws FileNotFoundException {
        cabImagem = null;
        if (configuracao.getEMP_CAB() != null && !"".equals(configuracao.getEMP_CAB())) {
            File file = new File(configuracao.getEMP_CAB());
            String ext = configuracao.getEMP_CAB().substring((configuracao.getEMP_CAB().length() - 3));
            if (file.exists()) {
                InputStream inputStream = new FileInputStream(file);
                cabImagem = new DefaultStreamedContent(inputStream, "image/" + ext, configuracao.getEMP_CAB());
            }
////            logoImagem = new DefaultStreamedContent(inputStream, "image/jpeg", configuracao.getEMP_LOGO());
//            logoImagem = new DefaultStreamedContent(inputStream, "image/" + ext);
        }
        getCabLabel();
        return cabImagem;
    }

    public void setCabImagem(StreamedContent cabImagem) {
        this.cabImagem = cabImagem;
    }

    public InputStream getCabIS() {
        return cabIS;
    }

    public void setCabIS(InputStream cabIS) {
        this.cabIS = cabIS;
    }

}
