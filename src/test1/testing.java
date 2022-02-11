package test1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class testing{

		public static int fail = 0;
		public static int pass = 0;
		public static WebDriver driver = null;
		public static ExtentTest test;
		public static ExtentReports reports;
		public static xlsClasses xc = null;
		public static String browser = "";
		public static boolean reject=false;

		//// main function
//		public static void main(String[] args) throws Exception {
		public static void test() throws Exception {

			System.out.println("_____________________________________________________________");
			System.out.println("Test execution Begin!!");

			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\msedgedriver.exe");

			browser = "Edge";

			xc = new xlsClasses(System.getProperty("user.dir") + "//LokOS testcases.xlsx", "Login");

			int[] dimensions = xc.getRowCols("Login");
			int row = dimensions[0];

			if (row == -1) {
				throw new Exception("There are no rows in 'Login'");
			}

			int iterations = 0;
			for (int r = 1; r < row - 1; r++) {

				xc.changeSheet("Login");

				System.out.println("============================");
				System.out.println("Iteration number:" + (++iterations));

				try {
					browser = xc.getCellString(r, 1);
					System.out.println("Testing in " + browser);

					TestBase tb = new TestBase();
					driver = tb.launchBrowser(browser);
					
					WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(120, 1));

					String url = xc.getCellString(r, 2);
					System.out.println("Opening URL " + url);
					driver.get(url);

					String text_name = xc.getCellString(r, 3);
					System.out.println("We are in:" + text_name);
					
					if (text_name.equals("State")) {

						driver.findElement(By.xpath(loginConstants.startPage_StatePath)).click();
						WebElement ddown = driver.findElement(By.xpath(loginConstants.userRoleDDown_StatePath));
						Select select = new Select(ddown);
						select.selectByVisibleText(xc.getCellString(r, 4));
						driver.findElement(By.xpath(loginConstants.userID_StatePath)).sendKeys(xc.getCellString(r, 5));
						driver.findElement(By.xpath(loginConstants.password_StatePath)).sendKeys(xc.getCellString(r, 6));
						Thread.sleep(3000);
						if (browser.equals("Chrome"))
							Thread.sleep(1000);
						driver.findElement(By.xpath(loginConstants.submitButton_StatePath)).click();
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

						try {
							driver.findElement(By.xpath(loginConstants.userScreenLoginSuccess_StatePath));
						} catch (Exception e) {
							// test.log(Status.FAIL,"Invalid User or Password:Login failed");
							System.out.println("Invalid User or Password:Login failed");
							throw new Exception("Invalid User or Password:Login failed");
						}

						if (xc.getCellString(r, 4).equals("Block Program Manager")) {
							try {
								WebElement shgVBP;
								shgVBP=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BPMConstants.shgVerificationBoxPath)));
								driver.findElement(By.xpath(BPMConstants.shgVerificationBoxPath)).click();
								Thread.sleep(1500);
								driver.findElement(By.xpath(BPMConstants.shgMemApprovalCountPath_1 + "1"+ BPMConstants.shgMemApprovalCountPath_2)).click();
								Thread.sleep(1500);
								driver.findElement(By.xpath(BPMConstants.memNameButtonPath_1 + "1" + BPMConstants.memNameButtonPath_2)).click();
								Thread.sleep(1500);
								driver.findElement(By.xpath(BPMConstants.memApprovalButtonPath_1 + "1" + BPMConstants.memRejectionButtonPath_3)).click();
								try {
//									WebElement dd = driver.findElement(By.xpath("(//span[text()='Select List'])["+"1"+"]"));
//									Select S = new Select(dd);
//									S.selectByIndex(0);
									driver.findElement(By.xpath("(//span[text()='Select List'])["+"1"+"]")).click();
									Thread.sleep(2000);
									driver.findElement(By.xpath("(//div[text()='Select All'])[1]")).click();
								}catch(Exception e) {
									System.out.println("Error@1:"+e.getMessage());
								}
								try {
									driver.findElement(By.xpath(BPMConstants.memApprovalButtonPath_1 + "2" + BPMConstants.memRejectionButtonPath_3)).click();
									driver.findElement(By.xpath("(//span[text()='Select List'])["+"1"+"]")).click();
									driver.findElement(By.xpath("(//div[text()='Select All'])[1]")).click();
								}catch(Exception e){
									System.out.println("Error@2:"+e.getMessage());
								}
								try {
									driver.findElement(By.xpath(BPMConstants.memApprovalButtonPath_1 + "3" + BPMConstants.memRejectionButtonPath_3)).click();
									driver.findElement(By.xpath("(//span[text()='Select List'])["+"1"+"]")).click();									
//									driver.findElement(By.xpath("(//div[text()='Select All'])["+"1"+"]")).click();
									driver.findElement(By.xpath("(//div[text()='Select All'])[1]")).click();
																		
								}catch(Exception e){
									System.out.println("Error@3:"+e.getMessage());
								}
								driver.findElement(By.id("reject_btn")).click();
								driver.findElement(By.id("exampleFormControlTextarea1")).sendKeys("Test run");
								driver.findElement(By.xpath("//button[@class='navbar-toggler close-button']")).click();
								
								
							} catch (Exception e) {
								System.out.println("Failed BPM SHG Approval");
								e.printStackTrace();
								throw new Exception("Failed BPM SHG Approval");
							}
						}
						++pass;
					} else {
						// test.log(Status.FAIL, "Wrong Section");
						System.out.println("Should be National/State");
						throw new Exception("Should be National/State");

					}

					Thread.sleep(3000);
					xlsClasses.closeReaders();
					driver.findElement(By.xpath("//div[@class='profile-pic']")).click();
					driver.findElement(By.xpath("//button[@class='btn-search mr-3']")).click();
					driver.quit();

				} catch (Exception e) {
					Thread.sleep(3000);
					driver.quit();
					++fail;
					System.out.println(e.getMessage());
					// test.log(Status.FAIL, "Process Failed");
					System.out.println("\n........................");
					System.out.println(
							"Process failed in iteration " + iterations + "\nReason for failure: " + e.getMessage());
					continue;
				}

			}

			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			System.out.println("Fails:" + fail + " out of " + iterations);
			// test.log(Status.INFO, "Fails:"+f+" out of"+t);
			// test.log(Status.PASS,"Execution complete");
			System.out.println("Execution Complete!!");
			System.out.println("_____________________________________________________________");
		}

		
