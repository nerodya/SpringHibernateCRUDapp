package ru.nerodya.Library.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.nerodya.Library.models.Person;

public interface PepleRepository extends JpaRepository<Person, Integer> {
}
