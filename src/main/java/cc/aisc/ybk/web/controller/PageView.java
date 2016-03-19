package cc.aisc.ybk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sjf on 16-3-17.
 */
@Controller
public class PageView {

    @RequestMapping("/")
    public String home(){
        return "home";
    }
}
