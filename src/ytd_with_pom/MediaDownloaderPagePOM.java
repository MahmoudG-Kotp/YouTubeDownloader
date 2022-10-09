package ytd_with_pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


class MediaDownloaderPagePOM {
    private final ChromeBrowserPOM browserPOM = new ChromeBrowserPOM();

    By searchET = By.id("id_url");
    By downloadButton720 = By.id("download-mp4-720-audio");

    private WebElement getSearchETElement(){
        return browserPOM.getDriver().findElement(searchET);
    }

    public WebElement getDownloadButton720() {
        return browserPOM.getDriver().findElement(downloadButton720);
    }

    public void clearSearchET() {
        getSearchETElement().clear();
    }

    public void setTextET(String text) {
        getSearchETElement().sendKeys(text + Keys.ENTER);
    }

    public void clickDownloadButton() {
        ((JavascriptExecutor) browserPOM.getDriver()).executeScript("arguments[0].click()", getDownloadButton720());
    }

    public void navigateToMediaDownloader() {
        String MEDIA_DOWNLOADER_LINK = "https://ssyoutube.com/en1/youtube-video-downloader";
        if (!browserPOM.getDriver().getCurrentUrl().equals(MEDIA_DOWNLOADER_LINK))
            browserPOM.getDriver().get(MEDIA_DOWNLOADER_LINK);
    }
}
