package pl.edu.agh.to.app.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.agh.to.app.model.Solution;
import pl.edu.agh.to.app.model.Student;
import pl.edu.agh.to.app.persistence.HibernateUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class HomeController {

    @RequestMapping("/api")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/students/new")
    public Student findAll() {
        return new Student("aaa", "bbb", "usr1", "secret", null);
    }

	@RequestMapping("/students/")
	public List<Student> findAllStudents() {
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		List<Student> studentList = session.createQuery("from Student").list();
		transaction.commit();
		session.close();
		return studentList;
	}

	@RequestMapping(path="api/tasks/{id}/solutions/new", method = RequestMethod.POST)
	public String addSolution(@PathVariable("id") int taskId, @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                stream.write(bytes);
                stream.close();
                return "Success " + file.getOriginalFilename();
            } catch (Exception e) {
                return "Fail => " + e.getMessage();
            }
        } else {
            return "You failed to upload because the file was empty.";
        }
	}

}