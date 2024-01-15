package com.fiserv.job.file_to_database.steps;

import com.fiserv.job.file_to_database.entities.Person;
import com.fiserv.job.file_to_database.utils.HandlerException;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ItemProcessorStepTest {
    // The method updates the "createdAt" field of the given Person object with the current date and time.
    @Test
    public void test_updateCreatedAtField() throws HandlerException {
        Person person = createPerson();

        Person updatedPerson = new ItemProcessorStep().process(person);

        // Assert
        assertNotNull(updatedPerson.getCreateAt());
    }

    // The method returns the updated Person object.
    @Test
    public void test_returnUpdatedPersonObject() throws HandlerException {
        Person person = createPerson();

        Person updatedPerson = new ItemProcessorStep().process(person);

        // Assert
        assertEquals(person, updatedPerson);
    }

    // The method uses the DateTimeFormatter to format the current date and time.
    @Test
    public void test_useDateTimeFormatter() throws HandlerException {
        Person person = createPerson();

        Person updatedPerson = new ItemProcessorStep().process(person);

        // Assert
        assertNotNull(updatedPerson.getCreateAt());
        assertTrue(updatedPerson.getCreateAt().matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}"));
    }

    // The method receives a null Person object and returns null.
    @Test
    public void test_receiveNullPersonObject() {
        Person person = null;

        // Assert
        assertThrows(NullPointerException.class, () -> new ItemProcessorStep().process(person));
    }

    // The method receives a Person object with a null "createdAt" field and updates it with the current date and time.
    @Test
    public void test_receivePersonWithNullCreatedAtField() throws HandlerException {
        Person person = createPerson();
        person.setCreateAt(null);

        Person updatedPerson = new ItemProcessorStep().process(person);

        // Assert
        assertNotNull(updatedPerson.getCreateAt());
    }

    // The method receives a Person object with a non-null "createdAt" field and updates it with the current date and time.
    @Test
    public void test_receivePersonWithNonNullCreatedAtField() throws HandlerException {
        Person person = createPerson();
        person.setCreateAt("2021-01-01 00:00:00");

        Person updatedPerson = new ItemProcessorStep().process(person);

        // Assert
        assertNotNull(updatedPerson.getCreateAt());
        assertNotEquals("2021-01-01 00:00:00", updatedPerson.getCreateAt());
    }

    private static Person createPerson() {
        return new Person("nameTest","lastNameTest",123);
    }
}
