package top.zshan.eduonline;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@MapperScan("top.zshan.eduonline.mapper")
@ServletComponentScan("top.zshan.eduonline")
@SpringBootApplication
public class EduOnlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduOnlineApplication.class, args);
    }

}
