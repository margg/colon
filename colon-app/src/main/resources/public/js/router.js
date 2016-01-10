define([
    'jquery',
    'underscore',
    'backbone',
    'views/main-page',
    'views/student/tasks',
    'views/student/task'
], function ($, _, Backbone, MainPageView, StudentTasksView, StudentTaskView) {
    var AppRouter = Backbone.Router.extend({
        routes: {
            '!/tasks/:id': 'showTask',
            '!/tasks': 'showTasks',
            '!': 'mainPage'
        },

        mainPage: function () {
            var mainPageView = new MainPageView({
                el: $('.sub-cont')
            });
            mainPageView.render();
        },

        showTasks: function() {
            var studentTasksView = new StudentTasksView({
                el: $('.sub-cont')
            });
            studentTasksView.render();
        },

        showTask: function(id) {
            var studentTaskView = new StudentTaskView({
                el: $('.sub-cont'),
                id: id
            });
            studentTaskView.render();
        }

    });

    return AppRouter;
})
;