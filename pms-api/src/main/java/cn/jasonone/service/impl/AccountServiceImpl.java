package cn.jasonone.service.impl;

import cn.jasonone.mapper.AccountMapper;
import cn.jasonone.model.bean.Account;
import cn.jasonone.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xujie
 * @version 1.0.0
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;
    @Override
    public Account login(String username, String password) {
        return accountMapper.login(username, password);
    }
}
