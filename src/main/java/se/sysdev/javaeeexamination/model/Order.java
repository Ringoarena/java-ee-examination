package se.sysdev.javaeeexamination.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<OrderLine> orderLines;
    @ManyToOne
    private User user;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address address;
    private boolean processed;

    public Order() {
    }

    public Order(List<OrderLine> orderLines, User user, Address address, boolean processed) {
        this.orderLines = orderLines;
        this.user = user;
        this.address = address;
        this.processed = processed;
    }

    public double getOrderTotal() {
        double orderTotal = 0;
        for (OrderLine orderLine : orderLines) {
            orderTotal += orderLine.getOrderLineTotal();
        }
        return orderTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public User getCustomer() {
        return user;
    }

    public void setCustomer(User user) {
        this.user = user;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}
