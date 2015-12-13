define([
    'jquery',
    'underscore',
    'backbone'
], function($, _, Backbone) {
    var Task = Backbone.Model.extend({
        defaults: {
            'teacher': null,
            'dates': null,
            'timeLimit': 0,
            'testInput': '',
            'testOutput': ''
        }
    });

    return Task;


});
