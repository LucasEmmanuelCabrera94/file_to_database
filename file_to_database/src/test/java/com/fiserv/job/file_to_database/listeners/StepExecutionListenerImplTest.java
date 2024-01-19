package com.fiserv.job.file_to_database.listeners;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Tests for StepExecutionListenerImpl class")
public class StepExecutionListenerImplTest {

    @Test
    @DisplayName("Test for afterStep() Behavior with Success")
    public void testAfterStepSuccess() {
        //Arrange
        StepExecutionListenerImpl listener = new StepExecutionListenerImpl();
        StepExecution stepExecution = mock(StepExecution.class);
        when(stepExecution.getStatus()).thenReturn(BatchStatus.COMPLETED);
        when(stepExecution.getExitStatus()).thenReturn(ExitStatus.COMPLETED);

        ExitStatus exitStatus = listener.afterStep(stepExecution);

        //Assert
        assertEquals(ExitStatus.COMPLETED, exitStatus);
    }

    @Test
    @DisplayName("Test for afterStep() Behavior with Failure")
    public void testAfterStepFailure() {
        //Arrange
        StepExecutionListenerImpl listener = new StepExecutionListenerImpl();
        StepExecution stepExecution = mock(StepExecution.class);
        when(stepExecution.getStatus()).thenReturn(BatchStatus.FAILED);
        when(stepExecution.getExitStatus()).thenReturn(ExitStatus.FAILED);

        ExitStatus exitStatus = listener.afterStep(stepExecution);

        //Assert
        assertEquals(ExitStatus.FAILED, exitStatus);
    }

    @Test
    @DisplayName("Test for afterStep() Behavior with No Failure")
    public void testAfterStepNoFailure() {
        //Arrange
        StepExecutionListenerImpl listener = new StepExecutionListenerImpl();
        StepExecution stepExecution = mock(StepExecution.class);
        when(stepExecution.getStatus()).thenReturn(BatchStatus.COMPLETED);
        when(stepExecution.getExitStatus()).thenReturn(ExitStatus.COMPLETED);

        ExitStatus exitStatus = listener.afterStep(stepExecution);

        //Assert
        assertEquals(ExitStatus.COMPLETED, exitStatus);
    }
}
