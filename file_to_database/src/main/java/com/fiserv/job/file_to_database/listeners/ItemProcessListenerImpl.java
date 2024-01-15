package com.fiserv.job.file_to_database.listeners;

import com.fiserv.job.file_to_database.entities.Person;
import com.fiserv.job.file_to_database.utils.HandlerException;
import org.springframework.batch.core.ItemProcessListener;

public class ItemProcessListenerImpl implements ItemProcessListener<Person, Person> {

    /**
     * Handles errors that occur during the processing of a person object.
     *
     * @param person The person object that caused the error.
     * @param ex The exception that occurred.
     */
    @Override
    public void onProcessError(Person person, Exception ex) {
        if (!(ex instanceof HandlerException)) {
            //Handle other types of exceptions as needed
            throw new RuntimeException(ex);
        }
        //Here you can change the process to fix the error, if necessary.
    }
}