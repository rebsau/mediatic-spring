angular.module('ModuleAdherent').service('AdherentService', ['$http', 'urlService', function($http, urlService){
	var self = this;
	
	var url = urlService.getRechercheAdherentUrl(); 
	
	var getPromise = function(recherche){
		return $http.get(url, {params:recherche}).then(function(response) {
			var adherents = [];
			for(var index in response.data){
				var itemFromServeur = response.data[index];
				var itemForIHM = {
					id : itemFromServeur.id,
					nom : itemFromServeur.nom,
					prenom : itemFromServeur.prenom,
					date_naissance : new Date(itemFromServeur.date_naissance),
					cotisation_correcte : itemFromServeur.cotisation_correcte,
					email : itemFromServeur.email,
					adresse : {},
					cotisation : {},
					age : itemFromServeur.age,
					emprunt : [],
					nombre_media : itemFromServeur.nombre_media	
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
							debut: itemFromServeur.cotisation.debut,
							fin: itemFromServeur.cotisation.fin,
							montant: itemFromServeur.cotisation.montant
					};
				}
					
				adherents.push(itemForIHM);						
			}	
			return adherents;
		});
	};
	
	self.getList = function(recherche){
		return getPromise(recherche);
	};
	
	


}]);