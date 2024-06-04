package com.insmess.meeting.intergration.service;

import cn.hutool.crypto.symmetric.AES;
import com.insmess.meeting.intergration.mapper.UserMapper;
import com.insmess.meeting.intergration.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    private String securityKey = "insmessinsmessfd";

    /**
     * 登录，并返回token。
     * @return
     */
    public String login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        if (!password.equals(user.getPassword())) {
            throw new RuntimeException("密码错误");
        }
        //生成token。通过AES加密算法模拟（对用户名加密）
        String token = aesEncrypt(username);
        return token;
    }

    public User getByToken(String token) {
        String username = aesDecrypt(token);
        User user = userMapper.selectByUsername(username);
        return user;
    }


    private String aesEncrypt(String content) {
        AES aes = new AES(securityKey.getBytes());
        return aes.encryptHex(content);
    }

    private String aesDecrypt(String content) {
        AES aes = new AES(securityKey.getBytes());
        return aes.decryptStr(content);
    }
}
