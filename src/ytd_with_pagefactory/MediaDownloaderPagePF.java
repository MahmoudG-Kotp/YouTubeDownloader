package ytd_with_pagefactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MediaDownloaderPagePF {
    private final ChromeBrowserPF browserPF = new ChromeBrowserPF();

    @FindBy(id = "id_url")
    private WebElement searchET;

    @FindBy(id = "download-mp4-720-audio")
    private WebElement downloadButton720;

    public void clearSearchET() {
        searchET.clear();
    }

    public void setTextET(String text) {
        searchET.sendKeys(text + Keys.ENTER);
    }

    public void clickDownloadButton() {
        ((JavascriptExecutor) browserPF.getDriver()).executeScript("arguments[0].click()", downloadButton720);
    }

    public void navigateToMediaDownloader() {
        String MEDIA_DOWNLOADER_LINK = "https://ssyoutube.com/en1/youtube-video-downloader";
        if (!browserPF.getDriver().getCurrentUrl().equals(MEDIA_DOWNLOADER_LINK))
            browserPF.getDriver().get(MEDIA_DOWNLOADER_LINK);
    }
}
