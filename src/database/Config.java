package database;

public class Config {
	
	private String user;
	private String database;
	private String pwd;
	private String driver;
	private String url;


	
	public Config()
	{
		String host="localhost";
		String user="root";
		String pwd="root";
		String database="tooBox";
		String port="3306";
		String driver="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://localhost/sys?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	}
	
	
	public Config(String user, String pwd,String database ,String driver, String url) {

		String host = "localhost";
		String port = "3306";
		this.user = user;
		this.pwd = pwd;
		this.database = database;
		this.driver = driver;
		this.url = url;
	}


	public String getUser()
	{
		return this.user;
	}
	public String getPwd()
	{
		return this.pwd;
	}
	public String getDriver()
	{
		return this.driver;
	}
	public String getUrl()
	{
		return this.url;
	}
	public String getDatabase(){ return  this.database;}
	
	
}
