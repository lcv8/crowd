package com.crowd.service.api;

import com.crowd.entity.Admin;

public interface AdminService {
    Admin getAdminByLoginAcct(String loginAcct, String userPswd);
}
