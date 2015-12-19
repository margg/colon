package pl.edu.agh.to.app.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.edu.agh.to.app.model.Student;
import pl.edu.agh.to.app.persistence.HibernateUtils;

import java.util.List;

@Controller
@RequestMapping("/api")
public class HomeController {

	@RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=UTF-8", headers="Accept=*") @ResponseBody
	public List<Student> findAll() {
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		List<Student> studentList = session.createQuery("from Student").list();
		transaction.commit();
		session.close();
		return studentList;
	}
}
