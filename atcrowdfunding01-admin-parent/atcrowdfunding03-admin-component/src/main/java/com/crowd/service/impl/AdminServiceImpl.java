package com.crowd.service.impl;

import com.crowd.entity.Admin;
import com.crowd.mapper.AdminMapper;
import com.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
        return null;
    }
}
