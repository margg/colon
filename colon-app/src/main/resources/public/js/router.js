define([
    'jquery',
    'underscore',
    'backbone',
    'views/main-page'
], function ($, _, Backbone, MainPageView) {
    var AppRouter = Backbone.Router.extend({
        routes: {
            '!': 'mainPage',
            '!/': 'mainPage',
            '!ss': 'mainPage'
        },

        mainPage: function () {
            var mainPageView = new MainPageView({
                el: $('.sub-cont')
            });
            mainPageView.render();
        }

    });

    return AppRouter;
})
;