package com.gardnerdenver.bean;

import org.primefaces.context.RequestContext;

import com.gardnerdenver.util.JSFMessageUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class AbstractMB {

    private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";

    public AbstractMB() {
        super();
    }

    protected void displayErrorMessageToUser(String message) {
        JSFMessageUtil messageUtil = new JSFMessageUtil();
        messageUtil.sendErrorMessageToUser(message);
    }

    protected void displayInfoMessageToUser(String message) {
        JSFMessageUtil messageUtil = new JSFMessageUtil();
        messageUtil.sendInfoMessageToUser(message);
    }

    protected void closeDialog() {
        getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, false);
    }

    protected void keepDialogOpen() {
        getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, true);
    }

    protected RequestContext getRequestContext() {
        return RequestContext.getCurrentInstance();
    }

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public void redirect(String pagina) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gdpc" + pagina);
//            FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
        } catch (IOException ex) {
            Logger.getLogger(AbstractMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
