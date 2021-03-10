package gr.hua.dit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@Column(name = "sid")
	private int sid;
	
	@Column(name = "sname")
	private String sname;
	
	@Column(name = "department")
	private int department;
	
	@Column(name = "year")
	private int year;
	
	@Column(name = "semail")
	private String semail;
	
	@Column(name = "grade")
	private int grade;
	
	@Column(name = "spass")
	private String spass;
	
	@Column(name = "srole")
	private String srole;

	public Student(String sname, int department, int year, String semail, int grade, String spass, String srole) {
		super();
		this.sname = sname;
		this.department = department;
		this.year = year;
		this.semail = semail;
		this.grade = grade;
		this.spass = spass;
		this.srole = srole;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getSemail() {
		return semail;
	}

	public void setSemail(String semail) {
		this.semail = semail;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getSpass() {
		return spass;
	}

	public void setSpass(String spass) {
		this.spass = spass;
	}

	public String getSrole() {
		return srole;
	}

	public void setSrole(String srole) {
		this.srole = srole;
	}
	
}
