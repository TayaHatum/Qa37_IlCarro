package manager;


import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class HelperBase {
    WebDriver wd;
    Logger logger = LoggerFactory.getLogger(HelperBase.class);

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void click(By locator) {
        WebElement element = wd.findElement(locator);
        element.click();
    }

    public void type(By locator, String text) {
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        clearNew(element);
        if (text != null) {
            System.out.println("hello");
            element.sendKeys(text);
        }
    }

    public void clearNew(WebElement element) {
        element.sendKeys(" ");
        element.sendKeys(Keys.BACK_SPACE);


    }

    public void pause(int time) {


        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public boolean isElementPresent(By locator) {
        return wd.findElements(locator).size() > 0;
    }

    public void submit() {
        click(By.cssSelector("button[type='submit']"));
    }

    public String getMessage() {
//        WebElement element = wd.findElement(By.cssSelector(".dialog-container>h2"));
//        String  text = element.getText();
//        return text;

        // wait

        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector(".dialog-container"))));

        // pause(8000);
        return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();
    }

    public void getScreen(String link) {
        TakesScreenshot takesScreenshot=(TakesScreenshot) wd;
        File tmp =takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp,new File(link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearTextBox(By locator){
        WebElement el = wd.findElement(locator);
        String os = System.getProperty("os.name");
        System.out.println(os);
        if(os.startsWith("Win")){
            el.sendKeys(Keys.CONTROL,"a");

        }else {

            el.sendKeys(Keys.COMMAND, "a");

        }
        el.sendKeys(Keys.DELETE);

    }
}
