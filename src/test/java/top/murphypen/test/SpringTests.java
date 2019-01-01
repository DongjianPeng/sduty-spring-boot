package top.murphypen.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.murphypen.configuration.DiConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DiConfig.class)
@ActiveProfiles("prod")
public class SpringTests {

    @Autowired
    ApplicationContext context;

    @Test
    public void test() {
        System.out.println(context.getBeanDefinitionCount());
    }
}
