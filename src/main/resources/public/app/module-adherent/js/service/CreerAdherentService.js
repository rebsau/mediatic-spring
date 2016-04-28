

angular.module('ModuleAdherent').service('CreerAdherentService', ['$http', 'urlService', function($http, urlService) {
	var self = this;
	
	var url = urlService.getCreationAdherentUrl();
	
	
	
	
	self.submit = function(adherent) {
		return $http.post(url, adherent).then(function(response) {
			return {submited: true, status: response.status};
		},
		function(response) {
			return {submited: false, status: response.status};
		});
	};
	
	
	
}]);
