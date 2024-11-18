package orderme.service.enums;
public enum OrderStatusEnum {
    PENDING(1),
    IN_PROGRESS(2),
    SERVED(3),
    CANCELED(4);

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
