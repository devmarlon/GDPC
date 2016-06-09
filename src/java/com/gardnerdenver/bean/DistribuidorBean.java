package com.gardnerdenver.bean;

import com.gardnerdenver.facade.ConfiguracaoFacade;
import com.gardnerdenver.facade.ContratoFactoryFacade;
import com.gardnerdenver.facade.FactoryModeloFacade;
import com.gardnerdenver.facade.FactoryPecaFacade;
import com.gardnerdenver.facade.FactoryServicoFacade;
import com.gardnerdenver.facade.FuncionarioFacade;
import com.gardnerdenver.facade.ModeloFacade;
import com.gardnerdenver.facade.PecaFacade;
import com.gardnerdenver.facade.ServicoFacade;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.gardnerdenver.facade.UserFactoryFacade;
import com.gardnerdenver.facade.UserItemFactoryFacade;
import com.gardnerdenver.model.Configuracao;
import com.gardnerdenver.model.FactoryContrato;
import com.gardnerdenver.model.FactoryModelo;
import com.gardnerdenver.model.FactoryPeca;
import com.gardnerdenver.model.FactoryServico;
import com.gardnerdenver.model.Funcionario;
import com.gardnerdenver.model.Role;
import com.gardnerdenver.model.FactoryUser;
import com.gardnerdenver.model.FactoryUserItem;
import com.gardnerdenver.model.Modelo;
import com.gardnerdenver.model.Peca;
import com.gardnerdenver.model.Servico;
import com.gardnerdenver.util.Conexao;
import com.gardnerdenver.util.Util;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.SessionScoped;
import org.joda.time.DateTime;

@SessionScoped
@ManagedBean
public class DistribuidorBean extends AbstractMB implements Serializable {

    private static final long serialVersionUID = 1L;

//    fabrica
    private FactoryUser dist;
    private FactoryUser distribuidor;
    private FactoryUserItem usuarioDistribuidor;
    private FactoryUserItem usuario;
    private FactoryContrato contrato;
    private List<FactoryUser> distribuidores;
    private List<FactoryContrato> contratos;
//    private DogFacade dogFacade;
    private UserFactoryFacade userFacade;
    private UserItemFactoryFacade userItemFacade;
    //objects
    private Funcionario funcionario;
    private Configuracao configuracao;
    private ConfiguracaoFacade configFacade;
    private FuncionarioFacade funcionarioFacade;
    private ContratoFactoryFacade ctoFacade;
    //string
    private String tipoAcao = "";

    public String show() {
        reset();
//        redirect("/pages/protected/factory/distribuidor.xhtml");
        return "/pages/protected/factory/distribuidor.xhtml";
    }

    public void novo() {
        setTipoAcao("Inclusão de Distribuidor");
        reset();

        redirect("/pages/protected/factory/distribuidorCadastro.xhtml");
    }

