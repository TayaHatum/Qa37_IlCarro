package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000);
        System.out.println(i);

        System.out.println(System.currentTimeMillis());
        int z =(int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .setFirstName("Lisa")
                .setLastName("Snow")
                .setEmail("snow"+i+"@gmail.com")
                .setPassword("Snow12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        // You are logged in success
        Assert.assertEquals(app.getHelperUser().getMessage(),"You are logged in success");
    }

    @Test
    public void registrationEmptyName(){
        User user = new User()
                .setFirstName("")
                .setLastName("Simpson")
                .setEmail("simpson@gmail.com")
                .setPassword("Simp12345$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Name is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

    }
    @Test
    public void registrationEmptyLastName(){
        User user = new User()
                .setFirstName("Gomer")
                .setLastName("")
                .setEmail("simpson@gmail.com")
                .setPassword("Simp12345$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Last name is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void registrationWrongEmail(){
        User user = new User()
                .setFirstName("Gomer")
                .setLastName("Simpson")
                .setEmail("simpsongmail.com")
                .setPassword("Simp12345$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getErrorText().contains("Wrong email format"));

        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

    }
    @Test
    public void registrationWrongPassword(){
        User user = new User()
                .setFirstName("Gomer")
                .setLastName("Simpson")
                .setEmail("simpson@gmail.com")
                .setPassword("Simp12");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Password must contain minimum 8 symbols\n" +
                "Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().closeWindow();
    }
}
