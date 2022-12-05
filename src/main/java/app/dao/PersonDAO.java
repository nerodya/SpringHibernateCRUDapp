package app.dao;

import app.controller.PeopleController;
import app.models.Person;
import org.postgresql.Driver;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1769";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> index() throws SQLException {
        Statement statement = connection.createStatement();

        List<Person> people = new ArrayList<>();
        String SQL = "SELECT * FROM PERSON";

        ResultSet resultSet = statement.executeQuery(SQL);
        while (resultSet.next()) {
            Person person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));

            people.add(person);
        }
        return people;
    }

    public Person show(int id) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT * from person where id =?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Person person = new Person();

        person.setEmail(resultSet.getString("email"));
        person.setId(resultSet.getInt("id"));
        person.setAge(resultSet.getInt("age"));
        person.setName(resultSet.getString("name"));

        return person;
    }

    public void save(Person person) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO person VALUES(1, ?, ?, ?)");

        preparedStatement.setString(1, person.getName());
        preparedStatement.setInt(2, person.getAge());
        preparedStatement.setString(3, person.getEmail());

        preparedStatement.executeUpdate();
    }


    public void update(int id, Person person) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Person SET name=?, age=?, email=? where id=?");

            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE from Person where id=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
