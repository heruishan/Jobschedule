package org.hrs.jobschedule.Bean;

import java.util.Vector;

public class JobSourceSngl {

	private Vector<JobDefineBean> jobData = null;
	
	private JobSourceSngl (){
		this.init();
	} 
	
	public static final JobSourceSngl getInstance() {  
	    return JobSourceSnglHolder.INSTANCE;  
	    } 
	
	public void addJob(JobDefineBean job){
		jobData.addElement(job);
	}
	
	public void removeJob(int index){
		jobData.remove(index);
	}
	
	public JobDefineBean getJob(int index){
		if(jobData.size()>0){
			return jobData.elementAt(index);
		}else{
			return null;
		}
	}
	
	public void init(){
		jobData = new Vector<JobDefineBean>(100,100);
		
	}
	
	private static class JobSourceSnglHolder {  
	    private static final JobSourceSngl INSTANCE = new JobSourceSngl();  
	    }  
}
