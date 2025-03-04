package kitchenpos.core.menus.tobe.domain.support;

import java.util.List;

public record MenuNameValidationResult(boolean valid, List<String> errorMessages) {
}
