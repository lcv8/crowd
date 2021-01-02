package com.crowd.service.api;

import com.crowd.entity.Admin;
import com.github.pagehelper.PageInfo;

public interface AdminService {
    Admin getAdminByLoginAcct(String loginAcct, String userPswd);
    PageInfo<Admin> getAdminPage(String keyword, Integer pageNum, Integer pageSize);
    void remove(Integer adminId);
}
