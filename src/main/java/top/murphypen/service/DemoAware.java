package top.murphypen.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.*;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class DemoAware implements ApplicationEventPublisherAware, BeanNameAware,
        BeanFactoryAware, ApplicationContextAware , ResourceLoaderAware {

    private String beanName;

    public DemoAware() {
        System.out.println("------aware init");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("------aware beanFactory");
    }

    @Override
    public void setBeanName(String s) {
        beanName = s;
        System.out.println("------aware beanName:"+s);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("------aware applicationContext");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("------aware applicationEventPublisher");
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("------aware resourceLoader");
    }

    public String getBeanName() {
        return beanName;
    }
}
