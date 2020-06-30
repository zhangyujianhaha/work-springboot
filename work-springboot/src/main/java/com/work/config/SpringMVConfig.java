package com.work.config;



import com.work.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类 用于配置springmvc
 */
@Configuration
public class SpringMVConfig {
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer configurer=new WebMvcConfigurer() {
            //配置视图解析器
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/admin/user.html").setViewName("admin/user");
                registry.addViewController("/admin/company.html").setViewName("admin/company");
                registry.addViewController("/user/user.html").setViewName("user/user");
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/user/join.html").setViewName("user/join");
                registry.addViewController("/company/company.html").setViewName("company/company");
                registry.addViewController("/company/recruit.html").setViewName("company/recruit");
                registry.addViewController("/company/join.html").setViewName("/company/join");
                registry.addViewController("/user/recruit.html").setViewName("user/recruit");
                registry.addViewController("/user/apply.html").setViewName("user/apply");
                registry.addViewController("/company/interview.html").setViewName("company/interview");
                registry.addViewController("/company/apply.html").setViewName("company/apply");
                registry.addViewController("/user/interview.html").setViewName("user/interview");
                registry.addViewController("/password.html").setViewName("password");
                registry.addViewController("/love.html").setViewName("love");


            }

            //配置过滤器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").
                        excludePathPatterns("/","/login/login","/love","/love.html","/passwor/update","/password.html","/password","/password/send","/login/reg","/login/getCode","/login.html", "/images/**","/static/**", "/webjars/**");

            }
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                //外部访问路径映射到本地磁盘路径
                registry.addResourceHandler("/files/**").addResourceLocations("file:D:/files/");
            }

        };

        return configurer;
    }


 /*   @Bean

    public MultipartConfigElement multipartConfigElement(

            @Value("${multipart.maxFileSize}") String maxFileSize,

            @Value("${multipart.maxRequestSize}") String maxRequestSize) {

        MultipartConfigFactory factory = new MultipartConfigFactory();

// 单个文件最大

        factory.setMaxFileSize();





// 设置总上传数据总大小

        factory.setMaxRequestSize(maxRequestSize);

        return factory.createMultipartConfig();

    }*/

}
