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
    private static void initPerson(){
        Person e1 = new Person(UUID.randomUUID(),"Duc","Nguyen","QWWE");
        Person e2 = new Person(UUID.randomUUID(),"Duc1","Nguyen","QWWE");
        Person e3 = new Person(UUID.randomUUID(),"Duc2","Nguyen","QWWE");
        DB.add(e1);
        DB.add(e2);
        DB.add(e3);
    }
    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getFirstName(),person.getLastName(),person.getAddress()));
        return 1;
    }

    @Override
    public List<Person> getSelectPerson() {
        initPerson();
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
                DB.set(indexOfPersonToUpdate,new Person(id,update.getFirstName(),update.getLastName(),update.getAddress()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }



}
