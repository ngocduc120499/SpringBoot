package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.Optional;
import java.util.UUID;
import java.util.List;
public interface PersonDAO {
    int insertPerson(UUID id, Person person);
    default int insertPerson(Person person){
        UUID id  = UUID.randomUUID();
        return insertPerson(id, person);
    }
    List<Person> getSelectPerson();
    Optional<Person> selectPersonbyId(UUID id);
    int deletePerson (UUID id);
    int updatePerson (UUID id,Person person);
}
