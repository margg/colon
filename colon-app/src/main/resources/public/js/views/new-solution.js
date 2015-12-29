define([
    'jquery',
    'underscore',
    'backbone',
    '../models/group',
    'text!templates/new-solution.html'
], function($, _, Backbone, Group, newSolutionTemplate){
    var NewSolutionView = Backbone.View.extend({

        render: function(){
            this.$el.html(_.template(newSolutionTemplate));
        },

        onSubmitClick: function(e) {
            e.preventDefault();
            debugger;
        }
    });
    return NewSolutionView;
});
