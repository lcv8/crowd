package com.crowd.service.impl;

import com.crowd.contant.CrowdConstant;
import com.crowd.entity.Admin;
import com.crowd.entity.AdminExample;
import com.crowd.exception.LoginFailedException;
import com.crowd.mapper.AdminMapper;
import com.crowd.service.api.AdminService;
import com.crowd.util.CrowdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lcv8
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
        // 1.根据登录账号查询 Admin 对象
        AdminExample adminExample = new AdminExample();
        // ②创建 Criteria 对象
        AdminExample.Criteria criteria = adminExample.createCriteria();
        // ③在 Criteria 对象中封装查询条件
        criteria.andLoginAcctEqualTo(loginAcct);
        // ④调用 AdminMapper 的方法执行查询
        List<Admin> list = adminMapper.selectByExample(adminExample);
        if(list == null || list.size() == 0){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        if(list.size() > 1){
            throw new LoginFailedException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }

        Admin admin = list.get(0);
        // 3.如果 Admin 对象为 null 则抛出异常
        if(admin == null){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        String userPswdDB = admin.getUserPswd();
        String md5 = CrowdUtil.md5(userPswd);
        if(!userPswdDB.equals(md5)){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        return admin;
    }

    public PageInfo<Admin> getAdminPage(String keyword, Integer pageNum, Integer pageSize) {
        // 1.开启分页功能
        PageHelper.startPage(pageNum,pageSize);
        // 2.查询 Admin 数据
        List<Admin> list = adminMapper.selectAdminListByKeyword(keyword);

        // ※辅助代码：打印 adminList 的全类名
        Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
        logger.debug(("adminList 的全类名是："+list.getClass().getName()));
        // 3.为了方便页面使用将 list 封装为 PageInfo
        PageInfo<Admin> adminPageInfo = new PageInfo(list);
        return adminPageInfo;
    }
}
