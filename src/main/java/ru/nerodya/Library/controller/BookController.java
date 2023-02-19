package ru.nerodya.Library.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nerodya.Library.models.Book;
import ru.nerodya.Library.models.Person;
import ru.nerodya.Library.services.BookService;
import ru.nerodya.Library.services.PersonService;

//import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping()
    public String index(@RequestParam(defaultValue = "null") String  page,
                        @RequestParam(defaultValue = "null") String  books_per_page,
                        @RequestParam(defaultValue = "null") String  sort_by_year,
                        Model model) throws Exception {

        model.addAttribute("books", bookService.findAll(page, books_per_page, sort_by_year));
        return "/books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id_book,
                       Model model, @ModelAttribute("person") Person person) {

        model.addAttribute("book", bookService.findOne(id_book));
        System.out.println(bookService.findOne(id_book).hasOverdue());
        model.addAttribute("people", personService.findAll());

        return "/books/show";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "/books/new";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "books/new";

        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/update")
    public String pageEdit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.findOne(id));
        return "/books/update";
    }

    // для изменения полей
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "books/update";

        bookService.update(id, book);
        return "redirect:/books";
    }

    // appoint клиента
    @PatchMapping("/appoint/{id}")
    public String appoint(@ModelAttribute("id_person") Integer id_person,
                          @PathVariable Integer id) {

        bookService.editClient(id, id_person);
        return "redirect:/books";
    }

    @PatchMapping("/free/{id}")
    public String free(@PathVariable Integer id) {
        bookService.freeClient(id);
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/pageResearch")
    public String pageResearch(Model model){
        model.addAttribute("book", new Book());
        return "books/research";
    }

    @GetMapping("/foundBook")
    public String foundBook(@RequestParam("name") String name,
                            Model model){
        model.addAttribute("book", bookService.findByNameStartingWith(name));
        return "books/foundBook";
    }
}
