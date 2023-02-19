package ru.nerodya.Library.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nerodya.Library.models.Person;

import java.util.Optional;

@Repository
public interface PepleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByName(String name);
}
