
// Création du module ModuleApp avec les dépendances :
//  - ng-route : pour gérer des URL différentes
//  - ModuleGlobal : pour avoir les filtres


angular.module('ModuleApp', ['ngRoute', 'ModuleGlobal', 'ModuleMedia', 'ModuleAdherent','ui.bootstrap','FiltreModule']);

// Configuration du module ModuleApp
// => Injection du Provider du service $route afin de le configurer.
angular.module('ModuleApp').config(function($routeProvider, $httpProvider){
		$routeProvider.otherwise({
			redirectTo : '/media'
		});
		
		$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
});