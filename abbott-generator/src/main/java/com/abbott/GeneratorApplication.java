package com.abbott;

//import cn.spring.inter.config.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 默认 SpringBoot 会扫描主类所在包 及子包下组件
 */
//@ComponentScan(basePackages= {"cn.spring.inter.controller"})//扫描组件
//@EntityScan("cn.spring.inter.bean")
//@EnableJpaRepositories("cn.spring.inter.repository")
//@MapperScan({"cn.spring.inter.*.Mapper","org.example.*.mapper"})
@SpringBootApplication
public class GeneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class, args);
        System.out.println("start GeneratorApplication");
    }
//    /**
//     * 配置跨域访问的过滤器
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean registerFilter(){
//        FilterRegistrationBean bean = new FilterRegistrationBean();
//        bean.addUrlPatterns("/*");
//        bean.setFilter(new CorsFilter());
//        return bean;
//    }
}