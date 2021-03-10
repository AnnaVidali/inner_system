package gr.hua.dit.controller;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gr.hua.dit.dao.EmployeeDAO;
import gr.hua.dit.dao.StudentDAO;
import gr.hua.dit.entity.Applypoints;
import gr.hua.dit.entity.Changemail;
import gr.hua.dit.entity.Results;

@Controller
public class StudentController {
	
	@Autowired
	private EmployeeDAO employeedao;
	
	@Autowired
	private StudentDAO studentdao;
	
	@RequestMapping("/changeinfo")
	public String changeInfo() {
		return "change-info";
	}
	
	@RequestMapping("/changemessage")
	public String changeMessage(Model model, @RequestParam String fullname, @RequestParam String oldemail, @RequestParam String newemail) {
		Changemail change = new Changemail(fullname,oldemail,newemail);
		String validation = studentdao.changeEmail(change);
		String next;
		if(validation == "success") {
			model.addAttribute("newemail", newemail);
			next = "change-message";
		}else {
			next = "fail-message";
		}
		return next;
	}
	
	@RequestMapping("/makeapply")
	public String makeApply() {
		return "make-apply";
	}
	
	@RequestMapping("/form")
	public String makeForm(@RequestParam String personal_income, @RequestParam String family_income, @RequestParam String working, @RequestParam String siblings, @RequestParam String answer, @RequestParam String free_housing, @RequestParam String year, @RequestParam String fullname, @RequestParam String email) {
		int personalIncome = Integer.parseInt(personal_income), familyIncome = Integer.parseInt(family_income), workParents = Integer.parseInt(working), siblingsstudy = Integer.parseInt(siblings), freeHousingYears = Integer.parseInt(free_housing), years = Integer.parseInt(year);
		int grade = employeedao.employGetGrade(personalIncome, familyIncome, workParents, siblingsstudy, answer, freeHousingYears, years);
		String message;
		String next;
		Applypoints app = new Applypoints(fullname, email, grade);
		message = studentdao.application(app);
		if(message == "success") {
			next = "success-message";
		}else {
			next = "fail-message";
		}
		return next;
	}
	
	@RequestMapping("/checkranking")
	public String checkRanking() {
		return "check-ranking";
	}
	
	@RequestMapping("/rankmessage")
	public String rankmsg(Model model,@RequestParam String email) {
		int r = studentdao.getRank(email);
		model.addAttribute("rank", r);
		return "showrank";
	}
	
	@RequestMapping("/logoutstudent")
	public String logOut() {
		return "login";
	}
}
