package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
//    private final PersonService personService;
    @Autowired
    private PersonService personService;
    @PostMapping
    public void addPerson(@RequestBody Person person){
        personService.addPerson(person);
    }
    @GetMapping
    public List<Person> getAllPerson(){
        return personService.getAll();
    }
    @GetMapping(path = "{id}")
    public Person getByID(@PathVariable("id")  UUID id){
        return personService.getByID(id).orElse(null);
    }
    @DeleteMapping(path = "{id}")
    public void deletePersonByID(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }
    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id,@Validated @RequestBody Person personToUpdate){
        personService.updatePerson(id, personToUpdate);
    }

}
