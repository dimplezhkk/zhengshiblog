package cn.zh.blog.service;

import cn.zh.blog.pojo.User;

/**
 * @Author 郑豪
 * @Date 2020/4/6 22:49
 **/
public interface UserService {

    /**
     * 检查用户名与密码
     * @param username
     * @param password
     * @return
     */
    User checkUser(String username, String password);

    /**
     * 后台用户总数
     * @return
     */
    Long countUser();
}
