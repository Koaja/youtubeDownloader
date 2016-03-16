package main;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YoutubePlaylist {

	WebDriver driver;

	@FindBy(xpath = "//td[@class='pl-video-title']/a[contains(@href,'/watch')]")
	List<WebElement> playlistSongs;

	@FindBy(xpath = ".//*[@id='pl-load-more-destination']/tr[1]")
	List<WebElement> songNames;

	@FindBy(xpath = ".//*[@id='title']")
	WebElement titleSong;

	public YoutubePlaylist(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public ArrayList<String> getAllLinks() {

		ArrayList<String> songsUrl = new ArrayList<>();

		for (WebElement elem : playlistSongs) {
			songsUrl.add(elem.getAttribute("href"));
		}

		return songsUrl;
	}

	public String getSongName() {
		return titleSong.getText();
	}
}
