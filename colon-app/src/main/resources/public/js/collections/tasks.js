define([
    'jquery',
    'underscore',
    'backbone',
    'models/task'
], function($, _, Backbone, Task) {
    var Tasks = Backbone.Collection.extend({
        model: Task,
        url: function() {
            return 'api/student/tasks';
        }
    });

    return Tasks;


});
