package top.murphypen.beanmanager;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class DemoBeanManagerByAnnotation {

    @PostConstruct
    public void init(){
        System.out.println(getClass().getName()+" init");
    }

    public DemoBeanManagerByAnnotation() {
        System.out.println(getClass().getName()+" constructor");
    }

    @PreDestroy
    public void destory(){
        System.out.println(getClass().getName()+" destroy");
    }

}
