package com.bookstore.haid.service;


import com.bookstore.haid.mapper.RoleMapper;
import com.bookstore.haid.model.Permission;
import com.bookstore.haid.model.Role;
import com.bookstore.haid.qo.QueryObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl {
    @Autowired
    private RoleMapper roleMapper;


    public void save(Role role, Integer[] ids) {
        roleMapper.insert(role);
        for (Integer id : ids) {
            roleMapper.insertRelation(role.getId(), id);
        }

    }

    public PageInfo<Role> getRoleList(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Role> roles = roleMapper.selectForList(qo);
        return new PageInfo<>(roles);
    }

    public List<Role> selectAllRole() {
        List<Role> roles = roleMapper.selectAll();
        return roles;
    }

    public void update(Role role, Integer[] ids) {
        roleMapper.updateByPrimaryKey(role);
        roleMapper.deleteRelation(role.getId());
        if (ids != null && ids.length > 0) {
            for (Integer id : ids) {
                roleMapper.insertRelation(role.getId(), id);
            }
        }


    }

    public void deleteById(Integer id) {
        roleMapper.deleteRelation(id);
        roleMapper.deleteByPrimaryKey(id);

    }

    public Role selectRoleById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    public List<Permission> selectRoleAndPermissionRelation(Integer id) {
        return roleMapper.selectRoleAndPermissionRelation(id);
    }

    public List<String> queryRoleById(Integer id) {
        return roleMapper.selectRoleById(id);
    }

}
