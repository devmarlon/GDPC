package com.gardnerdenver.bean;

import com.gardnerdenver.facade.UserItemFactoryFacade;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.gardnerdenver.model.FactoryUserItem;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.context.RequestContext;

@SessionScoped
@ManagedBean(name = "userItemFactoryBean")
public class UserItemFactoryBean extends AbstractMB implements Serializable {

    public static final String INJECTION_NAME = "#{userItemFactoryBean}";
    private static final long serialVersionUID = 1L;
    public static String banco;
    public static String login;
    private String senhaAtual = "";
    private String senhaNova = "";
    private UserItemFactoryFacade usiFacade;

    private FactoryUserItem user; //logado
    private FactoryUserItem usuarioItem;
    private FactoryUserItem usuarioItemCompare;
    private List<FactoryUserItem> listUsuarioItem;
    private List<FactoryUserItem> listUsuarioItemSelected;
    private DataModel<FactoryUserItem> dtmUsuarioItem;

    public static int numConn = 0;

    public void novo() {
        senhaAtual = "";
        senhaNova = "";
        usuarioItem = new FactoryUserItem();
        usuarioItemCompare = new FactoryUserItem(usuarioItem);

        redirect("/pages/protected/factory/usuarioCadastro.xhtml");
    }

    public void alterar() {
        senhaAtual = "";
        senhaNova = "";
        usuarioItem = dtmUsuarioItem.getRowData();
        usuarioItemCompare = new FactoryUserItem(usuarioItem);

        redirect("/pages/protected/factory/usuarioCadastro.xhtml");
    }

    public void show() {
        listUsuarioItemSelected = null;
        redirect("/pages/protected/factory/usuario.xhtml");
    }

    public void salvar() {

        if (!usuarioItem.getUSI_LOGIN().contains("@")) {
            displayErrorMessageToUser("Forneça um endereço de email válido para o login!");
            return;
        }
        FactoryUserItem uif = null;

        uif = getUsiFacade().findByLogin(usuarioItem.getUSI_LOGIN());
//        if (usuarioItem.getUSI_ID() != 0) {
        if (uif != null) {
            displayInfoMessageToUser("Login já existente. escolha um email diferente.");
            return;
//            }
        }
        if (usuarioItem.getUSI_ID() == 0) { //save
            try {
                usuarioItem.setUserFactory(user.getUserFactory());
                getUsiFacade().createUserItemFactory(usuarioItem);
                displayInfoMessageToUser("Usuário salvo com sucesso.");
                show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                displayErrorMessageToUser("Não foi possível salvar o usuário.");
            }
        } else { //update
            try {
                getUsiFacade().updateUserItemFactory(usuarioItem);
                displayInfoMessageToUser("Usuário aterado com sucesso.");
                show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                displayErrorMessageToUser("Não foi possível alterar o usuário.");
            }
        }
    }

    public void validaFechar() {
        if (usuarioItem.equals(usuarioItemCompare)) {
            show();
        } else {
            RequestContext.getCurrentInstance().execute("usCloseDialogWidget.show()");
        }
    }

    public boolean isFabUser() {
        if (user != null) {
            return user.getUserFactory().isFab();
        } else {
            return false;
        }
    }

    public boolean isDisUser() {
        if (user != null) {
            return user.getUserFactory().isDis();
        } else {
            return false;
        }
    }

    public boolean isCliUser() {
        if (user != null) {
            return user.getUserFactory().isCli();
        } else {
            return false;
        }

    }

    public void logOut() {
        getRequest().getSession().invalidate();
        redirect("/pages/public/login.xhtml");
    }

    public void alteraSenha() {
        if (!senhaAtual.equals(user.getUSI_SENHA())) {
            displayInfoMessageToUser("Senha atual não confere");
            keepDialogOpen();
            return;
        }
        if (senhaNova.equals(user.getUSI_SENHA())) {
            displayInfoMessageToUser("A nova senha não deve ser igual a atual.");
            keepDialogOpen();
            return;
        }

        try {
            user.setUSI_SENHA(senhaNova);
            getUsiFacade().updateUserItemFactory(user);
            closeDialog();
            displayInfoMessageToUser("Senha alterada com sucesso!");
        } catch (Exception e) {
            user = getUsiFacade().findUsi(user.getUSI_ID());
            keepDialogOpen();
            displayErrorMessageToUser("Não foi possivel alterar a senha");
            e.printStackTrace();
        }
    }

    public FactoryUserItem getUser() {
        return user;
    }

    public void setUser(FactoryUserItem user) {
        this.user = user;
        banco = user.getUserFactory().getUSU_BANCO();
        login = user.getUSI_LOGIN();
    }

    public FactoryUserItem getUsuarioItem() {
        if (usuarioItem == null) {
            usuarioItem = new FactoryUserItem();
        }
        return usuarioItem;
    }

    public void setUsuarioItem(FactoryUserItem usuarioItem) {
        this.usuarioItem = usuarioItem;
    }

    public List<FactoryUserItem> getListUsuarioItem() {
        listUsuarioItem = new UserItemFactoryFacade().listFabrica();
        if (listUsuarioItem == null) {
            listUsuarioItem = new ArrayList<>();
        }
        return listUsuarioItem;
    }

    public void setListUsuarioItem(List<FactoryUserItem> listUsuarioItem) {
        this.listUsuarioItem = listUsuarioItem;
    }

    public DataModel<FactoryUserItem> getDtmUsuarioItem() {
        dtmUsuarioItem = new ListDataModel(getListUsuarioItem());
        return dtmUsuarioItem;
    }

    public void setDtmUsuarioItem(DataModel<FactoryUserItem> dtmUsuarioItem) {
        this.dtmUsuarioItem = dtmUsuarioItem;
    }

    public static String getBanco() {
        return banco;
    }

    public static String getLogin() {
        return login;
    }

    public static void setBanco(String banco) {
        UserItemFactoryBean.banco = banco;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getSenhaNova() {
        return senhaNova;
    }

    public void setSenhaNova(String senhaNova) {
        this.senhaNova = senhaNova;
    }

    public UserItemFactoryFacade getUsiFacade() {
        usiFacade = new UserItemFactoryFacade();
        return usiFacade;
    }

    public void setUsiFacade(UserItemFactoryFacade usiFacade) {
        this.usiFacade = usiFacade;
    }

    public List<FactoryUserItem> getListUsuarioItemSelected() {
        return listUsuarioItemSelected;
    }

    public void setListUsuarioItemSelected(List<FactoryUserItem> listUsuarioItemSelected) {
        this.listUsuarioItemSelected = listUsuarioItemSelected;
    }

    public FactoryUserItem getUsuarioItemCompare() {
        return usuarioItemCompare;
    }

    public void setUsuarioItemCompare(FactoryUserItem usuarioItemCompare) {
        this.usuarioItemCompare = usuarioItemCompare;
    }

  


}
