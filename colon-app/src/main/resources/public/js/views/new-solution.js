define([
    'jquery',
    'underscore',
    'backbone',
    '../models/group',
    'models/solution',
    'models/student',
    'text!templates/new-solution.html'
], function($, _, Backbone, Group, Solution, Student, newSolutionTemplate){
    var NewSolutionView = Backbone.View.extend({

        events: {
          'click button': 'onSubmitClick'
        },

        render: function(){
            this.$el.html(_.template(newSolutionTemplate));
        },

        onSubmitClick: function(e) {
            //e.preventDefault();
            //
            //var file = this.$('input[type="file"]')[0].files[0];
            //var formData = new FormData($('form')[0]);
            //
            ////$.ajax({
            ////    url: 'api/tasks/3/solutions/new',
            ////    type: 'post',
            ////    data: formData,
            ////    headers: {
            ////        'Accept': 'multipart/form-data',
            ////        'Content-Type': 'multipart/form-data'
            ////    },
            ////    dataType: 'json'
            ////});
            //debugger
        }
    });
    return NewSolutionView;
});
