package ytd_with_pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlaylistPagePF {
    private final ChromeBrowserPF browserPF = new ChromeBrowserPF();

    PlaylistPagePF() {
        PageFactory.initElements(browserPF.getDriver(), this);
    }

    @FindBy(xpath = "//a[@id='thumbnail'][contains(@href, 'index')]")
    private List<WebElement> playList;

    @FindBy(css = "button[aria-label='Accept all']")
    private WebElement acceptAllCookiesButton;

    private void acceptAllCookies() {
        acceptAllCookiesButton.click();
    }

    public void navigateToPlaylist() {
        System.out.println("Enter youtube playlist link: ");
        Scanner enteredPlaylistScanner = new Scanner(System.in);
        String playlistLink = enteredPlaylistScanner.nextLine();
        if (!browserPF.getDriver().getCurrentUrl().equals(playlistLink)) {
            browserPF.getDriver().get(playlistLink);
            acceptAllCookies();
        } else
            System.out.println("Playlist already opened!");
    }

    public List<String> getPlaylistLinks() {
        List<String> links = new ArrayList<>();
        for (WebElement video : playList) {
            links.add(video.getAttribute("href"));
        }
        return links;
    }
}
