package cn.jasonone.service.impl;

import cn.jasonone.mapper.PermissionMapper;
import cn.jasonone.mapper.RoleMapper;
import cn.jasonone.model.bean.Permission;
import cn.jasonone.model.bean.Role;
import cn.jasonone.service.StpInterfaceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xujie
 * @version 1.0.0
 */
@Service
public class StpInterfaceServiceImpl implements StpInterfaceService {
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<Permission> permissions = permissionMapper.findByAccountId(Integer.valueOf(loginId.toString()));
        return permissions.stream()
                .map(Permission::getCode)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<Role> roles = roleMapper.findByAccountId(Integer.valueOf(loginId.toString()));
        return roles.stream()
                .map(Role::getCode)
                .collect(Collectors.toList());
    }
}
