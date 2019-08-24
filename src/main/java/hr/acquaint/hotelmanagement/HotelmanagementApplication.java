package hr.acquaint.hotelmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Main Spring application entry-point class which starts the program
 *
 * @author Marko Pancirov
 * @version 0.0.1
 */
@SpringBootApplication
@EnableCaching
public class HotelmanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelmanagementApplication.class, args);
    }
}
