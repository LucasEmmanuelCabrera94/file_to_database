package com.fiserv.job.file_to_database.steps;

import com.fiserv.job.file_to_database.entities.Person;
import com.fiserv.job.file_to_database.utils.HandlerException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for ItemProcessorStep class")
public class ItemProcessorStepTest {
    @DisplayName("The 'createdAt' Field Should Not Be Null After Update")
    @Test
    public void test_updateCreatedAtField() throws HandlerException {
        Person person = createPerson();

        Person updatedPerson = new ItemProcessorStep().process(person);

        // Assert
        assertNotNull(updatedPerson.getCreateAt());
    }

    @DisplayName("The test creates a Person object, passes it to the process method, and asserts that the returned updatedPerson object is equal to the original person object.")
    @Test
    public void test_returnUpdatedPersonObject() throws HandlerException {
        Person person = createPerson();

        Person updatedPerson = new ItemProcessorStep().process(person);

        // Assert
        assertEquals(person, updatedPerson);
    }

    @Test
    @DisplayName("It tests the process method of the ItemProcessorStep class by passing a Person object and asserting that the createAt field of the updated person is not null and matches a specific date-time format.")
    public void test_useDateTimeFormatter() throws HandlerException {
        Person person = createPerson();

        Person updatedPerson = new ItemProcessorStep().process(person);

        // Assert
        assertNotNull(updatedPerson.getCreateAt());
        assertTrue(updatedPerson.getCreateAt().matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}"));
    }

    @Test
    @DisplayName("It uses the assertThrows method to assert that a NullPointerException is thrown when calling process with a null Person object.")
    public void test_receiveNullPersonObject() {
        Person person = null;

        // Assert
        assertThrows(NullPointerException.class, () -> new ItemProcessorStep().process(person));
    }

    @Test
    @DisplayName("The test asserts that the createdAt field of the returned Person object is not null.")
    public void test_receivePersonWithNullCreatedAtField() throws HandlerException {
        Person person = createPerson();
        person.setCreateAt(null);

        Person updatedPerson = new ItemProcessorStep().process(person);

        // Assert
        assertNotNull(updatedPerson.getCreateAt());
    }

    @Test
    @DisplayName("The test asserts that the createdAt field of the returned Person object is not equal to the original createdAt field.")
    public void test_receivePersonWithNonNullCreatedAtField() throws HandlerException {
        Person person = createPerson();
        person.setCreateAt("2021-01-01 00:00:00");

        Person updatedPerson = new ItemProcessorStep().process(person);

        // Assert
        assertNotNull(updatedPerson.getCreateAt());
        assertNotEquals("2021-01-01 00:00:00", updatedPerson.getCreateAt());
    }

    private static Person createPerson() {
        return Person.builder()
                .name("nameTest")
                .lastName("lastNameTest")
                .dni(123)
                .build();
    }
}
