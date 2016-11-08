package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Iurii on 25.10.2016.
 */
@Entity
@Table(name = "book")
@NamedQueries({@NamedQuery(name = "findBook", query = "SELECT c FROM Book c where c.author=:author and c.title=:title"),
        @NamedQuery(name = "getAllBooks", query = "SELECT c FROM Book c")})

public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "count", nullable = false)
    private int count;

    @ManyToMany(mappedBy="bookList", cascade=CascadeType.ALL)
    private List<Order> orders;

    public Book() {
    }

    public Book(String title, String author, int price, int count) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.count = count;

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (!title.equals(book.title)) return false;
        return author.equals(book.author);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + author.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", count='" + count + '\'' +
                ", price=" + price +
                '}';
    }
}
