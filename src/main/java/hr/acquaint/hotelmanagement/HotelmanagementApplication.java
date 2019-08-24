package hr.acquaint.hotelmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"hr.acquaint.hotelmanagement.controllers", "hr.acquaint.hotelmanagement.services"})
public class HotelmanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelmanagementApplication.class, args);
    }

}