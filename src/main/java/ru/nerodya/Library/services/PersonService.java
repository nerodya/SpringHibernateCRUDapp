package ru.nerodya.Library.services;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nerodya.Library.models.Book;
import ru.nerodya.Library.models.Person;
import ru.nerodya.Library.repositories.PepleRepository;

import java.util.*;

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

    public List<Book> bookList(int id){
        Optional<Person> foundPerson = pepleRepository.findById(id);
        if(foundPerson.isPresent()){
            Hibernate.initialize(foundPerson.get().getBooks());

            foundPerson.get().getBooks().forEach(book -> {
                if (book.getDateIssue() != null) {
                    long diffInMillies = Math.abs(new Date().getTime() - book.getDateIssue().getTime());
                    if (diffInMillies > 864000000)
                        book.setExpired(true);
                }
            });
            return foundPerson.get().getBooks();
        } else
            return Collections.emptyList();
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

    public Optional<Person> findByName(String name){
        return pepleRepository.findByName(name);
    }
}