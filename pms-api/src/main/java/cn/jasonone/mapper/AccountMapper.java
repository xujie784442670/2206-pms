package cn.jasonone.mapper;

import cn.jasonone.model.bean.Account;
import cn.jasonone.model.vo.AccountVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 新增账户
     * @param account 账户信息
     */
    void insert(Account account);

    /**
     * 批量删除账户
     * @param ids 账户id数组
     */
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * 修改账户信息
     * @param account 账户信息
     */
    void updateById(Account account);

    /**
     * 查询账户信息列表
     * @param avo
     * @return
     */
    List<Account> findAll(AccountVO avo);
}




