package kitchenpos.core.menus.tobe.domain;

import kitchenpos.core.menus.tobe.domain.support.MenuNameValidationResult;

public interface MenuNamePolicy {
    MenuNameValidationResult validate(String name);
}
