package top.murphypen.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ScheduleTaskService {
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println(dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 03 22 ? * *")
    public void fixTimeExecution() {
        System.out.println("cron:" + dateFormat.format(new Date()));
    }
}
