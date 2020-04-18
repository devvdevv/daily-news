package phambaolong.news.model;

public class RoleModel extends AbstractModel<RoleModel>{
	private String roleName;
	private String code;
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
