package com.gardnerdenver.bean;

import com.gardnerdenver.facade.ContratoFactoryFacade;
import com.gardnerdenver.facade.FactoryPecaFacade;
import com.gardnerdenver.facade.PecaFacade;
//import com.gardnerdenver.facade.ServicoFacade;
import com.gardnerdenver.model.Equipamento;
import com.gardnerdenver.model.FactoryContrato;
import com.gardnerdenver.model.FactoryPeca;
import com.gardnerdenver.model.Peca;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.context.RequestContext;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@SessionScoped
@ManagedBean(name = "factoryPecaBean")
public class FactoryPecaBean extends AbstractMB implements Serializable {
    
    private static final long serialVersionUID = 1L;

//    private Servico servico;
    private Equipamento selectedEquipamento;
    private FactoryPeca factoryPeca;
    private FactoryPeca pecaCompare;
    
    private List<FactoryPeca> pecas;
    private List<FactoryPeca> pecasSelected;
    private List<Equipamento> equipamentos;
    private static List<FactoryContrato> contratos;
    
    private DataModel<FactoryPeca> dtmFactoryPecas;

//    private ServicoFacade servicoFacade;
    private static FactoryPecaFacade factoryPecaFacade;
    private static PecaFacade pecaFacade;
    //busca
    private int tipoLista = 0;
    private String nomeBusca = "";
    
    public void alterar() {
        factoryPeca = dtmFactoryPecas.getRowData();
        pecaCompare = new FactoryPeca(factoryPeca);
        
        redirect("/pages/protected/factory/pecaCadastro.xhtml");
    }
    
    public void novo() {
        factoryPeca = new FactoryPeca();
        pecaCompare = null;
        redirect("/pages/protected/factory/pecaCadastro.xhtml");
    }
    
    public void showFactoryPeca() {
        tipoLista = 0;
        nomeBusca = "";
        factoryPeca = null;
        pecaCompare = null;
        redirect("/pages/protected/factory/peca.xhtml");
    }
    
    public void buscar() {
        if (nomeBusca.isEmpty()) {
            tipoLista = 0;
        } else {
            tipoLista = 1;
        }
        getDtmFactoryPecas();
    }
    
