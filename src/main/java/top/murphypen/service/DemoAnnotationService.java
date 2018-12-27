package top.murphypen.service;

import org.springframework.stereotype.Service;
import top.murphypen.aop.Action;

@Service
public class DemoAnnotationService {

    @Action(name = "add operation!")
    public void add(){

    }
}
