package com.gardnerdenver.bean;

import com.gardnerdenver.model.Generico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "genericoBean")
@RequestScoped
public class GenericoBean implements Serializable {

    private List<Generico> lista = new ArrayList<>(); //LISTA GERAL
    private List<Generico> listaParInt = new ArrayList<>(); // LISTA 1 A 60
    private List<Generico> listaSN = new ArrayList<>(); // SIM OU NAO
    private List<Generico> listaDM = new ArrayList<>(); // DIA, MES
    private List<Generico> listaDMN = new ArrayList<>(); // DIA, MES, nenhum
    private List<Generico> listaStatusTIT = new ArrayList<>(); // ABERTO QUITADO
    private List<Generico> listaTipoData = new ArrayList<>(); // VENCIMENTO EMISSAO QUITADO
    private List<Generico> listaTipoDataCx = new ArrayList<>(); // usado no caixa
    private List<Generico> listaTipoDataODS = new ArrayList<>(); //busca de ordem servico
    private List<Generico> listaTipoDataODC = new ArrayList<>(); //busca de ordem servico
    private List<Generico> listaTipoDataORC = new ArrayList<>(); //busca de ordem servico
    private List<Generico> listaTipoPlanoConta = new ArrayList<>(); //Planos de Conta
    private List<Generico> listaDigDoc = new ArrayList<>(); // Digito escolhido nas contas
    private List<Generico> listAcao = new ArrayList<>(); //LISTA DE AÇÕES QUITAÇÃO
    private List<Generico> listStOrdServ = new ArrayList<>(); //=STATUS ORDEM SERVICO
    private List<Generico> listStODSBusca = new ArrayList<>(); //=STATUS BUSCA
    private List<Generico> listStODSFinal = new ArrayList<>(); //=STATUS ORDEM SERVICO BUSCA
    private List<Generico> listStOrc = new ArrayList<>(); //status de orçamento
    private List<Generico> listStPed = new ArrayList<>(); //status pedido 
    private List<Generico> listStPedFin = new ArrayList<>(); //status pedido finalizado
    private List<Generico> listStPedOrc = new ArrayList<>(); //status pedido e orçamento
    private List<Generico> listStPedOrcTable = new ArrayList<>(); //status pedido e orçamento da tabela 
    private List<Generico> listStOrdemCompra = new ArrayList<>(); //status de ordem de compra
    private List<Generico> listStOrdemCompraBusca = new ArrayList<>(); //status de ordem de compra busca
    private List<Generico> listTipoPedOrc = new ArrayList<>(); //pedido / orçamento
    private List<Generico> listEntFinalOs = new ArrayList<>(); //entradas na finalização da OS
    private List<Generico> listViasImpressao = new ArrayList<>(); //Vias de Impressao
    private List<Generico> listPrecoVenda = new ArrayList<>();//configuracoes
    private List<Generico> listProTitem = new ArrayList<>(); //lsita de tipo de produto
    private List<Generico> listIndexFinanceiro = new ArrayList<>(); //lista de diaspara consulta no index
    private List<Generico> listFuncoesFunc = new ArrayList<>();
    private List<Generico> listAssuntoFaleConosco = new ArrayList<>();
    private List<Generico> listSitNota = new ArrayList<>();
    private List<Generico> listTipoDataNota = new ArrayList<>();
    private List<Generico> listTipoNota = new ArrayList<>();
    private List<Generico> listAmbNota = new ArrayList<>();

    public void setLista(List<Generico> lista) {
        this.lista = lista;
    }

