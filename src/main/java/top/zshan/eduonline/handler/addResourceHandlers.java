package top.zshan.eduonline.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author SansZhu
 * @create 2022/3/28 14:41
 */
@Configuration
public class addResourceHandlers implements WebMvcConfigurer {
//    配置绝对路径为虚拟路径让服务器可以访问
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/userimg/**").addResourceLocations("file:A:/Edu_Online/resource/userimg/");
        registry.addResourceHandler("/teachimg/**").addResourceLocations("file:A:/Edu_Online/resource/teachimg/");
        registry.addResourceHandler("/courseimg/**").addResourceLocations("file:A:/Edu_Online/resource/courseimg/");
        registry.addResourceHandler("/coursevideo/**").addResourceLocations("file:A:/Edu_Online/resource/coursevideo/");
    }
}
