package com.fiserv.job.file_to_database.listeners;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@DisplayName("Tests for ChunkListenerImpl class")
public class ChunkListenerImplTest {

    @Mock
    private ChunkContext mockChunkContext;

    @Mock
    private StepContext mockStepContext;

    @Mock
    private StepExecution mockStepExecution;

    @InjectMocks
    private ChunkListenerImpl chunkListener;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(mockChunkContext.getStepContext()).thenReturn(mockStepContext);
        when(mockStepContext.getStepExecution()).thenReturn(mockStepExecution);
    }

    @Test
    @DisplayName("After Chunk: Batch Status Failed")
    public void testAfterChunkBatchStatusFailed() {
        when(mockStepExecution.getStatus()).thenReturn(BatchStatus.FAILED);

        chunkListener.afterChunk(mockChunkContext);

        verify(mockStepExecution, times(1)).getStatus();

    }

    @Test
    @DisplayName("After Chunk: Batch Status is Completed")
    public void testAfterChunkBatchStatusNotFailed() {
        when(mockStepExecution.getStatus()).thenReturn(BatchStatus.COMPLETED);

        chunkListener.afterChunk(mockChunkContext);

        verify(mockStepExecution, times(1)).getStatus();

    }

    @Test
    @DisplayName("After Chunk with Error")
    public void testAfterChunkError() {
        Exception testException = new RuntimeException("Test exception");
        when(mockChunkContext.getAttribute("sb_rollback_exception")).thenReturn(testException);

        chunkListener.afterChunkError(mockChunkContext);

        verify(mockChunkContext, times(1)).getAttribute("sb_rollback_exception");

    }
}
