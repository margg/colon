package pl.edu.agh.to.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.edu.agh.to.app.User;

@Controller
@RequestMapping("/api")
public class HomeController {

	@RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=UTF-8", headers="Accept=*") @ResponseBody
	public User findAll() {
		return new User("aaa", "bbb");
	}
}
