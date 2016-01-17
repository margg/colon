define([
    'jquery',
    'underscore',
    'backbone',
    'collections/solutions',
    'models/solution'
], function($, _, Backbone, Solutions, Solution) {
    var Task = Backbone.Model.extend({
        defaults: {
            'id': 0,
            'name': '',
            'description': '',
            'solutions': new Solutions(),
            'dates': null,
            'timeLimit': 0,
            'testInput': '',
            'testOutput': ''
        },

        url : function() {
            return 'api/student/tasks/' + this.get('id');
        },

        parse: function(data) {
            _.forEach(data.solutions, function(solution){
                this.get('solutions').add(new Solution(solution));
            }.bind(this));

            return _.omit(data, 'solutions');
        }
    });

    return Task;


});
