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
		// ������
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();

		// ִ�еľ���ĵ�����(��������)
		JobDetail job = JobBuilder.newJob().ofType(Test.class).usingJobData("name", "chenmi").build();
		
		
		

		// ����ʱ������
		Trigger trigger = TriggerBuilder.newTrigger().startNow()
				/**
				 * ���ֲ�ͬ�ķ�ʽ
				 */
				// ÿ5����ִ��һ��
				.withSchedule(CronScheduleBuilder.cronSchedule("0,44,59 * * * * ? *"))
				// ÿ������ִ��һ��
				// .withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(10, 5))
				// ÿ������ִ��һ��
				// .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInSeconds(2))
				.build();
		
		
		
		// �������
		scheduler.scheduleJob(job, trigger);
		// ����
		scheduler.start();
		// ����һ��ʱ���ر�,���������ر�
		// Thread.sleep(15000);
		// �Զ��ر�
		// scheduler.shutdown(true);
	}
}
