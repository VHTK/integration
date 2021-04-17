package com.jinchi.spring.security.mapper;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {

    List<String> queryPermissionsByUserId(Integer id);
}
