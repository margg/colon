package pl.edu.agh.to.app.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.to.app.model.Student;
import pl.edu.agh.to.app.persistence.HibernateUtils;

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

}