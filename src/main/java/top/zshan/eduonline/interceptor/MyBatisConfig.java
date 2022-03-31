package top.zshan.eduonline.interceptor;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author SansZhu
 * @create 2022/3/31 0:28
 */
@Configuration
public class MyBatisConfig {

    @Bean
    public MybatisPlusInterceptor paginationInnerInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
            paginationInnerInterceptor.setOverflow(true);
            paginationInnerInterceptor.setMaxLimit(500l);
        mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);
        return mybatisPlusInterceptor;

    }
}