    public List<Generico> getLista() {
        lista = new ArrayList<>();

        lista.add(new Generico(1, "01"));
        lista.add(new Generico(2, "02"));
        lista.add(new Generico(3, "03"));
        lista.add(new Generico(4, "04"));
        lista.add(new Generico(5, "05"));
        lista.add(new Generico(6, "06"));
        lista.add(new Generico(7, "07"));
        lista.add(new Generico(8, "08"));
        lista.add(new Generico(9, "09"));
        lista.add(new Generico(10, "10"));
        lista.add(new Generico(11, "11"));
        lista.add(new Generico(12, "12"));
        lista.add(new Generico(13, "13"));
        lista.add(new Generico(14, "14"));
        lista.add(new Generico(15, "15"));
        lista.add(new Generico(16, "16"));
        lista.add(new Generico(17, "17"));
        lista.add(new Generico(18, "18"));
        lista.add(new Generico(19, "19"));
        lista.add(new Generico(20, "20"));
        lista.add(new Generico(21, "21"));
        lista.add(new Generico(22, "22"));
        lista.add(new Generico(23, "23"));
        lista.add(new Generico(24, "24"));
        lista.add(new Generico(25, "25"));
        lista.add(new Generico(26, "26"));
        lista.add(new Generico(27, "27"));
        lista.add(new Generico(28, "28"));
        lista.add(new Generico(29, "29"));
        lista.add(new Generico(30, "30"));
        lista.add(new Generico(31, "31"));
        lista.add(new Generico(32, "32"));
        lista.add(new Generico(33, "33"));
        lista.add(new Generico(34, "34"));
        lista.add(new Generico(35, "35"));
        lista.add(new Generico(36, "36"));
        lista.add(new Generico(37, "37"));
        lista.add(new Generico(38, "38"));
        lista.add(new Generico(39, "39"));
        lista.add(new Generico(40, "40"));
        lista.add(new Generico(41, "41"));
        lista.add(new Generico(42, "42"));
        lista.add(new Generico(43, "43"));
        lista.add(new Generico(44, "44"));
        lista.add(new Generico(45, "45"));
        lista.add(new Generico(46, "46"));
        lista.add(new Generico(47, "47"));
        lista.add(new Generico(48, "48"));
        lista.add(new Generico(49, "49"));
        lista.add(new Generico(50, "50"));
        lista.add(new Generico(51, "51"));
        lista.add(new Generico(52, "52"));
        lista.add(new Generico(53, "53"));
        lista.add(new Generico(54, "54"));
        lista.add(new Generico(55, "55"));
        lista.add(new Generico(56, "56"));
        lista.add(new Generico(57, "57"));
        lista.add(new Generico(58, "58"));
        lista.add(new Generico(59, "59"));
        lista.add(new Generico(60, "60"));

        lista.add(new Generico(100, "Sim"));
        lista.add(new Generico(101, "Não"));

        lista.add(new Generico(110, "Aberto"));
        lista.add(new Generico(111, "Quitado"));

        lista.add(new Generico(120, "Dia(s)"));
        lista.add(new Generico(121, "Mês(es)"));
        lista.add(new Generico(122, "Nenhum"));

        lista.add(new Generico(130, "Numérico"));
        lista.add(new Generico(131, "Alfabético"));
        lista.add(new Generico(132, "Nenhum"));

        lista.add(new Generico(140, "Nenhuma"));
        lista.add(new Generico(141, "Criar Nova Conta"));

        lista.add(new Generico(150, "Sem entrada"));
        lista.add(new Generico(151, "Com entrada igual"));
        lista.add(new Generico(152, "Com entrada diferente"));

        lista.add(new Generico(160, "Aguardando Orçamento"));
        lista.add(new Generico(161, "Orçamento"));
        lista.add(new Generico(162, "Aguardando Aprovação"));
        lista.add(new Generico(163, "Aprovado"));
        lista.add(new Generico(164, "Em execução"));
        lista.add(new Generico(165, "Aguardando Peças"));
        lista.add(new Generico(166, "Pronto"));
        lista.add(new Generico(167, "Entregue"));
        lista.add(new Generico(168, "Cobrado em Contrato"));
        lista.add(new Generico(169, "Cancelado"));
        lista.add(new Generico(170, "Aberto"));
        lista.add(new Generico(171, "Todos Menos Cancelados"));

        lista.add(new Generico(181, "3 - Despesas"));
        lista.add(new Generico(182, "4 - Receitas"));

        lista.add(new Generico(191, "Alterar automaticamente"));
        lista.add(new Generico(192, "Solicitar ao Usuário"));
        lista.add(new Generico(193, "Não alterar"));

        lista.add(new Generico(200, "Sem filtro de data"));
        lista.add(new Generico(201, "Vencimento por período"));
        lista.add(new Generico(202, "Vencimento até"));
        lista.add(new Generico(203, "Vencimento depois de"));
        lista.add(new Generico(204, "Emissão por Período"));
        lista.add(new Generico(205, "Emissão até"));
        lista.add(new Generico(206, "Emissão depois de"));
        lista.add(new Generico(207, "Quitação por Período"));
        lista.add(new Generico(208, "Quitação até"));
        lista.add(new Generico(209, "Quitação depois de"));

        lista.add(new Generico(220, "Sem Filtro de Data"));
        lista.add(new Generico(221, "Hoje"));
        lista.add(new Generico(222, "Ontem"));
        lista.add(new Generico(223, "Últimos 7 dias"));
        lista.add(new Generico(224, "Últimos 14 dias"));
        lista.add(new Generico(225, "Últimos 30 dias"));
        lista.add(new Generico(226, "Esse mês"));
        lista.add(new Generico(227, "Por período"));

        lista.add(new Generico(230, "Sem Filtro de Data"));
        lista.add(new Generico(231, "Emissão"));
        lista.add(new Generico(232, "Previsão"));
        lista.add(new Generico(233, "Finalização"));
        lista.add(new Generico(234, "Validade"));

        lista.add(new Generico(241, "Aberto"));
        lista.add(new Generico(242, "Cancelado"));
        lista.add(new Generico(243, "Transformado em Venda"));

        lista.add(new Generico(251, "Aberto"));
        lista.add(new Generico(252, "Cancelado"));
        lista.add(new Generico(253, "Entregue"));
        lista.add(new Generico(254, "Reprovado"));
        lista.add(new Generico(255, "Entregue Parcialmente"));
        lista.add(new Generico(258, "Cobrado em Contrato"));

        lista.add(new Generico(261, "Aberto"));
        lista.add(new Generico(262, "Cancelado"));
        lista.add(new Generico(263, "Venda Entregue"));
        lista.add(new Generico(264, "Venda Reprovada"));
        lista.add(new Generico(265, "Orc. Transf. Venda"));

        lista.add(new Generico(271, "Venda / Orçamento"));
        lista.add(new Generico(272, "Venda"));
        lista.add(new Generico(273, "Orçamento"));

        lista.add(new Generico(281, "Aberta"));
        lista.add(new Generico(282, "Cancelada"));
        lista.add(new Generico(283, "Recebida"));
        lista.add(new Generico(284, "Abertas e Recebidas"));

        lista.add(new Generico(291, "Vendedor"));
        lista.add(new Generico(292, "Técnico"));
        lista.add(new Generico(293, "Acesso ao Sistema"));

        lista.add(new Generico(300, "00–Mercadoria para Revenda"));
        lista.add(new Generico(301, "01–Matéria - Prima"));
        lista.add(new Generico(302, "02–Embalagem"));
        lista.add(new Generico(303, "03–Produto em Processo"));
        lista.add(new Generico(304, "04–Produto Acabado"));
        lista.add(new Generico(305, "05–Subproduto"));
        lista.add(new Generico(306, "06–Produto Intermediário"));
        lista.add(new Generico(307, "07–Material de Uso e Consumo"));
        lista.add(new Generico(308, "08–Ativo Imobilizado"));
        lista.add(new Generico(309, "09–Serviços"));
        lista.add(new Generico(310, "10–Outros insumos"));
        lista.add(new Generico(399, "99– Outras"));

        lista.add(new Generico(320, "Emissão"));
        lista.add(new Generico(321, "Entrada / Saída"));

        lista.add(new Generico(330, "Todas"));
        lista.add(new Generico(331, "Em Digitação"));
        lista.add(new Generico(332, "Autorizada"));
        lista.add(new Generico(333, "Rejeitada"));
        lista.add(new Generico(334, "Denegada"));
        lista.add(new Generico(335, "Cancelada"));
        lista.add(new Generico(336, "Inutilizada"));
        lista.add(new Generico(337, "Em digitação / rejeitada"));

        lista.add(new Generico(400, "Até hoje"));
        lista.add(new Generico(401, "Próximos 7 dias"));
        lista.add(new Generico(402, "Próximos 30 dias"));
        lista.add(new Generico(403, "Próximos 60 dias"));

        lista.add(new Generico(411, "Dúvida"));
        lista.add(new Generico(412, "Sugestão"));
        lista.add(new Generico(413, "Financeiro"));
        lista.add(new Generico(414, "Reclamação"));
        lista.add(new Generico(415, "Elogio"));
        lista.add(new Generico(416, "Chamado Técnico"));

        lista.add(new Generico(340, "Todas"));
        lista.add(new Generico(341, "Entrada"));
        lista.add(new Generico(342, "Saída"));

        lista.add(new Generico(350, "Todos"));
        lista.add(new Generico(351, "Homologação"));
        lista.add(new Generico(352, "Produção"));

        return lista;
    }

