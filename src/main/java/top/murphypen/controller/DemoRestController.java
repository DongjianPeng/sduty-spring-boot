package top.murphypen.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.murphypen.domain.DemoObj;

@RestController
@RequestMapping("/rest")
public class DemoRestController {

    @RequestMapping("/getjson")
    public DemoObj getJson(DemoObj obj) {
        return new DemoObj(obj.getId() + 1, obj.getName() + "yy");
    }

}
