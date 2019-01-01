package top.murphypen.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTaskService {

    @Async
    public void executeAsyncTask(Integer i) {
        System.out.println("execute async task:" + i);
    }

    @Async
    public void executeAsyncTaskPlus(Integer i) {
        System.out.println("execute async task plus:" + (i + 1));
    }

    public void executeSyncTask(Integer i) {
        System.out.println("execute sync task:" + i);
    }

}
