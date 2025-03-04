package kitchenpos.core.menus.tobe.domain.exception;

import kitchenpos.core.foundation.exception.SystemException;

public class MenuException extends SystemException {
    public MenuException(String format, Object... args) {
        super(format, args);
    }

    public MenuException(String message, Throwable cause) {
        super(message, cause);
    }
}
