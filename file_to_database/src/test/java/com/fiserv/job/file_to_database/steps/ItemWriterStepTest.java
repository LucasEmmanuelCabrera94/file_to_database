package com.fiserv.job.file_to_database.steps;

import com.fiserv.job.file_to_database.entities.Person;
import com.fiserv.job.file_to_database.services.IPersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.item.Chunk;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for ItemWriterStep class")
public class ItemWriterStepTest {
    @Mock
    private IPersonService personServiceMock;

    @InjectMocks
    private ItemWriterStep itemWriterStep;


    @Test
    @DisplayName("Save all persons in chunk")
    public void test_saveAllPersonsInChunk() {
        List<Person> personList = createListPersons();

        Chunk<Person> chunk = new Chunk<>(personList);

        this.itemWriterStep.write(chunk);

        // Assert
        verify(this.personServiceMock, times(1)).saveAll(personList);
    }

    @Test
    @DisplayName("Do not save anything if chunk is empty")
    public void test_doNotSaveAnythingIfChunkIsEmpty() {
        Chunk<Person> chunk = new Chunk<>(new ArrayList<>());

        this.itemWriterStep.write(chunk);

        // Assert
        verify(this.personServiceMock, never()).saveAll(anyList());
    }

    @Test
    @DisplayName("Throw exception if any person in chunk is null")
    public void test_throwExceptionIfAnyPersonIsNull() {
        List<Person> personList = createListWithPersonNull();

        Chunk<Person> chunk = new Chunk<>(personList);

        // Assert
        assertThrows(NullPointerException.class, () -> this.itemWriterStep.write(chunk));
    }

    private static List<Person> createListPersons() {
        return Arrays.asList(
                Person.builder().name("nameTest").lastName("lastNameTest").dni(123456).build(),
                Person.builder().name("nameTest2").lastName("lastNameTest2").dni(789012).build()
        );
    }
    private static List<Person> createListWithPersonNull() {
        Person personTest1 =  Person.builder().name("nameTest").lastName("lastNameTest").dni(123456).build();

        List<Person> personList = new ArrayList<>();
        personList.add(personTest1);
        personList.add(null);
        return personList;
    }
}
