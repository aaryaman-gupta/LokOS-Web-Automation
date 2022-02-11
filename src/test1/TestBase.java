package test1;

import java.time.Duration;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class TestBase {
	WebDriver driver;

	public WebDriver launchBrowser(String browserName) {
		if (browserName.equals("Mozilla")) {
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "logs\\firefox.log");
			FirefoxOptions options = new FirefoxOptions();
			FirefoxProfile prof = new FirefoxProfile();
			prof.setPreference("dom.webnotifications.enabled", false);
			options.setProfile(prof);
			driver = new FirefoxDriver(options);
		} else if (browserName.equals("Chrome")) {
			// System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY,
			// "logs\\chrome.log");
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			ChromeOptions ops = new ChromeOptions();
			ops.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			ops.addArguments("--disable-notifications");
			ops.addArguments("--start-maximized");
			driver = new ChromeDriver(ops);
		} else if (browserName.equals("Edge")) {
			System.setProperty(EdgeDriverService.EDGE_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			EdgeOptions options = new EdgeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			options.addArguments("--disable-notifications");
			options.addArguments("--start-maximized");
			driver = new EdgeDriver(options);
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;
	}

}
