package com.fiserv.job.file_to_database.utils;

import org.springframework.batch.core.step.skip.SkipPolicy;

public class SkipPolicyImpl implements SkipPolicy {
    private static final int MAX_SKIP_COUNT = 3;

    /**
     * Determines whether the given throwable should be skipped based on the skip count.
     *
     * @param  throwable  the throwable to be checked
     * @param  skipCount  the number of skips
     * @return            true if the throwable should be skipped, false otherwise
     */
    @Override
    public boolean shouldSkip(Throwable throwable, long skipCount) {
        if (throwable instanceof HandlerException && skipCount <= MAX_SKIP_COUNT) {
            System.out.println("The error was expected to skip: " + throwable.getMessage());
            return true;
        } else {
            return false;
        }
    }
}
