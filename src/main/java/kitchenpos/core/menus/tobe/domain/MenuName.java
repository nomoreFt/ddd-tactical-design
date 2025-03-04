package kitchenpos.core.menus.tobe.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import kitchenpos.core.menus.tobe.domain.exception.InvalidMenuNameException;
import kitchenpos.core.menus.tobe.domain.support.MenuNameValidationResult;
import kitchenpos.core.shared.domain.ValueObject;
import org.jetbrains.annotations.NotNull;

@Embeddable
public class MenuName extends ValueObject<MenuName> {
    private String name;

    protected MenuName() {
    }

    private MenuName(String name) {
        this.name = name;
    }

    public static MenuName create(@NotNull MenuNamePolicy policy, String name) {
        MenuNameValidationResult result = policy.validate(name);
        if (!result.valid()) {
            throw new InvalidMenuNameException((String.join("; ", result.errorMessages())));
        }
        return new MenuName(name.strip());
    }

    public String getName() {
        return name;
    }

    @Override
    @Transient
    protected Object[] getEqualityFields() {
        return new Object[]{name};
    }
}
