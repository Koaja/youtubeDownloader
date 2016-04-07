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

		FirefoxProfile myprofile = profile.getProfile("YoutubeDownloadPromptRemoved");

		driver = new FirefoxDriver(myprofile);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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

			String parentHandle = driver.getWindowHandle();

			System.out.println("Attempting to download " + ytpl.getSongName());

			driver.switchTo().window(parentHandle);

			dler.clickDownloadButton();

		}
	}
}
