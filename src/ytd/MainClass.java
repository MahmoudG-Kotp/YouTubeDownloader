package ytd;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MainClass {
    public static final int QUIT_DELAY_DURATION_MS = 2000;
    public static final String YT_MEDIA_DOWNLOAD = "https://ssyoutube.com/en1/youtube-video-downloader";

    public enum XPaths {
        YT_ELEMENT("//a[@id='thumbnail'][contains(@href, 'index')]");
        final String path;

        XPaths(String path) {
            this.path = path;
        }
    }

    public enum ElementsIDs {
        SSYT_SEARCH_ET("id_url"),
        SSYT_DOWNLOAD_BUTTON_720("download-mp4-720-audio");
        final String id;

        ElementsIDs(String id) {
            this.id = id;
        }
    }

    public WebDriver getChromeDriver() {
        String chromeDriverPath = "c:\\Program Files\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }

    public List<String> getPlaylistLinks(WebDriver driver, String playlistLink) {
        driver.get(playlistLink);
        List<WebElement> caughtYTElements = driver.findElements(By.xpath(XPaths.YT_ELEMENT.path));
        List<String> links = new ArrayList<>();
        for (WebElement element : caughtYTElements) {
            links.add(element.getAttribute("href"));
        }
        return links;
    }

    public void downloadVideo(WebDriver driver, String videoLink) throws InterruptedException {
        if (!driver.getCurrentUrl().equals(YT_MEDIA_DOWNLOAD))
            driver.get(YT_MEDIA_DOWNLOAD);
        WebElement ssytSearchET = driver.findElement(By.id(ElementsIDs.SSYT_SEARCH_ET.id));
        ssytSearchET.clear();
        ssytSearchET.sendKeys(videoLink + Keys.ENTER);
        Thread.sleep(1000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver.findElement(By.id(ElementsIDs.SSYT_DOWNLOAD_BUTTON_720.id)));
        driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(0));
        System.out.println("Downloading: " + videoLink + "\n");
    }

    public String getPlaylistLink() {
        System.out.println("Enter playlist link: ");
        Scanner enteredPlaylistScanner = new Scanner(System.in);
        return enteredPlaylistScanner.nextLine();
    }

    public void quit() {
        System.out.println("Are you wanna quit: ");
        Scanner keyEnteredScanner = new Scanner(System.in);
        if (keyEnteredScanner.nextLine().equalsIgnoreCase("Yes")) {
            System.exit(0);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MainClass mc = new MainClass();
        WebDriver chromeDriver = mc.getChromeDriver();
        for (String link : mc.getPlaylistLinks(chromeDriver, mc.getPlaylistLink())) {
            mc.downloadVideo(chromeDriver, link);
            Thread.sleep(2000);
        }
        Thread.sleep(QUIT_DELAY_DURATION_MS);
        mc.quit();
    }
}