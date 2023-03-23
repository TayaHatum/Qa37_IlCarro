package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.jws.soap.SOAPBinding;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }
    @Test
    public void loginSuccess1(){

        User user = new User().setEmail("noa@gmail.com").setPassword("Nnoa12345$");
//        user.setEmail("noa@gmail.com");
//        user.setPassword("Nnoa12345$");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");


    }

    @Test
    public void loginSuccess(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("noa@gmail.com","Nnoa12345$");
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");

    }
    @Test
    public void loginSuccessModel(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("noa@gmail.com","Nnoa12345$");
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");

    }
    @Test
    public void loginWrongEmail(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("","Nnoa12345$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void loginWrongPassword(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("noa@gmail.com","Nnoa12");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\"");
    }
    @Test
    public void loginUnregistered(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("luck@gmail.com","luck12345$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\"");
    }
    @AfterMethod
    public void postCondition(){
        app.getHelperUser().closeWindow();
    }
}
