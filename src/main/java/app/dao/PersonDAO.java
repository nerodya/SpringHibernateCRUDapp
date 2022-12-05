package app.dao;

import app.controller.PeopleController;
import app.models.Person;
import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index() throws SQLException {
//        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
//        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new PersonMapper())
//                .stream().findAny().orElse(null);

        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) throws SQLException {
        jdbcTemplate.update("INSERT INTO person VALUES(1, ?, ?, ?)", person.getName(), person.getAge(),
                person.getEmail());
    }


    public void update(int id, Person person) {
      jdbcTemplate.update("update person set name=?, age=?, email=? where id=?", person.getName(), person.getAge(),
              person.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE from person where id=?", id);
    }


}
