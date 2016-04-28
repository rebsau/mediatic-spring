
// Création du module ModuleAdherent
angular.module('ModuleAdherent', ['ngRoute']);

// Configuration du module ModuleCommande
// => Injection du Provider du service $route afin de configurer la route des commandes.
angular.module('ModuleAdherent').config(function($routeProvider){
	//route vers la recherche d'un adherent
	$routeProvider.when('/adherent', {
		templateUrl : './module-adherent/templates/rechercheAdherent.html',
		controller : 'AdherentController',
		controllerAs : 'AdherentCtrl' 
	});

	//route vers la visualisation d'un adherent
	$routeProvider.when('/visuAdherent/:adherentId', {
		templateUrl : './module-adherent/templates/visualisationAdherent.html',
		controller : 'VisuAdherentController',
		controllerAs : 'visuAdherentCtrl'
	});

	
	$routeProvider.when('/adherent/creation', {
		templateUrl : './module-adherent/templates/creationAdherent.html',
		controller : 'CreerAdherentController',
		controllerAs : 'newAdherentCtrl'
	});
});
