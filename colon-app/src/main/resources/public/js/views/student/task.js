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
                formData = new FormData();

            formData.append("file", file);

            $.ajax({
                url: 'api/student/tasks/' + this.model.get('id') + '/solutions/new',
                type: 'post',
                dataType: 'json',
                success: function(data) {
                    $.ajax({
                        url: '172.29.140.38:80/solutions/' + data.id,
                        type: 'post',
                        data: formData,
                        cache: false,
                        processData: false,
                        contentType: false,
                        success: function(data){
                            debugger
                        }
                    })
                }
            });
        }
    });
    return StudentTaskView;
});
