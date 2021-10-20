package com.yr.quartz;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Main {
	public static void main(String[] args) throws Exception {
		new Main().startScheduler();
	}

	public void startScheduler() throws Exception {
		// 调用器
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();

		// 执行的具体的调试类(调试任务)
		JobDetail job = JobBuilder.newJob().ofType(Test.class).usingJobData("name", "chenmi").build();
		
		
		

		// 调试时间配置
		Trigger trigger = TriggerBuilder.newTrigger().startNow()
				/**
				 * 三种不同的方式
				 */
				// 每5秒钟执行一次
				.withSchedule(CronScheduleBuilder.cronSchedule("0,44,59 * * * * ? *"))
				// 每两秒钟执行一次
				// .withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(10, 5))
				// 每两秒钟执行一次
				// .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInSeconds(2))
				.build();
		
		
		
		// 加入调度
		scheduler.scheduleJob(job, trigger);
		// 启动
		scheduler.start();
		// 运行一段时间后关闭,可以永不关闭
		// Thread.sleep(15000);
		// 自动关闭
		// scheduler.shutdown(true);
	}
}
