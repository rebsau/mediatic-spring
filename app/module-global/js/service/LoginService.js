

angular.module('ModuleGlobal').service('LoginService', ['$http', 'urlService', function($http, urlService) {
	var self = this;
	
	var url = urlService.getLoginUrl();
	var connected = false;
	
	self.identifier = '';
	
	
	
	self.isConnected = function() {
		return connected;
	};
	
	
	self.connect = function(identifier, password, remember) {
		var logs = {
			login : identifier,
			mdp : password,
		};
		
		return $http.post(url, logs).then(function(response) {
			connected = true;
			
			if (remember)
				self.identifier = identifier;
			else
				self.identifier = '';
			
			return {connected: true, status: response.status};
		},
		function(response) {
			return {connected: false, status: response.status};
		});
	};
	
	
	self.disconnect = function() {
		connected = false;
	};
	
	
}]);

