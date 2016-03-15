package main;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class YoutubeDownloader {

	WebDriver driver;
	YoutubePlaylist ytpl;
	Downloader dler;
	String url;
	String dest_path = "C:\\Users\\Koaja\\Desktop";
	String youtubeURL = "https://www.youtube.com/playlist?list=PLjJMPXVyEPxw3KHhx59HXGRI4lBtih5Z-";
	String downloaderURL = "http://www.youtube-mp3.org/";

	@BeforeMethod
	public void setup() {

		ProfilesIni profile = new ProfilesIni();

		FirefoxProfile myprofile = profile.getProfile("SetupWithoutDowloadPrompt");

		driver = new FirefoxDriver(myprofile);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setPreference("browser.download.dir", dest_path);
		firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
		firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);

		driver.get(youtubeURL);
	}

	@Test
	public void downloadAllSongs() throws InterruptedException {

		ytpl = new YoutubePlaylist(driver);
		dler = new Downloader(driver);
		ArrayList<String> links = new ArrayList<>();
		links.addAll(ytpl.getAllLinks());

		driver.get(downloaderURL);
		for (String link : links) {
			dler.addLinkToField(link);
			dler.clickConverButton();

			Thread.sleep(2000);

			String parentHandle = driver.getWindowHandle();
			driver.switchTo().window(parentHandle);
			// for (String handle : driver.getWindowHandles()) {
			// if (!handle.equals(parentHandle)) {
			// driver.switchTo().window(handle).close();
			// }
			// }

			Thread.sleep(2000);
			

			dler.clickDownloadButton();

		}
	}
}
