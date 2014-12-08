package com.gardnerdenver.bean;

import com.gardnerdenver.facade.FuncionarioFacade;
import com.gardnerdenver.facade.MunicipioFacade;
import com.gardnerdenver.facade.UserItemFactoryFacade;
import com.gardnerdenver.model.Estado;
import com.gardnerdenver.model.Funcionario;
import com.gardnerdenver.model.Municipio;
import com.gardnerdenver.model.FactoryUserItem;
import com.gardnerdenver.model.Servico;
import com.gardnerdenver.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

@SessionScoped
@ManagedBean(name = "funcionarioBean")
public class FuncionarioBean extends AbstractMB implements Serializable {

    private static final Logger log = Logger.getLogger(FuncionarioBean.class);

    public static final String INJECTION_NAME = "#{funcionarioBean}";
    private static final long serialVersionUID = 1L;

//    @ManagedProperty(value = UserItemFactoryBean.INJECTION_NAME)
//    private UserItemFactoryBean userMB;
    private Funcionario funcionario;
    private Funcionario funcionarioCompare;
    private Funcionario funcionarioLogado;
    private FuncionarioFacade funcFacade;
    private UserItemFactoryFacade uifFacade;
    //list
    private List<Funcionario> funcionarios;
    private List<Funcionario> funcionariosSelected;
    private List<Municipio> listMunicipio;
    //datamodel
    private DataModel<Funcionario> dtmFuncionarios;
    //
    private String tipoAcao;
    //BOOLEANS
    private boolean vendBool = false;
    private boolean repBool = false;
    private boolean tecBool = false;
    private boolean acessBool = false;
    private boolean panelAcessBool = false;
    private boolean edicao = false;

    public void showFuncionario() {
        funcionario = null;

        redirect("/pages/protected/distributor/funcionario.xhtml");
    }

    public void novo() {
        funcionario = new Funcionario();
        MunicipioFacade munFacade = new MunicipioFacade();
        funcionario.setFUN_UF(12);
        funcionario.setEstado(new EstadoBean().getObjById(12));
        funcionario.setMunicipio(getListMunicipio().get(0));
        tipoAcao = "Cadastro de";
        setEdicao(false);
        setPanelAcessBool(true);
        
        funcionarioCompare = new Funcionario(funcionario);
        redirect("/pages/protected/distributor/funcionarioCadastro.xhtml");
    }

    public void alterar() {
        tipoAcao = "Alteração de";
        setEdicao(true);
        pesqFunc();

        redirect("/pages/protected/distributor/funcionarioCadastro.xhtml");
    }

    private void pesqFunc() {

        funcionario = dtmFuncionarios.getRowData();
        funcionarioCompare = new Funcionario(funcionario);

//        EstadoBean estBean = new EstadoBean();
//        funcionario.setEstado(estBean.getObjById(funcionario.getFUN_UF()));;

        MunicipioFacade munFacade = new MunicipioFacade();
        funcionario.setMunicipio(munFacade.findMunicipio(funcionario.getFUN_CIDADE()));

        if ("S".equalsIgnoreCase(funcionario.getFUN_VEND())) {
            setVendBool(true);
        } else {
            setVendBool(false);
        }
        if ("S".equalsIgnoreCase(funcionario.getFUN_REP())) {
            setRepBool(true);
        } else {
            setRepBool(false);
        }
        if ("S".equalsIgnoreCase(funcionario.getFUN_TEC())) {
            setTecBool(true);
        } else {
            setTecBool(false);
        }
        if ("S".equalsIgnoreCase(funcionario.getFUN_ACESS())) {
            setAcessBool(true);
        } else {
            setAcessBool(false);
        }
        if (funcionario.getFUN_LOGIN() == null) {
            setPanelAcessBool(true);
        } else {
            setPanelAcessBool(false);
        }

    }

