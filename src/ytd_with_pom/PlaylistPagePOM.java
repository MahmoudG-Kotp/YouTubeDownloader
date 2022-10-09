package ytd_with_pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class PlaylistPagePOM {
    private final ChromeBrowserPOM browserPOM = new ChromeBrowserPOM();

    By acceptAllCookiesButton = By.cssSelector("button[aria-label='Accept all']");
    By playlistElements = By.xpath("//a[@id='thumbnail'][contains(@href, 'index')]");

    private void acceptAllCookies() {
        browserPOM.getDriver().findElement(acceptAllCookiesButton).click();
    }

    private List<WebElement> getPlaylistElements(){
        return browserPOM.getDriver().findElements(playlistElements);
    }

    public void navigateToPlaylist() {
        System.out.println("Enter youtube playlist link: ");
        Scanner enteredPlaylistScanner = new Scanner(System.in);
        String playlistLink = enteredPlaylistScanner.nextLine();
        if (!browserPOM.getDriver().getCurrentUrl().equals(playlistLink)) {
            browserPOM.getDriver().get(playlistLink);
            acceptAllCookies();
        } else
            System.out.println("Playlist already opened!");
    }

    public List<String> getPlaylistLinks() {
        List<String> links = new ArrayList<>();
        for (WebElement videoElement : getPlaylistElements()) {
            links.add(videoElement.getAttribute("href"));
        }
        return links;
    }
}
