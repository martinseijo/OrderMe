package orderme.service.enums;

public enum RoleEnum {
    ROLE_USER(1),
    ROLE_ADMIN(2);

    private final int id;

    RoleEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name();
    }
}
