package com.fiserv.job.file_to_database.steps;

import com.fiserv.job.file_to_database.entities.Person;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

import java.nio.charset.StandardCharsets;

public class ItemReaderStep extends FlatFileItemReader<Person> {

    public ItemReaderStep() {
        setName("readPersons");
        //This path should be replaced by the location of the corresponding file.
        setResource(new ClassPathResource("persons.csv"));
        setLinesToSkip(1);
        setEncoding(StandardCharsets.UTF_8.name());
        setLineMapper(getLineMapper());
    }

    /**
     * Returns the LineMapper for the Person class.
     * IS A MOCK VERSION, OF COURSE THIS NEED TRUTH LOGIC!
     *
     * @return the LineMapper object for mapping CSV lines to Person objects
     */
    public DefaultLineMapper<Person> getLineMapper() {
        DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setNames("name", "lastName", "dni");
        lineTokenizer.setIncludedFields(0, 1, 2);
        lineTokenizer.setDelimiter(",");

        BeanWrapperFieldSetMapper<Person> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Person.class);
        fieldSetMapper.setDistanceLimit(0);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }
}
