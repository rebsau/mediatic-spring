
angular.module('ModuleMedia')
	.controller('CreerMediaController', ['$rootScope', '$scope', '$location', 'CreerMediaService', function($rootScope, $scope, $location, CreerMediaService){
		var ctrl = this;
		
		$rootScope.title = 'Création d\'un média';
		
		
		ctrl.error = {};
		ctrl.error.badTitre = false;
		ctrl.error.badAuteur = false;
		ctrl.error.badType = false;
		
		
		ctrl.submit = function() {
			if ($scope.media.$valid) {
				CreerMediaService.submit(ctrl.titre, ctrl.auteur, ctrl.type).then(function(response) {
					if(response.submited) {
						$location.path('/media');
					}
				});
			}
			else {
				ctrl.error = {};
				
				
				if (!$scope.media.titre.$valid) {
					ctrl.error.badTitre = true;
					return;
				}
				
				if (!$scope.media.auteur.$valid) {
					ctrl.error.badAuteur = true;
					return;
				}
				
				if (!$scope.media.type.$valid) {
					ctrl.error.badType = true;
					return;
				}
			}
		};
		
		
	}]);



