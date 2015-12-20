define([
    'jquery',
    'underscore',
    'backbone',
    'router',
    'bootstrap'
], function ($, _, Backbone, AppRouter, Bootstrap) {
    var App = {
        initialize: function () {
            var router = new AppRouter();
            Backbone.history.start();
        }
    };

    return App;
});