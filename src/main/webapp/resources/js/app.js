'user strict';

angular
	.module('orchestrationEditor', [ 
	    'ngResource', 
	    'ngCookies'
	])
	.config(configure)
	.run(runBlock);

configure.$inject = ['$httpProvider'];

function configure($httpProvider) {
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
};

runBlock.$inject = ['$rootScope', '$cookieStore'];

function runBlock($rootScope, $cookieStore) {
	$rootScope.globals = $cookieStore.get('globals') || {};
}; 