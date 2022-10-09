package ytd_with_pagefactory;

public class MainClassPF {
    ChromeBrowserPF browserPF = new ChromeBrowserPF();
    PlaylistPagePF playlistPagePF = new PlaylistPagePF();
    MediaDownloaderPagePF downloaderPagePF = new MediaDownloaderPagePF();
    public static final int QUIT_DELAY_DURATION_MS = 2000;

    public void downloadVideo(String videoLink) throws InterruptedException {
        downloaderPagePF.navigateToMediaDownloader();
        downloaderPagePF.clearSearchET();
        downloaderPagePF.setTextET(videoLink);
        Thread.sleep(1000);
        downloaderPagePF.clickDownloadButton();
        System.out.println("Downloading: " + videoLink + "\n");
    }

    public static void main(String[] args) throws InterruptedException {
        MainClassPF ma = new MainClassPF();
        ma.browserPF.open();
        ma.playlistPagePF.navigateToPlaylist();
        int lastWindowCount;
        for (String link : ma.playlistPagePF.getPlaylistLinks()) {
            ma.downloadVideo(link);
            Thread.sleep(2000);
            lastWindowCount = ma.browserPF.getWindowsSize();
            while (lastWindowCount > 1){
                ma.browserPF.closeWindow(lastWindowCount--);
            }
        }
        Thread.sleep(QUIT_DELAY_DURATION_MS);
        ma.browserPF.quit();
    }
}
