package top.murphypen.condition;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import top.murphypen.condition.config.WindowsCondition;
import top.murphypen.condition.impl.OSInterface;

@Component
@Conditional(WindowsCondition.class)
public class WindowsService implements OSInterface {
    @Override
    public String showList() {
        System.out.println("dir");
        return "dir";
    }
}
