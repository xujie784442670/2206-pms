package cn.jasonone.mapper;

import cn.jasonone.model.bean.Account;
import org.apache.ibatis.annotations.Param;

/**
* @author xujie
* @description 针对表【account(账号表;账号信息表)】的数据库操作Mapper
* @createDate 2022-11-21 16:47:15
* @Entity cn.jasonone.model.bean.Account
*/
public interface AccountMapper {
    /**
     * 登录查询
     * @param username 用户名
     * @param password 密码
     * @return
     */
    Account login(@Param("username") String username,@Param("password") String password);

}




