package gr.hua.dit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@Column(name = "eid")
	private int eid;

	@Column(name = "ename")
	private String ename;

	@Column(name = "eemail")
	private String eemail;

	@Column(name = "epass")
	private String epass;

	@Column(name = "erole")
	private String erole;

	public Employee(String ename, String eemail, String epass, String erole) {
		super();
		this.ename = ename;
		this.eemail = eemail;
		this.epass = epass;
		this.erole = erole;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}
	
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEemail() {
		return eemail;
	}

	public void setEemail(String eemail) {
		this.eemail = eemail;
	}

	public String getEpass() {
		return epass;
	}

	public void setEpass(String epass) {
		this.epass = epass;
	}

	public String getErole() {
		return erole;
	}

	public void setErole(String erole) {
		this.erole = erole;
	}
	
}
