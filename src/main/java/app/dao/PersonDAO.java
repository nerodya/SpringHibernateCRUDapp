package app.dao;

import app.models.Person;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Person p", Person.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Person show(int id_person) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id_person);
        Hibernate.initialize(person.getBooks());
        return person;
    }

    @Transactional
    public void createPerson(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }

    @Transactional
    public void update(Person person, int id) {
        Session session = sessionFactory.getCurrentSession();
        Person personToBeUpdate = session.get(Person.class, id);

        personToBeUpdate.setBooks(person.getBooks());
        personToBeUpdate.setName(person.getName());
        personToBeUpdate.setYear_birth(person.getYear_birth());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
    }
}
