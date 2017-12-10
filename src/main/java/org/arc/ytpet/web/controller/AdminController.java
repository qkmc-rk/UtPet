package org.arc.ytpet.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.arc.ytpet.core.model.Admin;
import org.arc.ytpet.core.model.databind.Result;
import org.arc.ytpet.core.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam("password")String password) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Admin admin = new Admin();
        admin.setPassword(password);
        admin = adminService.login(admin);
        if (admin != null) {
            //登陆成功,返回json登陆成功的提示
            Result rs = new Result(Result.Rs.RESULT_TRUE);
            return objectMapper.writeValueAsString(rs);
        } else {
            //登录失败,返回登录失败
            Result rs = new Result(Result.Rs.RESULT_FALSE);
            return objectMapper.writeValueAsString(rs);
        }
    }
//    去主页
    @RequestMapping("/index")
    public String toindex(){
        return "index";
    }
//    去会员基本信息页
    @RequestMapping("/primaryinfo")
    public String topriminfo(){
        return "primaryinfo";
    }
    //    余额管理页面
    @RequestMapping("/balance")
    public String tobalance(){
        return "balance";
    }
    //    充值管理页面
    @RequestMapping("/charge")
    public String tocharge(){
        return "charge";
    }
    //    消费管理页面
    @RequestMapping("/consume")
    public String toconsume(){
        return "consume";
    }


//    tologin:if people who logined a wrong password
    @RequestMapping(value = "/tologin")
    public String tologin(ModelAndView modelAndView){
        return "login";
    }
    public AdminService getAdminService() {
        return adminService;
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }
}
