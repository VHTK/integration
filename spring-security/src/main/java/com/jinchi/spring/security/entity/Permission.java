package com.jinchi.spring.security.entity;

import lombok.Data;

/**
 * Created by Administrator on 2018-9-12.
 */
@Data
public class Permission {

    private Integer id;
    private String url;
    private String name;
    private String description;
    private Integer pid;
}
