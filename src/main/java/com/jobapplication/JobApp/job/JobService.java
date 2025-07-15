package com.jobapplication.JobApp.job;

import java.util.List;

public interface JobService {

	List<Job> findAll();

	void createJob(Job job);
	
	Job getJobById(Long Id);
	
	Boolean deleteJobById(Long Id);
	
	Boolean updateJob(Long Id, Job job);
	
	

}
