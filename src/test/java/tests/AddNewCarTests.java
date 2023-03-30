package tests;

import models.Car;
import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTests extends TestBase {

    @BeforeClass
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().setEmail("noa@gmail.com").setPassword("Nnoa12345!") );
        }
    }

    @Test
    public void addNewCarSuccess() {
        int i = new Random().nextInt(1000) + 1000;
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("678-900-" + i)
                .price(50)
                .about("Very nice car")
                .build();
        app.getHelperCar().openCarForm();
       app.getHelperCar().fillCarForm(car);
       // app.getHelperCar().sumitCarForm();

    }
}
