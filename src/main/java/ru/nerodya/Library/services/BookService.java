package ru.nerodya.Library.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nerodya.Library.models.Book;
import ru.nerodya.Library.repositories.BooksRepository;
import ru.nerodya.Library.repositories.PepleRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BooksRepository booksRepository;
    private final PepleRepository pepleRepository;

    @Autowired
    public BookService(BooksRepository booksRepository, PepleRepository pepleRepository) {
        this.booksRepository = booksRepository;
        this.pepleRepository = pepleRepository;
    }

    public List<Book> findAll(String page, String books_per_page, String sort_by_year) throws Exception {

        if (page.equals("null") ^ books_per_page.equals("null"))
            throw new Exception("один из параметров page и books_per_page не указан");

        if (sort_by_year.equals("true")){
            if (!page.equals("null"))
                return booksRepository.findAll(PageRequest.of(Integer.parseInt(page),
                        Integer.parseInt(books_per_page), Sort.by("year"))).getContent();
            else
                return booksRepository.findAll(Sort.by("year"));
        }
        else
            return booksRepository.findAll();
    }

    public Book findOne(int id) {
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book) {
        book.setId_book(id);
        booksRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    //    это пиздец переделать
    @Transactional
    public void editClient(int id_book, int id_person) {
        Optional<Book> book = booksRepository.findById(id_book);
        book.ifPresent(value -> value.setDateIssue(new Date()));
        book.ifPresent(value -> value.setOwner(pepleRepository.findById(id_person).orElse(null)));
    }

    @Transactional
    public void freeClient(int id) {
        booksRepository.findById(id).ifPresent(value -> value.setDateIssue(null));
        booksRepository.findById(id).ifPresent(value -> value.setOwner(null));
    }

    public List<Book> findByNameStartingWith(String name_book) {
        return booksRepository.findByNameStartingWith(name_book);
    }

}
