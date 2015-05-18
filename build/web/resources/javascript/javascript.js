/* 
 * CommandButton
 */
function disableSubmit(id) {
    $("#" + escapeJSFid(id)).click(
            function() {
                alert("DISABLED");
                return false;
            });
}


function setaFoco(elemento) {
    var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
    if (keyCode == 13) {
        var i;
        for (i = 0; i < elemento.form.elements.length; i++)
            if (elemento == elemento.form.elements[i])
                break;
        i = (i + 1) % elemento.form.elements.length;
        elemento.form.elements[i].focus();
        event.preventDefault();
        return false;
    }
    return false;
}

function setaTab() {
    var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
    if (keyCode == 13) {
        keyCode = 9;
        return false;
    } else {
        return true;
    }
}

window.onload = function() {

    if (document.getElementById("cadastro") != undefined) {
        document.getElementById("cadastro:nomerazao").focus();
    }

    //carregar cookies nos campos input login
    if (document.getElementById("login") != undefined) {
        //alert("identificador: " + LerCookie("identificador"));
        //alert("login: "+LerCookie("login"));

        identificador = LerCookie("identificador");
        login = LerCookie("login");
        var arrCookies = document.cookie.split(';');
        for (var i = 0; i < arrCookies.length; i++) {
            //alert(arrCookies[i].toString());
        }

        if ((identificador != null) && (identificador != "") && (identificador != '""')) {
            document.getElementById("fmlogin:lgEmpresa").value = identificador;
        }
        if ((login != null) && (login != "") && (login != '""')) {
            document.getElementById("fmlogin:lgUsuario").value = login;
        }
        if (document.getElementById("fmlogin:lgEmpresa").value == "") {
            document.getElementById("fmlogin:lgEmpresa").focus();
            //alert('foco CNPJ');
        } else if (document.getElementById("fmlogin:lgUsuario").value == "") {
            document.getElementById("fmlogin:lgUsuario").focus();
            //alert('foco Usu');
        } else if (document.getElementById("fmlogin:lgSenha").value == "") {
            document.getElementById("fmlogin:lgSenha").focus();
            //alert('foco Sen');
        } else {
            document.getElementById("fmlogin:btLogin").focus();
            //alert('foco bt');
        }
    }
//
//
//
//
//
//
//
//
//    shortcut.add("Ctrl+f1", function() {
//        alert("HELP!");
//    });
//    if (document.getElementById("fmproduto") != undefined) {                  // Tela de Produto
//        //alert("fmProduto");
//        shortcut.add("f8", function() {
//            document.getElementById("fmproduto:btNew").click();
//        });
//        document.getElementById("fmproduto:codNome").focus();
//    } else if (document.getElementById("fmprodutoregistro") != undefined) {   // Produto Registro
//        shortcut.add("f8", function() {
//            document.getElementById("fmprodutoregistro:btSave").click();
//        });
//        shortcut.add("f4", function() {
//            document.getElementById("fmprodutoregistro:btCanc").click();
//        });
//        document.getElementById("fmprodutoregistro:prodNome").focus();
//    } else if (document.getElementById("fmcondpgtoregistro") != undefined) {   //   //Cond Pgto Registro
//        if (document.getElementById("fmcondpgtoregistro:cpt_nome").value == "") {
//            document.getElementById("fmcondpgtoregistro:cpt_nome").focus();
//        } else {
//            document.getElementById("fmcondpgtoregistro:coi_prazoDLG").focus();
//        }
//        shortcut.add("f8", function() {
//            document.getElementById("fmcondpgtoregistro:btSaveCond").click();
//        });
//        shortcut.add("f4", function() {
//            document.getElementById("fmcondpgtoregistro:btCancCond").click();
//        });
//    } else if (document.getElementById("fmcusto") != undefined) {           //   //Custo
//        shortcut.add("f8", function() {
//            document.getElementById("fmcusto:cctBtNew").click();
//        });
//    } else if (document.getElementById("fmcustoregistro") != undefined) {   //   //Custo Registro
//        if (document.getElementById("fmcustoregistro:cct_nome").value == "") {
//            document.getElementById("fmcustoregistro:cct_nome").focus();
//        } else {
//            document.getElementById("fmcustoregistro:cti_nomeDLG").focus();
//        }
//        shortcut.add("f8", function() {
//            document.getElementById("fmcustoregistro:cctBtSave").click();
//        });
//        shortcut.add("f4", function() {
//            document.getElementById("fmcustoregistro:cctBtCanc").click();
//        });
//    } else if (document.getElementById("fmcondicaopagamento") != undefined) {                  // Tela de Produto
//        //alert("condicaopagamento");
//        shortcut.add("f8", function() {
//            document.getElementById("fmcondicaopagamento:btNewCond").click();
//        });
//    } else if (document.getElementById("fmpdv") != undefined) {                  // Tela de PDV
//        shortcut.add("f8", function() {
//            document.getElementById("fmpdv:btNewpdv").click();
//        });
//    } else if (document.getElementById("fmgrupo") != undefined) {                  // Tela de Produto
//        shortcut.add("f8", function() {
//            document.getElementById("fmgrupo:btNewGrp").click();
//        });
//    } else if (document.getElementById("fmgruporegistro") != undefined) {   //   //Cond Pgto Registro
//        shortcut.add("f8", function() {
//            document.getElementById("fmgruporegistro:btSaveGRP").click();
//        });
//        shortcut.add("f4", function() {
//            document.getElementById("fmgruporegistro:btCancGRP").click();
//        });
//    } else if (document.getElementById("fmkardexregistro") != undefined) {
//        shortcut.add("f8", function() {
//            document.getElementById("fmkardexregistro:btSalvarKDX").click();
//        });
//        shortcut.add("f4", function() {
//            document.getElementById("fmkardexregistro:btCancKDX").click();
//        });
//    } else if (document.getElementById("fmkardex") != undefined) {
//        document.getElementById("fmkardex:codProId").focus();
//        shortcut.add("f8", function() {
//            document.getElementById("fmkardex:btNewKdx").click();
//        });
//    } else if (document.getElementById("fmcaixacontrole") != undefined) {
//        shortcut.add("f8", function() {
//            document.getElementById("fmcaixacontrole:btNewcxc").click();
//        });
//    } else if (document.getElementById("fmcaixaregistro") != undefined) {
//        shortcut.add("f8", function() {
//            document.getElementById("fmcaixaregistro:btSaveCXI").click();
//        });
//        shortcut.add("f4", function() {
//            document.getElementById("fmcaixaregistro:btCancCXI").click();
//        });
//    } else if (document.getElementById("fmempresaregistro") != undefined) {
//        shortcut.add("f8", function() {
//            document.getElementById("fmempresaregistro:empBtSave").click();
//        });
//    } else if (document.getElementById("fmfuncionario") != undefined) {          // Funcionario
//        shortcut.add("f8", function() {
//            document.getElementById("fmfuncionario:funcBtNew").click();
//        });
//    } else if (document.getElementById("fmfuncionarioregistro") != undefined) {  // Funcionario Registro
//        shortcut.add("f4", function() {
//            document.getElementById("fmfuncionarioregistro:funcBtCanc").click();
//        });
//        shortcut.add("f8", function() {
//            document.getElementById("fmfuncionarioregistro:funcBtSave").click();
//        });
//    } else if (document.getElementById("fmparceiro") != undefined) {          // Parceiro
//        shortcut.add("f8", function() {
//            document.getElementById("fmparceiro:parcBtNew").click();
//        });
//    } else if (document.getElementById("fmparceiroregistro") != undefined) {  // Parceiro Registro
//        shortcut.add("f4", function() {
//            document.getElementById("fmparceiroregistro:parcBtCanc").click();
//        });
//        shortcut.add("f8", function() {
//            document.getElementById("fmparceiroregistro:parcBtSave").click();
//        });
//    } else if (document.getElementById("fmindex") != undefined) {  // Index
//        //
//    } else if (document.getElementById("fmpedidoregistro") != undefined) {  // Index
//        shortcut.add("f8", function() {
//            document.getElementById("fmpedidoregistro:btSavePED").click();
//        });
//        shortcut.add("f4", function() {
//            document.getElementById("fmpedidoregistro:btCancPED").click();
//        });
//        document.getElementById("fmpedidoregistro:pedPro_Id").focus();
//    } else if (document.getElementById("fmpedido") != undefined) {  // Index
//        shortcut.add("f8", function() {
//            document.getElementById("fmpedido:btNewped").click();
//        });
//    } else if (document.getElementById("fmtitulo") != undefined) {  // Titulo
//        shortcut.add("f8", function() {
//            document.getElementById("fmtitulo:titBtNew").click();
//        });
//    } else if (document.getElementById("fmtituloregistro") != undefined) {  // Index
//        shortcut.add("f8", function() {
//            document.getElementById("fmtituloregistro:btSaveTIT").click();
//        });
//        shortcut.add("f4", function() {
//            document.getElementById("fmtituloregistro:btCancTIT").click();
//        });
//    } else if (document.getElementById("fmossituacao") != undefined) {          // Situação
//        shortcut.add("f8", function() {
//            document.getElementById("fmossituacao:btNewSit").click();
//        });
//    } else if (document.getElementById("fmpedidoprint") != undefined) {
//        window.print();
//        window.history.go(-1);
//    } else if (document.getElementById("fmordemservico") != undefined) {  // Ordem Serviço
//        shortcut.add("f4", function() {
//            document.getElementById("fmordemservico:btLimpOds").click();
//        });
//        shortcut.add("f8", function() {
//            document.getElementById("fmordemservico:btSaveOds").click();
//        });
//    } else if (document.getElementById("fmordemservicoconsulta") != undefined) {  // Consulta Ordem Serviço      
//        shortcut.add("f8", function() {
//            document.getElementById("fmordemservico:btNewPeds").click();
//        });
//    } else if (document.getElementById("fmordemservicoprint") != undefined) {
//        document.getElementById("fmordemservicoprint:odsDefRel"); // Ordem serviço print
//        //document.getElementById("fmordemservicoprint").window.print();
//        //document.getElementById("fmordemservicoprint:odsDescProd").focus();
//        //document.getElementById("fmordemservicoprint:odsObsTec").focus();
//        //document.getElementById("fmordemservicoprint:odsDefRel").focus();
//        //document.getElementById("fmordemservicoprint:odsObsGel").focus();
//        //alert("A");
//        //document.getElementById("fmordemservicoprint").window.close();
//    }






    PrimeFaces.widget.CommandButton = function(id, cfg) {
        this.id = id;
        this.cfg = cfg;
        this.jqId = PrimeFaces.escapeClientId(id);
        this.jq = jQuery(this.jqId);
        this.jq.button(this.cfg);
        //firefox focus fix
        this.jq.mouseout(function() {
            jQuery(this).removeClass('ui-state-focus');
        });
    }

    PrimeFaces.widget.CommandButton.prototype.disable = function() {
        this.jq.button('disable');
    }

    PrimeFaces.widget.CommandButton.prototype.enable = function() {
        this.jq.button('enable');
    }

    PrimeFaces.widget.LinkButton = function(id, cfg) {
        this.id = id;
        this.cfg = cfg;
        this.jqId = PrimeFaces.escapeClientId(id);
        jQuery(this.jqId).button(this.cfg);
    }

    PrimeFaces.widget.Button = function(id, cfg) {
        this.id = id;
        this.cfg = cfg;
        this.jqId = PrimeFaces.escapeClientId(id);
        jQuery(this.jqId).button(this.cfg);
    }

    PrimeFaces.widget.MenuButton = function(id, cfg) {
        this.id = id;
        this.cfg = cfg;
        this.jqId = PrimeFaces.escapeClientId(id);
        this.menuId = this.id + '_menu'
        this.cfg.icons = {
            primary: 'ui-icon-triangle-1-s'
        };
        jQuery(this.jqId + "_button").button(this.cfg);
        var manager = YAHOO.widget.MenuManager;
        if (manager.getMenu(this.menuId)) {
            manager.removeMenuWithId(this.menuId);
        }
        this.menu = new YAHOO.widget.Menu(id + '_menu', {
            itemData: this.cfg.items,
            context: [id + '_button', 'tl', 'bl', ['beforeShow', 'windowResize']],
            effect: {
                effect: YAHOO.widget.ContainerEffect.FADE,
                duration: 0.25
            }
        });
        if (this.cfg.appendToBody)
            this.menu.render(document.body);
        else
            this.menu.render(this.id + '_menuContainer');
    }
    PrimeFaces.widget.MenuButton.prototype.showMenu = function() {
        this.menu.show();
    }

    PrimeFaces.widget.MenuButton.prototype.hide = function() {
        this.menu.hide();
    }

    PrimeFaces.widget.MenuButton.prototype.getMenu = function() {
        return this.menu;
    }

    PrimeFaces.widget.CheckButton = function(id, cfg) {
        this.id = id;
        this.cfg = cfg;
        this.jqId = PrimeFaces.escapeClientId(id);
        this.jq = this.jqId + '_input';
        jQuery(this.jq).button(this.cfg);
    }
}

PrimeFaces.locales['pt'] = {
    closeText: 'Fechar',
    prevText: 'Anterior',
    nextText: 'Próximo',
    currentText: 'Começo',
    monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
    monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
    dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
    dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'],
    dayNamesMin: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S'],
    weekHeader: 'Semana',
    firstDay: 1,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix: '',
    timeOnlyTitle: 'Só Horas',
    timeText: 'Tempo',
    hourText: 'Hora',
    minuteText: 'Minuto',
    secondText: 'Segundo',
    currentText: 'Data Atual',
            ampm: false,
    month: 'Mês',
    week: 'Semana',
    day: 'Dia',
    allDayText: 'Todo Dia'
};



