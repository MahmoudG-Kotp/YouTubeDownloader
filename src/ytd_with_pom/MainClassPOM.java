package ytd_with_pom;

class MainClassPOM {
    ChromeBrowserPOM browserPOM = new ChromeBrowserPOM();
    PlaylistPagePOM playlistPagePOM = new PlaylistPagePOM();
    MediaDownloaderPagePOM downloaderPagePOM = new MediaDownloaderPagePOM();
    public static final int QUIT_DELAY_DURATION_MS = 2000;

    private void downloadVideo(String videoLink) throws InterruptedException {
        downloaderPagePOM.navigateToMediaDownloader();
        downloaderPagePOM.clearSearchET();
        downloaderPagePOM.setTextET(videoLink);
        Thread.sleep(1000);
        downloaderPagePOM.clickDownloadButton();
        System.out.println("Downloading: " + videoLink + "\n");
    }

    public static void main(String[] args) throws InterruptedException {
        MainClassPOM ma = new MainClassPOM();
        ma.browserPOM.open();
        ma.playlistPagePOM.navigateToPlaylist();
        int lastWindowCount;
        for (String link : ma.playlistPagePOM.getPlaylistLinks()) {
            ma.downloadVideo(link);
            Thread.sleep(2000);
            lastWindowCount = ma.browserPOM.getWindowsSize()-1;
            while (lastWindowCount > 0){
                ma.browserPOM.closeWindow(lastWindowCount--);
            }
        }
        Thread.sleep(QUIT_DELAY_DURATION_MS);
        ma.browserPOM.quit();
    }
}
