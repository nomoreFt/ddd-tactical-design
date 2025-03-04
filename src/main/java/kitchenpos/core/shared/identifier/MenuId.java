package kitchenpos.core.shared.identifier;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import kitchenpos.core.shared.domain.ValueObject;

import java.util.UUID;

@Embeddable
public class MenuId extends ValueObject<MenuId> {
    private UUID id;

    @SuppressWarnings("unused")
    protected MenuId() {
    }

    private MenuId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Menu ID 는 null 이거나 빈 값이 될 수 없습니다.");
        }
        this.id = id;
    }

    public static MenuId of(UUID value) {
        return new MenuId(value);
    }

    public UUID getId() {
        return id;
    }

    @Override
    @Transient
    protected Object[] getEqualityFields() {
        return new Object[] {id};
    }
}
