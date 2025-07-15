package com.jobapplication.JobApp.job.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.jobapplication.JobApp.JobAppApplication;
import com.jobapplication.JobApp.job.Job;
import com.jobapplication.JobApp.job.JobRepository;
import com.jobapplication.JobApp.job.JobService;

@Service
public class JobServiceImpl implements JobService {

	private JobRepository jobRepository;

	public JobServiceImpl(JobRepository jobRepository) {
		this.jobRepository = jobRepository;

	}

	@Override
	public List<Job> findAll() {
		// TODO Auto-generated method stub
		return jobRepository.findAll();
	}

	@Override
	public void createJob(Job job) {
		// TODO Auto-generated method stub
		Job jb = new Job();
		jb.setDescription(job.getDescription());
		jb.setTitle(job.getTitle());
		jb.setMinSalary(job.getMinSalary());
		jb.setMaxSalary(job.getMaxSalary());
		jb.setLocation(job.getLocation());
		jb.setCompany(job.getCompany());
		jobRepository.save(jb);
	}

	@Override
	public Job getJobById(Long Id) {
		// TODO Auto-generated method stub
		return jobRepository.findById(Id).orElse(null);
	}

	@Override
	public Boolean deleteJobById(Long Id) {
		try {
			jobRepository.deleteById(Id);
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

	@Override
	public Boolean updateJob(Long Id, Job job) {
		// TODO Auto-generated method stub

		Optional<Job> jobOptional = jobRepository.findById(Id);
		if (jobOptional.isPresent()) {
			Job jb = jobOptional.get();
			jb.setTitle(job.getTitle());
			jb.setDescription(job.getDescription());
			jb.setLocation(job.getLocation());
			jb.setMaxSalary(job.getMaxSalary());
			jb.setMinSalary(job.getMinSalary());
			jobRepository.save(jb);
			return true;
		}

		return false;
	}

}
