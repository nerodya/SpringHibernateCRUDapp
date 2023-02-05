package app.controller;


import app.dao.BookDAO;
import app.dao.PersonDAO;
import app.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    @Autowired
    public PersonController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id_person) {
        model.addAttribute("person", personDAO.show(id_person));
        model.addAttribute("books", bookDAO.hasPerson(id_person));

        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.createPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/update")
    public String editPage(@PathVariable("id") int id,
                           Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/update";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }


    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("person") @Valid Person person,
                       BindingResult bindingResult,
                       @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "people/update";

        personDAO.update(person, id);
        return "redirect:/people";
    }
}
