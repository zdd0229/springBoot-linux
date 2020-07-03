package com.z.service;

import com.z.bean.PO.UserPo;
import com.z.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserPo getUserByName(String name){
        return userDao.getUserByName(name);
    }

}
