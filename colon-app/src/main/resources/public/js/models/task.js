define([
    'jquery',
    'underscore',
    'backbone',
    'collections/solutions'
], function($, _, Backbone, Solutions) {
    var Task = Backbone.Model.extend({
        defaults: {
            'id': 0,
            'name': '',
            'description': '',
            'solutions': new Solutions(),
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
