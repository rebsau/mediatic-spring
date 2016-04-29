

angular.module('ModuleMedia').controller('VisuMediaController', [ '$http', '$routeParams', '$rootScope', '$scope', 'urlService', function($http, $routeParams, $rootScope, $scope, urlService){
	var myCtrl = this;
	
	$rootScope.title = "Visualisation du media";
	
	myCtrl.media = undefined;
	myCtrl.emprunteurs = undefined;
	myCtrl.showFormAjout = false;
	
	myCtrl.totalItems = undefined;
	myCtrl.currentPage = 1;
	myCtrl.maxSize = 10;
	
	myCtrl.tri = {
			param : 'id',
			dir : true
	}
	
	var urlMedia = urlService.getAccessionMediaUrl();
	var urlMediaEmprunt = urlService.getEmpruntForMediaUrl();
	
	var initMedia = function(response){
		
		var itemFromServeur = response.data;
		var itemForIHM = {
				id:itemFromServeur.id,
				titre:itemFromServeur.titre,
				auteur:itemFromServeur.auteur,
				type:itemFromServeur.type,
				emprunt:itemFromServeur.emprunt
			};
		
		myCtrl.media = itemForIHM;
		myCtrl.showFormAjout = myCtrl.media.emprunt!=null;
		myCtrl.calculDateReturn();
		
	}
	
	$http.get(urlMedia +'/'+ $routeParams.mediaId).then(function(response){
		initMedia(response);
	});
	
	var initEmprunteurs = function(response){
				
		myCtrl.emprunteurs = [];
		for(var index in response.data.content){
				
			var emp = response.data.content[index];
				
			myCtrl.emprunteurs.push({
				adherent : emp.adherent,
				depart : new Date(emp.dateEmprunt),
				retour : new Date(emp.dateRetour),
			});;
		}
		
		myCtrl.totalItems = response.data.totalElements;
	}
	
	$http.get(urlMediaEmprunt, {params : {page:0, ascend:true, triParam:myCtrl.tri.param, mediaId:$routeParams.mediaId}}).then(function(response){
		initEmprunteurs(response);
	}, function(){
		// En cas d'erreur
		myCtrl.emprunteurs = -1;
	});
	
	myCtrl.pagination = function(myPage){
		
		var rech = {
			page : myPage,
			triParam : myCtrl.tri.param,
			ascend : myCtrl.tri.dir,	
			mediaId : $routeParams.mediaId
				
		}
		
		$http.get(urlMediaEmprunt, {params : rech}).then(function(response){
			myCtrl.initEmprunteurs(response);
		})
	}
	
	myCtrl.nomPrenom = function(adh){
		if(adh!=null){
			return adh.adherent.nom+" "+adh.adherent.prenom;
		}else{
			return "";
		}
		
	}
		
	myCtrl.dateToday = new Date();
	myCtrl.dateReturn = new Date();
	
	myCtrl.calculDateReturn = function(){
		var date = myCtrl.dateToday;
		if(myCtrl.media.type=="Livre"){
			myCtrl.dateReturn = new Date(date.getFullYear() ,date.getMonth() ,date.getDate()+30);
		}else{
			myCtrl.dateReturn = new Date(date.getFullYear() ,date.getMonth() ,date.getDate()+15);
		}
	}
	
	myCtrl.modificationMedia = function() {
		if ($scope.media.$valid) {
				
			if(!myCtrl.showFormAjout){
				myCtrl.media.emprunt=null;
			}
			
			var urlModif = urlService.getModificationMediaUrl();
		
			$http.put(urlModif, myCtrl.media).then(function(response) {			
				console.log("success");		
			},function(response) {
				console.log("perdu");		
			});
		}
	};

	myCtrl.adherents = undefined;
	
	myCtrl.rechercheAdherents = function(){
		var recherche = {
			name : myCtrl.nomAdh
		}	
		
		if(myCtrl.nomAdh=="" || myCtrl.nomAdh==undefined){
			myCtrl.showSelect = false;
		}else{
			myCtrl.showSelect = true;
		}
		
		var urlAdh = urlService.getRechercheAdherentUrl();
		
		$http.get(urlAdh + '/allByName', {params : recherche}).then(function(response){
			myCtrl.adherents = [];
			for(var index in response.data){
				var itemFromServeur = response.data[index];
				var itemForIHM = {
					id : itemFromServeur.id,
					nom : itemFromServeur.nom,
					prenom : itemFromServeur.prenom,
					date_naissance : new Date(itemFromServeur.date_naissance),
					email : itemFromServeur.email,
					adresse : itemFromServeur.adresse ,
					code_postal: itemFromServeur.code_postal,
					ville: itemFromServeur.ville,
					email: itemFromServeur.email,
					date_paiement : itemFromServeur.date_paiement,
					montant_cotisation : itemFromServeur.montant_cotisation,
					nbMedia : itemFromServeur.nbMedia
				};
				myCtrl.adherents.push(itemForIHM);
			}
			
		})
	}
	
	
	myCtrl.ajoutEmprunteur = function() {
		if ($scope.emprunteur.$valid) {
			
			var emprunt = {
				media : myCtrl.media,
				adherent : myCtrl.Adh,
				dateEmprunt : myCtrl.dateToday,
				dateRetour : myCtrl.dateReturn
			}
			
			var newEmprunt = null;
			
			var urlEmprunt = urlService.getAjoutEmpruntUrl();
		
			$http.post(urlEmprunt, emprunt).then(function(response) {			
				newEmprunt  = response.data;
				
				myCtrl.media.emprunt = newEmprunt;
				myCtrl.showFormAjout = true;
			
				
				var urlModif = urlService.getModificationMediaUrl();
				
				$http.put(urlModif, myCtrl.media).then(function(response) {			
					console.log("success");		
				},function(response) {
					console.log("perdu");		
				});
				
			},function(response) {
				console.log("perdu");		
			});
			
			
			
			
		}	
	};
	
}]);