    public List<Generico> getListaSN() {
        listaSN = new ArrayList<>();
        listaSN.add(new Generico(100, "Sim"));
        listaSN.add(new Generico(101, "Não"));

        return listaSN;
    }

    public void setListaSN(List<Generico> listaSN) {
        this.listaSN = listaSN;
    }

    public List<Generico> getListaStatusTIT() {
        listaStatusTIT = new ArrayList<>();
        listaStatusTIT.add(new Generico(110, "Aberto"));
        listaStatusTIT.add(new Generico(111, "Quitado"));
        return listaStatusTIT;
    }

    public void setListaStatusTIT(List<Generico> listaStatusTIT) {
        this.listaStatusTIT = listaStatusTIT;
    }

    public List<Generico> getListaDM() {
        listaDM = new ArrayList<>();
        listaDM.add(new Generico(120, "Dia(s)"));
        listaDM.add(new Generico(121, "Mês(es)"));
        return listaDM;
    }

    public void setListaDM(List<Generico> listaDM) {
        this.listaDM = listaDM;
    }

    public List<Generico> getListaDMN() {
        listaDMN = new ArrayList<>();
        listaDMN.add(new Generico(120, "Dia(s)"));
        listaDMN.add(new Generico(121, "Mês(es)"));
        listaDMN.add(new Generico(122, "Nenhum"));
        return listaDMN;
    }

