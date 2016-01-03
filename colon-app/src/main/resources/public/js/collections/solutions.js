define([
    'jquery',
    'underscore',
    'backbone',
    'models/solution'
], function($, _, Backbone, Solution) {
    var Solutions = Backbone.Collection.extend({
        model: Solution,
        url: function() {
            return 'api/student/tasks/' + this.model.get('task').get('id') + '/solutions';
        }
    });

    return Solutions;


});
