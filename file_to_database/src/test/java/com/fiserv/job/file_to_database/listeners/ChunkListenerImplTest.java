package com.fiserv.job.file_to_database.listeners;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;

import static org.mockito.Mockito.when;

public class ChunkListenerImplTest {

    @Mock
    private ChunkContext mockChunkContext;

    @Mock
    private StepContext mockStepContext;

    @Mock
    private StepExecution mockStepExecution;

    @InjectMocks
    private ChunkListenerImpl chunkListener;

    /**
     * Set up the test environment before each test case.
     *
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(mockChunkContext.getStepContext()).thenReturn(mockStepContext);
        when(mockStepContext.getStepExecution()).thenReturn(mockStepExecution);
    }

    /**
     * A description of the entire Java function.
     *
     */
    @Test
    public void testAfterChunkBatchStatusFailed() {
        when(mockStepExecution.getStatus()).thenReturn(BatchStatus.FAILED);

        chunkListener.afterChunk(mockChunkContext);
    }

    /**
     * Test the behavior of the `afterChunk` method when the batch status is not failed.
     *
     */
    @Test
    public void testAfterChunkBatchStatusNotFailed() {
        when(mockStepExecution.getStatus()).thenReturn(BatchStatus.COMPLETED);

        chunkListener.afterChunk(mockChunkContext);
    }

    /**
     * Test case for the method `afterChunkError` in the class `ChunkListener`.
     *
     */
    @Test
    public void testAfterChunkError() {
        Exception testException = new RuntimeException("Test exception");
        when(mockChunkContext.getAttribute("sb_rollback_exception")).thenReturn(testException);

        chunkListener.afterChunkError(mockChunkContext);
    }
}
