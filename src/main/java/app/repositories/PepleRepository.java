package app.repositories;

import app.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PepleRepository extends JpaRepository<Person, Integer> {
}
