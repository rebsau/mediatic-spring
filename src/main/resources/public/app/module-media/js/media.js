
// Création du module ModuleMedia
angular.module('ModuleMedia', ['ngRoute']);

// Configuration du module ModuleMedia
// => Injection du Provider du service $route afin de configurer la route des commandes.
angular.module('ModuleMedia').config(function($routeProvider){
	$routeProvider.when('/media', {
		templateUrl : './module-media/templates/rechercheMedia.html',
		controller : 'MediaController',
		controllerAs : 'medCtrl'
	});

	$routeProvider.when('/media/creation', {
		templateUrl : './module-media/templates/creationMedia.html',
		controller : 'CreerMediaController',
		controllerAs : 'newMediaCtrl'
	});
	
	$routeProvider.when('/visuMedia/:mediaId', {
		templateUrl : './module-media/templates/visualisationMedia.html',
		controller : 'VisuMediaController',
		controllerAs : 'visuMedCtrl'
	});
});

