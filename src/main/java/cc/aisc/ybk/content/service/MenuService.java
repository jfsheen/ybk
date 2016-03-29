package cc.aisc.ybk.content.service;

import cc.aisc.ybk.content.model.Menu;

import java.util.List;
import java.util.Optional;

/**
 * Created by sjf on 16-3-25.
 */
public interface MenuService {
    void insert(Menu menu);
    Optional<Menu> findById(Integer id);
    Optional<List<Menu>> findAll();
}
