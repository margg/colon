package pl.edu.agh.to.app.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//package pl.edu.agh.to.app.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import pl.edu.agh.to.app.model.*;
@RestController
public class HomeController {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

}
//import java.util.*;
//
//@RestController
//@RequestMapping("/api")
//public class HomeController {
//
//	@RequestMapping(value="/teacher", method = RequestMethod.GET, produces = "application/json;charset=UTF-8", headers="Accept=*")
//	public Teacher findAll() {
//        Teacher t = new Teacher("Jan", "Kowalski", "jkow", null, null);
//        Group gr = new Group(t, null, "3A");
//        List<Group> grs = new ArrayList<Group>();
//        grs.add(gr);
//
//        Map<Group, Date> map = new HashMap<Group, Date>();
//        map.put(gr, new Date());
//        Task t1 = new Task(t, map, 15, "lalala", "hihihi");
//        List<Task> tasks = new ArrayList<Task>();
//        tasks.add(t1);
//
//        t.setGroups(grs);
//        t.setTasks(tasks);
//
//
//        try {
//            return t;
//        } catch (Exception e) {
//            return null;
//        }
//    }
//}
