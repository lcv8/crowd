package com.crowd.mvc.handle;

import com.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RoleHandle {

    @Autowired
    private RoleService roleService;


}
