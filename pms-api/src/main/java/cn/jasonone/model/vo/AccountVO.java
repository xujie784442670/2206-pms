package cn.jasonone.model.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

/**
 * @author xujie
 * @version 1.0.0
 */
@Data
public class AccountVO{
    public static interface StatusGroup{}
    public static interface UpdateGroup{}
    public static interface InsertGroup{}
    @NotNull(groups = {UpdateGroup.class,StatusGroup.class},message = "id不能为空")
    private Integer id;
    @NotBlank(groups = {InsertGroup.class},message = "用户名不能为空")
    @Length(groups = {InsertGroup.class},min = 5,max = 20,message = "用户名长度必须在5-20之间")
    @Null(groups = {UpdateGroup.class,StatusGroup.class},message = "用户名不能修改")
    private String username;
    @NotBlank(groups = {InsertGroup.class,UpdateGroup.class},message = "密码不能为空")
    @Length(groups = {InsertGroup.class,UpdateGroup.class},min = 3,max = 20,message = "密码长度必须在3-20之间")
    @Null(groups = {StatusGroup.class},message = "密码不能修改")
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date[] createTime;
    @NotNull(groups = {StatusGroup.class},message = "状态不能为空")
    @Null(groups = {UpdateGroup.class},message = "状态不能修改")
    private Integer status;
}
