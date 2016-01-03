define([
    'jquery',
    'underscore',
    'backbone',
    'models/task',
    'text!templates/student/task.html'
], function($, _, Backbone, Task, studentTaskTemplate){
    var StudentTaskView = Backbone.View.extend({

        model: Task,

        initialize: function() {
            this.model = new Task({id: this.id});
        },

        events: {
            'click button': 'onSubmitClick'
        },

        render: function(){

            this.model.fetch({
                success: function (model, response, options) {
                    this.renderView();
                }.bind(this)
            });
        },

        renderView: function() {
            this.$el.html(_.template(studentTaskTemplate, {
                model: this.model
            }));
        },

        onSubmitClick: function(e) {
            e.preventDefault();

            var file = this.$('input[type="file"]')[0].files[0],
                data = new FormData();

            data.append("file", file);

            $.ajax({
                url: 'api/tasks/' + this.model.get('id') + '/solutions/new',
                type: 'post',
                data: data,
                cache: false,
                dataType: 'json',
                processData: false,
                contentType: false
            });
        }
    });
    return StudentTaskView;
});
