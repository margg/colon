package pl.edu.agh.to.app;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.agh.to.app.model.*;
import pl.edu.agh.to.app.persistence.HibernateUtils;

import java.util.ArrayList;
import java.util.HashSet;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }

}