define([
    'jquery',
    'underscore',
    'backbone',
    'views/main-page',
    'views/new-solution'
], function ($, _, Backbone, MainPageView, NewSolutionView) {
    var AppRouter = Backbone.Router.extend({
        routes: {
            '!/solutions/new': 'newSolution',
            '!': 'mainPage'
        },

        mainPage: function () {
            var mainPageView = new MainPageView({
                el: $('.sub-cont')
            });
            mainPageView.render();
        },

        newSolution: function() {
            var newSolutionView = new NewSolutionView({
                el: $('.sub-cont')
            });
            newSolutionView.render();
        }

    });

    return AppRouter;
})
;