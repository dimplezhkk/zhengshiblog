package cn.zh.blog.dao;

import cn.zh.blog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author 郑豪
 * @Date 2020/4/6 22:52
 **/
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * 根据用户名与密码查询用户
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username,String password);
}
