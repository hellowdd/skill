package com.wdd.skill.controller;

import com.wdd.skill.dao.UserDao;
import com.wdd.skill.redis.UserKey;
import com.wdd.skill.service.RedisService;
import com.wdd.skill.util.JsonUtil;
import com.wdd.skill.util.domain.User;
import com.wdd.skill.util.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试
 *
 * @author
 * @create 2018-01-15 22:12
 **/
@Controller
@RequestMapping("/demo")
@Slf4j
public class SampleController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisService redisService;


    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        log.info("qwe");
        model.addAttribute("name","韦冬冬");
        return "hello";
    }


    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(){
        return userDao.selectByPrimaryKey(1);
    }


    @RequestMapping("/redisGet")
    @ResponseBody
    public Result<User> redisGet(){
        User user= redisService.get(UserKey.getById,""+3,User.class);
        return Result.success(user);
    }

    @RequestMapping("/redisSet")
    @ResponseBody
    public void redisSet(){
        User user=new User();
        user.setId(2);
        user.setUserName("weidongdong3");
        redisService.set(UserKey.getById, ""+3, user);
    }
}
