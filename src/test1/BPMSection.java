package test1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class BPMSection extends LoginTest {

	// GOES INTO SHG VERIFICATION SCREEN OF BPM
	/*
	 * Can only access the (1,1) of BPM SHG Approval
	 */
	public static int bpmApprovalSHG(int row) throws Exception {

		System.out.println("\n*************************");
		System.out.println("We are in BPM user screen");

		Thread.sleep(10000);
		int shgCount = Integer.valueOf(driver.findElement(By.xpath(BPMConstants.shgCountPath)).getText());
		System.out.println("No. of SHGs in queue:" + shgCount);

		//// SHG Count check
		if (shgCount == 0) {
			System.out.println("No SHG in queue for verification");
			return 0;
		} else {
			driver.findElement(By.xpath(BPMConstants.shgVerificationBoxPath)).click();
			boolean shgPresent_flag = false;

			int shgListPageCount = (shgCount / 25) + 1;// default list length is 25
			String shg = xc.getCellString(row, 1);

			//// Iteration done for each page until SHG linkText is found
			for (int i = 0; i < shgListPageCount; i++) {
				Thread.sleep(1500);
				System.out.println("Name SHG: " + xc.getCellString(row, 1));
				shgPresent_flag = LoginTest.isElementPresent(By.linkText(shg));
				if ((!shgPresent_flag) && (i < shgListPageCount - 1))
					driver.findElement(By.xpath(BPMConstants.selectSHG_NextPageButtonPath)).click();
				else if (shgPresent_flag) {
					Thread.sleep(1500);
					driver.findElement(By.linkText(shg)).click();
					Thread.sleep(5000);
					driver.findElement(By.linkText("Members")).click();
					Thread.sleep(2000);
					int shgMemberCount = Integer
							.valueOf(driver.findElement(By.xpath(BPMConstants.memApprovalCountPath)).getText().trim());
					if (shgMemberCount == 0) {
						System.out.println("No members to approve");
					} else {
						System.out.println("Total SHG members:" + shgMemberCount);
						///////////////////////////////////////////
						BPMSection.approveMembers_BPM(shgMemberCount, row);
						/////////////////////////////////////////////
					}
					//////////////////////////////////
					BPMSection.approveSHGProfile_BPM(row);
					//////////////////////////////////
				}
			}

			if (shgPresent_flag == false) {
				return 0;
			} else {
				return 1;
			}

		}
	}
//			Thread.sleep(1500);
//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
//					driver.findElement(By.xpath("//a[@class='btn btn-primary'][@href='/my-tasks']")));
//			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
//			driver.findElement(By.xpath(BPMConstants.shgProfileToSHGListBackPath)).click();
//			driver.back();
//			driver.execute_script("window.history.go(-1)");

//	//APPROVE SHG PROFILE
//	public static void approveSHG_BPM() throws InterruptedException {
//
//		System.out.println("We are in SHG approvals screen");
//
//		boolean flag = true;
//		int i = 1;
//
//		Thread.sleep(2500);
//		
//		////Approve all points one by one
//		while (flag) {
//			driver.findElement(
//					By.xpath(BPMConstants.shgApprovalButtonPath_1 + i + BPMConstants.shgApprovalButtonPath_2)).click();
//			i++;
//			flag = LoginTest.isElementPresent(
//					By.xpath(BPMConstants.shgApprovalButtonPath_1 + i + BPMConstants.shgApprovalButtonPath_2));
//		}
//
//		System.out.println("All approvals given");
//
//	}

	// APPROVE SHG MEMBER
	public static void approveMembers_BPM(int shgMemberCount, int row) throws InterruptedException {

		Thread.sleep(2000);
		System.out.println("We are in SHG Member Verification Screen ");

		Thread.sleep(1000);

		//// Goes into all members one by one
		for (int j = 1; j <= shgMemberCount; j++) {

			memRejection.rejectMem(j, row);// Used for selecting rejection criteria

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
					driver.findElement(By.xpath("//button[text()='Reject Profile Changes']")).click();
					Thread.sleep(3000);
					flag = false;
				}

			}
			if (!reject) {
				driver.findElement(By.xpath("//button[@class='approval_btn mr-1']")).click();;
				Thread.sleep(3000);
			}
			if (!reject)
				System.out.println("->Approved");
			else
				System.out.println("--->Rejected");
//			driver.findElement(By.xpath(BPMConstants.memApprovalToMemListBackPath)).click();// Delete on trial
		}

		System.out.println("All members approved/rejected.");

	}

	// APPROVE SHG PROFILE
	public static void approveSHGProfile_BPM(int row) throws InterruptedException {

		Thread.sleep(1500);
		driver.findElement(By.linkText("SHG Profile")).click();
		Thread.sleep(3500);
		System.out.println("We are in SHG Profile verification screen");

		boolean shgProfileApproveButton_flag = true;
		int i = 1;

		Thread.sleep(2500);

		memRejection.rejectSHGProfile(row);

		if (LoginTest.isElementPresent(By.xpath(
				BPMConstants.shgProfileApprovalButtonPath_1 + i + BPMConstants.shgProfileApprovalButtonPath_2))) {

			while (shgProfileApproveButton_flag) {
				if (!rejectSHG)
					driver.findElement(By.xpath(BPMConstants.shgProfileApprovalButtonPath_1 + i
							+ BPMConstants.shgProfileApprovalButtonPath_2)).click();
				if (rejectSHG) {
					driver.findElement(By.xpath(BPMConstants.shgProfileApprovalButtonPath_1 + i
							+ BPMConstants.shgProfileRejectionButtonPath_3)).click();

					driver.findElement(By.xpath("(//span[text()='Select List'])[1]")).click();
					driver.findElement(By.xpath("//table[contains(@class,'table-new table-hover')]/tbody[1]/tr[" + i
							+ "]/td[8]/ng-multiselect-dropdown[1]/div[1]/div[2]/ul[2]/li[1]/div[1]")).click();
					// Select All:table[contains(@class,'table-new
					// table-hover')]/tbody[1]/tr[1]/td[8]/ng-multiselect-dropdown[1]/div[1]/div[2]/ul[2]/li[1]
					// Duplicate:table[contains(@class,'table-new
					// table-hover')]/tbody[1]/tr[1]/td[8]/ng-multiselect-dropdown[1]/div[1]/div[2]/ul[2]/li[1]/div[1]
				}
				i++;
				shgProfileApproveButton_flag = LoginTest.isElementPresent(By.xpath(
						BPMConstants.shgProfileApprovalButtonPath_1 + i + BPMConstants.shgProfileApprovalButtonPath_2));

			}

		} else
			System.out.println("Nothing to approve in SHG Profile");

		System.out.println("All SHG Profile approvals given");
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		if (reject) {
			driver.findElement(By.id(BPMConstants.rejectButtonID)).click();
			driver.findElement(By.id(BPMConstants.rejectFormID)).sendKeys("Test run");
			driver.findElement(By.xpath(BPMConstants.rejectFormOKButtonPath)).click();
			driver.findElement(By.xpath("//button[text()='Reject Profile']")).click();
			Thread.sleep(2000);
		} else if (!reject) {
			driver.findElement(By.xpath("//button[text()=' Approve Profile']")).click();
			Thread.sleep(2000);
		}
//		driver.findElement(By.xpath(BPMConstants.shgProfileToSHGListBackPath)).click();

	}

}
