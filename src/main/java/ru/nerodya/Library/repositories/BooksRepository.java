package ru.nerodya.Library.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.nerodya.Library.models.Book;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findByNameStartingWith(String name);
}
