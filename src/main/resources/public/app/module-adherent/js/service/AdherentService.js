angular.module('ModuleAdherent').service('AdherentService', ['$http', 'urlService', function($http, urlService){
	var self = this;
	
	var url = urlService.getRechercheAdherentUrl(); 
	
	var getPromise = function(recherche){
		return $http.get(url, {params:recherche}).then(function(response) {
			var adherents = [];
			for (var index in response.data.content){
				var itemFromServeur = response.data.content[index];
				var itemForIHM = {
							id : itemFromServeur.id,
							nom : itemFromServeur.nom,
							prenom : itemFromServeur.prenom,
							date_naissance : new Date(itemFromServeur.date_naissance),
							adresse : itemFromServeur.adresse,
							code_postal : itemFromServeur.code_postal,
							ville : itemFromServeur.ville,
							email : itemFromServeur.email,
							date_paiement : new Date(itemFromServeur.date_paiement),
							montant_cotisation : itemFromServeur.montant_cotisation,
							nbMedia : itemFromServeur.nbMedia
				};
				
				var datePaiement = new Date(itemFromServeur.date_paiement);
				var year = datePaiement.getFullYear()+1;
				var dateFinCotisation = datePaiement.setFullYear(year);
				if(Date.now() - dateFinCotisation <= 0){
					itemForIHM.cotisation_correcte = true;
				}else{
					itemForIHM.cotisation_correcte = false;
				}
				
				adherents.push(itemForIHM);
			} 
			var item = {
					adhs : adherents,
					totalElements : response.data.totalElements
			}
			return item;
		});
	};
	
	self.getList = function(recherche){
		return getPromise(recherche);
	};
	
	


}]);