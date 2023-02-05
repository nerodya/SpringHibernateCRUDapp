package app.models;




import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "id_book")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_book;

    @ManyToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id_person")
    private Person owner;

    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 50, message = "Слишком короткое или длинное название")
    @Column(name = "name_book")
    private String name_book;

    @Max(value = 2023, message = "Некорректный год")
    @Min(value = 0, message = "Некорректный год")
    @Column(name = "year")
    private int year;

    @Pattern(regexp = "^([А-Я][а-я]+) ([А-Я][а-я]+)$", message = "РеГуЛяРоЧкА")
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 50, message = "Слишком короткое или длинное ФИО")
    @Column(name = "author")
    private String author;

    public Book(Person owner, String name_book,  int year, String author) {
        this.owner = owner;
        this.name_book = name_book;
        this.author = author;
        this.year = year;
    }

    public Book(){};

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName_book() {
        return name_book;
    }

    public void setName_book(String name_book) {
        this.name_book = name_book;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
