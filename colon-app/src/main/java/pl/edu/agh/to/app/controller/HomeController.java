package pl.edu.agh.to.app.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.agh.to.app.model.Solution;
import pl.edu.agh.to.app.model.Student;
import pl.edu.agh.to.app.persistence.HibernateUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

	@RequestMapping(path="api/solutions/new", method = RequestMethod.POST)
	public String addSolution(@RequestParam("name") String name,
                            @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
	}

}