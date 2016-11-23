package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iurii on 25.10.2016.
 */

@Entity
@Table(name = "orders")
@NamedQueries({@NamedQuery(name = "getAllOrders", query = "SELECT c FROM Order c")})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(cascade=CascadeType.ALL )
    @JoinColumn(referencedColumnName = "id")
    private User user;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="ORDER_BOOK",
            joinColumns=
            @JoinColumn(name="ORDER_ID", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="BOOK_ID", referencedColumnName="id"))
    private List<Book> bookList;

    @Column(name = "totalPrice", nullable = true)
    private long totalPrice;

    public Order() {
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public long getTotal() {
        return totalPrice;
    }

    public void setTotal(long total) {
        this.totalPrice = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", user=" + user +
                ", bookList=" + bookList +
                ", total=" + totalPrice +
                '}';
    }
}
