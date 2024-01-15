package com.fiserv.job.file_to_database.listeners;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class StepExecutionListenerImpl implements StepExecutionListener {

    /**
     * This method is called after the execution of a step.
     * It checks if the step execution status is FAILED and prints an error message if it is.
     * It then returns the exit status of the step execution.
     *
     * @param stepExecution the StepExecution object representing the execution of the step
     * @return the exit status of the step execution
     */
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        if (stepExecution.getStatus() == BatchStatus.FAILED) {
            System.out.println("The step has failed. Failure message: " + stepExecution.getFailureExceptions().toString());
        }

        return stepExecution.getExitStatus();
    }
}

