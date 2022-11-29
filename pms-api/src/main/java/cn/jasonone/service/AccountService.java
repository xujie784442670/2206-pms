package cn.jasonone.service;

import cn.jasonone.model.bean.Account;
import cn.jasonone.model.vo.AccountVO;
import com.github.pagehelper.PageInfo;

/**
 * 账号业务操作接口
 *
 * @author xujie
 * @version 1.0.0
 */
public interface AccountService {
    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 匹配的用户信息
     * @author xujie
     */
    Account login(String username, String password);

    /**
     * 新增账户
     *
     * @param account 账户信息
     * @author xujie
     */
    void insert(AccountVO account);

    /**
     * 批量删除账户
     *
     * @param ids 账户id数组
     */
    void deleteByIds(int... ids);

    /**
     * 修改账户信息
     *
     * @param avo 账户信息
     */
    void updateById(AccountVO avo);

    /**
     * 分页查询账户信息列表
     *
     * @param accountVO 查询条件
     * @param page      页码
     * @param pageSize  每页显示数量
     * @return 分页信息
     */
    PageInfo<Account> findAll(AccountVO accountVO, int page, int pageSize);
}
