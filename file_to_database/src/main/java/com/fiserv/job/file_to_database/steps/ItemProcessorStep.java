package com.fiserv.job.file_to_database.steps;

import com.fiserv.job.file_to_database.entities.Person;
import com.fiserv.job.file_to_database.utils.HandlerException;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ItemProcessorStep implements ItemProcessor<Person,Person> {

    /**
     * Process the given person object and update the "createdAt" field with the current date and time.
     * IS A MOCK VERSION, OF COURSE THIS NEED TRUTH LOGIC!
     *
     * @param  person  the person object to be processed
     * @return         the updated person object
     */
    @Override
    public Person process(Person person) throws HandlerException {

        //The above is an example to make the batch process fail.
        /*if (person.getName().matches("Nicole")){
            throw new HandlerException("Error");
        }*/

        person.setCreateAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        return person;
    }
}
