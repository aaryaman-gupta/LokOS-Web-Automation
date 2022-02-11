package test1;

public class BPMConstants {

	// BPM LHS Drop-down menu
	public static final String myTaskButtonPath = "//p[text()='My Task']";
	public static final String myTaskButton_1Path = "(//a[@href='/notification'])[2]";

	// BPM user first screen
	public static final String shgCountPath = "(//span[@class='value-nt'])[1]";
	public static final String shgVerificationBoxPath = "(//a[@class='com-box my_boxs'])[1]";
	public static final String selectSHG_NextPageButtonPath = "//path[@d='M10 6L8.59 7.41 13.17 12l-4.58 4.59L10 18l6-6z'";

	// Member approval
	public static final String memApprovalCountPath = "//span[@id='totalShg'][@class='ng-star-inserted']";
	public static final String memApprovalToMemListBackPath = "//button[text()=' Back']";
	public static final String memNameButtonPath_1 = "//table[contains(@class,'table-new dtable-hover')]/tbody[1]/tr[";
	public static final String memNameButtonPath_2 = "]/td[3]/a[1]";
	public static final String memApprovalButtonPath_1 = "//table[contains(@class,'table-new table-hover')]/tbody[1]/tr[";
	public static final String memApprovalButtonPath_2 = "]/td[8]/div[1]/button[1]/i[1]";
	public static final String memRejectionButtonPath_3 = "]/td[8]/div[1]/button[2]/i[1]";
	public static final String rejectButtonID = "reject_btn";
	public static final String rejectFormID = "exampleFormControlTextarea1";
	public static final String rejectFormOKButtonPath ="//button[text()=' Submit ']";

	// SHG profile approvals
	public static final String shgProfileToSHGListBackPath = "//a[@href='/my-tasks']//i[1]";
	public static final String shgProfileApprovalButtonPath_1 = "//table[contains(@class,'table-new table-hover')]/tbody[1]/tr[";
	public static final String shgProfileApprovalButtonPath_2 = "]/td[7]/div[1]/button[1]/i[1]";
	public static final String shgProfileRejectionButtonPath_3="]/td[7]/div[1]/button[2]/i[1]";

	// SHG List
	public static final String shgListNamePath_1 = "//table[@class='table-new']/tbody[1]/tr[";
	public static final String shgListNamePath_2 = "]/td[5]/a[1]";
	public static final String shgMemApprovalCountPath_1 = "//table[@class='table-new']/tbody[1]/tr[";
	public static final String shgMemApprovalCountPath_2 = "]/td[11]/a[1]";

	// SHG approvals
	public static final String shgApprovalButtonPath_1 = "//table[contains(@class,'table-new table-hover')]/tbody[1]/tr[";
	public static final String shgApprovalButtonPath_2 = "]/td[7]/div[1]/button[1]/i[1]";

}