    public void setListaDMN(List<Generico> listaDMN) {
        this.listaDMN = listaDMN;
    }

    public List<Generico> getListaDigDoc() {
        listaDigDoc = new ArrayList<>();
        listaDigDoc.add(new Generico(130, "Numérico"));
        listaDigDoc.add(new Generico(131, "Alfabético"));
        listaDigDoc.add(new Generico(132, "Nenhum"));
        return listaDigDoc;
    }

    public void setListaDigDoc(List<Generico> listaDigDoc) {
        this.listaDigDoc = listaDigDoc;
    }

    public List<Generico> getListAcao() {
        listAcao = new ArrayList<>();
        listAcao.add(new Generico(140, "Nenhuma"));
        listAcao.add(new Generico(141, "Criar Nova Conta"));
        return listAcao;
    }

    public void setListAcao(List<Generico> listAcao) {
        this.listAcao = listAcao;
    }

    public List<Generico> getListEntFinal() {
        listEntFinalOs = new ArrayList<>();
        listEntFinalOs.add(new Generico(150, "Sem entrada"));
        listEntFinalOs.add(new Generico(151, "Com entrada igual"));
        listEntFinalOs.add(new Generico(152, "Com entrada diferente"));
        return listEntFinalOs;
    }

    public void setListEntFinalOs(List<Generico> listEntFinalOs) {
        this.listEntFinalOs = listEntFinalOs;
    }

    public List<Generico> getListStOrdServ() {
        listStOrdServ = new ArrayList<>();

        listStOrdServ.add(new Generico(160, "Aguardando Orçamento"));
        listStOrdServ.add(new Generico(161, "Orçamento"));
        listStOrdServ.add(new Generico(162, "Aguardando Aprovação"));
        listStOrdServ.add(new Generico(163, "Aprovado"));
        listStOrdServ.add(new Generico(164, "Em execução"));
        listStOrdServ.add(new Generico(165, "Aguardando Peças"));
        listStOrdServ.add(new Generico(166, "Pronto"));

        return listStOrdServ;
    }

    public void setListStOrdServ(List<Generico> listStOrdServ) {
        this.listStOrdServ = listStOrdServ;
    }

    public List<Generico> getListStODSBusca() {
        listStODSBusca = new ArrayList<>();

        listStODSBusca.add(new Generico(160, "Aguardando Orçamento"));
        listStODSBusca.add(new Generico(161, "Orçamento"));
        listStODSBusca.add(new Generico(162, "Aguardando Aprovação"));
        listStODSBusca.add(new Generico(163, "Aprovado"));
        listStODSBusca.add(new Generico(164, "Em execução"));
        listStODSBusca.add(new Generico(165, "Aguardando Peças"));
        listStODSBusca.add(new Generico(166, "Pronto"));
        listStODSBusca.add(new Generico(167, "Finalizado"));
        listStODSBusca.add(new Generico(168, "Cobrado em Contrato"));
        listStODSBusca.add(new Generico(169, "Cancelado"));
        listStODSBusca.add(new Generico(170, "Aberto"));
        listStODSBusca.add(new Generico(171, "Todos Menos Cancelados"));

        return listStODSBusca;
    }

    public void setListStODSBusca(List<Generico> listStODSBusca) {
        this.listStODSBusca = listStODSBusca;
    }

    public List<Generico> getListStODSFinal() {
        listStODSFinal = new ArrayList<>();

        listStODSFinal.add(new Generico(167, "Entregue"));
        listStODSFinal.add(new Generico(168, "Cobrado em Contrato"));

        return listStODSFinal;
    }

    public void setListStODSFinal(List<Generico> listStODSFinal) {
        this.listStODSFinal = listStODSFinal;
    }

