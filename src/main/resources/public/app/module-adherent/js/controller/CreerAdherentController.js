
angular.module('ModuleAdherent')
	.controller('CreerAdherentController', ['$rootScope', '$scope', '$location', 'CreerAdherentService', function($rootScope, $scope, $location, CreerAdherentService){
		var ctrl = this;
		
		$rootScope.title = 'Création d\'un adhérent';
		
		
		ctrl.error = {};
		ctrl.error.badNom = false;
		ctrl.error.badPrenom = false;
		ctrl.error.badBrithday = false;
		ctrl.error.badEmail = false;
		ctrl.error.badAddresse = false;
		ctrl.error.badCP = false; 
		ctrl.error.badVille = false;
		ctrl.error.badPayday = false;
		ctrl.error.badMontant = false;
		
		
		
		
		ctrl.getAge = function() {
			if(ctrl.adherent == undefined || ctrl.adherent.date_naissance == undefined)
				return undefined;
			
			var ageDate = new Date(Date.now() - ctrl.adherent.date_naissance.getTime());
			return Math.abs(ageDate.getFullYear() - 1970);
		}
		
		ctrl.getDateFin = function() {
			if(ctrl.adherent == undefined || ctrl.adherent.cotisation == undefined || ctrl.adherent.cotisation.debut == undefined)
				return undefined;
			
			var dateFin = new Date(ctrl.adherent.cotisation.debut);
			dateFin.setFullYear(dateFin.getFullYear() + 1);
			ctrl.adherent.cotisation.fin = dateFin;
			
			return ctrl.adherent.cotisation.fin;
		}
		
		
		
		
		
		ctrl.submit = function() {
			if ($scope.adherent.$valid) {
				
				var adherentToSend = {
					nom: ctrl.adherent.nom,
					prenom: ctrl.adherent.prenom,
					date_naissance: ctrl.adherent.date_naissance,
					adresse: ctrl.adherent.adresse.ligne1 + ' ' + ctrl.adherent.adresse.ligne2,
					code_postal: ctrl.adherent.adresse.codepostal,
					ville: ctrl.adherent.adresse.ville,
					email: ctrl.adherent.email,
					date_paiement: ctrl.adherent.cotisation.debut,
					montant_cotisation: ctrl.adherent.cotisation.montant
				};
				
				
				CreerAdherentService.submit(adherentToSend).then(function(response) {
					if(response.submited) {
						$location.path('/adherent');
					}
				});
			}
			else {
				ctrl.error = {};
				
				if (!$scope.adherent.nom.$valid) {
					ctrl.error.badNom = true;
					return;
				}
				
				if (!$scope.adherent.prenom.$valid) {
					ctrl.error.badPrenom = true;
					return;
				}
				
				if (!$scope.adherent.birthday.$valid) {
					ctrl.error.badBrithday = true;
					return;
				}
				
				if (!$scope.adherent.email.$valid) {
					ctrl.error.badEmail = true;
					return;
				}
				
				if (!$scope.adherent.adresse.$valid || !$scope.adherent.adresse2.$valid) {
					ctrl.error.badAddresse = true;
					return;
				}
				
				if (!$scope.adherent.cp.$valid) {
					ctrl.error.badCP = true;
					return;
				}
				
				if (!$scope.adherent.ville.$valid) {
					ctrl.error.badVille = true;
					return;
				}
				
				if (!$scope.adherent.payday.$valid) {
					ctrl.error.badPayday = true;
					return;
				}
				
				if (!$scope.adherent.montant.$valid) {
					ctrl.error.badMontant = true;
					return;
				}
			}
		};
		
		
	}]);



