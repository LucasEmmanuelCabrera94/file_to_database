package com.fiserv.job.file_to_database.listeners;

import com.fiserv.job.file_to_database.entities.Person;
import com.fiserv.job.file_to_database.utils.HandlerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

@DisplayName("Tests for ItemProcessListenerImpl class")
public class ItemProcessListenerImplTest {

    @DisplayName("onProcessError() method tests the exception is an instance of HandlerException")
    @Test
    public void testOnProcessError() {
        //Arrange
        ItemProcessListenerImpl listener = new ItemProcessListenerImpl();
        Person person = new Person();
        Exception mockException = mock(HandlerException.class);

        Assertions.assertDoesNotThrow(() -> listener.onProcessError(person, mockException));
    }

    @Test
    @DisplayName("onProcessError() method If the exception is not an instance of HandlerException, it throws a RuntimeException")
    public void testOnProcessErrorWithHandlerException() {
        //Arrange
        ItemProcessListenerImpl listener = new ItemProcessListenerImpl();
        Person person = new Person();
        Exception handlerException = mock(Exception.class);

        Assertions.assertThrows(RuntimeException.class, (() -> listener.onProcessError(person, handlerException)));

    }
}
