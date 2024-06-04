package com.insmess.meeting.intergration.mapper;

import com.insmess.meeting.intergration.pojo.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserMapper {
    private List<User> userList = new ArrayList<>();

    @PostConstruct
    public void init() {
        userList.add(new User("aa", "jack", "杰克", "123456"));
        userList.add(new User("bb", "lucy", "露西", "123456"));
        userList.add(new User("cc", "tom", "汤姆", "123456"));
    }

    public List<User> list() {
        return userList;
    }

    public User selectById(String id) {
        for (User user : userList) {
            if (id.equals(user.getId())) {
                return user;
            }
        }
        return null;
    }

    public User selectByUsername(String username) {
        for (User user : userList) {
            if (username.equals(user.getUsername())) {
                return user;
            }
        }
        return null;
    }
}
