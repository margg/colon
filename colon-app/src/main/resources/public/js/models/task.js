define([
    'jquery',
    'underscore',
    'backbone'
], function($, _, Backbone) {
    var Task = Backbone.Model.extend({
        defaults: {
            'id': 0,
            'name': '',
            'description': '',
            'dates': null,
            'timeLimit': 0,
            'testInput': '',
            'testOutput': '',
            'inFilePath': '',
            'outFilePath': ''
        },

        url : function() {
            return 'api/student/tasks/' + this.get('id');
        }
    });

    return Task;


});
