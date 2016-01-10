package pl.edu.agh.to.app.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.agh.to.app.model.Solution;
import pl.edu.agh.to.app.model.Student;
import pl.edu.agh.to.app.model.Task;
import pl.edu.agh.to.app.model.Teacher;
import pl.edu.agh.to.app.persistence.HibernateUtils;
import pl.edu.agh.to.app.persistence.TaskDao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {

    private TaskDao taskDao = new TaskDao();

    @RequestMapping(path="api/student/tasks/{id}", method = RequestMethod.GET)
    public Task showTask(@PathVariable long id) throws IOException {
        Task task = taskDao.getTask(id);
        return task;
    }

    @RequestMapping(path="api/student/tasks", method = RequestMethod.GET)
    public List<Task> showTasks() throws IOException {
        return taskDao.getTasks(1);
    }

    @RequestMapping(path="api/student/tasks/{id}", method = RequestMethod.POST)
    public Solution createSolution(@PathVariable int id) throws IOException {
        return null;
    }

}