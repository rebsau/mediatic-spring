
// Récupération du module des catalogue pour y ajouter le controller
angular.module('ModuleMedia').controller('MediaController', [ '$http', '$sce', '$location', '$rootScope', 'urlService', function($http, $sce, $location, $rootScope, urlService){
	var myCtrl = this;
	
	$rootScope.title = "Recherche d'un media";
	
	myCtrl.medias = undefined;
	
	myCtrl.totalItems = undefined;
	myCtrl.currentPage = 1;
	myCtrl.maxSize = 5;
	
	myCtrl.triParam = 'titre';
	
	var url = urlService.getRechercheMediaUrl();

	myCtrl.initMedia = function(response){
		myCtrl.medias = [];
		for(var index in response.data){
			var itemFromServeur = response.data[index];
			var itemForIHM = {
				id:itemFromServeur.id,
				titre:itemFromServeur.titre,
				auteur:itemFromServeur.auteur,
				type:itemFromServeur.type,
				emprunteur:itemFromServeur.emprunteur,
			};
			if(itemFromServeur.retour==undefined){
				itemForIHM.retour = "";
			}else{
				itemForIHM.retour = new Date(itemFromServeur.retour);
			}		
			myCtrl.medias.push(itemForIHM);
		}
	}
	
	$http.get(url, {params : {page:0, tri:myCtrl.triParam}}).then(function(response){
		myCtrl.initMedia(response);
	}, function(){
		// En cas d'erreur
		myCtrl.medias = -1;
	});
	
	myCtrl.nomPrenom = function(adh){
		if(adh!=null){
			return adh.nom+" "+adh.prenom;
		}else{
			return "";
		}
		
	}
	
	myCtrl.disponibiliter = function(media){
		if(media.retour == null){
			return "Disponible"
		}else{
			return "Emprunté par "+ myCtrl.nomPrenom(media.emprunteur) +"jusqu'au "+media.retour;
		}
	}
	
	myCtrl.icons = function(type){
		if(type=="Livre"){
			return $sce.trustAsHtml('<span class="fa fa-book" />');
		}else if(type=="CD"){
			return $sce.trustAsHtml('<span class="fa fa-music" />');
		}else{
			return $sce.trustAsHtml('<span class="fa fa-film" />');
		}
	}
	
	myCtrl.recherche = function(){
		var rech = {
			titre : myCtrl.titre,
			auteur : myCtrl.auteur,
			type : myCtrl.type,
			page : 0,
			tri : myCtrl.triParam
		}
					
		$http.get(url, {params : rech}).then(function(response){
			myCtrl.initMedia(response);
			myCtrl.initPagination();
		})
	}
	
	myCtrl.initPagination = function(){
		var urlTaille = urlService.getRechercheMediaTailleUrl();
		
		var rech = {
			titre : myCtrl.titre,
			auteur : myCtrl.auteur,
			type : myCtrl.type,
		}
		
		$http.get(urlTaille, {params : rech}).then(function(response){
			myCtrl.totalItems = response.data.items;
		})
		
	}
	
	myCtrl.initPagination();
	
	
	myCtrl.pagination = function(myPage){
		var rech = {
			titre : myCtrl.titre,
			auteur : myCtrl.auteur,
			type : myCtrl.type,
			page : myPage,
			tri : myCtrl.triParam
		}			
				
		$http.get(url, {params : rech}).then(function(response){
			myCtrl.initMedia(response);
		})
	}

	myCtrl.initTriParam = function(typeParam){
		if(myCtrl.triParam==typeParam){
			myCtrl.triParam=undefined;
		}else{
			myCtrl.triParam=typeParam;
		}
	}
	
	myCtrl.triMedia = function(){
		var rech = {
			titre : myCtrl.titre,
			auteur : myCtrl.auteur,
			type : myCtrl.type,
			page :myCtrl.currentPage-1,
			tri : myCtrl.triParam
		}
		
		$http.get(url, {params : rech}).then(function(response){
			myCtrl.initMedia(response);
			myCtrl.initPagination();
		})
	}

	myCtrl.showMedia = function(media){
		$location.path("/visuMedia/"+media.id);
	}

}]);