    public void salvar() {
        boolean result = false;
        factoryPeca.setAtivo(true);
        if (factoryPeca.getPEC_ID() == 0) { //save
            try {
                result = true;
                getFactoryPecaFacade().createPeca(factoryPeca);
                displayInfoMessageToUser("Peça salva com sucesso.");
//                showFactoryPeca();
            } catch (Exception e) {
                result = false;
                System.out.println(e.getMessage());
                displayErrorMessageToUser("Não foi possível salvar a Peça.");
            }
            if (result) {
                loadContratos();
                if (getContratos().size() > 0) {
                    for (FactoryContrato c : getContratos()) {
                        loadPecaFacade(c.getUsuId().getUSU_BANCO());
                        
                        Peca peca = getPecaFacade().findPecaByFab(factoryPeca.getPEC_ID());
                        Peca p = new Peca(factoryPeca);
                        
                        if (peca != null) {
                            p.setPEC_ID(peca.getPEC_ID());
                            p.setAtivo(true);
                            p.setCodigo(peca.getCodigo());
                            p.setDescricao(peca.getDescricao());
                        }
                        
                        if (p.getPEC_ID() == 0) {
                            try {
                                getPecaFacade().createPeca(p);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            try {
                                getPecaFacade().updatePeca(p);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        
                    }
                }
                showFactoryPeca();
            }
        } else { //update
            try {
                result = true;
                getFactoryPecaFacade().updatePeca(factoryPeca);
                displayInfoMessageToUser("Peça salva com sucesso.");
//                showFactoryPeca();
            } catch (Exception e) {
                result = false;
                System.out.println(e.getMessage());
                displayErrorMessageToUser("Não foi possível salvar a Peça.");
            }
            if (result) {
                loadContratos();
                if (getContratos().size() > 0) {
                    for (FactoryContrato c : getContratos()) {
                        loadPecaFacade(c.getUsuId().getUSU_BANCO());
                        Peca p = getPecaFacade().findPecaByFab(factoryPeca.getPEC_ID());
                        if (p == null) {
                            p = new Peca(factoryPeca);
                        }
                        p.setCodigo(factoryPeca.getCodigo());
                        p.setDescricao(factoryPeca.getDescricao());
                        try {
                            getPecaFacade().updatePeca(p);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                showFactoryPeca();
            }
        }
    }
    
    public void validaDelete() {
        if (pecasSelected.isEmpty()) {
            displayInfoMessageToUser("Selecione ao menos uma peça.");
        } else {
            RequestContext.getCurrentInstance().execute("pecDeleteDialogWidget.show()");
        }
    }
    
    public void validaFechar() {
        if (factoryPeca.equals(pecaCompare)) {
            showFactoryPeca();
        } else {
            RequestContext.getCurrentInstance().execute("pecCloseDialogWidget.show()");
        }
    }
    
    public void desativaPeca() {
        for (FactoryPeca pec : pecasSelected) {
            
            try {
                pec.setAtivo(Boolean.FALSE);
                factoryPeca = pec;
                salvar();
//                getFactoryPecaFacade().updatePeca(pec);
                closeDialog();
                displayInfoMessageToUser("Peça desativada com sucesso.");
            } catch (Exception e) {
                displayErrorMessageToUser(pec.getDescricao() + " não pode ser removido pois está em uso");
                keepDialogOpen();
                e.printStackTrace();
            }
        }
    }
    
    public static void loadPecaFacade(String banco) {
        pecaFacade = new PecaFacade(banco);
    }
    
    public void deleteFactoryPeca() {
        for (FactoryPeca pec : pecasSelected) {
//            if (pec.isFab()) {
//                closeDialog();
//                displayInfoMessageToUser(pec.getDescricao() + " não pode ser removido");
//            } else {
            try {
                getFactoryPecaFacade().deletePeca(pec);
                closeDialog();
                displayInfoMessageToUser("Serviço removido com sucesso.");
//                loadDogs();
//                resetDog();
            } catch (Exception e) {
                displayErrorMessageToUser(pec.getDescricao() + " não pode ser removido pois está em uso");
                keepDialogOpen();
                e.printStackTrace();
            }
        }
//        }
    }
    
    public Equipamento getSelectedEquipamento() {
        return selectedEquipamento;
    }
    
    public void setSelectedEquipamento(Equipamento selectedEquipamento) {
        this.selectedEquipamento = selectedEquipamento;
    }
    
    public List<FactoryPeca> getFactoryPecas() {
        pecasSelected = null;
        
        if (tipoLista == 0) {
            pecas = getFactoryPecaFacade().listAll();
        } else {
            pecas = getFactoryPecaFacade().listBusca(nomeBusca);
        }
        if (pecas == null) {
            pecas = new ArrayList<>();
        }
        return pecas;
    }
    
    public void setFactoryPecas(List<FactoryPeca> pecas) {
        this.pecas = pecas;
    }
    
    public List<FactoryPeca> complete(String name) {
        List<FactoryPeca> queryResult = new ArrayList<>();
        
        if (pecas == null) {
            factoryPecaFacade = new FactoryPecaFacade();
            pecas = factoryPecaFacade.listAll();
        }

//        parceiros.removeAll(personWithDogs.getDogs());
        for (FactoryPeca fP : pecas) {
            if (fP.getDescricao().toLowerCase().contains(name.toLowerCase())) {
                queryResult.add(fP);
            }
        }
        
        return queryResult;
    }
    
    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }
    
    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }
    
    public DataModel<FactoryPeca> getDtmFactoryPecas() {
        dtmFactoryPecas = new ListDataModel(getFactoryPecas());
        return dtmFactoryPecas;
    }
    
    public void setDtmFactoryPecas(DataModel<FactoryPeca> dtmFactoryPecas) {
        this.dtmFactoryPecas = dtmFactoryPecas;
    }
    
    public static FactoryPecaFacade getFactoryPecaFacade() {
        factoryPecaFacade = new FactoryPecaFacade();
        return factoryPecaFacade;
    }
    
    public void setFactoryPecaFacade(FactoryPecaFacade pecaFacade) {
        this.factoryPecaFacade = pecaFacade;
    }
    
    public FactoryPeca getFactoryPeca() {
        if (factoryPeca == null) {
            factoryPeca = new FactoryPeca();
        }
        return factoryPeca;
    }
    
    public void setFactoryPeca(FactoryPeca peca) {
        this.factoryPeca = peca;
    }
    
    public FactoryPeca getFactoryPecaCompare() {
        return pecaCompare;
    }
    
    public void setFactoryPecaCompare(FactoryPeca pecaCompare) {
        this.pecaCompare = pecaCompare;
    }
    
    public List<FactoryPeca> getFactoryPecasSelected() {
        return pecasSelected;
    }
    
    public void setFactoryPecasSelected(List<FactoryPeca> pecasSelected) {
        this.pecasSelected = pecasSelected;
    }
    
    public int getTipoLista() {
        return tipoLista;
    }
    
    public void setTipoLista(int tipoLista) {
        this.tipoLista = tipoLista;
    }
    
    public String getNomeBusca() {
        return nomeBusca;
    }
    
    public void setNomeBusca(String nomeBusca) {
        this.nomeBusca = nomeBusca;
    }
    
    public FactoryPeca getPecaCompare() {
        return pecaCompare;
    }
    
    public void setPecaCompare(FactoryPeca pecaCompare) {
        this.pecaCompare = pecaCompare;
    }
    
    public List<FactoryPeca> getPecas() {
        return pecas;
    }
    
    public void setPecas(List<FactoryPeca> pecas) {
        this.pecas = pecas;
    }
    
    public List<FactoryPeca> getPecasSelected() {
        return pecasSelected;
    }
    
    public void setPecasSelected(List<FactoryPeca> pecasSelected) {
        this.pecasSelected = pecasSelected;
    }
    
    public static List<FactoryContrato> getContratos() {
        return contratos;
    }
    
    private static void loadContratos() {
        contratos = new ContratoFactoryFacade().listDist();
        if (getContratos() == null) {
            contratos = new ArrayList<>();
        }
    }
    
    public void setContratos(List<FactoryContrato> contratos) {
        this.contratos = contratos;
    }
    
    public static PecaFacade getPecaFacade() {
        return pecaFacade;
    }
    
    public void setPecaFacade(PecaFacade pecaFacade) {
        this.pecaFacade = pecaFacade;
    }
    
    public static void main(String... args) {

        // TODO code application logic here  
        //usando SS.model  
        try {
            //FileInputStream file = new FileInputStream("Teste 2010.xlsx");  
            File file = new File("D:\\Varkon\\gdpc\\Documentos\\pecasresumidas.xlsx");
            String name = file.toString();
            int pos = name.lastIndexOf('.');
            String ext = name.substring(pos + 1);
            FileInputStream fileIn = new FileInputStream(file);
            Workbook obj = null;
            if (ext.equals("xlsx")) {
                try {
                    //Metodo aceita o path do arquivo  
                    obj = new XSSFWorkbook(fileIn);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (ext.equals("xls")) {
                try {
                    //Metodo nao aceita string do path do arquivo  
                    obj = new HSSFWorkbook(fileIn);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                throw new IllegalArgumentException("Received file does not have a standard excel extension.");
            }
            int o = 0;
            Sheet worksheet = obj.getSheet("Lista de Itens");
            Row row;
            Cell cell;
            for (int i = 0; i <= worksheet.getLastRowNum() - 1; i++) {
                
                row = worksheet.getRow(i);
                String linha = "";
                FactoryPeca peca = new FactoryPeca();
//                for (int j = 0; j < 2; j++) {
//                    cell = row.getCell(j);
//                    
//                    if (cell.getCellType() == 1) {
//                        linha += " | " + cell.getStringCellValue();
//                    } else {
//                        double aux = 0;
//                        int aux2 = 0;
//                        aux = cell.getNumericCellValue();
//                        aux2 = (int) aux;
//                        linha += " | " + aux2;
//                    }
//                }
                int a = 1;
                a += i;
                peca.setPEC_ID(0);
                cell = row.getCell(0);
                peca.setCodigo(cell.getStringCellValue());
                cell = row.getCell(1);
                peca.setDescricao(cell.getStringCellValue());
                
                System.out.println(peca.toString());
                System.out.println("");
                salvarExcel(peca);
                System.out.println("");
                
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FactoryPecaBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado");
        }
        
    }
    
    public static void salvarExcel(FactoryPeca fp) {
        boolean result = false;
        if (fp.getPEC_ID() == 0) { //save
            try {
                result = true;
                getFactoryPecaFacade().createPeca(fp);
                System.out.println("Peça salva com sucesso.");
//                showFactoryPeca();
            } catch (Exception e) {
                result = false;
                System.out.println(e.getMessage());
                System.out.println("Não foi possível salvar a Peça.");
            }
            if (result) {
                loadContratos();
                if (getContratos().size() > 0) {
                    for (FactoryContrato c : getContratos()) {
                        loadPecaFacade(c.getUsuId().getUSU_BANCO());
                        Peca p = new Peca(fp);
                        try {
                            getPecaFacade().createPeca(p);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        } else { //update
            try {
                result = true;
                getFactoryPecaFacade().updatePeca(fp);
                System.out.println("Peça atualizada com sucesso.");
//                showFactoryPeca();
            } catch (Exception e) {
                result = false;
                System.out.println(e.getMessage());
                System.out.println("Não foi possível atualizar a Peça.");
            }
            if (result) {
                loadContratos();
                if (getContratos().size() > 0) {
                    for (FactoryContrato c : getContratos()) {
                        loadPecaFacade(c.getUsuId().getUSU_BANCO());
                        Peca p = getPecaFacade().findPecaByFab(fp.getPEC_ID());
                        if (p == null) {
                            p = new Peca(fp);
                        }
                        p.setCodigo(fp.getCodigo());
                        p.setDescricao(fp.getDescricao());
                        try {
                            getPecaFacade().updatePeca(p);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println(fp);
        System.out.println("");
        System.out.println("");
        System.out.println("");
        
    }
    
}
