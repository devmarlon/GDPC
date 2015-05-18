window.onload = function() {
    if (document.getElementById("fmpedido:btCarregaCokie") !== undefined) {
        movID = LerCookie("movID");
        movTIPO = LerCookie("movTIPO");
        if ((movID !== null) && (movTIPO === "PED")) {
            document.getElementById("fmpedido:btCarregaCokie").click();
        }
    }

// if (document.getElementById("fmnotafiscal:btCarregaCokie") !== undefined) {
// pedToNfe = LerCookie("pedToNfe");
// if (pedToNfe !== null) {
// document.getElementById("fmnotafiscal:btCarregaCokie").click();
// }
// }


    if (document.getElementById("fmnotafiscal:btCarregaCokie") !== undefined) {
        movToNfe = LerCookie("movToNfe");
        movTipoToNfe = LerCookie("movTipoToNfe");
        if (movToNfe !== null && movTipoToNfe !== null) {
            document.getElementById("fmnotafiscal:btCarregaCokie").click();
        }
    }


    if (document.getElementById("fmnotafiscalservico:btCarregaCokie") !== undefined) {
        movToNfse = LerCookie("movToNfse");
        movTipoToNfse = LerCookie("movTipoToNfse");
        if (movToNfse !== null && movTipoToNfse !== null) {
            document.getElementById("fmnotafiscalservico:btCarregaCokie").click();
        }
    }



};

