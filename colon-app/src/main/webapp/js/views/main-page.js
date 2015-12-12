define([
    'jquery',
    'underscore',
    'backbone',
    'text!templates/main-page.html'
], function($, _, Backbone, mainPageTemplate){
    var MainPageView = Backbone.View.extend({

        render: function(){
            $.get('api').done(function(data){
                this.$el.html(_.template(mainPageTemplate));
                this.$('.user').html("The user is " + data.firstName + " " + data.lastName);
            }.bind(this));

        }
    });
    return MainPageView;
});