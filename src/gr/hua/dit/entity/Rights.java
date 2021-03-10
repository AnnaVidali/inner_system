package gr.hua.dit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rights")
public class Rights {
	
	@Column(name = "name")
	private String name;
	
	@Id
	@Column(name = "email")
	private String email;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "crtdelrole")
	private int crtdelrole;
	
	@Column(name = "crtdelmodusers")
	private int crtdelmodusers;
	
	@Column(name = "crtdelservices")
	private int crtdelservices;
	
	@Column(name = "modrole")
	private int modrole;
	
	@Column(name = "makeapp")
	private int makeapp;
	
	public Rights() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rights(String name, String email, String role, int crtdelrole, int crtdelmodusers,
			int crtdelservices, int modrole, int makeapp) {
		super();
		this.name = name;
		this.email = email;
		this.role = role;
		this.crtdelrole = crtdelrole;
		this.crtdelmodusers = crtdelmodusers;
		this.crtdelservices = crtdelservices;
		this.modrole = modrole;
		this.makeapp = makeapp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getCrtdelrole() {
		return crtdelrole;
	}

	public void setCrtdelrole(int crtdelrole) {
		this.crtdelrole = crtdelrole;
	}

	public int getCrtdelmodusers() {
		return crtdelmodusers;
	}

	public void setCrtdelmodusers(int crtdelmodusers) {
		this.crtdelmodusers = crtdelmodusers;
	}

	public int getCrtdelservices() {
		return crtdelservices;
	}

	public void setCrtdelservices(int crtdelservices) {
		this.crtdelservices = crtdelservices;
	}

	public int getModrole() {
		return modrole;
	}

	public void setModrole(int modrole) {
		this.modrole = modrole;
	}

	public int getMakeapp() {
		return makeapp;
	}

	public void setMakeapp(int makeapp) {
		this.makeapp = makeapp;
	}
	
}
