package top.murphypen.sse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
public class SseController {

    final static Logger logger = LoggerFactory.getLogger(SseController.class);

    @RequestMapping(value = "/push", produces = "text/event-stream")
    public String push() {
        Random random = new Random();
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (Exception e) {
            logger.error(">>>Exception", e);
        }
        return "data:Testing 1,2,3" + random.nextInt() + "\n\n";
    }
}
