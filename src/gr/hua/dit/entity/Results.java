package gr.hua.dit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "results")
public class Results {
	
	@Id
	@Column(name = "rid")
	private int rid;

	@Column(name = "studname")
	private String studname;

	@Column(name = "stdemail")
	private String stdemail;
	
	@Column(name = "studgrade")
	private int studgrade;
	
	public Results() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Results(int rid, String studname, String stdemail, int studgrade) {
		super();
		this.rid = rid;
		this.studname = studname;
		this.stdemail = stdemail;
		this.studgrade = studgrade;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getStudname() {
		return studname;
	}

	public void setStudname(String studname) {
		this.studname = studname;
	}

	public String getStdemail() {
		return stdemail;
	}

	public void setStdemail(String stdemail) {
		this.stdemail = stdemail;
	}

	public int getStudgrade() {
		return studgrade;
	}

	public void setStudgrade(int studgrade) {
		this.studgrade = studgrade;
	}
	
}
