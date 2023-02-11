package app.models;


//import jakarta.validation.constraints.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
//import javax.validation.constraints.*;


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
    private String name;

    @Max(value = 2023, message = "Некорректный год")
    @Min(value = 0, message = "Некорректный год")
    @Column(name = "year")
    private int year;

    @Pattern(regexp = "^([А-Я][а-я]+) ([А-Я][а-я]+)$", message = "РеГуЛяРоЧкА")
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 50, message = "Слишком короткое или длинное ФИО")
    @Column(name = "author")
    private String author;

    @Column(name = "date_issue")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateIssue;


    public boolean hasOverdue(){
        if (dateIssue == null)
            return false;
        return Math.round((new Date().getTime() - dateIssue.getTime()) / (1000.0 * 60 * 60 * 24)) > 10;
    }

    public Book(Person owner, String name, int year, String author) {
        this.owner = owner;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book() {
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(Date dateIssue) {
        this.dateIssue = dateIssue;
    }
}