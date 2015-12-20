define([
    'jquery',
    'underscore',
    'backbone'
], function($, _, Backbone) {
    var User = Backbone.Model.extend({
        defaults: {
            'firstName': '',
            'lastName': '',
            'username': '',
            'solutions': null
        }
    });

    return User;


});