    public List<Generico> getListStOrc() {
        listStOrc = new ArrayList<>();

        listStOrc.add(new Generico(241, "Aberto"));
        listStOrc.add(new Generico(242, "Cancelado"));
        listStOrc.add(new Generico(243, "Transformado em Venda"));

        return listStOrc;
    }

    public void setListStOrc(List<Generico> listStOrc) {
        this.listStOrc = listStOrc;
    }

    public List<Generico> getListStPed() {
        listStPed = new ArrayList<>();

        listStPed.add(new Generico(251, "Aberto"));
        listStPed.add(new Generico(252, "Cancelado"));
        listStPed.add(new Generico(253, "Entregue"));
        listStPed.add(new Generico(254, "Reprovado"));
        listStPed.add(new Generico(258, "Cobrado em Contrato"));

        return listStPed;
    }

    public void setListStPed(List<Generico> listStPed) {
        this.listStPed = listStPed;
    }

    public List<Generico> getListStPedFin() {
        listStPedFin = new ArrayList<>();

        listStPedFin.add(new Generico(253, "Entregue"));
        listStPedFin.add(new Generico(255, "Entregue Parcialmente"));
        //listStPedFin.add(new Generico(254, "Reprovado"));

        return listStPedFin;
    }

    public void setListStPedFin(List<Generico> listStPedFin) {
        this.listStPedFin = listStPedFin;
    }

    public List<Generico> getListStPedOrc() {
        listStPedOrc = new ArrayList<>();

        listStPedOrc.add(new Generico(261, "Aberto"));
        listStPedOrc.add(new Generico(262, "Cancelado"));
        listStPedOrc.add(new Generico(263, "Entregue"));
        //listStPedOrc.add(new Generico(264, "Reprovado"));
        //listStPedOrc.add(new Generico(265, "Transformado em Venda"));

        return listStPedOrc;
    }

    public void setListStPedOrc(List<Generico> listStPedOrc) {
        this.listStPedOrc = listStPedOrc;
    }

    public List<Generico> getListStPedOrcTable() {
        listStPedOrcTable = new ArrayList<>();
        listStPedOrcTable.add(new Generico(241, "Aberto"));
        listStPedOrcTable.add(new Generico(242, "Cancelado"));
        listStPedOrcTable.add(new Generico(243, "Transformado em Venda"));
        listStPedOrcTable.add(new Generico(251, "Aberto"));
        listStPedOrcTable.add(new Generico(252, "Cancelado"));
        listStPedOrcTable.add(new Generico(253, "Entregue"));
        listStPedOrcTable.add(new Generico(254, "Reprovado"));
        listStPedOrcTable.add(new Generico(258, "Cobrado em Contrato"));
        return listStPedOrcTable;
    }

    public void setListStPedOrcTable(List<Generico> listStPedOrcTable) {
        this.listStPedOrcTable = listStPedOrcTable;
    }

    public List<Generico> getListTipoPedOrc() {
        listTipoPedOrc = new ArrayList<>();

        listTipoPedOrc.add(new Generico(271, "Venda / Orçamento"));
        listTipoPedOrc.add(new Generico(272, "Venda"));
        listTipoPedOrc.add(new Generico(273, "Orçamento"));

        return listTipoPedOrc;
    }

    public void setListTipoPedOrc(List<Generico> listTipoPedOrc) {
        this.listTipoPedOrc = listTipoPedOrc;
    }

    public List<Generico> getListStOrdemCompra() {
        listStOrdemCompra = new ArrayList<>();
        listStOrdemCompra.add(new Generico(281, "Aberta"));
        listStOrdemCompra.add(new Generico(282, "Cancelada"));
        listStOrdemCompra.add(new Generico(283, "Recebida"));

        return listStOrdemCompra;
    }

    public void setListStOrdemCompra(List<Generico> listStOrdemCompra) {
        this.listStOrdemCompra = listStOrdemCompra;
    }

    public List<Generico> getListStOrdemCompraBusca() {
        listStOrdemCompraBusca = new ArrayList<>();
        listStOrdemCompraBusca.add(new Generico(281, "Aberta"));
        listStOrdemCompraBusca.add(new Generico(282, "Cancelada"));
        listStOrdemCompraBusca.add(new Generico(283, "Recebida"));
        listStOrdemCompraBusca.add(new Generico(284, "Abertas e Recebidas"));

        return listStOrdemCompraBusca;
    }

    public void setListStOrdemCompraBusca(List<Generico> listStOrdemCompraBusca) {
        this.listStOrdemCompraBusca = listStOrdemCompraBusca;
    }

    public void setListaTipoData(List<Generico> listaTipoData) {
        this.listaTipoData = listaTipoData;
    }

