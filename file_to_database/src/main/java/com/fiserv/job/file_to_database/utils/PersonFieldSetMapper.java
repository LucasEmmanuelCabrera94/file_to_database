package com.fiserv.job.file_to_database.utils;

import com.fiserv.job.file_to_database.entities.Person;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class PersonFieldSetMapper implements FieldSetMapper<Person> {

    /**
     * This is a class for local use only, for production you should create the corresponding entity.
     *
     */
    @Override
    public Person mapFieldSet(FieldSet fieldSet) {
        Person person = new Person();
        person.setName(fieldSet.readString("name"));
        person.setLastName(fieldSet.readString("lastName"));
        person.setDni(fieldSet.readInt("dni"));

        person.setCreateAt(fieldSet.readString("createAt"));
        return person;
    }
}