    public void salvar() {
        FactoryUserItem uif = null;

        uif = getUifFacade().findByLogin(funcionario.getFUN_LOGIN());
        if (!isEdicao()) {
            if (uif != null) {
                displayInfoMessageToUser("Login já existente. escolha um diferente");
                return;
            }
        }

        if (vendBool) {
            funcionario.setFUN_VEND("S");
            if (repBool) {
                funcionario.setFUN_REP("S");
            } else {
                funcionario.setFUN_REP("N");
            }
        } else {
            funcionario.setFUN_VEND("N");
            funcionario.setFUN_REP("N");
        }
        if (tecBool) {
            funcionario.setFUN_TEC("S");
        } else {
            funcionario.setFUN_TEC("N");
        }
        if (acessBool) {
            funcionario.setFUN_ACESS("S");
        } else {
            funcionario.setFUN_ACESS("N");
        }

        if (uif == null) {
            uif = new FactoryUserItem();
        }

        funcionario.setFUN_UF(funcionario.getEstado().getJEST_ID());
        funcionario.setFUN_CIDADE(funcionario.getMunicipio().getMUN_ID());

        uif.setUSI_LOGIN(funcionario.getFUN_LOGIN());
        uif.setUSI_SENHA(funcionario.getFUN_SENHA());
//        uif.setUserFactory(userMB.getUser().getUserFactory());
        FactoryUserItem user = (FactoryUserItem) getRequest().getSession().getAttribute("user");
        uif.setUserFactory(user.getUserFactory());
        if (acessBool) {
            if (uif.getUSI_ID() == 0) {
                getUifFacade().createUserItemFactory(uif);
            } else {
                getUifFacade().updateUserItemFactory(uif);
            }
        }

        if (funcionario.getFUN_ID() == 0) {//insert
            try {
                getFuncFacade().createFuncionario(funcionario);
                displayInfoMessageToUser("Funcionário salvo com sucesso.");
                showFuncionario();
//                retorno = "parceiro";
            } catch (Exception e) {
                System.out.println(e.getMessage());
                displayErrorMessageToUser("Não foi possível salvar o Funcionário.");
            }
        } else {//update
            try {
                getFuncFacade().updateFuncionario(funcionario);
                displayInfoMessageToUser("Funcionário atualizado com sucesso.");
                showFuncionario();
//                retorno = "parceiro";
            } catch (Exception e) {
                System.out.println(e.getMessage());
                displayErrorMessageToUser("Não foi possível atualizar o Funcionário.");
            }
        }
    }

    public void validaDelete() {
        if (funcionariosSelected.isEmpty()) {
            displayInfoMessageToUser("Selecione ao menos um usuário.");
        } else {
            RequestContext.getCurrentInstance().execute("funcDeleteDialogWidget.show()");
        }
    }

    public void validaFechar() {
        if (funcionario.equals(funcionarioCompare)) {
            showFuncionario();
        } else {
            RequestContext.getCurrentInstance().execute("funCloseDialogWidget.show()");
        }
    }

    public void deleteFuncionario() {
        for (Funcionario funcDel : funcionariosSelected) {
            if (funcDel.isFUN_ADMIN()) {
                displayInfoMessageToUser("Usuario " + funcDel.getFUN_NOME() + " Não pode ser deletado pois é administrador");
                funcionariosSelected.remove(funcDel);
                return;
            }
            if (funcDel.getFUN_LOGIN().equalsIgnoreCase(UserItemFactoryBean.login)) {
                displayInfoMessageToUser("Você não pode deletar seu próprio usuário.");
                funcionariosSelected.remove(funcDel);
                return;
            }

            try {
                getFuncFacade().deleteFuncionario(funcDel);
                if (funcDel.getFUN_ACESS().equalsIgnoreCase("S")) {
                    getUifFacade().deleteUserItemFactory(getUifFacade().findByLogin(funcDel.getFUN_LOGIN()));
                }
                closeDialog();
                displayInfoMessageToUser("Funcionario " + funcDel.getFUN_NOME() + " removido com sucesso.");
                log.info("Funcionario " + funcDel.getFUN_NOME() + " removido com sucesso.");
//                loadDogs();
//                resetDog();
            } catch (Exception e) {
                displayErrorMessageToUser(funcDel.getFUN_NOME() + " não pode ser removido pois está em uso");
                keepDialogOpen();
                log.warn(funcDel.getFUN_NOME() + " não pode ser removido pois está em uso");
                log.warn(e);
            }
        }
    }

