package cn.jasonone.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xujie
 * @version 1.0.0
 */
@Configuration
public class MqConfig {
    public static final String MQ_EMAIL = "email";
    public static final String MQ_ES_ACCOUNT = "es-account";
    public static final String MQ_ES_ROLE = "es-role";
    public static final String MQ_ES_PERMISSION = "es-permission";
    @Bean
    public Queue queue(){
        return new Queue(MQ_EMAIL);
    }
    @Bean
    public Queue queueEsAccount(){
        return new Queue(MQ_ES_ACCOUNT);
    }

    @Bean
    public Queue queueEsRole(){
        return new Queue(MQ_ES_ROLE);
    }

    @Bean
    public Queue queueEsPermission(){
        return new Queue(MQ_ES_PERMISSION);
    }


}
