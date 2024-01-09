package com.fiserv.job.file_to_database.services;

import com.fiserv.job.file_to_database.entities.Person;

import java.util.List;

public interface IPersonService {
    /**
     * Saves all the persons in the given list.
     *
     * @param  personList  the list of persons to be saved
     */
    void saveAll(List<Person> personList);

}
