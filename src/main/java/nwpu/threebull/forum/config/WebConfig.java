package nwpu.threebull.forum.config;

import nwpu.threebull.forum.tool.interceptor.AdminInterceptor;
import nwpu.threebull.forum.tool.interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * WebMVC配置
 * <p>
 * Created in 2019/11/17 17:05
 */
@Configuration  //定义为配置类
@EnableWebMvc   //启用Spring MVC
@ComponentScan
@ComponentScan("nwpu.threebull.forum.controller")
public class WebConfig implements WebMvcConfigurer {

    /**
     * 自定义视图解析器
     *
     * @return
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     * 配置静态资源的处理
     * DispatcherServlet破坏了Servlet的一个特性（根目录下的文件可以直接访问），
     * DefaultServletHttpRequestHandler是帮助回归这个特性的
     *
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 定义静态资源处理
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    /**
     * 定义拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor())
                .addPathPatterns("/user/**")//添加拦截
                .excludePathPatterns(new String[]{"/user/register", "/user/login"});// excludePathPatterns 排除拦截
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/admin/**")//添加拦截
                .excludePathPatterns("/admin/login");// excludePathPatterns 排除拦截
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
