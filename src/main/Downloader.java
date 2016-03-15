package main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Downloader {

	WebDriver driver;

	@FindBy(id = "submit")
	WebElement converterBtn;

	@FindBy(xpath = ".//*[@id='dl_link']/a[4]")
	@CacheLookup
	WebElement downloadBtn;

	@FindBy(id = "progress_info")
	WebElement downloadBox;

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

		System.out.println(downloadBtn.isDisplayed());
		downloadBtn.click();
	}

	public void addLinkToField(String link) {

		urlInput.clear();
		urlInput.sendKeys(link);
	}

}
