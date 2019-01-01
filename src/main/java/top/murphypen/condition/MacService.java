package top.murphypen.condition;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(MacCondition.class)
public class MacService implements OSInterface{

    @Override
    public String showList() {
        System.out.println("ls -al");
        return "ls -al";
    }
}
