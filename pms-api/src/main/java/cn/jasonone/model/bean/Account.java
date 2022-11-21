package cn.jasonone.model.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 账号表;账号信息表
 * @TableName account
 */
@Data
public class Account implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 账号;用户登录的唯一凭证
     */
    private String username;

    /**
     * 密码;用户登录的唯一凭证的密码
     */
    private String password;

    /**
     * 最后修改时间;在修改时,自动获取当前时间
     */
    private Date updateTime;

    /**
     * 创建时间;默认在新增时获取当前时间
     */
    private Date createTime;

    /**
     * 状态;0-正常,1-封禁
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}