    public List<Generico> getListaTipoData() {
        listaTipoData = new ArrayList<>();
        //listaTipoData.add(new Generico(200, "Sem Filtro de Data"));
        listaTipoData.add(new Generico(201, "Vencimento por período"));
        listaTipoData.add(new Generico(202, "Vencimento até"));
        listaTipoData.add(new Generico(203, "Vencimento depois de"));
        listaTipoData.add(new Generico(204, "Emissão por Período"));
        listaTipoData.add(new Generico(205, "Emissão até"));
        listaTipoData.add(new Generico(206, "Emissão depois de"));
        listaTipoData.add(new Generico(207, "Quitação por Período"));
        listaTipoData.add(new Generico(208, "Quitação até"));
        listaTipoData.add(new Generico(209, "Quitação depois de"));

        return listaTipoData;
    }

    public void setListaTipoDataCx(List<Generico> listaTipoDataCx) {
        this.listaTipoDataCx = listaTipoDataCx;
    }

    public List<Generico> getListaTipoDataCx() {
        listaTipoDataCx = new ArrayList<>();
        listaTipoDataCx.add(new Generico(220, "Sem Filtro de Data"));
        listaTipoDataCx.add(new Generico(221, "Hoje"));
        listaTipoDataCx.add(new Generico(222, "Ontem"));
        listaTipoDataCx.add(new Generico(223, "Últimos 7 dias"));
        listaTipoDataCx.add(new Generico(224, "Últimos 14 dias"));
        listaTipoDataCx.add(new Generico(225, "Últimos 30 dias"));
        listaTipoDataCx.add(new Generico(226, "Esse mês"));
        listaTipoDataCx.add(new Generico(227, "Por período"));

        return listaTipoDataCx;
    }

    public List<Generico> getListaTipoDataODS() {
        listaTipoDataODS = new ArrayList<>();
        //listaTipoDataODS.add(new Generico(230, "Sem Filtro de Data"));
        listaTipoDataODS.add(new Generico(231, "Emissão"));
        listaTipoDataODS.add(new Generico(232, "Previsão"));
        listaTipoDataODS.add(new Generico(233, "Finalização"));

        return listaTipoDataODS;
    }

    public List<Generico> getListaTipoDataODC() {
        listaTipoDataODC = new ArrayList<>();
        //listaTipoDataODS.add(new Generico(230, "Sem Filtro de Data"));
        listaTipoDataODC.add(new Generico(231, "Emissão"));
        listaTipoDataODC.add(new Generico(232, "Previsão"));
        listaTipoDataODC.add(new Generico(233, "Finalização"));

        return listaTipoDataODC;
    }

    public List<Generico> getListaTipoDataORC() {
        listaTipoDataORC = new ArrayList<>();
        //listaTipoDataODS.add(new Generico(230, "Sem Filtro de Data"));
        listaTipoDataORC.add(new Generico(231, "Emissão"));
        listaTipoDataORC.add(new Generico(234, "Validade"));
        listaTipoDataORC.add(new Generico(233, "Finalização"));

        return listaTipoDataORC;
    }

    public List<Generico> getListaTipoPlanoConta() {
        listaTipoPlanoConta = new ArrayList<>();
        //listaTipoDataODS.add(new Generico(180, "Sem Filtro"));
        listaTipoPlanoConta.add(new Generico(181, "3 - Despesas"));
        listaTipoPlanoConta.add(new Generico(182, "4 - Receitas"));

        return listaTipoPlanoConta;
    }

    public void setListaTipoDataODS(List<Generico> listaTipoDataODS) {
        this.listaTipoDataODS = listaTipoDataODS;
    }

    public void setListaTipoDataODC(List<Generico> listaTipoDataODC) {
        this.listaTipoDataODC = listaTipoDataODC;
    }

    public void setListaTipoDataORC(List<Generico> listaTipoDataORC) {
        this.listaTipoDataORC = listaTipoDataORC;
    }

    public void setListaTipoPlanoConta(List<Generico> listaTipoPlanoConta) {
        this.listaTipoPlanoConta = listaTipoPlanoConta;
    }

    public List<Generico> getListViasImpressao() {
        listViasImpressao = new ArrayList<>();
        //listViasImpressao.add(new Generico(0, "Sem Filtro"));
        listViasImpressao.add(new Generico(1, "01"));
        listViasImpressao.add(new Generico(2, "02"));
        listViasImpressao.add(new Generico(3, "03"));

        return listViasImpressao;
    }

    public void setListViasImpressao(List<Generico> listViasImpressao) {
        this.listViasImpressao = listViasImpressao;
    }

