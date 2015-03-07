/*global webapp, Backbone, JST, _*/

webapp.Views = webapp.Views || {};

(function () {
  'use strict';

  webapp.Views.OrdersView = Backbone.View.extend({

    template: $("#orders"),

    initialize: function() {
      this.render();
    },
    
    render: function() {
      this.$el.html(_.template(this.template(this.model)));

      return this;
    }

  });

})();