define([
    'jquery',
    'underscore',
    'backbone',
    'text!templates/teacher-mp.html'
], function($, _, Backbone, mainPageTemplate){
    var MainPageView = Backbone.View.extend({

        render: function(){
            this.$el.html(_.template(mainPageTemplate));
        }
    });
    return MainPageView;
});