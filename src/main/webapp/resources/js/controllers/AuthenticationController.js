'user strict';

angular
	.module('orchestrationEditor')
	.controller('AuthenticationController', AuthenticationController);

AuthenticationController.$inject = 
	['$scope', '$rootScope', '$location','AuthenticationService'];

function AuthenticationController($scope, $rootScope, $location,
		AuthenticationService) {
	var vm = this;
	
	vm.credentials = {};
	vm.role;
	
	vm.loginError = false;
	
	vm.authenticated = function() {
		return $rootScope.globals.authenticated;
	};
	
	vm.login = function() {
		vm.proccessingLogin = true;
		AuthenticationService.authenticate(vm.credentials, successCallback,
				errorCallback);

		function successCallback(response) {
			AuthenticationService.setCredentials(vm.credentials,
					response.data.role);
			vm.proccessingLogin = false;
			vm.loginError = false;
			$('#loginModal').modal('hide');
		}

		function errorCallback(response) {
			vm.proccessingLogin = false;
			vm.loginError = true;
		}
	};

	vm.logout = function() {
		AuthenticationService.clearCredentials();
	};

};