define([
    'jquery',
    'underscore',
    'backbone',
    'models/student'
], function($, _, Backbone, Student) {
    var Group = Backbone.Collection.extend({
        model: Student
    });

    return Group;


});