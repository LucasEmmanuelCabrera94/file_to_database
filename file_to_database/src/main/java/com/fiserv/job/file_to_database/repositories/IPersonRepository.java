package com.fiserv.job.file_to_database.repositories;

import com.fiserv.job.file_to_database.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface IPersonRepository extends CrudRepository<Person, Long> {
}
