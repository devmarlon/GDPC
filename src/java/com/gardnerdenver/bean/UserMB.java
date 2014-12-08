package com.gardnerdenver.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.gardnerdenver.model.FactoryUserItem;

@SessionScoped
@ManagedBean(name = "userMB")
public class UserMB extends AbstractMB implements Serializable {

    public static final String INJECTION_NAME = "#{userMB}";

    private static final long serialVersionUID = 1L;

    private FactoryUserItem userFactory;

    public boolean isFabUser() {
        return userFactory.getUserFactory().isFab();
    }

    public boolean isDisUser() {
        return userFactory.getUserFactory().isDis();
    }

    public boolean isCliUser() {
        return userFactory.getUserFactory().isCli();
    }

    public String logOut() {
        getRequest().getSession().invalidate();
        return "/pages/public/login.xhtml";
    }

//    private HttpServletRequest getRequest() {
//        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//    }
    public FactoryUserItem getUserFactory() {
        return userFactory;
    }

    public void setUserFactory(FactoryUserItem userFactory) {
        this.userFactory = userFactory;
    }

}
