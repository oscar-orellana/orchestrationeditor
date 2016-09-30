'use strict';

angular
	.module('orchestrationEditor')
	.factory('AuthenticationService', AuthenticationService);

AuthenticationService.$inject = ['$rootScope', '$cookieStore', '$http'];

function AuthenticationService($rootScope, $cookieStore, $http) {
	var service = {};

	service.authenticate = function(credentials, successCallback, errorCallback) {
		var headers = {
			authorization : 'Basic '
					+ btoa(credentials.username + ':' + credentials.password)
		};

		$http({
			method : 'GET',
			url : '/api/user',
			headers : headers
		}).then(successCallback, errorCallback);
	};
	
	service.setCredentials = function(credentials, role) {
		var authdata = btoa(credentials.username + ':' + credentials.password);
		
		$rootScope.globals = {
			currentUser: {
				username: credentials.username,
				authdata: authdata,
				role: role
			},
			authenticated: true
		};
		
		$cookieStore.put('globals', $rootScope.globals);
	};
	
	service.clearCredentials = function () {
		$rootScope.globals = {};
		$cookieStore.remove('globals');
		
		$http({
			method: 'POST',
			url: '/logout'
		});
	}
	
	return service;
};