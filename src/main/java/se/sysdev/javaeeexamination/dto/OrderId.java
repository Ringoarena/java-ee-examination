package se.sysdev.javaeeexamination.dto;

public class OrderId {
    private Long orderId;

    public OrderId() {
    }

    public OrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
