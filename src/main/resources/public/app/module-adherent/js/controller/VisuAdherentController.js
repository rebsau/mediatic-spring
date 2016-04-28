// Récupération du module des catalogue pour y ajouter le controller
angular.module('ModuleAdherent').controller('VisuAdherentController', ['$http','$routeParams','$location', '$rootScope', 'AdherentService', 'urlService', function( $http, $routeParams, $location, $rootScope, AdherentService, urlService) {
	var myCtrl = this;

	$rootScope.title = "Visualisation d\'un adherent";
	myCtrl.adherent = undefined;
	
	myCtrl.medias = undefined;
	
	var url = urlService.getAccessionAdherentUrl()+"?id="+$routeParams.adherentId;
	

	var initAdherent = function(response){
		
		var itemFromServeur = response.data
		var itemForIHM = {
			id:itemFromServeur.id,
			nom:itemFromServeur.nom,
			prenom:itemFromServeur.prenom,
			date_naissance: new Date(itemFromServeur.date_naissance),
			cotisation_correcte:itemFromServeur.cotisation_correcte,
			email:itemFromServeur.email,
			adresse:{},
			age:itemFromServeur.age,
			emprunt:[],
			cotisation:{},
			nombre_media:itemFromServeur.nombre_media,			
		};
		
		
		for (index in itemFromServeur.emprunt){
			itemForIHM.emprunt.push(
				{media:
					{
						id: itemFromServeur.emprunt[index].media.id,
						titre: itemFromServeur.emprunt[index].media.titre
					},
						depart: itemFromServeur.emprunt[index].depart,
						retour: itemFromServeur.emprunt[index].retour
				}
			)
		} 
				
		if(itemFromServeur.adresse != undefined){
			itemForIHM.adresse = {
				ligne1: itemFromServeur.adresse.ligne1,
				ligne2: itemFromServeur.adresse.ligne2,
				codepostal: itemFromServeur.adresse.codepostal,
				ville: itemFromServeur.adresse.ville	
			};
		}
				
		if(itemFromServeur.cotisation != undefined){
			itemForIHM.cotisation = {
				debut:  new Date(itemFromServeur.cotisation.debut),
				fin:  new Date(itemFromServeur.cotisation.fin),
				montant: itemFromServeur.cotisation.montant
			};
		}

		myCtrl.adherent = itemForIHM;
		myCtrl.calculDateReturn();
	}
	
	$http.get(url).then(function(response){
		initAdherent(response);
	}, function(){
		// En cas d'erreur
		myCtrl.emprunteurs = -1;
	});
	
	
	myCtrl.submitAdherent = function() {
	
			var url = urlService.getModificationMediaUrl();	
		
			$http.post(url, myCtrl.adherent).then(function(response) {			
					
			},function(response) {
		
			});
	}
	
	
	myCtrl.dateToday = new Date();
	myCtrl.dateRetour = new Date();
	
	myCtrl.calculDateReturn = function(){
		var date = myCtrl.dateToday;
		myCtrl.dateReturn = new Date(date.getFullYear()+1 ,date.getMonth() ,date.getDate());

	}
	
	myCtrl.rechercheMedias = function(){
		var recherche = {
			titre : myCtrl.titreMedia
		}	
		
		if(myCtrl.titreMedia=="" || myCtrl.titreMedia==undefined){
			myCtrl.showSelect = false;
		}else{
			myCtrl.showSelect = true;
		}
		
		var urlMedia = urlService.getRechercheMediaUrl();
		
		$http.get(urlMedia, {params : recherche}).then(function(response){
			myCtrl.medias = [];
			for(var index in response.data){
				var itemFromServeur = response.data[index];
				var itemForIHM = {
					id:itemFromServeur.id,
					titre:itemFromServeur.titre,
				};
				myCtrl.medias.push(itemForIHM);
		
			}
		})
	}
	
	
	myCtrl.submitMedia = function() {
	
			var urlMedia = urlService.getAjoutEmpruntUrl();
			
			var emprunt ={
				idMedia : myCtrl.idMedia,
				idAdh : myCtrl.adherent.id,
				date_emprunt : myCtrl.dateToday
			}
			
			$http.post(url, {params:emprunt}).then(function(response) {			
					
			},function(response) {
		
			});
	}
	
	
	
	
	

	
	
}]);




	