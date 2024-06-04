package com.insmess.meeting.intergration.controller;

import com.insmess.meeting.intergration.common.api.CommonResult;
import com.insmess.meeting.intergration.pojo.User;
import com.insmess.meeting.intergration.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public CommonResult login(@RequestBody User user) {
        try {
            String token = userService.login(user.getUsername(), user.getPassword());
            return CommonResult.success(token);
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
    }


    /**
     * 根据header中的token获取用户详情。需返回username和realname信息
     */
    @PostMapping("/userInfo")
    public CommonResult userInfo(HttpServletRequest request) {
        try {
            String token = request.getHeader("insmess-meeting-token");
            User user = userService.getByToken(token);
            return CommonResult.success(user);
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
    }
}
