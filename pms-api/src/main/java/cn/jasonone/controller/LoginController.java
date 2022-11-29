package cn.jasonone.controller;

import cn.dev33.satoken.annotation.SaCheckDisable;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.GifCaptcha;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import cn.jasonone.model.bean.Account;
import cn.jasonone.model.vo.EmailVO;
import cn.jasonone.model.vo.LoginVO;
import cn.jasonone.model.vo.HttpResult;
import cn.jasonone.service.AccountService;
import cn.jasonone.service.RedisService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author xujie
 * @version 1.0.0
 */
@RestController
@Validated
public class LoginController {
    @Resource
    private AccountService accountService;
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private RedisService redisService;
    @GetMapping("/sendCode")
    public HttpResult sendEmail(
            @NotBlank(message = "邮箱不能为空")
            @Email(message = "邮箱格式不正确")
            String email){
        GifCaptcha captcha = CaptchaUtil.createGifCaptcha(100, 30, 4);
        captcha.createCode();
        String code = captcha.getCode();

        Long seq = null;
        if(!redisService.exists("seq::"+email)){
            seq = RandomUtil.randomLong(10,100);
            redisService.set("seq::"+email,seq);
        }else{
            seq = redisService.inc("seq::"+email);
        }
        redisService.set("code::"+email,code,300);
        EmailVO emailVO = new EmailVO();
        emailVO.setTo(email);
        emailVO.setSubject("验证码");
        emailVO.setContent("您的验证码为："+code +" 序列号:"+seq+" 请在5分钟内输入");
        rabbitTemplate.convertAndSend("email",emailVO);
        return HttpResult.ok().put("seq",seq);
    }

    @PostMapping("/login")
    public HttpResult login(@Validated LoginVO account) {
        String code = redisService.get("code::" + account.getUsername());
        if(code == null){
            return HttpResult.error(400,"验证码已过期");
        }
        if(!code.equals(account.getCode())){
            return HttpResult.error(400,"验证码错误");
        }
        Account login = accountService.login(account.getUsername(), account.getPassword());
        if(login != null){
            StpUtil.login(login.getId());
            String token = StpUtil.getTokenValue();
            List<String> permissions = StpUtil.getPermissionList();
            List<String> roles = StpUtil.getRoleList();
            return HttpResult.ok()
                    .put("token", token)
                    .put("permissions", permissions)
                    .put("roles", roles);
        }else{
            return HttpResult.error(400,"用户名或密码错误");
        }
    }
}
