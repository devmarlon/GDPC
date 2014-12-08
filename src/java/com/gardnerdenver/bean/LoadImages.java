/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gardnerdenver.bean;

import com.gardnerdenver.model.Configuracao;
import com.gardnerdenver.util.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@SessionScoped
@ManagedBean(name = "loadImagesBean")
public class LoadImages {

    private StreamedContent logoImagem;
    private StreamedContent cabImagem;

    private String cab;
    private String logo;

    private Configuracao configuracao;

    public StreamedContent getLogoImagem() {
        logoImagem = null;
        if (configuracao.getEMP_LOGO() != null && !"".equals(configuracao.getEMP_LOGO())) {
            File file = new File(configuracao.getEMP_LOGO());
            String ext = configuracao.getEMP_LOGO().substring((configuracao.getEMP_LOGO().length() - 3));
            if (file.exists()) {
                InputStream inputStream = null;
                try {
                    inputStream = new FileInputStream(file);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(LoadImages.class.getName()).log(Level.SEVERE, null, ex);
                }
                logoImagem = new DefaultStreamedContent(inputStream, "image/" + ext, configuracao.getEMP_LOGO());
            }
        }

        return logoImagem;
    }

    public void setLogoImagem(StreamedContent logoImagem) {
        this.logoImagem = logoImagem;
    }

    public StreamedContent getCabImagem() {
        return cabImagem;
    }

    public void setCabImagem(StreamedContent cabImagem) {
        this.cabImagem = cabImagem;
    }

    public Configuracao getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(Configuracao configuracao) {
        this.configuracao = configuracao;
    }

    public String getCab() {
        return cab;
    }

    public void setCab(String cab) {
        this.cab = cab;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

}
