package org.hrs.jobschedule.frame;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hrs.jobschedule.Bean.JobDefineBean;

public class GetJobThread extends Thread {
	static Logger logger = Logger.getLogger(GetJobThread.class.getName());
	
	List<JobDefineBean> jobList = null;

	public GetJobThread() {
		// TODO Auto-generated constructor stub
	}

	public GetJobThread(List<JobDefineBean> jobList) {
		this.jobList = jobList;
	}

	public GetJobThread(String name) {
		super(name);
	}

	public  void run(){
		
		while (true) {

			getNewJob(jobList);

			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void getNewJob(List<JobDefineBean> jobList) {
		//get new job from DB/file
		jobList = testJob();
	}

	
	private List<JobDefineBean> testJob() {
		
		List<JobDefineBean> jobList = null;
		JobDefineBean job = new JobDefineBean();
		Properties properties = new Properties(); 
		try {
			properties.load(GetJobThread.class.getClassLoader().getResourceAsStream("testjob.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		job.setJobID(properties.getProperty("jobID"));
		job.setGroupID(properties.getProperty("groupID"));
		job.setHostName(properties.getProperty("hostName"));
		job.setJobClassName(properties.getProperty("jobClassName"));
		job.setJobName(properties.getProperty("jobName"));
		job.setJobParameter(properties.getProperty("jobParameter"));
		job.setRefreshType(properties.getProperty("refreshType"));
		job.setScheduleDetail(properties.getProperty("scheduleDetail"));
		job.setScheduleType(properties.getProperty("scheduleType"));
		jobList.add(job);
		
		return jobList;
	}

	public static void main(String[] args){
		Properties properties = new Properties(); 
		try {
			properties.load(GetJobThread.class.getClassLoader().getResourceAsStream("testjob.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println(properties.getProperty("jobID"));  
        System.out.println(properties.getProperty("scheduleDetail"));  
        System.out.println(properties.getProperty("jobClassName")); 
        
	}

}
