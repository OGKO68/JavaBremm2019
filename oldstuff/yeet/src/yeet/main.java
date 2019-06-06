package yeet;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

import traurig.Car;
import traurig.Toyota;

public class main {
	public static void main(String[] args) {
        Car testCar = new Car();
        Toyota testToyota = new Toyota(1,5);
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("target/car.json"), testToyota);
        
    }
}
