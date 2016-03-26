package cc.aisc.ybk.content.service;

import cc.aisc.ybk.content.model.User;

/**
 * Created by sjf on 16-3-19.
 */
public interface UserService {

    User findById(Integer id);
    int create(User user);
}
