package cn.jasonone.mapper;

import cn.jasonone.model.bean.Permission;

import java.util.List;

/**
* @author xujie
* @description 针对表【permission(权限;权限信息表)】的数据库操作Mapper
* @createDate 2022-11-21 16:47:15
* @Entity cn.jasonone.model.bean.Permission
*/
public interface PermissionMapper {
    /**
     * 查询指定用户拥有的权限信息
     * @param accountId
     * @return
     */
    List<Permission> findByAccountId(Integer accountId);
}




