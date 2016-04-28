
// Création du module ModuleApp avec les dépendances :
//  - ng-route : pour gérer des URL différentes
//  - ModuleGlobal : pour avoir les filtres


angular.module('ModuleApp', ['ngRoute', 'ngCookies', 'ModuleGlobal', 'ModuleMedia', 'ModuleAdherent','ui.bootstrap','FiltreModule']);

// Configuration du module ModuleApp
// => Injection du Provider du service $route afin de le configurer.
angular.module('ModuleApp').config(function($routeProvider, $httpProvider){
		$routeProvider.otherwise({
			redirectTo : '/media'
		});
		
});

angular.module('ModuleApp').run(['$rootScope', '$cookieStore', '$http', function ($rootScope, $cookieStore, $http) {
	// keep user logged in after page refresh
	$rootScope.globals = $cookieStore.get('globals') || {};
	
	if ($rootScope.globals.currentUser) {
		$http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
	}
}]);