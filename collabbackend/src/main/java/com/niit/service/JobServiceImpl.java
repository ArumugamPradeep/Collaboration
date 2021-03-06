package com.niit.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.dao.JobDAO;
import com.niit.model.Job;

@Service
@Transactional
public class JobServiceImpl implements JobService {

	@Autowired
	private JobDAO jobDAO;

	@Override
	public void addJob(Job job) {
		jobDAO.addJob(job);

	}

	@Override
	public List<Job> getAllJobs() {
		return jobDAO.getAllJobs();
	}

	@Override
	public Job getJob(int jobId) {
		return jobDAO.getJob(jobId);

	}

}
