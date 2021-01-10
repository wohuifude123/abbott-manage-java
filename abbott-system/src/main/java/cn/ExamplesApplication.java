package cn;

//import cn.spring.inter.config.CorsFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan(basePackages= {"cn.spring.inter.controller"})//扫描组件
//@EntityScan("cn.spring.inter.bean")
//@EnableJpaRepositories("cn.spring.inter.repository")
//@MapperScan({"cn.spring.inter.*.Mapper","org.example.*.mapper"})
public class ExamplesApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExamplesApplication.class, args);
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