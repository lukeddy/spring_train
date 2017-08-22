package com.tangzq.repository;

import com.tangzq.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * User文档操作类
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * 用户名查找用户
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 邮件查找用户
     *
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * 用户名就、密码查找
     *
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);

}
