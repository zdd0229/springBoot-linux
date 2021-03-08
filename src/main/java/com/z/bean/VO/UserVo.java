package com.z.bean.VO;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class UserVo {
    private int id;

    @NotNull
    private String username;

    @NotNull
    @Length(min = 6 , message = "密码格式错误")
    private String password;

    private MultipartFile touxiang;
}
