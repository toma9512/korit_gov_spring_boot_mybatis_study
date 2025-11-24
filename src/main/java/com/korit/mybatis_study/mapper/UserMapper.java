package com.korit.mybatis_study.mapper;

import com.korit.mybatis_study.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<User> findUserByUsername(String username);

    int signup(User user);

    List<User> getUserAll();

    int editUser(User user);
}
