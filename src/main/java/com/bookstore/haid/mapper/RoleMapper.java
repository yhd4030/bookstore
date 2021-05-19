package com.bookstore.haid.mapper;


import com.bookstore.haid.model.Permission;
import com.bookstore.haid.model.Role;
import com.bookstore.haid.qo.QueryObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role role);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    boolean updateByPrimaryKey(Role role);

    List<Role> selectForList(QueryObject qo);

    void insertRelation(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

    List<Permission> selectRoleAndPermissionRelation(Integer id);

    void deleteRelation(Integer id);

    List<String> selectRoleById(Integer id);
}