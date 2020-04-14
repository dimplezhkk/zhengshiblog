package cn.zh.blog.service.impl;

import cn.zh.blog.dao.UserRepository;
import cn.zh.blog.pojo.User;
import cn.zh.blog.service.UserService;
import cn.zh.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 郑豪
 * @Date 2020/4/6 22:51
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

    @Override
    public Long countUser() {
        return userRepository.count();
    }
}
