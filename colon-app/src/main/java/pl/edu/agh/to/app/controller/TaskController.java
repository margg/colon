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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {

    @RequestMapping(path="api/student/tasks/{id}", method = RequestMethod.GET)
    public Task showTask(@PathVariable int id) throws IOException {
        return new Task(3, "Zadanie 1", "Napisz program...", new Teacher("Jan", "Kowalski", "jkowals", "pass", null, null), null, 5, "abc123", "qwerty");
    }

    @RequestMapping(path="api/student/tasks", method = RequestMethod.GET)
    public List<Task> showTasks() throws IOException {
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(3, "Zadanie 1", "Napisz program...", new Teacher("Jan", "Kowalski", "jkowals", "pass", null, null), null, 5, "abc123", "qwerty"));
        return tasks;
    }

    @RequestMapping(path="api/student/tasks/{id}", method = RequestMethod.POST)
    public Solution createSolution(@PathVariable int id) throws IOException {
        return null;
    }

}