package gr.hua.dit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gr.hua.dit.dao.EmployeeDAO;
import gr.hua.dit.dao.StudentDAO;

@Controller
public class HomeController {
	
	@Autowired
	private StudentDAO studentdao;
	
	@RequestMapping("/")
	public String startuppage() {
		return "login";
	}
	
	@RequestMapping("/menu")
	public String menu(@RequestParam String email, @RequestParam String password) {
		String next, role;
		role = studentdao.getLogin(email, password);
		if(role == "student") {
			next = "student-menu";
		}else if(role == "admin") {
			next = "admin-menu";
		}else {
			next = "failed-login";
		}
		return next;
	}
}
