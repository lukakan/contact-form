package pl.lukakan.contactform;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
public class ContactFormApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactFormApplication.class, args);
    }

}
