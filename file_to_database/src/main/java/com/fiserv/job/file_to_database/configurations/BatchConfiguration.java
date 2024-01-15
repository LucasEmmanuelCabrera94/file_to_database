package com.fiserv.job.file_to_database.configurations;

import com.fiserv.job.file_to_database.entities.Person;
import com.fiserv.job.file_to_database.listeners.*;
import com.fiserv.job.file_to_database.steps.ItemProcessorStep;
import com.fiserv.job.file_to_database.steps.ItemReaderStep;
import com.fiserv.job.file_to_database.steps.ItemWriterStep;
import com.fiserv.job.file_to_database.utils.SkipPolicyImpl;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.retry.policy.RetryContextCache;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.validation.BindException;

@Configuration
public class BatchConfiguration {

    @Bean(name = "PersonItemReader")
    public ItemReaderStep itemReaderStep() throws BindException {
        return new ItemReaderStep();
    }

    @Bean
    public ItemProcessor<? super Person, ? extends Person> itemProcessorStep(){
        return new ItemProcessorStep();
    }

    @Bean
    public ItemWriter<? super Person> itemWriterStep(){
        return new ItemWriterStep();
    }


    @Bean
    public StepExecutionListenerImpl stepExecutionListener(){
        return new StepExecutionListenerImpl();
    }


    @Bean
    public SkipPolicyImpl skipPolicy(){
        return new SkipPolicyImpl();
    }

    @Bean
    public ItemProcessListenerImpl ItemProcessListener(){
        return new ItemProcessListenerImpl();
    }

    /**
     * Creates a job object for the batchJob-file_to_database.
     *
     * @param  jobRepository the JobRepository to be used
     * @param  jobStep     the first Step to be executed
     * @return               the created Job object
     */
    @Bean(name = "batchJob-file_to_database")
    public Job job(JobRepository jobRepository, @Qualifier("jobStep") Step jobStep) {
        return new JobBuilder("batchJob-file_to_database", jobRepository)
                .start(jobStep)
                .build();
    }

    /**
     * Generates a Step object for the jobStep.
     *
     * @param  jobRepository        the JobRepository instance
     * @param  transactionManager   the PlatformTransactionManager instance
     * @return                      the Step object for the jobStep
     */
    @Bean
    protected Step jobStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws BindException {
        return new StepBuilder("readFile", jobRepository)
                .<Person, Person> chunk(2, transactionManager)
                .taskExecutor(taskExecutor())
                .reader(itemReaderStep())
                .listener(ItemProcessListener())
                .processor(itemProcessorStep())
                .writer(itemWriterStep())
                .listener(stepExecutionListener())
                .faultTolerant()
                .retryLimit(3)
                .retry(RuntimeException.class)
                .skipPolicy(skipPolicy())
                .build();
    }


    /**
     * Creates and configures a task executor bean.
     *
     * @return          The configured task executor bean.
     */
    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.setThreadNamePrefix("batchJob-file_to_database");
        executor.initialize();
        return executor;
    }
}
