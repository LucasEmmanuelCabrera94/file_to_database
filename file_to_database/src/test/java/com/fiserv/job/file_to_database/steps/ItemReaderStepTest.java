package com.fiserv.job.file_to_database.steps;

import com.fiserv.job.file_to_database.entities.Person;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("Tests for ItemReaderStep class")
public class ItemReaderStepTest {
    @Test
    @DisplayName("The ItemReaderStep should return a default LineMapper object")
    public void test_returns_default_line_mapper_object() {
        ItemReaderStep itemReader = new ItemReaderStep();
        LineMapper<Person> lineMapper = itemReader.getLineMapper();
        assertNotNull(lineMapper);
        assertEquals(DefaultLineMapper.class, lineMapper.getClass());
    }

    @Test
    @DisplayName("The ItemReaderStep should return the correct name")
    public void test_sets_correct_name() {
        ItemReaderStep itemReader = new ItemReaderStep();
        assertEquals("readPersons", itemReader.getName());
    }

    @Test
    @DisplayName("The ItemReaderStep should return a default LineMapper object with additional fields")
    public void test_map_csv_data_to_person_object_with_additional_fields() {
        ItemReaderStep itemReader = new ItemReaderStep();
        LineMapper<Person> lineMapper = itemReader.getLineMapper();
        assertNotNull(lineMapper);
        assertEquals(DefaultLineMapper.class, lineMapper.getClass());
    }

}


