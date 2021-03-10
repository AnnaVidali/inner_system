package gr.hua.dit.dao;

import java.util.List;

import gr.hua.dit.entity.Applypoints;
import gr.hua.dit.entity.Changemail;
import gr.hua.dit.entity.Student;

public interface EmployeeDAO {
	
	public String employeeDeleteRights(String email);
	
	public String employeeCreateRights(String fullname, String email, int year);
	
    public String employeeCreateStudent(Student student);
	
	public String employeeDeleteStudent(String email, int id);
	
	public String employeeModifyStudent(String name, String oldemail, String newemail);
	
	public String activateStudent(String email);
	
	public int authorizedrole(String email);
	
	public String changerole(String email);
	
	public int employGetGrade(int personalIncome, int familyIncome, int workParents, int siblings, String sameCity, int freeHousingYears, int years);
	
	public String applyPoints(String fullname, String stdemail, int points);
	
	public int authorized(String email);
	
	public List<Applypoints> pointsNotifications();
	
	public List<Changemail> emailNotifications();
		
}
