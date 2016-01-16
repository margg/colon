define([
    'jquery',
    'underscore',
    'backbone',
    'models/task',
    'models/solution',
    'text!templates/student/task.html'
], function ($, _, Backbone, Task, Solution, studentTaskTemplate) {
    var StudentTaskView = Backbone.View.extend({

        model: Task,

        initialize: function () {
            this.model = new Task({id: this.id});
        },

        events: {
            'click button': 'onSubmitClick'
        },

        render: function () {

            this.model.fetch({
                success: function (model, response, options) {
                    this.renderView();
                }.bind(this)
            });
        },

        renderView: function () {
            this.$el.html(_.template(studentTaskTemplate, {
                model: this.model
            }));
        },

        onSubmitClick: function (e) {
            e.preventDefault();

            var file = this.$('input[type="file"]')[0].files[0],
                formData = new FormData();

            formData.append("solution_file", file);

            $.ajax({
                url: 'api/student/tasks/' + this.model.get('id') + '/solutions/new',
                type: 'post',
                dataType: 'json',
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
                success: function (data) {
                    debugger;
                    this.model.get('solutions').add(new Solution(data));

                }.bind(this)
            });
}
})
;
return StudentTaskView;
})
;
