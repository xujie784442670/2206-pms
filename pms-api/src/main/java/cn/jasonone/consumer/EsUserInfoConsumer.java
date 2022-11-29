package cn.jasonone.consumer;

import cn.jasonone.config.MqConfig;
import cn.jasonone.mapper.UserInfoIndexMapper;
import cn.jasonone.model.index.UserInfoIndex;
import cn.jasonone.repository.UserInfoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xujie
 * @version 1.0.0
 */
@Component
public class EsUserInfoConsumer {
    @Resource
    private UserInfoIndexMapper userInfoIndexMapper;
    @Resource
    private UserInfoRepository repository;


    @RabbitListener(queues = {MqConfig.MQ_ES_ACCOUNT,MqConfig.MQ_ES_PERMISSION,MqConfig.MQ_ES_ROLE})
    public void handler(String value){
        List<UserInfoIndex> list = userInfoIndexMapper.findAll();
        repository.saveAll(list);
    }
}
