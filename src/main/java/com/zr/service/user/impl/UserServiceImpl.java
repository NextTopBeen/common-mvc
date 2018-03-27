package com.zr.service.user.impl;

import com.zr.common.DBEnum;
import com.zr.dao.entity.User;
import com.zr.dao.entity.UserQuery;
import com.zr.dao.mapper.UserMapper;
import com.zr.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Miste on 3/26/2018.
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;


    public User getById(Integer id) {
        if(null != id) {
            return  userMapper.selectByPrimaryKey(id);
        }
        return  null;
    }

    public List<User> list(UserQuery userQuery) {
        return userMapper.selectByQuery(userQuery);
    }

    public User add(User user) {
        if(null != user.getName() && null != user.getUsername() && null != user.getPassword()) {
            user.setIsdel((byte) DBEnum.FALSE.getCode());
            user.setEnable((byte) DBEnum.TRUE.getCode());
            userMapper.insert(user);
        }
        return user;
    }

    public User delete(User user) {
        if(null != user && null != user.getId()) {
            user.setIsdel((byte)DBEnum.TRUE.getCode());
        }
        userMapper.updateByPrimaryKey(user);
        return  user;
    }

    public User update(User user) {
        if(null != user.getId() &&
                null != user.getName() && null != user.getUsername() && null != user.getPassword()) {
            userMapper.updateByPrimaryKeySelective(user);
        }
        return  user;
    }

}