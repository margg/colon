package pl.edu.agh.to.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.to.app.model.Student;

@RestController
public class HomeController {

    @RequestMapping("/api")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/students")
    public Student findAll() {
        return new Student("aaa", "bbb", "usr1", "secret", null);
    }

}