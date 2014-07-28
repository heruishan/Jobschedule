/**
 * 
 */
package org.hrs.jobschedule.jobs;

import org.apache.log4j.Logger;
import org.hrs.jobschedule.gate.ScheduleGate;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author heruishan
 *
 */
public class ShellJobs implements Job {

	static Logger logger = Logger.getLogger(ShellJobs.class.getName());
	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub

		logger.info("test ShellJobs:" + Thread.currentThread().getName() + ":" + arg0.getMergedJobDataMap().getString("JOB_PARAMETER"));
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
