package com.jinchi.spring.security.mapper;


import com.jinchi.spring.security.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User queryUserByUsername(String username);
}
