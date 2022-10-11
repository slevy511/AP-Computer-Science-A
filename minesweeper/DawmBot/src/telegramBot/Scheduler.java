package telegramBot;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
	
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private SpiceRunner runner = new SpiceRunner();
	ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
	ZonedDateTime nextRun = now.withHour(16).withMinute(20).withSecond(0);
	 
	private long delay = (nextRun.getHour()* 3600 + nextRun.getMinute()*60 + nextRun.getSecond()) - (now.getHour()* 3600 + now.getMinute()*60 + now.getSecond());
	private long dayInSecs = 86400;
	
	 
	public Scheduler() {
		if(delay > 0) {
			scheduler.scheduleAtFixedRate(runner, delay, dayInSecs, TimeUnit.SECONDS);
		}else {
			//delay += dayInSecs;
			delay += dayInSecs;
		 	scheduler.scheduleAtFixedRate(runner, delay, dayInSecs, TimeUnit.SECONDS);
		}
	}
}
