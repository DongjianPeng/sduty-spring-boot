package top.murphypen.beanmanager;

import org.springframework.stereotype.Component;

public class DemoBeanManagerByJava {
    public void init(){
        System.out.println(getClass().getName()+" init");
    }

    public DemoBeanManagerByJava() {
        System.out.println(getClass().getName()+" constructor");
    }

    public void destory(){
        System.out.println(getClass().getName()+" destroy");
    }
}
