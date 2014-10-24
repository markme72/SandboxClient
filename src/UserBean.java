import java.io.Serializable;

public class UserBean implements Serializable {
	
	private Integer id;
	private String uname;
	private String pword;
	
	public UserBean() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPword() {
		return pword;
	}

	public void setPword(String pword) {
		this.pword = pword;
	}
}
