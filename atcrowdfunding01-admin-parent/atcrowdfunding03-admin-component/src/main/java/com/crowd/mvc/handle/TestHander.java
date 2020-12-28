package com.crowd.mvc.handle;

import com.crowd.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TestHander {

    @RequestMapping("/test/aaa/bbb/ccc.html")
    public String test(){
        return "index";
    }

    @ResponseBody
    @RequestMapping("/aa/bb.json")
    public Employee ajaxTest(){
        return new Employee(555, "empName555", 555.55);
    }


    
    @ResponseBody
    @RequestMapping(value = "/send/array/one.html",method = RequestMethod.POST)
    public String arrayOne(@RequestParam("array[]") List<Integer> array){
        for ( Integer num: array) {
            System.out.println("array==>" + num);
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/send/array/three.html",method = RequestMethod.POST)
    public String arrayThree(@RequestBody List<Integer> array){
        Logger logger = LoggerFactory.getLogger(TestHander.class);
        for ( Integer num: array) {
            logger.info("array==>" + num);
        }
        return "success";
    }
}
