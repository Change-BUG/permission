package com.ChangeBUG;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application  {
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
