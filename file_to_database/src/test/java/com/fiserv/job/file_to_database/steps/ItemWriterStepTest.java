package com.fiserv.job.file_to_database.steps;

import com.fiserv.job.file_to_database.entities.Person;
import com.fiserv.job.file_to_database.services.IPersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.item.Chunk;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ItemWriterStepTest {
    @Mock
    private IPersonService personServiceMock;

    @InjectMocks
    private ItemWriterStep itemWriterStep;


    // The write method should save all the persons in the given chunk to the database using the IPersonService.
    @Test
    public void test_saveAllPersonsInChunk() {
        List<Person> personList = createListPersons();

        Chunk<Person> chunk = new Chunk<>(personList);

        this.itemWriterStep.write(chunk);

        // Assert
        verify(this.personServiceMock, times(1)).saveAll(personList);
    }

    // If the chunk is empty, the write method should not save anything to the database.
    @Test
    public void test_doNotSaveAnythingIfChunkIsEmpty() {
        Chunk<Person> chunk = new Chunk<>(new ArrayList<>());

        this.itemWriterStep.write(chunk);

        // Assert
        verify(this.personServiceMock, never()).saveAll(anyList());
    }

    // The IPersonService should throw an exception if any of the persons in the list to be saved is null.
    @Test
    public void test_throwExceptionIfAnyPersonIsNull() {
        List<Person> personList = createListWithPersonNull();

        Chunk<Person> chunk = new Chunk<>(personList);

        // Assert
        assertThrows(NullPointerException.class, () -> this.itemWriterStep.write(chunk));
    }

    private static List<Person> createListPersons() {
        Person personTest1 = new Person("nameTest","lastNameTest",123);
        Person personTest2 =new Person("nameTest2","lastNameTest2",456);

        List<Person> personList = new ArrayList<>();
        personList.add(personTest1);
        personList.add(personTest2);
        return personList;
    }
    private static List<Person> createListWithPersonNull() {
        Person personTest1 = new Person("nameTest","lastNameTest",123);

        List<Person> personList = new ArrayList<>();
        personList.add(personTest1);
        personList.add(null);
        return personList;
    }
}
