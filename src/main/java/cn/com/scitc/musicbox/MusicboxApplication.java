package cn.com.scitc.musicbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


//@EnableJpaAuditing
@SpringBootApplication
public class MusicboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicboxApplication.class, args);
    }

}