    public String salvar() {
        String ret = "";
        if (!usuarioDistribuidor.getUSI_LOGIN().contains("@")) {
            displayErrorMessageToUser("Forneça um endereço de email valido para o login!");
            return "";
        }
        if (distribuidor.getUSU_ID() == 0) {
            try {
                distribuidor.setRol(Role.DIS);
                distribuidor.setUSU_CADASTRO(new Date());

                contrato.setCtoCodpromo("");
                contrato.setCtoComissao(0);
                contrato.setCtoCpfcnpj("");
                contrato.setCtoDiavenc(10);
                contrato.setCtoEmail("");
                contrato.setCtoEmissao(new Date());
                contrato.setCtoFormapag(0);
                contrato.setCtoNomerazao("");
                contrato.setCtoStatus(true);
                contrato.setCtoTelefone("");
                contrato.setCtoTipopessoa("");
                contrato.setPlnId(0);
                DateTime dt = new DateTime(contrato.getCtoEmissao());
                contrato.setCtoValidade(dt.plusYears(1).toDate());

                if (distribuidor.getUSU_ID() == 0) {
                    getDistFacade().createUserFactory(distribuidor);
                }
                contrato.setUsuId(distribuidor);
                distribuidor.getContratoList().add(contrato);
                distribuidor.setUSU_BANCO("gdpc_" + distribuidor.getUSU_ID());
                getDistFacade().updateUserFactory(distribuidor);
                usuarioDistribuidor.setUserFactory(distribuidor);
                getUsrItemFacade().createUserItemFactory(usuarioDistribuidor);

                createDB(distribuidor.getUSU_BANCO());

                configuracao.setEMP_RAZAO(distribuidor.getUSU_EMPRESA());
                configuracao.setEMP_LOGO(Util.getCaminho() + "\\GDPC\\logo.png");
                configuracao.setEMP_CAB(Util.getCaminho() + "\\GDPC\\cab.png");
                configuracao.setEST_IDEMP(12);
                configuracao.setCID_IDEMP(1200013);
                System.out.println("inicio config");
                getConfigFacade(distribuidor.getUSU_BANCO()).createConfig(configuracao);
                System.out.println("fim config");

                funcionario.setFUN_UF(12);
                funcionario.setFUN_CIDADE(1200013);
                funcionario.setFUN_LOGIN(usuarioDistribuidor.getUSI_LOGIN());
                funcionario.setFUN_EMAIL(usuarioDistribuidor.getUSI_LOGIN());
                funcionario.setFUN_SENHA(usuarioDistribuidor.getUSI_SENHA());
                funcionario.setFUN_ACESS("S");
                funcionario.setFUN_ADMIN(true);
                System.out.println("inicio dist");
                getFuncionarioFacade(distribuidor.getUSU_BANCO()).createFuncionario(funcionario);
                System.out.println("fim dist");

                String caminho = Util.getCaminho() + "/" + distribuidor.getUSU_BANCO();

                File dirLogo = new File(caminho + "/logo");
                if (dirLogo.mkdirs()) {
                    System.out.println("Diretorio criado com sucesso!");
                } else {
                    System.out.println("Erro ao criar diretorio! " + dirLogo);
                }
                File dirCab = new File(caminho + "/cab");
                if (dirCab.mkdirs()) {
                    System.out.println("Diretorio criado com sucesso!");
                } else {
                    System.out.println("Erro ao criar diretorio! " + dirCab);
                }

//                if (inserePeca(distribuidor.getUSU_BANCO())) {
//                    System.out.println("Peças criadas com êxito > " + distribuidor.getUSU_BANCO());
//                } else {
//                    System.out.println("Erro ao inserir peças > " + distribuidor.getUSU_BANCO());
//                }
//                if (insereModelo(distribuidor.getUSU_BANCO())) {
//                    System.out.println("Modelos criados com êxito > " + distribuidor.getUSU_BANCO());
//                } else {
//                    System.out.println("Erro ao inserir modelos > " + distribuidor.getUSU_BANCO());
//                }
//                if (insereServico(distribuidor.getUSU_BANCO())) {
//                    System.out.println("Servicos criados com êxito > " + distribuidor.getUSU_BANCO());
//                } else {
//                    System.out.println("Erro ao inserir Servicos > " + distribuidor.getUSU_BANCO());
//                }
                displayInfoMessageToUser("Distribuidor cadastrado com sucesso!");
//                loadContratos();
//                reset();
//                redirect("/pages/protected/factory/distribuidor.xhtml");
                ret = show();
            } catch (Exception e) {
                ret = "";
                Connection conn2;
                try {
                    conn2 = Conexao.getConexao("", "localhost", "varkon", "qwert1234");
                    Statement st2 = conn2.createStatement();
                    st2.execute("DROP DATABASE " + dist.getUSU_BANCO());
                    conn2.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                keepDialogOpen();
                displayErrorMessageToUser("Ops, we could not create. Try again later");
                e.printStackTrace();
            }

        }
        return ret;
    }

    public Boolean inserePeca(String banco) {
        boolean result = true;
        FactoryPecaFacade fPcFacade = new FactoryPecaFacade();
        List<FactoryPeca> fPecas = fPcFacade.listAll();
        for (FactoryPeca fP : fPecas) {
            Peca p = new Peca(fP);
            try {
                PecaFacade pFacade = new PecaFacade(banco);
                pFacade.createPeca(p);
            } catch (Exception e) {
                result = false;
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return result;
    }

    public Boolean insereModelo(String banco) {
        boolean result = true;
        FactoryModeloFacade fModFacade = new FactoryModeloFacade();
        List<FactoryModelo> fModelos = fModFacade.listAll();
        for (FactoryModelo fm : fModelos) {
            Modelo m = new Modelo(fm);
            try {
                ModeloFacade mFadadse = new ModeloFacade(banco);
                mFadadse.create(m);
            } catch (Exception e) {
                result = false;
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return result;
    }

    public Boolean insereServico(String banco) {
        boolean result = true;
        FactoryServicoFacade fServFacade = new FactoryServicoFacade();
        List<FactoryServico> fServicos = fServFacade.listAll();
        for (FactoryServico fs : fServicos) {
            Servico s = new Servico(fs);
            try {
                ServicoFacade mFadadse = new ServicoFacade(banco);
                mFadadse.createServico(s);;
            } catch (Exception e) {
                result = false;
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return result;
    }

    public UserFactoryFacade getDistFacade() {
//        if (userFacade == null) {
        userFacade = new UserFactoryFacade();
//        }

        return userFacade;
    }

    public FactoryContrato getContrato() {
        return contrato;
    }

    public void setContrato(FactoryContrato contrato) {
        this.contrato = contrato;
    }

    public ContratoFactoryFacade getCtoFacade() {
        ctoFacade = new ContratoFactoryFacade();
        return ctoFacade;
    }

    public void setCtoFacade(ContratoFactoryFacade ctoFacade) {
        this.ctoFacade = ctoFacade;
    }

    public UserItemFactoryFacade getUsrItemFacade() {
        if (userItemFacade == null) {
            userItemFacade = new UserItemFactoryFacade();
        }

        return userItemFacade;
    }

    public ConfiguracaoFacade getConfigFacade(String banco) {
//        if (configFacade == null) {
        configFacade = new ConfiguracaoFacade(banco);
//        }

        return configFacade;
    }

    public FuncionarioFacade getFuncionarioFacade(String banco) {
//        if (funcionarioFacade == null) {
        funcionarioFacade = new FuncionarioFacade(banco);
//        }

        return funcionarioFacade;
    }

    public FactoryUser getDist() {
        if (dist == null) {
            dist = new FactoryUser();
        }

        return dist;
    }

    public void setDist(FactoryUser dist) {
        this.dist = dist;
    }

    /*
     public void createDist() {
     if (!usuario.getUSI_LOGIN().contains("@")) {

     }
     try {
     dist.setRol(Role.DIS);
     dist.setUSU_CADASTRO(new Date());

     contrato.setCtoCodpromo("");
     contrato.setCtoComissao(0);
     contrato.setCtoCpfcnpj("");
     contrato.setCtoDiavenc(10);
     contrato.setCtoEmail("");
     contrato.setCtoEmissao(new Date());
     contrato.setCtoFormapag(0);
     contrato.setCtoNomerazao("");
     contrato.setCtoStatus(true);
     contrato.setCtoTelefone("");
     contrato.setCtoTipopessoa("");
     contrato.setPlnId(0);
     DateTime dt = new DateTime(contrato.getCtoEmissao());
     contrato.setCtoValidade(dt.plusYears(1).toDate());

     if (dist.getUSU_ID() == 0) {
     getDistFacade().createUserFactory(dist);
     }
     contrato.setUsuId(dist);
     dist.getContratoList().add(contrato);
     dist.setUSU_BANCO("gdpc_" + dist.getUSU_ID());
     getDistFacade().updateUserFactory(dist);
     usuario.setUserFactory(dist);
     getUsrItemFacade().createUserItemFactory(usuario);
     createDB(dist.getUSU_BANCO());
     funcionario.setFUN_LOGIN(usuario.getUSI_LOGIN());
     funcionario.setFUN_EMAIL(usuario.getUSI_LOGIN());
     funcionario.setFUN_SENHA(usuario.getUSI_SENHA());
     funcionario.setFUN_ACESS("S");
     funcionario.setFUN_ADMIN(true);
     //            getFuncionarioFacade();
     getFuncionarioFacade(dist.getUSU_BANCO()).createFuncionario(funcionario);
     closeDialog();
     displayInfoMessageToUser("Criado com sucesso");
     //            loadDistribuidores();
     //            loadContratos();
     reset();
     redirect("/pages/protected/factory/index.xhtml");
     } catch (Exception e) {
     Connection conn2;
     try {
     conn2 = Conexao.getConexao("", "localhost", "root", "qwert1234");
     Statement st2 = conn2.createStatement();
     st2.execute("DROP DATABASE " + dist.getUSU_BANCO());
     conn2.close();
     } catch (SQLException ex) {
     Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
     }
     keepDialogOpen();
     displayErrorMessageToUser("Ops, we could not create. Try again later");
     e.printStackTrace();
     }
     }
     */
    public void createDB(String banco) {

        System.out.println("Criando Banco de dados");
        Connection conn2;
        try {
            conn2 = Conexao.getConexao("", "localhost", "root", "qwert1234");
            Statement st2 = conn2.createStatement();
            st2.execute("CREATE DATABASE " + banco);
            st2.execute("GRANT ALL PRIVILEGES ON " + banco + ".* TO 'root'@'%' IDENTIFIED BY 'qwert1234';");
            conn2.close();

        } catch (SQLException ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<FactoryUser> getAllDistrib() {
        if (distribuidores == null) {
            loadDistribuidores();
        }

        return distribuidores;
    }

    public List<FactoryContrato> getAllDistribContratos() {
//        if (contratos == null) {
        loadContratos();
//        }

        return contratos;
    }

//    private void loadDogs() {
//        dogs = getDogFacade().listAll();
//    }
    private void loadDistribuidores() {
        distribuidores = getDistFacade().listDist();
    }

    private void loadContratos() {
        contratos = getCtoFacade().listDist();
    }

    public void reset() {
        System.out.println("inicio reset");
        resetDistribuidor();
        resetUsuarioDistribuidor();
        resetDist();
        resetUser();
        resetFuncionario();
        resetConfig();
        resetContrato();
        System.out.println("fim reset");
    }

    public void resetDistribuidor() {
        distribuidor = new FactoryUser();
    }

    public void resetUsuarioDistribuidor() {
        usuarioDistribuidor = new FactoryUserItem();
    }

    public void resetDist() {
        dist = new FactoryUser();
    }

    public void resetFuncionario() {
        funcionario = new Funcionario();
    }

    public void resetUser() {
        usuario = new FactoryUserItem();
    }

    public void resetConfig() {
        configuracao = new Configuracao();
    }

    public void resetContrato() {
        contrato = new FactoryContrato();
        contrato.setCtoId(0);
    }

    public FactoryUserItem getUsuario() {
        return usuario;
    }

    public void setUsuario(FactoryUserItem usuario) {
        this.usuario = usuario;
    }

    public List<FactoryUser> getDistribuidores() {
        return distribuidores;
    }

    public void setDistribuidores(List<FactoryUser> distribuidores) {
        this.distribuidores = distribuidores;
    }

    public UserFactoryFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFactoryFacade userFacade) {
        this.userFacade = userFacade;
    }

    public UserItemFactoryFacade getUserItemFacade() {
        return userItemFacade;
    }

    public void setUserItemFacade(UserItemFactoryFacade userItemFacade) {
        this.userItemFacade = userItemFacade;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Configuracao getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(Configuracao configuracao) {
        this.configuracao = configuracao;
    }

    public ConfiguracaoFacade getConfigFacade() {
        return configFacade;
    }

    public void setConfigFacade(ConfiguracaoFacade configFacade) {
        this.configFacade = configFacade;
    }

    public FuncionarioFacade getFuncionarioFacade() {
        return funcionarioFacade;
    }

    public void setFuncionarioFacade(FuncionarioFacade funcionarioFacade) {
        this.funcionarioFacade = funcionarioFacade;
    }

    public FactoryUser getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(FactoryUser distribuidor) {
        this.distribuidor = distribuidor;
    }

    public FactoryUserItem getUsuarioDistribuidor() {
        return usuarioDistribuidor;
    }

    public void setUsuarioDistribuidor(FactoryUserItem usuarioDistribuidor) {
        this.usuarioDistribuidor = usuarioDistribuidor;
    }

    public List<FactoryContrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<FactoryContrato> contratos) {
        this.contratos = contratos;
    }

    public String getTipoAcao() {
        return tipoAcao;
    }

    public void setTipoAcao(String tipoAcao) {
        this.tipoAcao = tipoAcao;
    }
}
