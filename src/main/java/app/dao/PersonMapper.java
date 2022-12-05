package app.dao;


import app.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Person person = new Person();

        person.setEmail(resultSet.getString("email"));
        person.setId(resultSet.getInt("id"));
        person.setAge(resultSet.getInt("age"));
        person.setName(resultSet.getString("name"));

        return person;
    }
}
