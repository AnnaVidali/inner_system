package gr.hua.dit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "changemail")
public class Changemail {
	
	@Column(name = "stdname")
	private String stdname;
	
	@Id
	@Column(name = "stdoldmail")
	private String stdoldmail;
	
	@Column(name = "stdnewmail")
	private String stdnewmail;

	public Changemail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Changemail(String stdname, String stdoldmail, String stdnewmail) {
		super();
		this.stdname = stdname;
		this.stdoldmail = stdoldmail;
		this.stdnewmail = stdnewmail;
	}

	public String getStdname() {
		return stdname;
	}

	public void setStdname(String stdname) {
		this.stdname = stdname;
	}

	public String getStdoldmail() {
		return stdoldmail;
	}

	public void setStdoldmail(String stdoldmail) {
		this.stdoldmail = stdoldmail;
	}

	public String getStdnewmail() {
		return stdnewmail;
	}

	public void setStdnewmail(String stdnewmail) {
		this.stdnewmail = stdnewmail;
	}
	
	
}
