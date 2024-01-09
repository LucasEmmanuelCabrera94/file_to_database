package com.fiserv.job.file_to_database.services;

import com.fiserv.job.file_to_database.entities.Person;
import com.fiserv.job.file_to_database.repositories.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService {
    @Autowired
    private IPersonRepository personRepository;

    /**
     * Saves a list of persons.
     *
     * @param  personList  the list of persons to be saved
     */
    @Override
    @Transactional
    public void saveAll(List<Person> personList) {
        this.personRepository.saveAll(personList);
    }
}