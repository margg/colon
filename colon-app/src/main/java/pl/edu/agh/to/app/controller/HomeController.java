package pl.edu.agh.to.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/api")
    String home() {
        return "Hello World!";
    }

}