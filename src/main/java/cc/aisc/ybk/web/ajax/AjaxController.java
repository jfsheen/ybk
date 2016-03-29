package cc.aisc.ybk.web.ajax;

import cc.aisc.ybk.commons.costant.Constant;
import cc.aisc.ybk.commons.utils.FileUtils;
import cc.aisc.ybk.commons.utils.HtmlUtils;
import cc.aisc.ybk.commons.utils.StringUtils;
import cc.aisc.ybk.content.model.Menu;
import cc.aisc.ybk.content.model.WebContent;
import cc.aisc.ybk.content.service.MenuService;
import cc.aisc.ybk.content.service.WebContentService;
import cc.aisc.ybk.content.vo.MenuTree;
import com.github.pagehelper.PageHelper;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by sjf on 16-3-23.
 */
@RestController
@RequestMapping("/ajax")
public class AjaxController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxController.class);

    @Autowired
    private WebContentService webContentService;
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/1000", method = {RequestMethod.POST})
    public List<Object> fetchMenu(){
        MenuTree menuTree = new MenuTree();
        try{
            List<Menu> menuList = menuService.findAll().get();
            menuTree.listMenuMap(menuList);
        }catch (NoSuchElementException e){
            LOGGER.info("No menu exists!");
            return null;
        }
        return menuTree.list;
    }

    @RequestMapping(value = "/1001", method = {RequestMethod.POST})
    public String uploadFile(HttpServletRequest request) throws Exception{
        String path = new StringBuilder(request.getSession().getServletContext().getRealPath("/"))
                .append(Constant.UPLOAD_IMAGES_PATH).toString();
        String fpath = "";
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;
            Iterator iter = mr.getFileMap().values().iterator();

            if (iter.hasNext()) {
                MultipartFile file = (MultipartFile) iter.next();
                String oname = file.getOriginalFilename();

                DateTime now = new DateTime();
                path += now.getYear() + "/" + now.getMonthOfYear() + "/";
                String fn = oname.substring(oname.lastIndexOf(".") + 1);
                path += StringUtils.textToMD5L32(oname + now.toString()) + "." + fn;
                // 保存文件
                fpath = FileUtils.uploadToServer(file.getInputStream(), oname, path);
            }
        }
        return fpath;
    }

    @RequestMapping("/1002")
    public String saveContent(@ModelAttribute("content") String content){
        WebContent webContent = new WebContent();
        webContent.setContent(HtmlUtils.getHTMLToString(content));
        webContent.setPostAt(new Date());
        webContent.setTitle("");
        webContent.setOutline("");
        webContent.setPostBy(1);
        int rows = webContentService.create(webContent);
        LOGGER.debug(" [{}] row(s) inserted, ID = [{}] ", rows, webContent.getId());
        LOGGER.debug("{}", HtmlUtils.getStringToHTML(webContent.getContent()));

        return String.valueOf(rows);
    }

    @RequestMapping("/1003/{p}")
    public Map<String, Object> fetchContents(@PathVariable Integer p){
        PageHelper.startPage(p, 8);
        List<WebContent> webContents = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        try {
            webContents = webContentService.findSimpleList().get();
            Integer rows = webContentService.count();
            map.put("data", webContents);
            map.put("totalRows", rows.toString());
        }catch (NoSuchElementException e){
            LOGGER.info("No record is current database.");
        }
        return map;
    }


}
