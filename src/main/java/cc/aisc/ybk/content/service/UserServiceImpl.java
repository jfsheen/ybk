package cc.aisc.ybk.content.service;

import cc.aisc.ybk.content.model.User;
import cc.aisc.ybk.content.mybatis.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sjf on 16-3-19.
 */
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int create(User user) {
        return userMapper.insert(user);
    }
}
