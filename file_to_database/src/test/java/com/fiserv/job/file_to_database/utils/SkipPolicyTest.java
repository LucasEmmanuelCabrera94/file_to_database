package com.fiserv.job.file_to_database.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@DisplayName("Tests for SkipPolicy class")
public class SkipPolicyTest {

    @Test
    @DisplayName("The test case creates an instance of SkipPolicyImpl and a mock HandlerException object. It then calls shouldSkip() with different input values and asserts the expected boolean values using the assertTrue() and assertFalse() methods.")
    public void testShouldSkipHandlerException() {
        SkipPolicyImpl skipPolicy = new SkipPolicyImpl();

        HandlerException handlerException = mock(HandlerException.class);

        assertTrue(skipPolicy.shouldSkip(handlerException, 2));
        assertFalse(skipPolicy.shouldSkip(handlerException, 4));
    }

    @Test
    @DisplayName("It verifies that when a non-handler exception is passed to the shouldSkip method with either a value of 2 or 0, the method returns false.")
    public void testShouldSkipNonHandlerException() {
        SkipPolicyImpl skipPolicy = new SkipPolicyImpl();

        Throwable nonHandlerException = mock(Throwable.class);

        assertFalse(skipPolicy.shouldSkip(nonHandlerException, 2));
        assertFalse(skipPolicy.shouldSkip(nonHandlerException, 0));
    }
}