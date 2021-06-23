package com.example.demo.service;

import com.example.demo.dao.PersonDAO;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDAO personDAO;
    @Autowired
    public PersonService(@Qualifier("fakeDao")PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public int addPerson(Person person){
        return personDAO.insertPerson(person);
    }

    public List<Person> getAll(){
        return personDAO.getSelectPerson();
    }

    public Optional<Person> getByID(UUID id){
        return personDAO.selectPersonbyId(id);
    }
    public int deletePerson(UUID id){
        return personDAO.deletePerson(id);
    }
    public int updatePerson(UUID id,Person newPerson){
        return personDAO.updatePerson(id, newPerson);
    }

}
