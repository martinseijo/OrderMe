package orderme.service.enums;
public enum OrderStatusEnum {
    PENDING(1),
    SERVED(2),
    CANCELED(3),
    PAID(4);

    private final int id;

    OrderStatusEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name();
    }
}
