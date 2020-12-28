package com.crowd.mvc.handle;

import com.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AdminHandle {
    @Autowired
    private AdminService adminService;

    @RequestMapping("admin/do/login.html")
    public String doLogin(
            @RequestParam("loginAcct") String userName ,
            @RequestParam("userPswd") String password , HttpSession session){

        return "";
    }
}
