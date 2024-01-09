package com.fiserv.job.file_to_database;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@Slf4j
@SpringBootApplication
public class FileToDatabaseApplication {
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	/**
	 * Main method to run the RetrievingFromBdApplication.
	 *
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(FileToDatabaseApplication.class, args);
	}

	/**
	 * Executes the job.
	 */
	@PostConstruct
	public void executeJob() {
		try {
			JobParameters jobParameters = createJobParameters();

			jobLauncher.run(job, jobParameters);

		} catch (Exception e) {
			log.error("Error al iniciar el proceso Batch, Error {}", e.getMessage());
			throw new RuntimeException(e);
		}
	}

	/**
	 * Create job parameters with name, id, and date.
	 *
	 * @return the created job parameters
	 */
	private JobParameters createJobParameters() {
		return new JobParametersBuilder()
				.addString("name", "chunk")
				.addLong("id", System.currentTimeMillis())
				.addDate("date", new Date())
				.toJobParameters();
	}
}
