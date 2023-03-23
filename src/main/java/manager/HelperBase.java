package manager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperBase {
WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void click(By locator){
        WebElement element= wd.findElement(locator);
        element.click();
    }

    public void type(By locator, String  text){
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        if(text!=null){
            element.sendKeys(text);
        }
    }

    public void pause(int time)  {


        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public boolean isElementPresent(By locator){
        return wd.findElements(locator).size()>0;
    }
}
