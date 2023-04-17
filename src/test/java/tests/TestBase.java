package tests;

import manager.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {

   Logger logger = LoggerFactory.getLogger(TestBase.class);
   static ApplicationManager app = new ApplicationManager();

   @BeforeMethod
   public void startLogger(Method m){
      logger.info("Name of method -->" +m.getName());
   }

@BeforeSuite
   public void setUp(){
       app.init();
   }
@AfterSuite
   public void tearDown(){
       //app.stop();
   }
}
