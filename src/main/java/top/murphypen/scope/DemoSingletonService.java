package top.murphypen.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
//default scope:singleton
public class DemoSingletonService {
}
