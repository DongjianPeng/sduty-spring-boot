package top.murphypen.condition;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import top.murphypen.condition.config.LinuxCondition;
import top.murphypen.condition.impl.OSInterface;

@Component
@Conditional(LinuxCondition.class)
public class LinuxService implements OSInterface {
    @Override
    public String showList() {
        System.out.println("ls -al --linux");
        return "ls -al";
    }
}
