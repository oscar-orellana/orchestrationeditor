'use strict';

angular
	.module('orchestrationEditor')
	.directive('cleanable', cleanableDirective);

function cleanableDirective() {
	return {
		restrict: "A",
		link: function(scope, elem, attrs) {
			elem.on('show.bs.modal', function () {
				vm.credentials = {};
				vm.loginError = false;
		    });
		}
	}
}
