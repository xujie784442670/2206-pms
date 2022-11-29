package cn.jasonone.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.jasonone.config.MqConfig;
import cn.jasonone.mapper.AccountMapper;
import cn.jasonone.model.bean.Account;
import cn.jasonone.model.vo.AccountVO;
import cn.jasonone.service.AccountService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author xujie
 * @version 1.0.0
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Override
    public Account login(String username, String password) {
        return accountMapper.login(username, password);
    }

    @Override
    public void insert(AccountVO avo) {
//        Account account = new Account();
//        BeanUtils.copyProperties(avo,account );
        // hutool
        Account account = BeanUtil.copyProperties(avo, Account.class);
        accountMapper.insert(account);
        avo.setId(account.getId());
        redisTemplate.delete("account::findAll");
        rabbitTemplate.convertAndSend(MqConfig.MQ_ES_ACCOUNT, "account::insert");
    }

    @Override
    public void deleteByIds(int... ids) {
        accountMapper.deleteByIds(ids);
        redisTemplate.delete("account::findAll");
        rabbitTemplate.convertAndSend(MqConfig.MQ_ES_ACCOUNT, "account::insert");
    }

    @Override
    public void updateById(AccountVO avo) {
        Account account = BeanUtil.copyProperties(avo, Account.class);
        accountMapper.updateById(account);
        redisTemplate.delete("account::findAll");
        rabbitTemplate.convertAndSend(MqConfig.MQ_ES_ACCOUNT, "account::insert");
    }

    @Override
    public PageInfo<Account> findAll(AccountVO accountVO, int page, int pageSize) {
        PageInfo<Account> pageInfo = (PageInfo<Account>) redisTemplate.opsForValue().get("account::findAll");
        if(pageInfo == null){
            PageHelper.startPage(page, pageSize);
            List<Account> list = accountMapper.findAll(accountVO);
            pageInfo = new PageInfo<>(list);
            redisTemplate.opsForValue().set("account::findAll",pageInfo);
        }
        return pageInfo;
    }
}
