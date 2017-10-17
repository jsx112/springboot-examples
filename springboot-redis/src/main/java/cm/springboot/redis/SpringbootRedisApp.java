package cm.springboot.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author jiasx
 * @create 2017-09-15 11:03
 **/
@SpringBootApplication
@EnableScheduling
public class SpringbootRedisApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedisApp.class,args);

    }
}
