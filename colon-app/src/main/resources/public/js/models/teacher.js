define([
    'jquery',
    'underscore',
    'backbone',
    'group',
    'task'
], function($, _, Backbone, Group, Task) {
    var Teacher = Backbone.Model.extend({
        defaults: {
            'firstName': '',
            'lastName': '',
            'username': '',
            'groups': null,
            'tasks': null
        }
    });

    return Teacher;


});