    public List<Generico> getListPrecoVenda() {
        listPrecoVenda = new ArrayList<>();
        //listPrecoVenda.add(new Generico(190, "Sem Filtro"));
        listPrecoVenda.add(new Generico(191, "Alterar automaticamente"));
        listPrecoVenda.add(new Generico(192, "Solicitar ao Usuário"));
        listPrecoVenda.add(new Generico(193, "Não alterar"));

        return listPrecoVenda;
    }

    public void setListPrecoVenda(List<Generico> listPrecoVenda) {
        this.listPrecoVenda = listPrecoVenda;
    }

    public List<Generico> getListProTitem() {
        listProTitem = new ArrayList<>();

        listProTitem.add(new Generico(300, "00–Mercadoria para Revenda"));
        listProTitem.add(new Generico(301, "01–Matéria - Prima"));
        listProTitem.add(new Generico(302, "02–Embalagem"));
        listProTitem.add(new Generico(303, "03–Produto em Processo"));
        listProTitem.add(new Generico(304, "04–Produto Acabado"));
        listProTitem.add(new Generico(305, "05–Subproduto"));
        listProTitem.add(new Generico(306, "06–Produto Intermediário"));
        listProTitem.add(new Generico(307, "07–Material de Uso e Consumo"));
        listProTitem.add(new Generico(308, "08–Ativo Imobilizado"));
        listProTitem.add(new Generico(309, "09–Serviços"));
        listProTitem.add(new Generico(310, "10–Outros insumos"));
        listProTitem.add(new Generico(399, "99–Outras"));
        return listProTitem;
    }

    public void setListProTitem(List<Generico> listProTitem) {
        this.listProTitem = listProTitem;
    }

    public List<Generico> getListSitNota() {
        listSitNota = new ArrayList<>();

        listSitNota.add(new Generico(330, "Todas"));
        listSitNota.add(new Generico(331, "Em Digitação"));
        listSitNota.add(new Generico(332, "Autorizada"));
        listSitNota.add(new Generico(333, "Rejeitada"));
        listSitNota.add(new Generico(334, "Denegada"));
        listSitNota.add(new Generico(335, "Cancelada"));
        listSitNota.add(new Generico(336, "Inutilizada"));
        listSitNota.add(new Generico(337, "Em digitação / rejeitada"));
        return listSitNota;
    }

    public void setListSitNota(List<Generico> listSitNota) {
        this.listSitNota = listSitNota;
    }

    public List<Generico> getListTipoDataNota() {
        listTipoDataNota = new ArrayList<>();
        listTipoDataNota.add(new Generico(220, "Sem Filtro de Data"));
        listTipoDataNota.add(new Generico(320, "Emissão"));
        listTipoDataNota.add(new Generico(321, "Entrada / Saída"));
        return listTipoDataNota;
    }

    public void setListTipoDataNota(List<Generico> listTipoDataNota) {
        this.listTipoDataNota = listTipoDataNota;
    }

    public List<Generico> getListTipoNota() {
        listTipoNota = new ArrayList<>();
        listTipoNota.add(new Generico(340, "Todas"));
        listTipoNota.add(new Generico(341, "Entrada"));
        listTipoNota.add(new Generico(342, "Saída"));
        return listTipoNota;
    }

    public void setListTipoNota(List<Generico> listTipoNota) {
        this.listTipoNota = listTipoNota;
    }

    public List<Generico> getListAmbNota() {
        listAmbNota = new ArrayList<>();
        listAmbNota.add(new Generico(350, "Todos"));
        listAmbNota.add(new Generico(351, "Homologação"));
        listAmbNota.add(new Generico(352, "Produção"));
        return listAmbNota;
    }

    public void setListAmbNota(List<Generico> listAmbNota) {
        this.listAmbNota = listAmbNota;
    }

