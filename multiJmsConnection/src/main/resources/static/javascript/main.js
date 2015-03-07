/*global webapp, $*/


window.webapp = {
  Models: {},
  Collections: {},
  Views: {},
  Routers: {},
  init: function () {
    'use strict';
    var loginContainer = $('#login');
    if(loginContainer.length > 0) {
      new webapp.Views.LoginView({el: loginContainer.get(0)});
	};

	$(document).ready(function () {
	  'use strict';
	  webapp.init();
	});
});