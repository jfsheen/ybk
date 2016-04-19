package cc.aisc.ybk.content.service;

import cc.aisc.ybk.config.mybatis.datasource.annotation.TargetDataSource;
import cc.aisc.ybk.content.model.WebContent;
import cc.aisc.ybk.content.mybatis.dao.WebContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by sjf on 16-3-23.
 */
@Service
@Transactional
public class WebContentServiceImpl implements WebContentService {

    @Autowired
    private WebContentMapper webContentMapper;

    @Override
    @TargetDataSource("ds1")
    public int create(WebContent webContent) {
        return webContentMapper.insert(webContent);
    }

    @Override
    @TargetDataSource("ds1")
    public int delete(WebContent webContent) {
        return webContentMapper.deleteByPrimaryKey(webContent.getId());
    }

    @Override
    @TargetDataSource("ds1")
    public void modify(WebContent webContent) {

    }

    @Override
    @TargetDataSource("ds1")
    public Optional<List<WebContent>> findSimpleList(String type) {
        return Optional.ofNullable(webContentMapper.simpleSelectAllByType(type));
    }

    @Override
    public int count(String type) {
        return webContentMapper.countByType(type);
    }

    @Override
    public Optional<WebContent> findById(Integer id) {
        return Optional.ofNullable(webContentMapper.selectByPrimaryKey(id));
    }
}
