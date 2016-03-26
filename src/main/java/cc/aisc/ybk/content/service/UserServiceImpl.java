package cc.aisc.ybk.content.service;

import cc.aisc.ybk.config.mybatis.datasource.annotation.TargetDataSource;
import cc.aisc.ybk.content.model.User;
import cc.aisc.ybk.content.mybatis.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by sjf on 16-3-19.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    @TargetDataSource("ds1")
    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    @TargetDataSource("default")
    public int create(User user) {
        return userMapper.insert(user);
    }
}
