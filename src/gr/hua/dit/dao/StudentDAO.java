package gr.hua.dit.dao;

import gr.hua.dit.entity.Applypoints;
import gr.hua.dit.entity.Changemail;
import gr.hua.dit.entity.Results;

public interface StudentDAO {
	
	public String getLogin(String email, String password);
	
	public int getStudentByEmail(String email);
	
	public String changeEmail(Changemail changemail);
	
	public String application(Applypoints application);
	
	public int getRank(String email);

}
