package ytd_with_pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;

public class ChromeBrowserPF {

    private static WebDriver driver;

    ChromeBrowserPF() {
        String driverPath = "c:\\Program Files\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
    }

    public void open() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public WebDriver switchToWindow(int index) {
        return driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(index));
    }

    public void closeWindow(int index){
        switchToWindow(index).close();
        switchToWindow(index-1);
    }

    public int getWindowsSize(){
        return driver.getWindowHandles().size();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quit() {
        System.out.println("Are you wanna quit: ");
        Scanner keyEnteredScanner = new Scanner(System.in);
        if (keyEnteredScanner.nextLine().equalsIgnoreCase("Yes")) {
            driver.quit();
        }
    }
}
