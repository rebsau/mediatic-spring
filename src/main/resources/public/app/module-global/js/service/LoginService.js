

angular.module('ModuleGlobal').service('LoginService', ['$http', '$rootScope', '$cookieStore', 'urlService', function($http, $rootScope, $cookieStore, urlService) {
	var self = this;
	
	var url = urlService.getLoginUrl();
	//var connected = false;
	
	self.identifier = '';
	
	
	
	self.isConnected = function() {
		$rootScope.globals = $cookieStore.get('globals') || {};
		
		if ($rootScope.globals.currentUser) {
			return true;
		}
		
		return false;
	};
	
	
	self.connect = function(identifier, password, remember) {
		var authdata = btoa(identifier + ":" + password);
		
		var config = {
			headers: {
				'Authorization': 'Basic ' + authdata
			}
		};
		  
        
		
		return $http.get(url, config).then(function(response) {
			connected = true;
			
			
			$rootScope.globals = {
	            currentUser: {
	                username: identifier,
	                authdata: authdata
	            }
	        };

	        $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata;
	        $cookieStore.put('globals', $rootScope.globals);
	        
			
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
		
		$rootScope.globals = {};
        $cookieStore.remove('globals');
        $http.defaults.headers.common.Authorization = 'Basic ';
	};
	
	
}]);

