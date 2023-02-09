//package app.dao;
//
//import app.models.Book;
//import app.models.Person;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Component
//public class BookDAO {
//
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public BookDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional(readOnly = true)
//    public List<Book> index() {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("select b from Book b", Book.class)
//                .getResultList();
//    }
//
//    @Transactional
//    public void createBook(Book book) {
//        Session session = sessionFactory.getCurrentSession();
//        session.persist(book);
//    }
//
//    @Transactional(readOnly = true)
//    public Book show(int id_book) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(Book.class, id_book);
//    }
//
//    @Transactional
//    public void edit(Book book, int id) {
//        Session session = sessionFactory.getCurrentSession();
//        Book bookToBeEdit = session.get(Book.class, id);
//
//        bookToBeEdit.setOwner(book.getOwner());
//        bookToBeEdit.setName_book(book.getName_book());
//        bookToBeEdit.setAuthor(book.getAuthor());
//        bookToBeEdit.setYear(book.getYear());
//    }
//
//    @Transactional
//    public void editClient(int id_book, int id_person) {
//        Session session = sessionFactory.getCurrentSession();
//        Book book = session.get(Book.class, id_book);
//        Person person = session.get(Person.class, id_person);
//
//        book.setOwner(person);
//    }
//
//    @Transactional
//    public void freeClient(Integer id) {
//        Session session = sessionFactory.getCurrentSession();
//        Book book = session.get(Book.class, id);
//        book.setOwner(null);
//    }
//
////    @Transactional
////    public List<Book> hasPerson(int id_person) {
////        Session session = sessionFactory.getCurrentSession();
////
////        return session.get(Person.class, id_person).getBooks();
////    }
//
//    @Transactional
//    public void delete(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        session.remove(session.get(Book.class, id));
//    }
//}
