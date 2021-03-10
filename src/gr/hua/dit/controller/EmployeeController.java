package gr.hua.dit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gr.hua.dit.dao.EmployeeDAO;
import gr.hua.dit.dao.StudentDAO;
import gr.hua.dit.entity.Applypoints;
import gr.hua.dit.entity.Changemail;
import gr.hua.dit.entity.Student;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeDAO employeedao;
	
	@Autowired
	private StudentDAO studentdao;
	
	@RequestMapping("/createstudent") 
	public String createMenu(){
		return "authorized";
	}
	
	@RequestMapping("/authorization") 
	public String autorization(@RequestParam String email){
		int rights;
		String next;
		rights = employeedao.authorized(email);
		if(rights == 0) {
			next = "not-authorized";
		}else {
			next = "create-student";
		}
		return next;
	}
	
	@RequestMapping("/createmessage")
	public String create(@RequestParam String fullname, @RequestParam String department, @RequestParam String year, @RequestParam String email ) {
		int studentdepartment = Integer.parseInt(department), studentyear = Integer.parseInt(year);
		Student student = new Student(fullname, studentdepartment , studentyear, email, 0, email, "student");
		String validation = employeedao.employeeCreateStudent(student);
		String next;
		if(validation == "success") {
			next = "success-message";
		}else {
			next = "fail-message";
		}
		return next;	
	}
	
	@RequestMapping("/modifystudent") 
	public String modifyMenu(){
		return "authorizedmod";
	}
	
	@RequestMapping("/authorizationmod") 
	public String autorizationmod(@RequestParam String email){
		int rights;
		String next;
		rights = employeedao.authorized(email);
		if(rights == 0) {
			next = "not-authorized";
		}else {
			next = "modify-student";
		}
		return next;
	}
	
	@RequestMapping("/modifymessage")
	public String modify(@RequestParam String fullname, @RequestParam String oldemail, @RequestParam String email) {
		String validation = employeedao.employeeModifyStudent(fullname, oldemail, email);
		String next;
		if(validation == "success") {
			next = "success-message";
		}else {
			next = "fail-message";
		}
		return next;	
	}
	
	@RequestMapping("/deletestudent") 
	public String deleteMenu(){
		return "authorizeddel";
	}
	
	@RequestMapping("/authorizationdel") 
	public String autorizationdel(@RequestParam String email){
		int rights;
		String next;
		rights = employeedao.authorized(email);
		if(rights == 0) {
			next = "not-authorized";
		}else {
			next = "delete-student";
		}
		return next;
	}
	
	@RequestMapping("/deletemessage")
	public String delete(@RequestParam String email ) {
		String validation = employeedao.employeeDeleteStudent(email, studentdao.getStudentByEmail(email));
		String next;
		if(validation == "success") {
			next = "success-message";
		}else {
			next = "fail-message";
		}
		return next;	
	}
	
	@RequestMapping("/activatestudent") 
	public String activatestudent(){
		return "activate-student";
	}
	
	@RequestMapping("/activatemessage") 
	public String activatemsg(@RequestParam String email){
		String next, validation;
		validation = employeedao.activateStudent(email);
		if(validation == "success") {
			next = "success-message";
		}else {
			next = "fail-message";
		}
		return next;
	}
	
	@RequestMapping("/changerole") 
	public String changerole(){
		return "authorized-change-role";
	}
	
	@RequestMapping("/authorizationrole") 
	public String autorizationch(@RequestParam String email){
		int rights;
		String next;
		rights = employeedao.authorizedrole(email);
		if(rights == 0) {
			next = "not-authorized";
		}else {
			next = "change-role";
		}
		return next;
	}
	
	@RequestMapping("/changerolemessage")
	public String chrolemsg(@RequestParam String email) {
		String validation = employeedao.changerole(email);
		String next;
		if(validation == "success") {
			next = "success-message";
		}else {
			next = "fail-message";
		}
		return next;
	}
	
	@RequestMapping("/applypoints")
	public String applypoints() {
		return "applypoints";
	}
	
	@RequestMapping("/applypointsmessage")
	public String applypointsmsg(@RequestParam String fullname, @RequestParam String email, @RequestParam String points) {
		String validation = employeedao.applyPoints(fullname, email, Integer.parseInt(points));
		String next;
		if(validation=="success") {
			next="success-message";
		} else {
			next="fail-message";
		}
		return next;
	}
	
	@RequestMapping("/notifications") 
	public String notif(Model model, Model model1){
		List<Applypoints> applypointslist = employeedao.pointsNotifications();
		List<Changemail> changelist = employeedao.emailNotifications();
		model.addAttribute("applypoints", applypointslist);
		model1.addAttribute("changemail", changelist);
		return "notifications";
	}
	
	@RequestMapping("/logout") 
	public String logout(){
		return "login";
	}
}
