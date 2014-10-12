package tangzq.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "user")
public class User {

	private List<UserRole> userRoles;

	private String id;

	private Date createdatetime;

	private Date modifydatetime;

	private String name;

	private String pwd;

	private Date createTime;

	private Date updateTime;


    public List<UserRole> getUserRoles() {
        return userRoles;
    }
    //注意XmlElement不能放到private属性上
    @XmlElementWrapper(name = "roles")
    @XmlElement(name = "role")
    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public Date getCreatedatetime() {
		return createdatetime;
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	public Date getModifydatetime() {
		return modifydatetime;
	}

	public void setModifydatetime(Date modifydatetime) {
		this.modifydatetime = modifydatetime;
	}

	public String getName() {
		return name;
	}
    @XmlElement
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
    @XmlElement
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd == null ? null : pwd.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}