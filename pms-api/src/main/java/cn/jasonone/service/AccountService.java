package cn.jasonone.service;

import cn.jasonone.model.bean.Account;

/**
 * @author xujie
 * @version 1.0.0
 */
public interface AccountService {

    Account login(String username, String password);
}
