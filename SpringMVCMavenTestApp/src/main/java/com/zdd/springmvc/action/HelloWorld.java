package com.zdd.springmvc.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdd.springmvc.iservice.IUserService;

@Controller
public class HelloWorld {
	@Autowired
	private IUserService userService;

    /**
     * 1. 使用RequestMapping注解来映射请求的URL
     * 2. 返回值会通过视图解析器解析为实际的物理视图, 对于InternalResourceViewResolver视图解析器，会做如下解析
     * 通过prefix+returnVal+suffix 这样的方式得到实际的物理视图，然后会转发操作
     * "/WEB-INF/views/success.jsp"
     * @return
     */
    @RequestMapping("/helloworld")
    public String hello(){
    	List<Map<String,Object>> userList = userService.queryUserList();
        System.out.println(userList.toString());
        return "success";
    }
    
    @RequestMapping("/addUser")
    public void addUser(){
    	try {
			userService.addUser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}