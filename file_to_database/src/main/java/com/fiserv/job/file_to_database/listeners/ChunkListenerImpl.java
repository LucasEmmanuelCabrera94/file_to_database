package com.fiserv.job.file_to_database.listeners;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

public class ChunkListenerImpl implements ChunkListener {

    /**
     * This method is called after chunk processing.
     *
     * @param  context  the chunk context
     */
    @Override
    public void afterChunk(ChunkContext context) {
        System.out.println("After chunk processing...");
        if (isBatchStatusFailed(context)) {
            System.out.println("FINALIZO EL STATUS COMPLETE...");
        }
    }


    /**
     * This method is called after an error occurs during chunk processing.
     *
     * @param  context  the chunk context
     */
    @Override
    public void afterChunkError(ChunkContext context) {
        Exception exception = (Exception) context.getAttribute("sb_rollback_exception");

        if (exception != null) {
            String errorMessage = "Error during chunk processing: " + exception.toString();
            System.err.println(errorMessage);
        }
    }

    /**
     * Checks if the batch status of the given ChunkContext is failed.
     *
     * @param  context  the ChunkContext object to check the batch status of
     * @return          true if the batch status is failed, false otherwise
     */
    private boolean isBatchStatusFailed(ChunkContext context) {
        return context.getStepContext().getStepExecution().getStatus() == BatchStatus.FAILED;
    }
}
