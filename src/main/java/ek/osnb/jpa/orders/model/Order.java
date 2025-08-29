package ek.osnb.jpa.orders.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ek.osnb.jpa.common.model.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
public class Order extends BaseEntity {

    private LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @JsonManagedReference
    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLineList = new ArrayList<>();

    public Order() {}

    public Order(LocalDate orderDate, OrderStatus status) {
        this.orderDate = orderDate;
        this.status = status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderLine> getOrderLineList() {
        return orderLineList;
    }

    public void addOrderLine(OrderLine orderLine) {
        orderLineList.add(orderLine);
        orderLine.setOrder(this);
    }

    public void removeOrderLine(OrderLine orderLine) {
        orderLineList.remove(orderLine);
        orderLine.setOrder(null);
    }

    public void clearOrderLines() {
        for (OrderLine orderLine : new ArrayList<>(orderLineList)) {
            removeOrderLine(orderLine);
        }
    }
}
