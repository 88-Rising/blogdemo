package frank.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
* 用户登录拦截器
* */
@Configuration
public class BlogConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //添加拦截器 使用排除的方式来做(静态文件)
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns("/css/**")//两个*匹配多级路径
                .excludePathPatterns("/fonts/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/plugins/editor/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/login")
                .excludePathPatterns("/a/*");//一个*匹配一级路径，多级不能匹配

    }
}
