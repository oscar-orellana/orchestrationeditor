'user strict';

angular
	.module('orchestrationEditor')
	.factory('UserService', UserService);

UserService.$inject = ['$resource'];

function UserService($resource) {
	return $resource('/api/users', {}, {
		getUsers : {
			method : 'GET',
			isArray : true
		},
		getUser : {
			method : 'GET',
			url : '/api/users/:id'
		},
		createUser : {
			method : 'POST'
		},
		updateUser : {
			method : 'PUT',
			url : '/api/users/:id'
		},
		deleteUser : {
			method : 'DELETE',
			url : '/api/users/:id'
		}
	});
};