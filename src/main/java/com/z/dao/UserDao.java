package com.z.dao;

import com.z.bean.PO.UserPo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    @Select("select * from t_user where username = #{userName}")
    public UserPo getUserByName(String userName);

}
