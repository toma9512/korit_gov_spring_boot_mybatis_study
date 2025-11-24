package com.korit.mybatis_study.repository;

import com.korit.mybatis_study.entity.User;
import com.korit.mybatis_study.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    @Autowired
    private UserMapper userMapper;

    public Optional<User> findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    public int signup(User user) {
        return userMapper.signup(user);
    }

    public List<User> getUserAll() {
        return userMapper.getUserAll();
    }

    public int editUser(User user) {
        return userMapper.editUser(user);
    }

    public int removeUser(String username) {
        return userMapper.removeUser(username);
    }
}
