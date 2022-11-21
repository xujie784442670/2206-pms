package cn.jasonone.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.jasonone.model.bean.Account;
import cn.jasonone.model.vo.AccountVO;
import cn.jasonone.model.vo.HttpResult;
import cn.jasonone.service.AccountService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xujie
 * @version 1.0.0
 */
@RestController
public class LoginController {
    @Resource
    private AccountService accountService;
    @PostMapping("/login")
    public HttpResult login(@Validated AccountVO account) {
        Account login = accountService.login(account.getUsername(), account.getPassword());
        StpUtil.login(login.getId());
        String token = StpUtil.getTokenValue();
        List<String> permissions = StpUtil.getPermissionList();
        List<String> roles = StpUtil.getRoleList();
        return HttpResult.ok()
                .put("token", token)
                .put("permissions", permissions)
                .put("roles", roles);
    }

}
