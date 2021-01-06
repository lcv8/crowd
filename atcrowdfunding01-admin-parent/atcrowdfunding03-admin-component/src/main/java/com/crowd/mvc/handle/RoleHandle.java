package com.crowd.mvc.handle;

import com.crowd.entity.Role;
import com.crowd.service.api.RoleService;
import com.crowd.util.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class RoleHandle {

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping(value = "role/get/page/info.json")
    public ResultEntity<PageInfo<Role>> getPageInfo(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum ,
                                                    @RequestParam(value = "pageSize",defaultValue = "5" ) Integer pageSize,
                                                    @RequestParam(value = "keyword",defaultValue = "") String keyword){
        PageInfo<Role> pageInfo = roleService.getPageInfo(pageNum, pageSize, keyword);
        System.out.println("=====================>>>>"+ResultEntity.successWithData(pageInfo).toString());
        return ResultEntity.successWithData(pageInfo);
    }

    @ResponseBody
    @RequestMapping("role/save.json")
    public ResultEntity<String> saveRole(Role role){
        roleService.saveRole(role);
        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping("role/update.json")
    public ResultEntity<String> updateRole(Role role){
        roleService.updateRole(role);
        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping("/role/remove/by/role/id/array.json")
    public ResultEntity<String> removeByRoleIdArray(@RequestBody List<Integer> roleIdList){
        roleService.removeRole(roleIdList);
        return ResultEntity.successWithoutData();
    }

}
