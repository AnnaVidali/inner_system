package gr.hua.dit.restcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.dit.entity.*;
import gr.hua.dit.dao.*;

@RestController
@RequestMapping("/api")
public class RestStudent {
	
	@Autowired
	private EmployeeDAO employeedao;
	
	@Autowired
	private StudentDAO studentdao;
	
	@PutMapping("/changeinfo")
	public String changeInfo() {
		return "change-info";
	}
	
	@PutMapping("/changeMSG")
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
	
	
	@PostMapping("/doApply")
	public String makeApply() {
		return "make-apply";
	}
	
	@PostMapping("/applicationForm")
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
	
	@GetMapping("/checkRank")
	public String checkRanking() {
		return "check-ranking";
	}
	
	@GetMapping("/rankMSG")
	public String rankmsg(Model model,@RequestParam String email) {
		int r = studentdao.getRank(email);
		model.addAttribute("rank", r);
		return "showrank";
	}
	
	@GetMapping("/logoutSTD")
	public String logOut() {
		return "login";
	}

}
