package cn.jasonone.consumer;

import cn.hutool.extra.mail.Mail;
import cn.jasonone.config.MqConfig;
import cn.jasonone.model.vo.EmailVO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

/**
 * @author xujie
 * @version 1.0.0
 */
@Component
public class EmailConsumer {
    @Resource
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String form;

    @RabbitListener(queues = MqConfig.MQ_EMAIL)
    public void sendEmail(EmailVO email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(form);
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getContent());
        mailSender.send(message);
    }
}
