package se.sysdev.javaeeexamination.formdata;

public class OrderIdFormData {
    private Long orderId;

    public OrderIdFormData() {
    }

    public OrderIdFormData(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
