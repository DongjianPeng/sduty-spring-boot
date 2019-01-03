package top.murphypen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/index")
    public String hello(@ModelAttribute("msg") String msg) {
        throw new IllegalArgumentException("参数错误"+msg);
    }
}