function utf8_decode(str_data) {
    // Converts a UTF-8 encoded string to ISO-8859-1 
    // 
    // version: 1109.2015
    // discuss at: http://phpjs.org/functions/utf8_decode
    // + original by: Webtoolkit.info (http://www.webtoolkit.info/)
    // + input by: Aman Gupta
    // + improved by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
    // + improved by: Norman "zEh" Fuchs
    // + bugfixed by: hitwork
    // + bugfixed by: Onno Marsman
    // + input by: Brett Zamir (http://brett-zamir.me)
    // + bugfixed by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
    // * example 1: utf8_decode('Kevin van Zonneveld');
    // * returns 1: 'Kevin van Zonneveld'
    var tmp_arr = [],
            i = 0,
            ac = 0,
            c1 = 0,
            c2 = 0,
            c3 = 0;

    str_data += '';

    while (i < str_data.length) {
        c1 = str_data.charCodeAt(i);
        if (c1 < 128) {
            tmp_arr[ac++] = String.fromCharCode(c1);
            i++;
        } else if (c1 > 191 && c1 < 224) {
            c2 = str_data.charCodeAt(i + 1);
            tmp_arr[ac++] = String.fromCharCode(((c1 & 31) << 6) | (c2 & 63));
            i += 2;
        } else {
            c2 = str_data.charCodeAt(i + 1);
            c3 = str_data.charCodeAt(i + 2);
            tmp_arr[ac++] = String.fromCharCode(((c1 & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
            i += 3;
        }
    }

    return tmp_arr.join('');
}

function utf8_encode(argString) {
    // Encodes an ISO-8859-1 string to UTF-8 
    // 
    // version: 1109.2015
    // discuss at: http://phpjs.org/functions/utf8_encode
    // + original by: Webtoolkit.info (http://www.webtoolkit.info/)
    // + improved by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
    // + improved by: sowberry
    // + tweaked by: Jack
    // + bugfixed by: Onno Marsman
    // + improved by: Yves Sucaet
    // + bugfixed by: Onno Marsman
    // + bugfixed by: Ulrich
    // + bugfixed by: Rafal Kukawski
    // * example 1: utf8_encode('Kevin van Zonneveld');
    // * returns 1: 'Kevin van Zonneveld'
    if (argString === null || typeof argString === "undefined") {
        return "";
    }

    var string = (argString + ''); // .replace(/\r\n/g, "\n").replace(/\r/g, "\n");
    var utftext = "",
            start, end, stringl = 0;

    start = end = 0;
    stringl = string.length;
    for (var n = 0; n < stringl; n++) {
        var c1 = string.charCodeAt(n);
        var enc = null;

        if (c1 < 128) {
            end++;
        } else if (c1 > 127 && c1 < 2048) {
            enc = String.fromCharCode((c1 >> 6) | 192) + String.fromCharCode((c1 & 63) | 128);
        } else {
            enc = String.fromCharCode((c1 >> 12) | 224) + String.fromCharCode(((c1 >> 6) & 63) | 128) + String.fromCharCode((c1 & 63) | 128);
        }
        if (enc !== null) {
            if (end > start) {
                utftext += string.slice(start, end);
            }
            utftext += enc;
            start = end = n + 1;
        }
    }

    if (end > start) {
        utftext += string.slice(start, stringl);
    }

    return utftext;
}

function validaCNPJ(cnpj) {
    var numeros, digitos, soma, i, resultado, pos, tamanho, digitos_iguais;
    digitos_iguais = 1;
    if (cnpj.length < 14 && cnpj.length < 15)
        return false;
    for (i = 0; i < cnpj.length - 1; i++)
        if (cnpj.charAt(i) != cnpj.charAt(i + 1))
        {
            digitos_iguais = 0;
            break;
        }
    if (!digitos_iguais)
    {
        tamanho = cnpj.length - 2
        numeros = cnpj.substring(0, tamanho);
        digitos = cnpj.substring(tamanho);
        soma = 0;
        pos = tamanho - 7;
        for (i = tamanho; i >= 1; i--)
        {
            soma += numeros.charAt(tamanho - i) * pos--;
            if (pos < 2)
                pos = 9;
        }
        resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
        if (resultado != digitos.charAt(0))
            return false;
        tamanho = tamanho + 1;
        numeros = cnpj.substring(0, tamanho);
        soma = 0;
        pos = tamanho - 7;
        for (i = tamanho; i >= 1; i--)
        {
            soma += numeros.charAt(tamanho - i) * pos--;
            if (pos < 2)
                pos = 9;
        }
        resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
        if (resultado != digitos.charAt(1))
            return false;
        return true;
    }
    else
        return false;
}

function SomenteNumero(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if ((tecla > 47 && tecla < 58))
        return true;
    else {
        if (tecla == 8 || tecla == 0 || tecla == 13)
            return true;
        else
            return false;
    }
}

function SomenteNumeroeLetra(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (((tecla > 47 && tecla < 58) || (tecla > 96 && tecla < 123)))
        return true;
    else {
        if (tecla == 8 || tecla == 0 || tecla == 13)
            return true;
        else
            return false;
    }
}

function SomenteNumeroeVirgulaPonto(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if ((tecla > 47 && tecla < 58) || tecla == 44 || tecla == 46)
        return true;
    else {
        if (tecla == 8 || tecla == 0 || tecla == 13)
            return true;
        else
            return false;
    }
}

function pedRegEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if ((tecla > 47 && tecla < 58)) {
        return true;
    } else
    if (tecla == 13) {
        if (document.getElementById("fmpedidoregistro:btn_add") != null) {
            document.getElementById("fmpedidoregistro:btn_add").click();
        }
        return false;
    }
    return false;
}

function nffRegEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if ((tecla > 47 && tecla < 58)) {
        return true;
    } else
    if (tecla == 13) {
        if (document.getElementById("fmnotafiscalregistro:btn_add") != null) {
            document.getElementById("fmnotafiscalregistro:btn_add").click();
        }
        return false;
    }
    return false;
}

// Função para criar o cookie.
function GerarCookie(strCookie, strValor, lngDias) {
    var dtmData = new Date();
    var strExpires = '';

    if (lngDias)
    {
        dtmData.setTime(dtmData.getTime() + (lngDias * 24 * 60 * 60 * 1000));
        strExpires = "; expires=" + dtmData.toGMTString();
    }
    else
    {
        strExpires = "";
    }
    document.cookie = strCookie + "=" + strValor + strExpires + "; path=/";
}

// Função para ler o cookie.
function LerCookie(strCookie) {
    var strNomeIgual = strCookie + "=";
    var arrCookies = document.cookie.split(';');

    for (var i = 0; i < arrCookies.length; i++) {
        var strValorCookie = arrCookies[i];
        while (strValorCookie.charAt(0) == ' ') {
            strValorCookie = strValorCookie.substring(1, strValorCookie.length);
        }
        if (strValorCookie.indexOf(strNomeIgual) == 0) {
            return strValorCookie.substring(strNomeIgual.length, strValorCookie.length);
        }
    }
    return null;
}

// Função para excluir o cookie desejado.
function ExcluirCookie(strCookie) {
    GerarCookie(strCookie, '', -1);
}


//Hack para rodar HTML5 no IE
function html5() {
    var elements = ['article', 'content', 'footer', 'header', 'nav', 'section', 'placeholder'];
    for (var i = 0, j = elements.length; i < j; i++) {
        document.createElement(elements[i]);
    }
}

//-----------------------------------------------------
//Funcao: MascaraMoeda
//Sinopse: Mascara de preenchimento de moeda
//Parametro:
// objTextBox : Objeto (TextBox)
// SeparadorMilesimo : Caracter separador de milésimos
// SeparadorDecimal : Caracter separador de decimais
// e : Evento
//Retorno: Booleano
//Autor: Gabriel Fróes - www.codigofonte.com.br
//-----------------------------------------------------
function MascaraMoeda(objTextBox, SeparadorMilesimo, SeparadorDecimal, e) {

    var sep = 0;
    var key = '';
    var i = j = 0;
    var len = len2 = 0;
    var strCheck = '0123456789';
    var aux = aux2 = '';
    var whichCode = (window.Event) ? e.which : e.keyCode;
    if (whichCode == 13)
        return true;
    key = String.fromCharCode(whichCode); // Valor para o código da Chave
    if (strCheck.indexOf(key) == -1)
        return false; // Chave inválida
    len = objTextBox.value.length;
    for (i = 0; i < len; i++)
        if ((objTextBox.value.charAt(i) != '0') && (objTextBox.value.charAt(i) != SeparadorDecimal))
            break;
    aux = '';
    for (; i < len; i++)
        if (strCheck.indexOf(objTextBox.value.charAt(i)) != -1)
            aux += objTextBox.value.charAt(i);
    aux += key;
    len = aux.length;
    if (len == 0)
        objTextBox.value = '';
    if (len == 1)
        objTextBox.value = '0' + SeparadorDecimal + '0' + aux;
    if (len == 2)
        objTextBox.value = '0' + SeparadorDecimal + aux;
    if (len > 2) {
        aux2 = '';
        for (j = 0, i = len - 3; i >= 0; i--) {
            if (j == 3) {
                aux2 += SeparadorMilesimo;
                j = 0;
            }
            aux2 += aux.charAt(i);
            j++;
        }
        objTextBox.value = '';
        len2 = aux2.length;
        for (i = len2 - 1; i >= 0; i--)
            objTextBox.value += aux2.charAt(i);
        objTextBox.value += SeparadorDecimal + aux.substr(len - 2, len);
    }
    return false;
}

function MascaraMedida(objTextBox, SeparadorMilesimo, SeparadorDecimal, e) {

    var sep = 0;
    var key = '';
    var i = j = 0;
    var len = len2 = 0;
    var strCheck = '0123456789';
    var aux = aux2 = '';
    var whichCode = (window.Event) ? e.which : e.keyCode;
    if (whichCode == 13)
        return true;
    key = String.fromCharCode(whichCode); // Valor para o código da Chave
    if (strCheck.indexOf(key) == -1)
        return false; // Chave inválida
    len = objTextBox.value.length;
    for (i = 0; i < len; i++)
        if ((objTextBox.value.charAt(i) != '0') && (objTextBox.value.charAt(i) != SeparadorDecimal))
            break;
    aux = '';
    for (; i < len; i++)
        if (strCheck.indexOf(objTextBox.value.charAt(i)) != -1)
            aux += objTextBox.value.charAt(i);
    aux += key;
    len = aux.length;
    if (len == 0)
        objTextBox.value = '';
    if (len == 1)
        objTextBox.value = '0' + SeparadorDecimal + '0' + aux;
    if (len == 2)
        objTextBox.value = '0' + SeparadorDecimal + '0' + SeparadorDecimal + '0' + aux;
    if (len == 3)
        objTextBox.value = '0' + SeparadorDecimal + aux;
    if (len > 3) {
        aux2 = '';
        for (j = 0, i = len - 4; i >= 0; i--) {
            if (j == 4) {
                aux2 += SeparadorMilesimo;
                j = 0;
            }
            aux2 += aux.charAt(i);
            j++;
        }
        objTextBox.value = '';
        len2 = aux2.length;
        for (i = len2 - 1; i >= 0; i--)
            objTextBox.value += aux2.charAt(i);
        objTextBox.value += SeparadorDecimal + aux.substr(len - 3, len);
    }
    return false;
}

function nullIntDouble(objComp) {
    if (objComp.value == null || objComp.value.trim() == '') {
        objComp.value = '0';
    }
}

function mascara(o, f) {
    v_obj = o;
    v_fun = f;
    setTimeout("execmascara()", 1);
}

function execmascara() {
    v_obj.value = v_fun(v_obj.value);
}

function valor(v) {
    v = v.replace(/\D/g, "");
    v = v.replace(/[0-9]{15}/, "inválido");
    v = v.replace(/(\d{1})(\d{11})$/, "$1.$2"); // coloca ponto antes dos Ãºltimos 11 digitos
    v = v.replace(/(\d{1})(\d{8})$/, "$1.$2"); // coloca ponto antes dos Ãºltimos 8 digitos
    v = v.replace(/(\d{1})(\d{5})$/, "$1.$2"); // coloca ponto antes dos Ãºltimos 5 digitos
    v = v.replace(/(\d{1})(\d{1,2})$/, "$1.$2"); // coloca virgula antes dos Ãºltimos 2 digitos
    return v;
}

//funções ENTER busca e adicionar itens
function pedBuscaEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla == 13) {
        if (document.getElementById("fmpedido:bt_pedBusca") != null) {
            document.getElementById("fmpedido:bt_pedBusca").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}

function pedAddEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla == 13) {
        if (document.getElementById("fmpedidoregistro:btn_add") != null) {
            document.getElementById("fmpedidoregistro:btn_add").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}

function parcBuscaEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla == 13) {
        if (document.getElementById("fmparceiro:parcBtBusc") != null) {
            document.getElementById("fmparceiro:parcBtBusc").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}

function eqpBuscaEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla === 13) {
        if (document.getElementById("fmequipamento:btBuscaEqp") !== null) {
            document.getElementById("fmequipamento:btBuscaEqp").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}
function srvBuscaEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla === 13) {
        if (document.getElementById("fmserv:btBuscaSrv") !== null) {
            document.getElementById("fmserv:btBuscaSrv").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}
function pecBuscaEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla === 13) {
        if (document.getElementById("formpeca:btBuscaPeca") !== null) {
            document.getElementById("formpeca:btBuscaPeca").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}

function parSel(e) {
    
        if (document.getElementById("fmpar:btParSel") !== null) {
            document.getElementById("fmpar:btParSel").click();
        }
        return false;
    
}

function cbBuscaEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla === 13) {
        if (document.getElementById("fmcontabancaria:cbcBtBusc") !== null) {
            document.getElementById("fmcontabancaria:cbcBtBusc").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}
function cbSalvaEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla == 13) {
        if (document.getElementById("fmcontabancariaregistro:btSaveCBC") != null) {
            document.getElementById("fmcontabancariaregistro:btSaveCBC").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}

function plcBuscaEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla == 13) {
        if (document.getElementById("fmplanoconta:plcBtBusc") != null) {
            document.getElementById("fmplanoconta:plcBtBusc").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}
function plcSalvaEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla == 13) {
        if (document.getElementById("fmplanocontaregistro:BtSave") != null) {
            document.getElementById("fmplanocontaregistro:BtSave").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}

function cptBuscaEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla == 13) {
        if (document.getElementById("fmcondpagamento:cptBtBusc") != null) {
            document.getElementById("fmcondpagamento:cptBtBusc").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}
function cptSalvaEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla == 13) {
        if (document.getElementById("fmcondpagamentoregistro:BtSave") != null) {
            document.getElementById("fmcondpagamentoregistro:BtSave").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}


function cfopBuscaEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla == 13) {
        if (document.getElementById("fmprodutonat:pnatBtBusc") != null) {
            document.getElementById("fmprodutonat:pnatBtBusc").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}
function cfopSalvaEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla == 13) {
        if (document.getElementById("fmprodutonatregistro:BtSave") != null) {
            document.getElementById("fmprodutonatregistro:BtSave").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}

function grpBuscaEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla == 13) {
        if (document.getElementById("fmgrupo:grpBtBusc") != null) {
            document.getElementById("fmgrupo:grpBtBusc").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}
function grpSalvaEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla == 13) {
        if (document.getElementById("fmgruporegistro:btSaveGRP") != null) {
            document.getElementById("fmgruporegistro:btSaveGRP").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}

function griBuscaEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla == 13) {
        if (document.getElementById("fmgrupoitem:griBtBusc") != null) {
            document.getElementById("fmgrupoitem:griBtBusc").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}
function griSalvaEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla == 13) {
        if (document.getElementById("fmgrupoitemregistro:btSaveGRI") != null) {
            document.getElementById("fmgrupoitemregistro:btSaveGRI").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}

function caiBuscaEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla == 13) {
        if (document.getElementById("fmcaixa:caiBtBusc") != null) {
            document.getElementById("fmcaixa:caiBtBusc").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}

function cntBuscaEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla == 13) {
        if (document.getElementById("fmconta:cntBtBusc") != null) {
            document.getElementById("fmconta:cntBtBusc").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}

function kdxBuscaEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla == 13) {
        if (document.getElementById("fmkardex:kdxBtBusc") != null) {
            document.getElementById("fmkardex:kdxBtBusc").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}

function odcBuscaEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla == 13)
    {
        if (document.getElementById("fmordemcompra:odcBtBusc") != null) {
            document.getElementById("fmordemcompra:odcBtBusc").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}

function prodBuscaEnter(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla === 13)
    {
        if (document.getElementById("fmproduto:proBtBusc") !== null) {
            document.getElementById("fmproduto:proBtBusc").click();
        }
        return false;
    } else {
        return true;
    }
    return false;
}

function clear_form_elements(ele) {
    $(ele).find(':input').each(function() {
        switch (this.type) {
            case 'password':
            case 'select-multiple':
            case 'select-one':
            case 'text':
            case 'textarea':
                $(this).val('');
                break;
            case 'checkbox':
            case 'radio':
                this.checked = false;
        }
    });

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
}