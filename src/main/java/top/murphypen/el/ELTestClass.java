package top.murphypen.el;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

@Configuration
@ComponentScan("top.murphypen.el")
@PropertySource(value = "classpath:el/test.properties",ignoreResourceNotFound = true)
public class ELTestClass {

    @Value("WTF?")
    private String normal;

    @Value("#{systemProperties['os.name']}")
    private String osName;

    @Value("#{T(java.lang.Math).random()*100.0}")
    private double randomNumber;

    @Value("#{demoElBean.another}")
    private String fromAnotherBean;

    @Value("classpath:el/test.txt")
    private Resource testFile;

    @Value("http://www.bing.com")
    private Resource testUrl;

    @Value("${student.name}")
    private String studentName;

    @Autowired
    private Environment environment;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigure() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    public void printResource() {
        try {
            System.out.println(normal);
            System.out.println(osName);
            System.out.println(randomNumber);
            System.out.println(IOUtils.toString(testFile.getInputStream(), "utf-8"));
            System.err.println(IOUtils.toString(testUrl.getInputStream(), "UTF-8"));
            System.out.println(studentName);
            System.out.println(environment.getProperty("student.name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
