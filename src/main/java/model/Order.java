package model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iurii on 25.10.2016.
 */

@Entity
@Table(name = "orders")
public class Order {
    private long id;
    private OrderStatus status;
    private User user;
    private List<Book> bookList = new ArrayList<>();
    private long total;

    public Order(User user) {
        this.user = user;

    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", user=" + user +
                ", bookList=" + bookList +
                ", total=" + total +
                '}';
    }
}
