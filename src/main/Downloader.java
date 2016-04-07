package main;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Downloader {

	WebDriver driver;

	@FindBy(id = "submit")
	WebElement converterBtn;

	@FindBy(xpath = ".//*[@id='dl_link']/a")
	List<WebElement> downloadBtn;

	@FindBy(id = "youtube-url")
	WebElement urlInput;

	public Downloader(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickConverButton() {
		converterBtn.click();
	}

	public void clickDownloadButton() {

		for (WebElement elem : downloadBtn) {
			try {
				System.out.println("Attempting to click on download button ");
				elem.click();
				System.out.println("-- Found the good button, moving on to next song --");
				break;
			} catch (Exception e) {
				System.out.println("That button wasn't found, trying the next one ");
			}
		}

	}

	public void addLinkToField(String link) {

		urlInput.clear();
		urlInput.sendKeys(link);
	}

}
