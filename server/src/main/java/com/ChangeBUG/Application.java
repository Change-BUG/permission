package com.ChangeBUG;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@ComponentScan("com.ChangeBUG") // 1. 多模块项目需要扫描的包
//@EnableJpaRepositories("***.***.***") // 2. Dao 层所在的包
@EntityScan("com.ChangeBUG.model") // 3. Entity 所在的包
@SpringBootApplication
public class Application  { //extends SpringBootServletInitializer

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(Application.class);
//    }

    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(Application.class, args);
        Environment env = application.getEnvironment();
        String port = env.getProperty("server.port");
        System.out.println("----------------------------------------------------------\n" +
                "访问地址:\thttp://127.0.0.1:" + port + "/\n" +
                "文档地址:\thttp://127.0.0.1:" + port + "/doc.html\n" +
                "管理端服务 启动完成\n" +
                new BCryptPasswordEncoder().encode("1234") + "\n" +
                "----------------------------------------------------------");
    }

}
