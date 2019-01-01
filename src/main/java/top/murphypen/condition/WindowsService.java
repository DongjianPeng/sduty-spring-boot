package top.murphypen.condition;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(WindowsCondition.class)
public class WindowsService implements OSInterface {
    @Override
    public String showList() {
        System.out.println("dir");
        return "dir";
    }
}
