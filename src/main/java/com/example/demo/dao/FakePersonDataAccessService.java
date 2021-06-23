package com.example.demo.dao;

import ch.qos.logback.classic.db.names.DBNameResolver;
import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDAO{
    private static List<Person> DB = new ArrayList<>();
    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> getSelectPerson() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonbyId(UUID id) {
        return DB.stream().filter(person -> person.getID().equals(id)).findFirst();
    }

    @Override
    public int deletePerson(UUID id) {
        Optional<Person> personMaybe = selectPersonbyId(id);
        if(personMaybe.equals(null)){
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePerson(UUID id,Person update) {
        return selectPersonbyId(id).map(person -> {
            int indexOfPersonToUpdate = DB.indexOf(person);
            if (indexOfPersonToUpdate>=0){
                DB.set(indexOfPersonToUpdate,new Person(id,update.getName()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }



}
