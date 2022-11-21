package cn.jasonone.model.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 角色表;角色信息表
 * @TableName role
 */
@Data
public class Role implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 角色代码;便于阅读的,唯一表示一个角色的代码
     */
    private String code;

    /**
     * 最后修改时间;在修改时,自动获取当前时间
     */
    private Date updateTime;

    /**
     * 创建时间;默认在新增时获取当前时间
     */
    private Date createTime;

    /**
     * 状态
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}