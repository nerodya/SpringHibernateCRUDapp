package app.controller;


import app.dao.BookDAO;
import app.dao.PersonDAO;
import app.models.Book;
import app.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "/books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id_book,
                       Model model, @ModelAttribute("person") Person person) {

        model.addAttribute("book", bookDAO.show(id_book));
        model.addAttribute("people", personDAO.index());

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

        bookDAO.createBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/update")
    public String pageEdit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "/books/update";
    }

    // для изменения полей
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "books/update";

        bookDAO.edit(book, id);
        return "redirect:/books";
    }

    // appoint клиента
    @PatchMapping("/appoint/{id}")
    public String appoint(@ModelAttribute("id_person") Integer id_person,
                          @PathVariable Integer id) {

        System.out.println(id + " " + id_person);
        bookDAO.editClient(id, id_person);
        return "redirect:/books";
    }

    @PatchMapping("/free/{id}")
    public String free(@PathVariable Integer id) {

        System.out.println(id);
        bookDAO.freeClient(id);
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
