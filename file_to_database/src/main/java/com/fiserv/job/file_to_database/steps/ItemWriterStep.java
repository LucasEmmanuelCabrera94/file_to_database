package com.fiserv.job.file_to_database.steps;

import com.fiserv.job.file_to_database.entities.Person;
import com.fiserv.job.file_to_database.services.IPersonService;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ItemWriterStep implements ItemWriter<Person>{
    @Autowired
    private IPersonService personService;

    /**
     * Writes the given chunk of data to the destination.
     *
     * @param chunk the chunk of data to be written
     */
    @Override
    public void write(Chunk<? extends Person> chunk) {
        if (chunk != null && chunk.getItems() != null && !chunk.getItems().isEmpty()) {
            List<Person> list = new ArrayList<>();

            chunk.forEach(person -> {
                if(person != null){
                    list.add(person);
                }
            });

            this.personService.saveAll(list);
        }
    }
}
