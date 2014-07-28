/**
 * 
 */
package org.hrs.jobschedule.demo;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author heruishan
 *
 */
public class DemoSchedule {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SchedulerFactory schedulerFactory =  new StdSchedulerFactory();
		Scheduler scheduler = null;
		try {
			//get scheduler
			scheduler = schedulerFactory.getScheduler();
			
			//define job detail
			JobDetail jobDetail = newJob(DemoJob.class).withIdentity("job1", "group1").build() ;// new JobDetailImpl("","",DemoJob.class); 
			
			//define trigger
			Trigger sTtrigger = newTrigger().withIdentity("job1", "group1").startNow().withSchedule(
                    simpleSchedule().withIntervalInSeconds(4)
                    .repeatForever()).build();
			
			jobDetail.getJobDataMap().put("test1", "oh yeah");
			
			scheduler.scheduleJob(jobDetail, sTtrigger);
			//start schedule
			scheduler.start();
			System.out.println("schedule start");
			
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			//define job detail
			JobDetail jobDetail2 = newJob(DemoJob.class).withIdentity("job2", "group2").build() ;// new JobDetailImpl("","",DemoJob.class); 
			
			//define trigger
			Trigger sTtrigger2 = newTrigger().withIdentity("job2", "group2").startNow().withSchedule(
                    simpleSchedule().withIntervalInSeconds(4)
                    .repeatForever()).build();
			
			jobDetail.getJobDataMap().put("test1", "oh yeah2");
			
			scheduler.scheduleJob(jobDetail2, sTtrigger2);
			
			
		} catch (SchedulerException e) {
			e.printStackTrace();
			try {
				scheduler.shutdown();
			} catch (SchedulerException e1) {
				e1.printStackTrace();
			}
		}

	}

}
