package com.gardnerdenver.bean;

import com.gardnerdenver.facade.ConfiguracaoFacade;
import com.gardnerdenver.facade.EquipamentoServicoFacade;
import com.gardnerdenver.facade.FuncionarioFacade;
import com.gardnerdenver.facade.ParceiroFacade;
import com.gardnerdenver.facade.UserFactoryFacade;
import com.gardnerdenver.facade.UserItemFactoryFacade;
import com.gardnerdenver.model.Configuracao;
import com.gardnerdenver.model.Funcionario;
import com.gardnerdenver.model.FactoryUserItem;
import com.gardnerdenver.util.Util;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.TransactionException;

@ViewScoped
@ManagedBean
public class LoginMB extends AbstractMB implements Serializable {

    @ManagedProperty(value = UserItemFactoryBean.INJECTION_NAME)
    private UserItemFactoryBean userMB;
    @ManagedProperty(value = FuncionarioBean.INJECTION_NAME)
    private FuncionarioBean funcionarioBean;
//    @ManagedProperty(value = ConfiguracaoBean.INJECTION_NAME)
//    private ConfiguracaoBean configuracaoBean;

    private String email;
    private String password;

    //
    private EquipamentoServicoFacade eqsFacade;
    private ParceiroFacade parFacade;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login() {

        UserItemFactoryFacade userFacade = new UserItemFactoryFacade();
        UserFactoryFacade usFacade = new UserFactoryFacade();

        FactoryUserItem user = null;
        try {
            user = userFacade.isValidLogin(email, password);
        } catch (TransactionException te) {
            getRequest().getSession().invalidate();
            redirect("/pages/public/login.xhtml");
        }

        Funcionario func = null;

        if (user != null) {
            user.getUserFactory().setUSU_ULTACESSO(new Date());
            usFacade.updateUserFactory(user.getUserFactory());

            if (user.getUserFactory().isDis()) {
                FuncionarioFacade funcFacade = new FuncionarioFacade(user.getUserFactory().getUSU_BANCO());
                func = funcFacade.isValidLogin(email, password);
                if (func == null) {
                    displayInfoMessageToUser("Usuário não cadastrado");
                    return;
                }
            }

//            funcionarioBean.getUserMB().setUser(user);
            userMB.setUser(user);
//            Util.gravarCookie("db", user.getUserFactory().getUSU_BANCO());
            Util.gravarCookie("database", user.getUserFactory().getUSU_BANCO());
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            request.getSession().setAttribute("user", user);
            if (func != null) {
//                funcionarioBean.setFuncionario(func);
                funcionarioBean.setFuncionarioLogado(func);
                ConfiguracaoFacade cfgFacade = new ConfiguracaoFacade();
//                configuracaoBean.setConfiguracao(cfgFacade.findConfig(1));
                request.getSession().setAttribute("func", func);

                Configuracao c = Util.getConfiguracao();
                if (c.getEMP_LOGO() == null || c.getEMP_LOGO().isEmpty()) {
                    c.setEMP_LOGO(Util.getCaminho() + "logo.png");
                    System.out.println("Setou logo ");
                }
                if (c.getEMP_CAB() == null || c.getEMP_CAB().isEmpty()) {
                    c.setEMP_CAB(Util.getCaminho() + "cab.png");
                    System.out.println("Setou cab ");
                }
                try {
                    cfgFacade.updateConfig(c);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (user.getUserFactory().isFab()) {
                redirect("/pages/protected/factory/index.xhtml");
            }
            if (user.getUserFactory().isDis()) {
                System.out.println(Util.lerCookie("db"));

                new PecaBean().gerarPecas();
                new ServicoBean().gerarServicos();
                new ModeloBean().gerarModelos();

                redirect("/pages/protected/distributor/index.xhtml");
            }
        }

        displayErrorMessageToUser("Verifique se o seu email / senha");
    }

    public void setUserMB(UserItemFactoryBean userMB) {
        this.userMB = userMB;
    }

    public void setFuncionarioBean(FuncionarioBean funcionarioBean) {
        this.funcionarioBean = funcionarioBean;
    }

    public EquipamentoServicoFacade getEqsFacade() {
        if (eqsFacade == null) {
            eqsFacade = new EquipamentoServicoFacade();
        }

        return eqsFacade;
    }

    public ParceiroFacade getParFacade() {
        if (parFacade == null) {
            parFacade = new ParceiroFacade();
        }
        return parFacade;
    }

}
