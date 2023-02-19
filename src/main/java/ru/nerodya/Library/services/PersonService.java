package ru.nerodya.Library.services;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nerodya.Library.models.Book;
import ru.nerodya.Library.models.Person;
import ru.nerodya.Library.repositories.PepleRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PepleRepository pepleRepository;

    @Autowired
    public PersonService(PepleRepository pepleRepository) {
        this.pepleRepository = pepleRepository;
    }

    public List<Person> findAll(){
        return pepleRepository.findAll();
    }


    public Person findOne(int id){
        Optional<Person> foundPerson = pepleRepository.findById(id);
        Hibernate.initialize(foundPerson.<Object>map(Person::getBooks).orElse(null));
        return foundPerson.orElse(null);
    }

//    пиздец
    public List<Book> bookList(int id){
        Optional<Person> foundPerson = pepleRepository.findById(id);
        if (foundPerson.isPresent())
            return foundPerson.get().getBooks();
        else return new ArrayList<>(Collections.emptyList());
    }

    @Transactional
    public void save(Person person){
        pepleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person person){
        person.setId_person(id);
        pepleRepository.save(person);
    }

    @Transactional
    public void delete(int id){
        pepleRepository.deleteById(id);
    }
}