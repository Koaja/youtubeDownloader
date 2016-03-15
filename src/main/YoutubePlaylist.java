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

	public YoutubePlaylist(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public ArrayList<String> getAllLinks() {

		ArrayList<String> songs = new ArrayList<>();

		for (WebElement elem : playlistSongs) {
			songs.add(elem.getAttribute("href"));
		}

		return songs;
	}

}
