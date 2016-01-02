define([
    'jquery',
    'underscore',
    'backbone',
    '../models/group',
    'text!templates/teacher-mp.html'
], function($, _, Backbone, Group, mainPageTemplate){
    var MainPageView = Backbone.View.extend({

        render: function(){
            this.$el.html(_.template(mainPageTemplate));
        }
    });
    return MainPageView;
});