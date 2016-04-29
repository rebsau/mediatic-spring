
// Récupération du module des catalogue pour y ajouter le controller
angular.module('ModuleMedia').controller('MediaController', [ '$http', '$sce', '$location', '$rootScope', 'urlService', function($http, $sce, $location, $rootScope, urlService){
	var myCtrl = this;
	
	$rootScope.title = "Recherche d'un media";
	
	myCtrl.medias = undefined;
	
	myCtrl.totalItems = undefined;
	myCtrl.currentPage = 1;
	myCtrl.maxSize = 10;
	
	myCtrl.tri = {
			param : 'titre',
			dir : true
	}
	
	var url = urlService.getRechercheMediaUrl();

	myCtrl.initMedia = function(response){
		myCtrl.medias = [];
				
		for(var index in response.data.content){
			var itemFromServeur = response.data.content[index];
						
			var itemForIHM = {
				id:itemFromServeur.id,
				titre:itemFromServeur.titre,
				auteur:itemFromServeur.auteur,
				type:itemFromServeur.type,
			};
			
			if(itemFromServeur.emprunt!=null){
				itemForIHM.emprunt = {
						id : itemFromServeur.emprunt.id,
						adh : itemFromServeur.emprunt.adherent,
						dateRetour : new Date(itemFromServeur.emprunt.dateRetour),	
				};
			}else{
				itemForIHM.emprunt = null;
			}		
			myCtrl.medias.push(itemForIHM);
		}
		myCtrl.totalItems = response.data.totalElements;
	}
	
	$http.get(url, {params : {page:0, ascend:true, triParam:myCtrl.tri.param,}}).then(function(response){
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
		if(media.emprunt == null){
			return "Disponible"
		}else{
			return "Emprunté par "+ myCtrl.nomPrenom(media.emprunt.adh) +"jusqu'au "+media.emprunt.dateRetour;
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
			page : myCtrl.currentPage-1,
			triParam : myCtrl.tri.param,
			ascend : myCtrl.tri.dir,	
			title : myCtrl.titre,
			author : myCtrl.auteur,
			type : myCtrl.type,
			
		}
		
		$http.get(url, {params : rech}).then(function(response){
			myCtrl.initMedia(response);		
		})
	}
	
	
	myCtrl.pagination = function(myPage){
	
		var rech = {
			page : myPage,
			triParam : myCtrl.tri.param,
			ascend : myCtrl.tri.dir,	
			title : myCtrl.titre,
			author : myCtrl.auteur,
			type : myCtrl.type,
				
		}
		
		$http.get(url, {params : rech}).then(function(response){
			myCtrl.initMedia(response);
		})
	}

	myCtrl.initTriParam = function(typeParam){
		if(myCtrl.tri.param==typeParam){
			myCtrl.tri.dir = !myCtrl.tri.dir;
		}else{
			myCtrl.tri.param=typeParam;
			myCtrl.tri.dir = true;
		}
	}
	

	myCtrl.showMedia = function(media){
		$location.path("/visuMedia/"+media.id);
	}

}]);