

angular.module('ModuleGlobal')
	.controller('TopController', ['LoginService', 'AuthorityService', '$location', '$rootScope', function(LoginService, AuthorityService, $location, $rootScope) {
		var ctrl = this;
		
		
		ctrl.error = {};
		ctrl.error.badLogin = false;
		ctrl.error.serverDown = false;
		
		ctrl.identifier = LoginService.identifier;
		ctrl.password = '';
		
		
		ctrl.isConnected = function() {
			return LoginService.isConnected();
		};
		
		
		
		ctrl.connect = function() {
			LoginService.connect(ctrl.identifier, ctrl.password, ctrl.rememberMe).then(function(response) {
				if(response.connected) {
					ctrl.error = {};
					ctrl.identifier = LoginService.identifier;
				}
				else {
					if (response.status == 400 || response.status == 401 || response.status == 403) {
						ctrl.error.badLogin = true;
					}
					else if (response.status == 500 || response.status == 503) {
						ctrl.error.serverDown = true;
					}
				}
				
				ctrl.password = '';
			});
		};
		
		
		ctrl.disconnect = function() {
			LoginService.disconnect();
		};
		
		
		
		ctrl.hasAuthority = function(authority) {
			return AuthorityService.hasAuthority(authority);
		}
		
		
	}]);