    public List<Funcionario> completeVend(String query) {
        List<Funcionario> listComplete = new ArrayList<>();
        List<Funcionario> listResult = new ArrayList<>();

        listComplete = getFuncFacade().listVends();

//        listComplete = funcDao.getLista(false, true, false, false);
        //listComplete = funcDao.getLista(acesso, vendedor, tecnico, representante);
        for (Funcionario aux : listComplete) {
            if (aux.getFUN_NOME().toUpperCase().contains(query.toUpperCase())) {
                listResult.add(aux);
            }
        }
        return listResult;
    }

    public List<Funcionario> completeTec(String query) {
        List<Funcionario> listComplete = new ArrayList<>();
        List<Funcionario> listResult = new ArrayList<>();

        listComplete = getFuncFacade().listTecs();

//        listComplete = funcDao.getLista(false, true, false, false);
        //listComplete = funcDao.getLista(acesso, vendedor, tecnico, representante);
        for (Funcionario aux : listComplete) {
            if (aux.getFUN_NOME().toUpperCase().contains(query.toUpperCase())) {
                listResult.add(aux);
            }
        }
        return listResult;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public FuncionarioFacade getFuncFacade() {
        funcFacade = new FuncionarioFacade();
        return funcFacade;
    }

    public void setFuncFacade(FuncionarioFacade funcFacade) {
        this.funcFacade = funcFacade;
    }

    public UserItemFactoryFacade getUifFacade() {
        uifFacade = new UserItemFactoryFacade();
        return uifFacade;
    }

    public void setUifFacade(UserItemFactoryFacade uifFacade) {
        this.uifFacade = uifFacade;
    }

    public List<Funcionario> getFuncionarios() {
        funcionarios = getFuncFacade().listAll();
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public DataModel<Funcionario> getDtmFuncionarios() {
        dtmFuncionarios = new ListDataModel<>(getFuncionarios());
        return dtmFuncionarios;
    }

    public void setDtmFuncionarios(DataModel<Funcionario> dtmFuncionarios) {
        this.dtmFuncionarios = dtmFuncionarios;
    }

    public List<Municipio> getListMunicipio() {
        MunicipioFacade munFacade = new MunicipioFacade();
        if (funcionario.getEstado() != null) {
            listMunicipio = munFacade.findListMunicipioByUF(funcionario.getEstado().getJEST_ID());
        } else {
            listMunicipio = munFacade.findListMunicipioByUF(funcionario.getFUN_UF());
        }
        return listMunicipio;
    }

    public void setListMunicipio(List<Municipio> listMunicipio) {
        this.listMunicipio = listMunicipio;
    }

    public List<Funcionario> getFuncionariosSelected() {
        return funcionariosSelected;
    }

    public void setFuncionariosSelected(List<Funcionario> funcionariosSelected) {
        this.funcionariosSelected = funcionariosSelected;
    }

    public String getTipoAcao() {
        return tipoAcao;
    }

    public void setTipoAcao(String tipoAcao) {
        this.tipoAcao = tipoAcao;
    }

    public boolean isVendBool() {
        return vendBool;
    }

    public void setVendBool(boolean vendBool) {
        this.vendBool = vendBool;
    }

    public boolean isRepBool() {
        return repBool;
    }

    public void setRepBool(boolean repBool) {
        this.repBool = repBool;
    }

    public boolean isTecBool() {
        return tecBool;
    }

    public void setTecBool(boolean tecBool) {
        this.tecBool = tecBool;
    }

    public boolean isAcessBool() {
        return acessBool;
    }

    public void setAcessBool(boolean acessBool) {
        this.acessBool = acessBool;
    }

    public boolean isPanelAcessBool() {
        return panelAcessBool;
    }

    public void setPanelAcessBool(boolean panelAcessBool) {
        this.panelAcessBool = panelAcessBool;
    }

    public boolean isEdicao() {
        return edicao;
    }

    public void setEdicao(boolean edicao) {
        this.edicao = edicao;
    }

    public Funcionario getFuncionarioLogado() {
        return funcionarioLogado;
    }

    public void setFuncionarioLogado(Funcionario funcionarioLogado) {
        this.funcionarioLogado = funcionarioLogado;
    }

}
