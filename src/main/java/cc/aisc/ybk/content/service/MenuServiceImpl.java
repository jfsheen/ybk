package cc.aisc.ybk.content.service;

import cc.aisc.ybk.content.model.Menu;
import cc.aisc.ybk.content.mybatis.dao.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by sjf on 16-3-25.
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuMapper menuMapper;

    public void insert(Menu menu){
        menuMapper.insert(menu);
    }

    public Optional<Menu> findById(Integer id){
        return Optional.ofNullable(menuMapper.selectByPrimaryKey(id));
    }

    public Optional<Menu> fetchMenuTreeData(Integer id){
        return Optional.ofNullable(menuMapper.treeData(id));
    }
}
