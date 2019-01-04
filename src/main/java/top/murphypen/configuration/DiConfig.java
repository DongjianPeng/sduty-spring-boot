package top.murphypen.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import top.murphypen.async.AsyncTaskService;
import top.murphypen.async.TaskExecutorConfig;
import top.murphypen.beanmanager.DemoBeanManagerByAnnotation;
import top.murphypen.beanmanager.DemoBeanManagerByJava;
import top.murphypen.condition.impl.OSInterface;
import top.murphypen.el.ELTestClass;
import top.murphypen.event.DemoPublisher;
import top.murphypen.interceptor.DemoInterceptor;
import top.murphypen.scope.DemoPrototypeService;
import top.murphypen.scope.DemoSingletonService;
import top.murphypen.service.DemoAnnotationService;
import top.murphypen.service.DemoAware;
import top.murphypen.service.DemoMethodService;
import top.murphypen.service.UseFunctionService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Configuration
@ComponentScan({"top.murphypen.service",
        "top.murphypen.aop",
        "top.murphypen.scope",
        "top.murphypen.beanmanager",
        "top.murphypen.event",
        "top.murphypen.async",
        "top.murphypen.schedule",
        "top.murphypen.condition",
        "top.murphypen.controller",
        "top.murphypen.interceptor",
        "top.murphypen.sse",
        "top.murphypen.advice",
        "top.murphypen.el"})
@EnableAspectJAutoProxy
@EnableScheduling
@EnableWebMvc
public class DiConfig extends TaskExecutorConfig implements WebApplicationInitializer {

    @Bean(initMethod = "init", destroyMethod = "destory")
    DemoBeanManagerByJava demoBeanManagerByJava() {
        return new DemoBeanManagerByJava();
    }

    @Bean
    DemoBeanManagerByAnnotation DemoBeanManagerByAnnotation() {
        return new DemoBeanManagerByAnnotation();
    }

    @Bean
    ProfileConfig profileConfig() {
        return new ProfileConfig();
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/view/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }

    @Autowired
    DemoInterceptor demoInterceptor;


    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiConfig.class);

//        context.getEnvironment().addActiveProfile("dev");

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

        DemoPublisher demoPublisher = context.getBean(DemoPublisher.class);
        demoPublisher.pushlish("Evnet MSG!!!");

        DemoAware demoAware = context.getBean(DemoAware.class);
        System.out.println(demoAware.getBeanName());

        AsyncTaskService asyncTaskService = context.getBean(AsyncTaskService.class);
        for (int i = 0; i < 10; i++) {
//            asyncTaskService.executeAsyncTask(i);
//            asyncTaskService.executeAsyncTaskPlus(i);
            asyncTaskService.executeSyncTask(i);
        }


        OSInterface osInterface = context.getBean(OSInterface.class);
        osInterface.showList();

//        TimeUnit.SECONDS.sleep(10L);
        context.close();
    }


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(DiConfig.class);
        ctx.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/assets/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor);
    }


    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController("/index").setViewName("/index");
        registry.addViewController("/toUpload").setViewName("/upload");
        registry.addViewController("/sse").setViewName("/sse");

    }
}
