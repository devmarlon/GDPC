package com.gardnerdenver.bean;

import com.gardnerdenver.facade.MunicipioFacade;
import com.gardnerdenver.facade.ParceiroFacade;
import com.gardnerdenver.model.Equipamento;
import com.gardnerdenver.model.Estado;
import com.gardnerdenver.model.Municipio;
import com.gardnerdenver.model.Parceiro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "parceiroBean")
@SessionScoped
public class ParceiroBean extends AbstractMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Parceiro parceiro = new Parceiro();
    private Parceiro parceiroCompare;
    private Parceiro parceiroAux;
    private Equipamento selectedEquipamento;

    private List<Parceiro> parceiros;
    private List<Parceiro> selectedParcs;
    private List<Municipio> listMunicipio;
    private List<Municipio> listMunicipioEnt;
    private List<Municipio> listMunicipioCob;
    private List<Parceiro> listParcs;

    private ParceiroFacade parceiroFacade;

    //
    private DataModel<Parceiro> dmParcs;
    //
    private String tipoAcao = "";
    private String maskCPFCNPJ = "99.999.999/9999-99";
    private String labelCPFCNPJ = "CNPJ";
    private String maskCPFCNPJENT = "99.999.999/9999-99";
    private String labelCPFCNPJENT = "CNPJ";
    private String maskCPFCNPJCOB = "99.999.999/9999-99";
    private String labelCPFCNPJCOB = "CNPJ";
    private String filtroBusca = "";
    private String nomeBusca = "";// id, fantasia, razao social
    private String CPFCNPJBusca = "";// cpf/cnpj
    private String endBusca = "";//endereco
    private String bairroBusca = "";
    private String outroBusca = "";//e-mail, site, telefone
    //
    private Estado estadoBUSCASelected;//ESTADO
    private Municipio municipioBUSCASelected;//MUNICIPIO 
    private List<Municipio> listMunicipioBUSCA = new ArrayList<>();//lista resultante da busca
    //int
    private int tipoLista = 0;//verifica se é uma busca ou se é uma abertura de pagina
    //
    private boolean delbool = false;
    private boolean required = false;

    public void novo() {
        required = true;
        setTipoAcao("Inclusão de Cliente");
        parceiro = new Parceiro();
        parceiro.setPAR_CLI(true);
        parceiro.setPAR_UF(12);
        getListMunicipio();
        parceiroCompare = new Parceiro(parceiro);
        redirect("/pages/protected/distributor/parceiroCadastro.xhtml");
    }

    public void alterar() {
        required = true;
        parceiro = dmParcs.getRowData();
        parceiroCompare = new Parceiro(parceiro);
        MunicipioFacade munFacade = new MunicipioFacade();
        parceiro.setMunicipio(munFacade.findMunicipio(parceiro.getPAR_CIDADE()));

        parceiroAux = parceiro;
        setTipoAcao(parceiro.getPAR_RAZAO());

        isCPFCNPJ();
        isCPFCNPJENT();
        isCPFCNPJCOB();

        redirect("/pages/protected/distributor/parceiroCadastro.xhtml");
    }

    public void salvar() {
        required = true;

        parceiro.setPAR_CIDADE(parceiro.getMunicipio().getMUN_ID());
        parceiro.setPAR_CIDADECOB(parceiro.getMunicipio().getMUN_ID());
        parceiro.setPAR_CIDADEENT(parceiro.getMunicipio().getMUN_ID());

//        parceiro.setPAR_RAZAO(parceiro.getPAR_RAZAO().trim());
//        if (!parceiro.getPAR_CNPJCPF().equalsIgnoreCase("")) {
//            String testeCPFCNPJ = parceiro.getPAR_CNPJCPF();
//            testeCPFCNPJ = Util.somenteNumeroLetra(testeCPFCNPJ);
//            if (!Util.validaCPF_CNPJ(testeCPFCNPJ)) {
//                displayErrorMessageToUser("CPF/CNPJ inválido!");
//                return;
//            }
//        }
        //
        if (parceiro.isPAR_ENDENTBOOL()) {
            parceiro.setPAR_ENDENT(1);
//            if (estadoENTSelected != null) {
//                parceiro.setPAR_UFENT(estadoENTSelected.getJEST_ID());
//            }
//            if (municipioENTSelected != null) {
//                parceiro.setPAR_CIDADEENT(municipioENTSelected.getMUN_ID());
//            }
        } else {
            parceiro.setPAR_ENDENT(0);
            parceiro.setPAR_CNPJCPFENT(parceiro.getPAR_CNPJCPF());
            parceiro.setPAR_CEPENT(parceiro.getPAR_CEP());
            parceiro.setPAR_BAIRROENT(parceiro.getPAR_BAIRRO());
            parceiro.setPAR_ENDERECOENT(parceiro.getPAR_ENDERECO());
            parceiro.setPAR_COMPLEMENTOENT(parceiro.getPAR_COMPLEMENTO());
            parceiro.setPAR_NUMEROENT(parceiro.getPAR_NUMERO());
            parceiro.setPAR_COMPLEMENTOENT(parceiro.getPAR_COMPLEMENTO());
// parceiro.setPAR_UFENT(parceiro.getPAR_UF());
//            parceiro.setPAR_UFENT(estadoENTSelected.getJEST_ID());
//// parceiro.setPAR_CIDADEENT(parceiro.getPAR_CIDADE());
//            parceiro.setPAR_CIDADEENT(municipioENTSelected.getMUN_ID());
        }
        if (parceiro.isPAR_ENDCOBBOOL()) {
            parceiro.setPAR_ENDCOB(1);
//            if (estadoCOBSelected != null) {
//                parceiro.setPAR_UFCOB(estadoCOBSelected.getJEST_ID());
//            }
//            if (municipioCOBSelected != null) {
//                parceiro.setPAR_CIDADECOB(municipioCOBSelected.getMUN_ID());
//            }
        } else {
            parceiro.setPAR_ENDCOB(0);
            parceiro.setPAR_CNPJCPFCOB(parceiro.getPAR_CNPJCPF());
            parceiro.setPAR_CEPCOB(parceiro.getPAR_CEP());
            parceiro.setPAR_BAIRROCOB(parceiro.getPAR_BAIRRO());
            parceiro.setPAR_ENDERECOCOB(parceiro.getPAR_ENDERECO());
            parceiro.setPAR_COMPLEMENTOCOB(parceiro.getPAR_COMPLEMENTO());
            parceiro.setPAR_NUMEROCOB(parceiro.getPAR_NUMERO());
            parceiro.setPAR_COMPLEMENTOCOB(parceiro.getPAR_COMPLEMENTO());
            parceiro.setPAR_UFCOB(parceiro.getPAR_UF());
            parceiro.setPAR_CIDADECOB(parceiro.getPAR_CIDADE());
        }
        if (parceiro.getPAR_ID() == 0) {//insert
            try {
                getParceiroFacade().createParceiro(parceiro);
                displayInfoMessageToUser("Cliente salvo com sucesso.");
                showParceiro();
//                retorno = "parceiro";
            } catch (Exception e) {
                System.out.println(e.getMessage());
                displayErrorMessageToUser("Não foi possível salvar o cliente.");
            }
        } else {//update
            try {
                getParceiroFacade().updateParceiro(parceiro);
                displayInfoMessageToUser("Cliente atualizado com sucesso.");
                showParceiro();
//                retorno = "parceiro";
            } catch (Exception e) {
                System.out.println(e.getMessage());
                displayErrorMessageToUser("Não foi possível atualizar o cliente.");
            }
        }
    }

    public void showParceiro() {
        parceiro = null;
        parceiroCompare = null;

        redirect("/pages/protected/distributor/parceiro.xhtml");
    }

    public void validaDelete() {
        if (selectedParcs.isEmpty()) {
            displayInfoMessageToUser("Selecione ao menos um cliente.");
        } else {
            RequestContext.getCurrentInstance().execute("parDeleteDialogWidget.show()");
        }
    }

    public void validaFechar() {
        required = false;
        if (getParceiroCompare().equals(getParceiro())) {
            showParceiro();
        } else {
            RequestContext.getCurrentInstance().execute("parCloseDialogWidget.show()");
        }
    }

    public void deletePar(ActionEvent ae) {
        for (Parceiro par : selectedParcs) {
            try {
                getParceiroFacade().deletePerson(par);
                closeDialog();
                displayInfoMessageToUser("Cliente apagado com sucesso.");
                setDelbool(false);
//                loadDogs();
//                resetDog();
            } catch (Exception e) {
                displayErrorMessageToUser(par.getPAR_RAZAO() + " não pode ser removido pois está em uso");
                keepDialogOpen();
                e.printStackTrace();
            }
        }
    }

    public void buscar() {
        tipoLista = 1;
        getDmParcs();
//        atualizaFiltro();
//        return "parceiro";
    }

    public void isCPFCNPJ() {
        switch (parceiro.getPAR_TIPOPESSOA()) {
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

    public void isCPFCNPJENT() {
        switch (parceiro.getPAR_TIPOPESSOAENT()) {
            case "F":
                maskCPFCNPJENT = "999.999.999-99";
                labelCPFCNPJENT = "CPF";
                break;
            case "J":
                maskCPFCNPJENT = "99.999.999/9999-99";
                labelCPFCNPJENT = "CNPJ";
                break;
        }
    }

    public void isCPFCNPJCOB() {
        switch (parceiro.getPAR_TIPOPESSOACOB()) {
            case "F":
                maskCPFCNPJCOB = "999.999.999-99";
                labelCPFCNPJCOB = "CPF";
                break;
            case "J":
                maskCPFCNPJCOB = "99.999.999/9999-99";
                labelCPFCNPJCOB = "CNPJ";
                break;
        }
    }

    public List<Parceiro> complete(String name) {
        List<Parceiro> queryResult = new ArrayList<>();

        if (parceiroFacade == null) {
            parceiroFacade = new ParceiroFacade();
        }
        parceiros = parceiroFacade.listParceiros();

        for (Parceiro parc : parceiros) {
            if (parc.getPAR_RAZAO().toLowerCase().contains(name.toLowerCase())) {
                queryResult.add(parc);
            } else if (name.isEmpty()) {
                queryResult.add(parc);
            }
        }

        return queryResult;
    }

    public void emailNFE() {
        if (tipoAcao.contains("Inclusão")) {
            parceiro.setPAR_EMAILNFE(parceiro.getPAR_EMAIL());
        }
    }

    public DataModel<Parceiro> getDmParcs() {
        dmParcs = new ListDataModel<>(getListParcs());
        if (dmParcs == null) {
            dmParcs = new ArrayDataModel<>();
        }
        return dmParcs;
    }

    public void setDmParcs(DataModel<Parceiro> dmParcs) {
        this.dmParcs = dmParcs;
    }

    public Parceiro getParceiro() {
        if (parceiro == null) {
            parceiro = new Parceiro();
        }

        return parceiro;
    }

    public void setParceiro(Parceiro parceiro) {
        this.parceiro = parceiro;
    }

    public Parceiro getParceiroAux() {
        if (parceiroAux == null) {
            parceiroAux = new Parceiro();
        }
        return parceiroAux;
    }

    public void setParceiroAux(Parceiro parceiroAux) {
        this.parceiroAux = parceiroAux;
    }

    public Equipamento getSelectedEquipamento() {
        return selectedEquipamento;
    }

    public void setSelectedEquipamento(Equipamento selectedEquipamento) {
        this.selectedEquipamento = selectedEquipamento;
    }

    public List<Parceiro> getParceiros() {
        return parceiros;
    }

    public void setParceiros(List<Parceiro> parceiros) {
        this.parceiros = parceiros;
    }

    public ParceiroFacade getParceiroFacade() {
        parceiroFacade = new ParceiroFacade();
        return parceiroFacade;
    }

    public void setParceiroFacade(ParceiroFacade parceiroFacade) {
        this.parceiroFacade = parceiroFacade;
    }

    public List<Parceiro> getSelectedParcs() {
        return selectedParcs;
    }

    public void setSelectedParcs(List<Parceiro> selectedParcs) {
        this.selectedParcs = selectedParcs;
    }

    public String getTipoAcao() {
        return tipoAcao;
    }

    public void setTipoAcao(String tipoAcao) {
        this.tipoAcao = tipoAcao;
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

    public String getMaskCPFCNPJENT() {
        return maskCPFCNPJENT;
    }

    public void setMaskCPFCNPJENT(String maskCPFCNPJENT) {
        this.maskCPFCNPJENT = maskCPFCNPJENT;
    }

    public String getLabelCPFCNPJENT() {
        return labelCPFCNPJENT;
    }

    public void setLabelCPFCNPJENT(String labelCPFCNPJENT) {
        this.labelCPFCNPJENT = labelCPFCNPJENT;
    }

    public String getMaskCPFCNPJCOB() {
        return maskCPFCNPJCOB;
    }

    public void setMaskCPFCNPJCOB(String maskCPFCNPJCOB) {
        this.maskCPFCNPJCOB = maskCPFCNPJCOB;
    }

    public String getLabelCPFCNPJCOB() {
        return labelCPFCNPJCOB;
    }

    public void setLabelCPFCNPJCOB(String labelCPFCNPJCOB) {
        this.labelCPFCNPJCOB = labelCPFCNPJCOB;
    }

    public List<Municipio> getListMunicipio() {
        MunicipioFacade munFacade = new MunicipioFacade();
        listMunicipio = munFacade.findListMunicipioByUF(getParceiro().getPAR_UF());
        return listMunicipio;
    }

    public void setListMunicipio(List<Municipio> listMunicipio) {
        this.listMunicipio = listMunicipio;
    }

    public List<Municipio> getListMunicipioEnt() {
        MunicipioFacade munFacade = new MunicipioFacade();
        listMunicipioEnt = munFacade.findListMunicipioByUF(getParceiro().getPAR_UFENT());
        return listMunicipioEnt;
    }

    public List<Municipio> getListMunicipioCob() {
        MunicipioFacade munFacade = new MunicipioFacade();
        listMunicipioCob = munFacade.findListMunicipioByUF(getParceiro().getPAR_UFCOB());
        return listMunicipioCob;
    }

    public void setListMunicipioEnt(List<Municipio> listMunicipioEnt) {
        this.listMunicipioEnt = listMunicipioEnt;
    }

    public void setListMunicipioCob(List<Municipio> listMunicipioCob) {
        this.listMunicipioCob = listMunicipioCob;
    }

    public boolean isDelbool() {
        return delbool;
    }

    public void setDelbool(boolean delbool) {
        this.delbool = delbool;
    }

    public String getNomeBusca() {
        return nomeBusca;
    }

    public void setNomeBusca(String nomeBusca) {
        this.nomeBusca = nomeBusca;
    }

    public int getTipoLista() {
        return tipoLista;
    }

    public void setTipoLista(int tipoLista) {
        this.tipoLista = tipoLista;
    }

    public String getFiltroBusca() {
        return filtroBusca;
    }

    public void setFiltroBusca(String filtroBusca) {
        this.filtroBusca = filtroBusca;
    }

    public String getCPFCNPJBusca() {
        return CPFCNPJBusca;
    }

    public void setCPFCNPJBusca(String CPFCNPJBusca) {
        this.CPFCNPJBusca = CPFCNPJBusca;
    }

    public String getEndBusca() {
        return endBusca;
    }

    public void setEndBusca(String endBusca) {
        this.endBusca = endBusca;
    }

    public String getBairroBusca() {
        return bairroBusca;
    }

    public void setBairroBusca(String bairroBusca) {
        this.bairroBusca = bairroBusca;
    }

    public String getOutroBusca() {
        return outroBusca;
    }

    public void setOutroBusca(String outroBusca) {
        this.outroBusca = outroBusca;
    }

    public Estado getEstadoBUSCASelected() {
        return estadoBUSCASelected;
    }

    public void setEstadoBUSCASelected(Estado estadoBUSCASelected) {
        this.estadoBUSCASelected = estadoBUSCASelected;
    }

    public Municipio getMunicipioBUSCASelected() {
        return municipioBUSCASelected;
    }

    public void setMunicipioBUSCASelected(Municipio municipioBUSCASelected) {
        this.municipioBUSCASelected = municipioBUSCASelected;
    }

    public List<Municipio> getListMunicipioBUSCA() {
        return listMunicipioBUSCA;
    }

    public void setListMunicipioBUSCA(List<Municipio> listMunicipioBUSCA) {
        this.listMunicipioBUSCA = listMunicipioBUSCA;
    }

    public List<Parceiro> getListParcs() {
        if (tipoLista == 1) {
            listParcs = getParceiroFacade().listParceirosBusca(nomeBusca, CPFCNPJBusca, endBusca, bairroBusca, outroBusca,
                    estadoBUSCASelected, municipioBUSCASelected);
        } else {
            listParcs = getParceiroFacade().listAll();
            //OU usa o lista busca passando os atributos sem valor
            // listParcs = parcDao.getListaBusca(campo, ordem, "", "", "", "", "", null, null, isCLI, isFRN, isTRA);
        }
        if (listParcs == null) {
            listParcs = new ArrayList<>();
        }
        return listParcs;
    }

    public void setListParcs(List<Parceiro> listParcs) {
        this.listParcs = listParcs;
    }

    public Parceiro getParceiroCompare() {
        return parceiroCompare;
    }

    public void setParceiroCompare(Parceiro parceiroCompare) {
        this.parceiroCompare = parceiroCompare;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

}
