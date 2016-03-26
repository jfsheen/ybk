package cc.aisc.ybk.content.service;

import cc.aisc.ybk.content.model.WebContent;

import java.util.List;
import java.util.Optional;

/**
 * Created by sjf on 16-3-23.
 */
public interface WebContentService {
    int create(WebContent webContent);
    int delete(WebContent webContent);
    void modify(WebContent webContent);
    Optional<List<WebContent>> findSimpleList();
    int count();
    Optional<WebContent> findById(Integer id);
}
