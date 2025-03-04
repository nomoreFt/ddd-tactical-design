package kitchenpos.core.menus.tobe.domain;

import jakarta.persistence.Embeddable;
import kitchenpos.core.shared.domain.ValueObject;
import kitchenpos.core.shared.value.Money;

@Embeddable
public class MenuPrice extends ValueObject<MenuPrice> {

    private Money price;

    @SuppressWarnings("unused")
    protected MenuPrice() {}
}
