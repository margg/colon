define([
    'jquery',
    'underscore',
    'backbone',
    'models/student',
    'collections/tasks',
    'text!templates/student/tasks.html',
    'text!templates/student/taskItem.html'
], function($, _, Backbone, Student, Tasks, studentTasksTemplate, taskItemTemplate){
    var StudentTasksView = Backbone.View.extend({

        initialize: function() {
            this.tasks = new Tasks();
        },

        render: function() {
            this.tasks.fetch({
                success: function (collection, response, options) {
                this.renderView();
                }.bind(this)
            });
        },

        renderView: function(){
            this.$el.html(_.template(studentTasksTemplate));

            var toAppend;
            this.tasks.each(function(task) {
                toAppend+=_.template(taskItemTemplate, {
                    model: task
                });
            });
            this.$('tbody').append(toAppend);
        }
    });
    return StudentTasksView;
});
