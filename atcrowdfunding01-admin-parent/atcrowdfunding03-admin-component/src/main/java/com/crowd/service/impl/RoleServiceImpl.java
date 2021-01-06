package com.crowd.service.impl;

import com.crowd.entity.Role;
import com.crowd.entity.RoleExample;
import com.crowd.mapper.RoleMapper;
import com.crowd.service.api.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
        // 1.开启分页功能
        PageHelper.startPage(pageNum,pageSize);
        // 2.查询 Role 数据
        List<Role> roleList = roleMapper.selectRoleByKeyword(keyword);
        System.out.println("rolelist======>>>>>>>"+roleList);
        // 3.为了方便页面使用将 roleList 封装为 PageInfo
        return new PageInfo<Role>(roleList);
    }

    @Override
    public void saveRole(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public void removeRole(List<Integer> roleIdList) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andIdIn(roleIdList);
        roleMapper.deleteByExample(roleExample);
    }
}
