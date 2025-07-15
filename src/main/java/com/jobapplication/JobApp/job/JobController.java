package com.jobapplication.JobApp.job;

import java.util.ArrayList;
import java.util.List;

import com.jobapplication.JobApp.JobAppApplication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {

	private JobService jobService;

	public JobController(JobService jobService) {
		super();
		this.jobService = jobService;
	}

	/*
	 * to get list of all jobs
	 */
	@GetMapping
	public ResponseEntity<List<Job>> findAll() {
		return ResponseEntity.ok(jobService.findAll());
	}

	/* to create job */
	@PostMapping
	public ResponseEntity<String> addJob(@RequestBody Job jb) {
		
		jobService.createJob(jb);
		return new ResponseEntity<>("job added succesfully", HttpStatus.OK);
	}

	/* to get perticular job details */
	@GetMapping("/{Id}")
	public ResponseEntity<Job> getJobById(@PathVariable Long Id) {
		Job job = jobService.getJobById(Id);
		if (job != null)
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	/* to delete specific job using jobId */
	@DeleteMapping("/{Id}")
	public ResponseEntity<String> deleteJobById(@PathVariable Long Id) {
		boolean deleted = jobService.deleteJobById(Id);
		if (deleted)
			return new ResponseEntity<String>("job deleted", HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	/* to update specific job detail */
	@PutMapping("/{Id}")
	public ResponseEntity<String> updateJob(@PathVariable Long Id, @RequestBody Job job) {
		boolean updated = jobService.updateJob(Id, job);
		if (updated)
			return new ResponseEntity<String>("job updated", HttpStatus.OK);
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);

	}
}

//endpoint 
///jobs   (GET)
///jobs/{id} (GET)
///jobs   (POST)
///jobs/{id}   (PUT)
///jobs/{id}   (DELETE)