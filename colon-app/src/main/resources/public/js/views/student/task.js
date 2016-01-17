define([
    'jquery',
    'underscore',
    'backbone',
    'models/task',
    'models/solution',
    'text!templates/student/task.html',
    'text!templates/student/solutionItem.html'
], function ($, _, Backbone, Task, Solution, studentTaskTemplate, solutionItemTemplate) {
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

            this.model.get('solutions').each(function(sol){
                this.$('.solutions-tab tbody').append(_.template(solutionItemTemplate, {
                    solution: sol
                }));
            })
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
                    this.model.get('solutions').add(new Solution(data));
                    this.model.save()
                        .done(this.renderView()).bind(this);

                }.bind(this)
            });
}
})
;
return StudentTaskView;
})
;
