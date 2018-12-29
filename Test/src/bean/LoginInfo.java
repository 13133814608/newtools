package bean;


public class LoginInfo {
	//登陆Id
	private double id;
	//登陆URl
	private  String url;
	//预期结果
	private String excepedresult;
	//实际结果
	private String reallyresult;
	//请求参数
	private  String username;
	//请求密码
	private String password;
	//预期结果
	private String result;
	public String getUsername() {
		return username;
	}
	public void setUsername(String name) {
		this.username = name;
	}
	public String getExcepedresult() {
		return excepedresult;
	}
	public void setExcepedresult(String excepedresult) {
		this.excepedresult = excepedresult;
	}
	public String getReallyresult() {
		return reallyresult;
	}
	public void setReallyresult(String reallyresult) {
		this.reallyresult = reallyresult;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
    

	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public double getId() {
		return id;
	}
	public void setId(double d) {
		this.id = d;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
