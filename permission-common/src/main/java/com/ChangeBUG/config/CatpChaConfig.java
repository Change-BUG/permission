package com.ChangeBUG.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


//* 验证码 图片
@Configuration
public class CatpChaConfig {

    @Bean
    public DefaultKaptcha defaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();

        properties.put("kaptcha.border", "no"); // 图片边框，合法值：yes , no
        properties.put("kaptcha.textproducer.char.length", "4"); // 验证码长度
        properties.put("kaptcha.image.height", "50"); // 图片高
        properties.put("kaptcha.image.width", "150"); // 图片宽
        properties.put("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy"); // 干扰实现类
        properties.put("kaptcha.textproducer.font.color", "black"); // 干扰 颜色，合法值： r,g,b 或者 white,black,blue.
        properties.put("kaptcha.textproducer.font.size", "40"); // 字体大小
        properties.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise"); // 干扰实现类
        properties.put("kaptcha.textproducer.char.string", "acdefhkmnprtwxy1234567890"); // 文本集合，验证码值从此集合中获取

        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }

}
