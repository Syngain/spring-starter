package com.hualongdata.springstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        String osName = System.getProperty("os.name");
        if (osName.contains("Linux") || osName.contains("Mac")) {
            app.addListeners(new ApplicationPidFileWriter("app.pid"));
        }
        app.run(args);
    }

}
