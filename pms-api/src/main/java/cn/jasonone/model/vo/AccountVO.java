package cn.jasonone.model.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author xujie
 * @version 1.0.0
 */
@Data
public class AccountVO {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "验证码不能为空")
    private String code;
}
