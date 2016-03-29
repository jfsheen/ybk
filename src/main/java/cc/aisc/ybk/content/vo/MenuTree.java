package cc.aisc.ybk.content.vo;

import cc.aisc.ybk.content.model.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by sjf on 16-3-26.
 */
public class MenuTree {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuTree.class);

    public static Map<String, Object> mapArray = new LinkedHashMap<>();
    public List<Menu> menuCommon;
    public List<Object> list = new ArrayList<>();

    public List<Object> listMenuMap(List<Menu> menus){
        this.menuCommon = menus;
        for (Menu m : menus){
            Map<String, Object> map = new LinkedHashMap<>();
            if(m.getParentId() == null){
                map.put("id", m.getId());
                map.put("caption", m.getCaption());
                map.put("tooltip", m.getTooltip());
                map.put("treeLvl", m.getTreeLvl());
                map.put("orderNum", m.getOrderNum());
                map.put("parentId", m.getParentId());
                map.put("enabled", m.getEnabled());
                map.put("funcPath", m.getFuncPath());
                map.put("iconCode", m.getIconCode());
                map.put("children", getChildren(m.getId()));
                list.add(map);
            }
        }
        return list;

    }

    public List<?> getChildren(Integer id){
        List<Object> objects = new ArrayList<>();
        for (Menu m : menuCommon){
            Map<String, Object> children = new LinkedHashMap<>();
            if (Objects.equals(m.getParentId(), id)){
                children.put("id", m.getId());
                children.put("caption", m.getCaption());
                children.put("tooltip", m.getTooltip());
                children.put("treeLvl", m.getTreeLvl());
                children.put("orderNum", m.getOrderNum());
                children.put("parentId", m.getParentId());
                children.put("enabled", m.getEnabled());
                children.put("funcPath", m.getFuncPath());
                children.put("iconCode", m.getIconCode());
                children.put("children", getChildren(m.getId()));
                objects.add(children);
            }
        }
        LOGGER.debug("objects sort.");
        Collections.sort(objects, (o1, o2) -> {
            if (o1 instanceof LinkedHashMap && o2 instanceof LinkedHashMap) {
                return (int)((Map<String, Object>) o1).get("orderNum") - (int)((Map<String, Object>) o2).get("orderNum");
            }
            return 0;
        });
        return objects;
    }

    /*Comparator<Map<String, Object>> comparator = (o1, o2) -> {
        if (null != o1 && null != o2) {
            if (o1.getSiKey().ordinal() > o2.getSiKey().ordinal())
                return 1;
            else
                return -1;
        }
        return 0;
    };*/
}
