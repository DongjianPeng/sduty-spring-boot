package top.murphypen.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

class DemoBean {
    private String content;

    public DemoBean(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

public class ProfileConfig {

    @Bean
    @Profile("dev")
    public DemoBean dev() {
        System.out.println("=====dev");
        return new DemoBean("dev");
    }

    @Bean
    @Profile("prod")
    public DemoBean prod() {
        System.out.println("======prod");
        return new DemoBean("prod");
    }

}
