package pt.feefo.jobtitleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class JobTitleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobTitleServiceApplication.class, args);
    }

}
