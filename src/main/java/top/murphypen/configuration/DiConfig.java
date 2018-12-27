package top.murphypen.configuration;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import top.murphypen.beanmanager.DemoBeanManagerByAnnotation;
import top.murphypen.beanmanager.DemoBeanManagerByJava;
import top.murphypen.el.ELTestClass;
import top.murphypen.scope.DemoPrototypeService;
import top.murphypen.scope.DemoSingletonService;
import top.murphypen.service.DemoAnnotationService;
import top.murphypen.service.DemoMethodService;
import top.murphypen.service.UseFunctionService;

@Configuration
@ComponentScan({"top.murphypen.service",
        "top.murphypen.aop",
        "top.murphypen.scope",
        "top.murphypen.beanmanager",
        "top.murphypen.el"})
@EnableAspectJAutoProxy
public class DiConfig {

    @Bean(initMethod = "init",destroyMethod = "destory")
    DemoBeanManagerByJava demoBeanManagerByJava(){
        return new DemoBeanManagerByJava();
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiConfig.class);


        int beanDefinitionCount = context.getBeanDefinitionCount();
        System.out.println(beanDefinitionCount);

        UseFunctionService us = context.getBean(UseFunctionService.class);
        System.out.println(us.sayHello("heheda-annotationconfig"));

        //aop testing
        DemoAnnotationService demoAnnotationService = context.getBean(DemoAnnotationService.class);
        demoAnnotationService.add();
        DemoMethodService demoMethodService = context.getBean(DemoMethodService.class);
        demoMethodService.add();

        DemoPrototypeService demoPrototypeService1 = context.getBean(DemoPrototypeService.class);
        DemoPrototypeService demoPrototypeService2 = context.getBean(DemoPrototypeService.class);

        System.out.println("prototype:" + demoPrototypeService1.equals(demoPrototypeService2));

        DemoSingletonService demoSingletonService1 = context.getBean(DemoSingletonService.class);
        DemoSingletonService demoSingletonService2 = context.getBean(DemoSingletonService.class);

        System.out.println("singleton:" + demoSingletonService1.equals(demoSingletonService2));


        ELTestClass elTestClass = context.getBean(ELTestClass.class);
        elTestClass.printResource();

        DemoBeanManagerByJava demoBeanManagerByJava = context.getBean(DemoBeanManagerByJava.class);
        DemoBeanManagerByAnnotation demoBeanManagerByAnnotation = context.getBean(DemoBeanManagerByAnnotation.class);



        context.close();
    }


}
