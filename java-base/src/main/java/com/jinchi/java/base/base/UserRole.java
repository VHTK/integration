package com.jinchi.java.base.base;



import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Administrator on 2018-9-12.
 */
@Data
public class UserRole implements Serializable {
    private Integer id;
    private Integer version;
    private String code;
    private Date updateTime;
    private Date createTime;
    private String userCode;
    private String roleCode;
    private String systemCode;
    private Integer isDelete;
    private LocalDate beginDate;
}
