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
import pl.edu.agh.to.app.persistence.SolutionDao;
import pl.edu.agh.to.app.persistence.StudentDao;
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
    private SolutionDao solutionDao = new SolutionDao();
    private StudentDao studentDao = new StudentDao();

    @RequestMapping(path="api/student/tasks/{id}", method = RequestMethod.GET)
    public Task showTask(@PathVariable long id) throws IOException {
        Task task = taskDao.getTask(id);
        return task;
    }

    @RequestMapping(path="api/student/tasks", method = RequestMethod.GET)
    public List<Task> showTasks() throws IOException {
        return taskDao.getTasks();
    }

    @RequestMapping(path="api/student/tasks/{id}/solutions/new", method = RequestMethod.POST)
    public Solution createSolution(@PathVariable long id) throws IOException {
        Task task = taskDao.getTask(id);
        Student author = studentDao.getStudent(1);

        Solution sol = new Solution(null, "NOT_TESTED", author, task, null);
        solutionDao.saveSolution(sol);
        return sol;

    }

}