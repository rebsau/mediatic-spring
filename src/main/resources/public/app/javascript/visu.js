

$(document).ready(function(){
	
	$('[data-toggle="tooltip"]').tooltip(); 
	
	dateToday();
	dateReturn();
	
	$('#DateEmprunt').change(function(){
		dateReturn();
	});
	
	$('#type').change(function(){
		dateReturn();
	});
	
	$('#estEmprunte').change(function(){
		if( $('#estEmprunte').is(':checked') ){
		    $(".formAjout").slideUp(200);
		}else{
			$(".formAjout").slideDown(200);
		}
	});
	
	

});

function dateToday(){
	var today = new Date();
	var yyyy = today.getFullYear();
	var mm = today.getMonth()+1;
	var dd = today.getDate();
	if(dd<10){
        dd='0'+dd
    } 
    if(mm<10){
        mm='0'+mm
    } 	
	var today = yyyy+'-'+mm+'-'+dd;
	$('#DateEmprunt').val(today);
}

function dateReturn(){
	var dateEmprunt = $('#DateEmprunt').val();
	var typeMedia = $('#type').val();
	if(typeMedia=="Livre"){
		var dateReturn = addDaysToDate(dateEmprunt, 30);
	}else{
		var dateReturn = addDaysToDate(dateEmprunt, 15);
	}	
	$('#DateRetour').val(dateReturn);
}
	
function addDaysToDate(old_date, delta_days) {
	   // Date plus plus quelques jours
	   var split_date = old_date.split('-');
	   // Les mois vont de 0 a 11 donc on enleve 1, cast avec *1
	   var new_date = new Date(split_date[0], split_date[1]*1 - 1, split_date[2]*1 + delta_days);
	   var new_day = new_date.getDate();
	       new_day = ((new_day < 10) ? '0' : '') + new_day; // ajoute un zéro devant pour la forme
	   var new_month = new_date.getMonth() + 1;
	       new_month = ((new_month < 10) ? '0' : '') + new_month; // ajoute un zéro devant pour la forme
	   var new_year = new_date.getYear();
	       new_year = ((new_year < 200) ? 1900 : 0) + new_year; // necessaire car IE et FF retourne pas la meme chose
	   var new_date_text = new_year + '-' + new_month + '-' + new_day;
	   return new_date_text;
	}
