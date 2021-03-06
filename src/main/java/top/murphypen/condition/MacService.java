package top.murphypen.condition;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import top.murphypen.condition.config.MacCondition;
import top.murphypen.condition.impl.OSInterface;

@Component
@Conditional(MacCondition.class)
public class MacService implements OSInterface {

    @Override
    public String showList() {
        System.out.println("ls -al --mac");
        return "ls -al";
    }
}
