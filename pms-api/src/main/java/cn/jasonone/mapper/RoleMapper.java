package cn.jasonone.mapper;

import cn.jasonone.model.bean.Role;

import java.util.List;

/**
* @author xujie
* @description 针对表【role(角色表;角色信息表)】的数据库操作Mapper
* @createDate 2022-11-21 16:47:15
* @Entity cn.jasonone.model.bean.Role
*/
public interface RoleMapper {
    /**
     * 查询指定用户拥有的角色信息
     * @param accountId 用户id
     * @return
     */
    List<Role> findByAccountId(Integer accountId);
}