public static void testApprovals() {
	Thread.sleep(1500);
	driver.findElement(By.linkText("SHG Profile")).click();
	Thread.sleep(1500);
	System.out.println("We are in SHG Profile verification screen");

	boolean shgProfileApproveButton_flag = true;
	int i = 1;

		memRejection.rejectMem();// Used for selecting rejection criteria

		Thread.sleep(1000);
		driver.findElement(By.xpath(BPMConstants.memNameButtonPath_1 + j + BPMConstants.memNameButtonPath_2))
				.click();

		System.out.print("Getting Approval for member: " + j);
		boolean flag = true;
		int i = 1;// marks row number in the approvals list

		Thread.sleep(2500);

		//// Approves or Rejects members one by one according to rejection criteria
		while (flag) {
			//
			if (!reject) {
				driver.findElement(
						By.xpath(BPMConstants.memApprovalButtonPath_1 + i + BPMConstants.memApprovalButtonPath_2))
						.click();
				i++;
				flag = LoginTest.isElementPresent(
						By.xpath(BPMConstants.memApprovalButtonPath_1 + i + BPMConstants.memApprovalButtonPath_2));
			} else {
				///////////////////////////////
				memRejection.rejectionRemarks();
				///////////////////////////////
				driver.findElement(By.id(BPMConstants.rejectButtonID)).click();
				driver.findElement(By.id(BPMConstants.rejectFormID)).sendKeys("Test run");
				driver.findElement(By.xpath(BPMConstants.rejectFormOKButtonPath)).click();
				flag = false;
			}

		}
		if (!reject)
			System.out.println("->Approved");
		else
			System.out.println("--->Rejected");
		driver.findElement(By.xpath(BPMConstants.memApprovalToMemListBackPath)).click();// Delete on trial
	}

	System.out.println("All members approved/rejected.");

}
	
}

	

