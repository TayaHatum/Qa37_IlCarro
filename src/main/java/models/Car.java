package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class Car {
    private String location;
    private String manufacture;
    private String model;
    private String year;
    private String fuel;
    private int seats;
    private String carClass;
    private String carRegNumber;
    private double price;
    private String about;

}
