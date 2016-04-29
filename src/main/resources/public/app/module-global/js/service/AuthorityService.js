

angular.module('ModuleGlobal').service('AuthorityService', ['$rootScope', 'LoginService', function($rootScope, LoginService) {
	var self = this;
	
	
	self.hasAuthority = function(authority) {
		if (!LoginService.isConnected())
			return false;
		
		var authorities = $rootScope.globals.currentUser.authorities;
		
		for (var i in authorities) {
			if (authorities[i].authority == authority)
				return true;
		}
		
		return false;
	};
	
	
	
}]);

