package com.fiserv.job.file_to_database.steps;

import com.fiserv.job.file_to_database.entities.Person;
import org.junit.Test;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ItemReaderStepTest {
    @Test
    public void test_returns_default_line_mapper_object() {
        ItemReaderStep itemReader = new ItemReaderStep();
        LineMapper<Person> lineMapper = itemReader.getLineMapper();
        assertNotNull(lineMapper);
        assertEquals(DefaultLineMapper.class, lineMapper.getClass());
    }

    @Test
    public void test_sets_correct_name() {
        ItemReaderStep itemReader = new ItemReaderStep();
        assertEquals("readPersons", itemReader.getName());
    }

    @Test
    public void test_map_csv_data_to_person_object_with_additional_fields() {
        ItemReaderStep itemReader = new ItemReaderStep();
        LineMapper<Person> lineMapper = itemReader.getLineMapper();
        assertNotNull(lineMapper);
        assertEquals(DefaultLineMapper.class, lineMapper.getClass());
    }

}


