package cn.jasonone.model.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 权限;权限信息表
 * @TableName permission
 */
@Data
public class Permission implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 父权限ID
     */
    private Integer pid;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限代码
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