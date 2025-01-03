var arrayIdElementsPage = new Array;

/**
 * Carregar um array global com os ids de todos os componentes da pagina para ter 
 * facilidades na obtenção de valores dos componentes, além de trabalhar com ajax
 */
function carregarIdElementosPagina(){
	arrayIdElementsPage = new Array;
	for (form = 0; form <= document.forms.length; form++) {
		var formAtual = document.forms[form];
		if (formAtual != undefined) {
			for (i = 0; i < document.forms[form].elements.length; i++){
				if (document.forms[form].elements[i].id != '') {
					arrayIdElementsPage[i] = document.forms[form].elements[i].id;
				}
			}
		}
	}
}

/**
 * Retornar valor do id do componente dentro do documento html passando como parâmetro a descrição do id declarada em jsf
 * 
 * @param id
 */
function getValorElementPorId(id) {
	carregarIdElementosPagina();
	for (i = 0; i < arrayIdElementsPage.length; i++) {
		var valor = "" + arrayIdElementsPage[i];
		if (valor.indexOf(id) > -1) {
			return valor;
		}
	}
	return idundefined;
}

function logout(contextPath) {
	
	var post = 'invalidar_session';
	
	$.ajax({
		type:"POST",
		url: post
	}).always(function(resposta) {
		document.location = contextPath + '/j_spring_security_logout';
	});
	
}

function invalidarSession(context, pagina) {
	document.location = (context + pagina + ".jsf");
}


function validarSenhaLogin() {


	j_username = document.getElementById("j_username").value;
	j_password = document.getElementById("j_password").value;


	if (j_username === null || j_username.trim() === '') {
		alert("Informe o Login.");
		$('#j_username').focus();
		return false;
	}


	if (j_password === null || j_password.trim() === '') {
		alert("Informe a Senha.");
		$('#j_password').focus();
		return false;
	}

	return true;
}

function abrirMenupop(){
	$("#menupop").show('slow').mouseleave(function(){
		fecharMenupop();
	});
}

function fecharMenupop(){
	if ($("#menupop").is(":visible")){
		$("#menupop").hide("slow");
	}	
}

function redirecionarPagina(context, pagina) {
	pagina = pagina + ".jsf";
	document.location = context + pagina;
}

function localeData_pt_br() {
	PrimeFaces.locales['pt'] = {
		closeText : 'Fechar',
		prevText : 'Anterior',
		nextText : 'Próximo',
		currentText : 'Começo',
		monthNames : [ 'Janeiro', 'Fevereiro', 'Marcio', 'Abril', 'Maio',
				'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro',
				'Dezembro' ],
		monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul',
				'Ago', 'Set', 'Out', 'Nov', 'Dez' ],
		dayNames : [ 'Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta',
				'Sexta', 'Sábado' ],
		dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb' ],
		dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S' ],
		weekHeader : 'Semana',
		firstDay : 0,
		isRTL : false,
		showMonthAfterYear : false,
		yearSuffix : '',
		timeOnlyTitle : 'São Horas',
		timeText : 'Tempo',
		hourText : 'Hora',
		minuteText : 'Minuto',
		secondText : 'Segundo',
		ampm : false,
		month : 'Mês',
		week : 'Semana',
		day : 'Dia',
		allDayText : 'Todo o Dia'
	};
}

function initTemplate() {
	$(document).ready(function() {
		  $('#menupop').hide();
		  $('#barramenu').hide();
		  $('#barramenu').css("left", "-200px");
		  $('#iniciarmenu').click(function() {
		  	if ($('#barramenu').is(':visible')) {
		  	  ocultarMenu();
		  	} else {
		  	  $('#barramenu').show();
		  	  $('#barramenu').animate({"left":"0px"}, "slow");	
		  	}
		  });
		});
	}

function ocultarMenu() {  
	  $('#barramenu').animate({"left":"-200px"}, "slow", function() {
	  	$('#barramenu').hide();
	  });
	}
	
function addFocoAoCampo(campo)	{
	var id = getValorElementPorId(campo);
	if (id != undefined) {
		document.getElementById(id).focus();
	}
}

// Faz com que a tecla enter tenha efeito de TAB pulando de campo em campo
function gerenciaTeclaEnter() {
	$(document).ready(function() {
		$('input').keypress(function(e) {
			var code = null;
			code = (e.keyCode ? e.keyCode : e.which);
			return (code === 13) ? false : true;

		});

		$('input[type=text]').keydown(function(e) {
			// Obter o próximo índice do elemento de entrada de texto
			var next_idx = $('input[type=text]').index(this) + 1;

			// Obter o número de elemento de entrada de texto em um documento html
			var tot_idx = $('body').find('input[type=text]').length;

			// Entra na tecla no código ASCII
			if (e.keyCode === 13) {
				if (tot_idx === next_idx)
					// Vá para o primeiro elemento de texto
					$('input[type=text]:eq(0)').focus();
				else
					// Vá para o elemento de entrada de texto seguinte
					$('input[type=text]:eq(' + next_idx + ')').focus();
			}
		});
	});

}
	
	
	
	