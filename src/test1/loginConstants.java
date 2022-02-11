package test1;

public class loginConstants {
	
	//// Default URL
	public static final String url_default="http://103.248.60.224:9090/choose-login";


	//// XPATHS FOR THE NATIONAL USER
	public static final String startPage_NationalPath = "//button[text()='National']";
	public static final String userID_NationalPath = "(//label[text()='User ID']/following::input)[1]";
	public static final String password_NationalPath = "(//label[text()='Password']/following::input)[1]";
	public static final String submitButton_NationalPath = "//button[text()='Login']";
	public static final String userScreenLoginSuccess_NationalPath = "//h4[text()='User List']";

	//// XPATHS FOR THE STATE USER
	public static final String startPage_StatePath = "//button[text()='State']";
	public static final String userRoleDDown_StatePath = "//select[contains(@class,'form-control round')]";
	public static final String userID_StatePath = "(//label[text()='User ID']/following::input)[1]";
	public static final String password_StatePath = "(//label[text()='Password']/following::input)[1]";
	public static final String submitButton_StatePath = "//button[@type='submit']";
	public static final String userScreenLoginSuccess_StatePath = "(//a[@class='com-box my_boxs'])[1]";
	public static final String profileButtonPath = "//div[@class='profile-pic']";
	public static final String logoutButtonPath = "//button[@class='btn-search mr-3']";

}