    public List<Generico> getListaParInt() {
        listaParInt = new ArrayList<>();

        listaParInt.add(new Generico(1, "01"));
        listaParInt.add(new Generico(2, "02"));
        listaParInt.add(new Generico(3, "03"));
        listaParInt.add(new Generico(4, "04"));
        listaParInt.add(new Generico(5, "05"));
        listaParInt.add(new Generico(6, "06"));
        listaParInt.add(new Generico(7, "07"));
        listaParInt.add(new Generico(8, "08"));
        listaParInt.add(new Generico(9, "09"));
        listaParInt.add(new Generico(10, "10"));
        listaParInt.add(new Generico(11, "11"));
        listaParInt.add(new Generico(12, "12"));
        listaParInt.add(new Generico(13, "13"));
        listaParInt.add(new Generico(14, "14"));
        listaParInt.add(new Generico(15, "15"));
        listaParInt.add(new Generico(16, "16"));
        listaParInt.add(new Generico(17, "17"));
        listaParInt.add(new Generico(18, "18"));
        listaParInt.add(new Generico(19, "19"));
        listaParInt.add(new Generico(20, "20"));
        listaParInt.add(new Generico(21, "21"));
        listaParInt.add(new Generico(22, "22"));
        listaParInt.add(new Generico(23, "23"));
        listaParInt.add(new Generico(24, "24"));
        listaParInt.add(new Generico(25, "25"));
        listaParInt.add(new Generico(26, "26"));
        listaParInt.add(new Generico(27, "27"));
        listaParInt.add(new Generico(28, "28"));
        listaParInt.add(new Generico(29, "29"));
        listaParInt.add(new Generico(30, "30"));
        listaParInt.add(new Generico(31, "31"));
        listaParInt.add(new Generico(32, "32"));
        listaParInt.add(new Generico(33, "33"));
        listaParInt.add(new Generico(34, "34"));
        listaParInt.add(new Generico(35, "35"));
        listaParInt.add(new Generico(36, "36"));
        listaParInt.add(new Generico(37, "37"));
        listaParInt.add(new Generico(38, "38"));
        listaParInt.add(new Generico(39, "39"));
        listaParInt.add(new Generico(40, "40"));
        listaParInt.add(new Generico(41, "41"));
        listaParInt.add(new Generico(42, "42"));
        listaParInt.add(new Generico(43, "43"));
        listaParInt.add(new Generico(44, "44"));
        listaParInt.add(new Generico(45, "45"));
        listaParInt.add(new Generico(46, "46"));
        listaParInt.add(new Generico(47, "47"));
        listaParInt.add(new Generico(48, "48"));
        listaParInt.add(new Generico(49, "49"));
        listaParInt.add(new Generico(50, "50"));
        listaParInt.add(new Generico(51, "51"));
        listaParInt.add(new Generico(52, "52"));
        listaParInt.add(new Generico(53, "53"));
        listaParInt.add(new Generico(54, "54"));
        listaParInt.add(new Generico(55, "55"));
        listaParInt.add(new Generico(56, "56"));
        listaParInt.add(new Generico(57, "57"));
        listaParInt.add(new Generico(58, "58"));
        listaParInt.add(new Generico(59, "59"));
        listaParInt.add(new Generico(60, "60"));
        return listaParInt;
    }

    public void setListaParInt(List<Generico> listaParInt) {
        this.listaParInt = listaParInt;
    }

    public List<Generico> getListIndexFinanceiro() {
        listIndexFinanceiro = new ArrayList<>();
        listIndexFinanceiro.add(new Generico(400, "Até hoje"));
        listIndexFinanceiro.add(new Generico(401, "Próximos 7 dias"));
        listIndexFinanceiro.add(new Generico(402, "Próximos 30 dias"));
        listIndexFinanceiro.add(new Generico(403, "Próximos 60 dias"));
        return listIndexFinanceiro;
    }

    public void setListIndexFinanceiro(List<Generico> listIndexFinanceiro) {
        this.listIndexFinanceiro = listIndexFinanceiro;
    }

    public List<Generico> getListFuncoesFunc() {
        listFuncoesFunc = new ArrayList<>();

        listFuncoesFunc.add(new Generico(291, "Vendedor"));
        listFuncoesFunc.add(new Generico(292, "Técnico"));
        listFuncoesFunc.add(new Generico(293, "Acesso ao Sistema"));
        return listFuncoesFunc;
    }

    public void setListFuncoesFunc(List<Generico> listFuncoesFunc) {

        this.listFuncoesFunc = listFuncoesFunc;
    }

    public List<Generico> getListAssuntoFaleConosco() {
        listAssuntoFaleConosco = new ArrayList<>();
        listAssuntoFaleConosco.add(new Generico(411, "Dúvida"));
        listAssuntoFaleConosco.add(new Generico(412, "Sugestão"));
        listAssuntoFaleConosco.add(new Generico(413, "Financeiro"));
        listAssuntoFaleConosco.add(new Generico(414, "Reclamação"));
        listAssuntoFaleConosco.add(new Generico(415, "Elogio"));
        listAssuntoFaleConosco.add(new Generico(416, "Chamado Técnico"));
        return listAssuntoFaleConosco;
    }

    public void setListAssuntoFaleConosco(List<Generico> listAssuntoFaleConosco) {
        this.listAssuntoFaleConosco = listAssuntoFaleConosco;
    }
}
