//package app.unit;
//
//import app.models.Person;
//import app.services.PersonService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//@Component
//public class PersonValidator implements Validator {
//
//    private final PersonService personService;
//
//    @Autowired
//    public PersonValidator(PersonService personService) {
//        this.personService = personService;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return Person.class.equals(aClass);
//    }
//
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        Person person = (Person) target;
//
//        if (personService.findOne(person.getId_person())) {
//            // поле, код ошибки, сообщение ошибки
//            errors.rejectValue("email", "", "This email is already in use");
//        }
//
//        // Проверяем, что у человека имя начинается с заглавной буквы
//        // Если имя не начинается с заглавной буквы - выдаем ошибку
//        if (!Character.isUpperCase(person.getName().codePointAt(0)))
//            errors.rejectValue("name", "", "Name should start with a capital letter");
//    }
//}