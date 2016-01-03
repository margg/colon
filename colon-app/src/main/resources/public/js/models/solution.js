define([
    'jquery',
    'underscore',
    'backbone',
    'models/task'
], function ($, _, Backbone, Task) {
    var Solution = Backbone.Model.extend({
        defaults: {
            'id': 0,
            'execTime': null,
            'status': '',
            'author': null,
            'task': null,
            'code': ''
        }
    });

    return Solution;


});