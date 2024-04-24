package thefirstgroup.myclassplanner.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("thefirstgroup.myclassplanner.model.dao")
public class MyClassPlannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyClassPlannerApplication.class, args);
    }

}
