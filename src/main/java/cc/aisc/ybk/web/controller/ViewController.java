package cc.aisc.ybk.web.controller;

import cc.aisc.ybk.content.model.Menu;
import cc.aisc.ybk.content.model.WebContent;
import cc.aisc.ybk.content.service.MenuService;
import cc.aisc.ybk.content.service.UserService;
import cc.aisc.ybk.content.service.WebContentService;
import cc.aisc.ybk.content.vo.MenuTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by sjf on 16-3-17.
 */
@RestController
public class ViewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private WebContentService webContentService;

    @Autowired
    private MenuService menuService;

    @RequestMapping("/")
    public ModelAndView home() {
        MenuTree menuTree = new MenuTree();
        try{
            //menu = menuService.fetchMenuTreeData(1).get();
            List<Menu> menuList = menuService.findAll().get();
            menuTree.listMenuMap(menuList);
        }catch (NoSuchElementException e){
            LOGGER.info("No menu exists by root id = 1.");
            return new ModelAndView("home", "message", "no menu exists!");
        }
        return new ModelAndView("home", "menus", menuTree.list);
    }

    @RequestMapping("/a")
    public ModelAndView edit(){
        return new ModelAndView("editor");
    }

    @RequestMapping("/b")
    public ModelAndView edit1(){
        return new ModelAndView("editor1");
    }

    @RequestMapping("/c")
    public ModelAndView showContent(){
        return new ModelAndView("content");
    }

    @RequestMapping("/page/content/{id}")
    public ModelAndView getContentDetails(@PathVariable Integer id){
        WebContent webContent = null;
        try {
            webContent = webContentService.findById(id).get();
            //webContent.setContent(HtmlUtils.getStringToHTML(webContent.getContent()));
        }catch (NoSuchElementException e){
            LOGGER.info("No record by id = {} .", id.toString());
        }
        return new ModelAndView("contentdetails", "details", webContent);
    }
}
