

angular.module('ModuleMedia').service('CreerMediaService', ['$http', 'urlService', function($http, urlService) {
	var self = this;
	
	var url = urlService.getCreationMediaUrl();
	
	
	
	
	self.submit = function(titre, auteur, type) {
		var media = {
			titre : titre,
			auteur : auteur,
			type : type
		};
		
		return $http.post(url, media).then(function(response) {
			return {submited: true, status: response.status};
		},
		function(response) {
			return {submited: false, status: response.status};
		});
	};
	
	
}]);
