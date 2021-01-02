package com.crowd.mvc.handle;

import com.crowd.contant.CrowdConstant;
import com.crowd.entity.Admin;
import com.crowd.exception.LoginAcctAlreadylnUseException;
import com.crowd.service.api.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AdminHandle {
    @Autowired
    private AdminService adminService;

    @RequestMapping("admin/update.html")
    public String update(Admin admin ,
                         @RequestParam("pageNum") Integer pageNum,
                         @RequestParam("keyword") String keyword){
        adminService.update(admin);
        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }

    @RequestMapping("admin/to/edit/page.html")
    public String toEditpage(@RequestParam("adminId") Integer adminId , ModelMap modelMap){
        Admin adminById = adminService.getAdminById(adminId);
        modelMap.addAttribute("admin",adminById);
        return "admin-edit";
    }

    @RequestMapping("admin/save.html")
    public String save(Admin admin){
        try{
            adminService.insert(admin);
        } catch (Exception e){
            e.printStackTrace();
            if (e instanceof DuplicateKeyException) {
                throw new LoginAcctAlreadylnUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }
        return "redirect:/admin/get/page.html?pageNum="+Integer.MAX_VALUE;
    }

    @RequestMapping("admin/remove/{adminId}/{pageNum}/{keyword}.html")
    public String remove(@PathVariable("adminId") Integer adminId ,
                         @PathVariable("pageNum") Integer pageNum ,
                         @PathVariable("keyword") String keyword){
        //删除
        adminService.remove(adminId);
        //页面跳转 回到分页页面
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }

    @RequestMapping("admin/get/page.html")
    public String getAdminPage(@RequestParam(value = "keyword",defaultValue = "") String keyword ,
                               @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum ,
                               @RequestParam(value = "pageSize" , defaultValue = "5") Integer pageSize ,
                               ModelMap modelMap) {
        PageInfo<Admin> pageInfo = adminService.getAdminPage(keyword, pageNum, pageSize);
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO,pageInfo);
        return "admin-page";
    }

    @RequestMapping("admin/do/login.html")
    public String doLogin(
            @RequestParam("loginAcct") String userName ,
            @RequestParam("userPswd") String password , HttpSession session){
        Admin admin = adminService.getAdminByLoginAcct(userName, password);
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN,admin);
        return "redirect:/admin/to/main/page.html";
    }

    @RequestMapping("admin/do/logout.html")
    public String logOut(HttpSession session){
        session.invalidate();
        return "redirect:/admin/to/login/page.html";
    }
}
