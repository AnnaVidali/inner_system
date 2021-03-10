package gr.hua.dit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "applypoints")
public class Applypoints {
	
	@Column(name = "name")
	private String name;
	
	@Id
	@Column(name = "email")
	private String email;
	
	@Column(name = "points")
	private int points;

	public Applypoints() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Applypoints(String name, String email, int points) {
		super();
		this.name = name;
		this.email = email;
		this.points = points;
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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	
}
