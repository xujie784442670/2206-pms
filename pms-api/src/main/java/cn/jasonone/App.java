package cn.jasonone;

import cn.jasonone.config.MqConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author xujie
 * @version 1.0.0
 */
@MapperScan("cn.jasonone.mapper")
@SpringBootApplication
public class App implements ApplicationRunner {
    @Resource
    private RabbitTemplate rabbitTemplate;
    public static void main(String[] args) {
        // 跨域:
        // 域名,端口不一致时,浏览器会发起一个预检请求,如果预检请求通过,才会发起真正的请求



        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        rabbitTemplate.convertAndSend(MqConfig.MQ_ES_ACCOUNT,"init");
    }
}
