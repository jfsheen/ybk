package cc.aisc.ybk.commons.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * Created by sjf on 15-11-14.
 */
public abstract class JsonUtils {

    public static String format(String jsonStr) {
        int level = 0;
        StringBuilder jsonForMatStr = new StringBuilder();
        for (int i = 0; i < jsonStr.length(); i++) {
            char c = jsonStr.charAt(i);
            if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
            }
            switch (c) {
                case '{':
                case '[':
                    jsonForMatStr.append(c).append("\n");
                    level++;
                    break;
                case ',':
                    jsonForMatStr.append(c).append("\n");
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n");
                    level--;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }

        return jsonForMatStr.toString();

    }

    private static String getLevelStr(int level) {
        StringBuilder levelStr = new StringBuilder();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }

    public static JSONArray treeNodeList(JSONArray nodeList, Long parentId) {
        JSONArray childNode = new JSONArray();
        for (Object object : nodeList) {
            JSONObject jo = JSONObject.fromObject(object);
            Long nodeId = jo.getLong("id");
            Long pid = 0L;
            if(!jo.getString("parentId").isEmpty()) {
                pid = jo.getLong("parentId");
            }
            if (Objects.equals(parentId, pid)) {
                JSONArray c_node = treeNodeList(nodeList, nodeId);
                jo.put("childNode", c_node);
                childNode.add(jo);
            }
        }
        return childNode;
    }

    public static JSONArray collections2JSONArrary(Collection collection) throws Exception{
        JSONArray ja = new JSONArray();
        for (Object aCollection : collection) {
            JSONObject jo = JSONObject.fromObject(aCollection);
            ja.add(jo);
        }
        return ja;
    }

    public static Map<String, Object> JSON2Map(JSONObject object) throws Exception{
        Map<String, Object> map = new HashMap<>();
        for (Object o : object.keySet()) {
            String key = String.valueOf(o);
            Object value = object.get(key);
            map.put(key, value);
        }
        return map;
    }
}
