define([
    'jquery',
    'underscore',
    'backbone',
    '../models/group',
    'text!templates/teacher-mp.html'
], function($, _, Backbone, Group, mainPageTemplate){
    var MainPageView = Backbone.View.extend({

        render: function(){
            debugger
            $.get('api/teacher').done(function(data){
                this.$el.html(_.template(mainPageTemplate));
                var group = new Group(data.groups[0]);
                debugger;
            }.bind(this));

        }
    });
    return MainPageView;
});