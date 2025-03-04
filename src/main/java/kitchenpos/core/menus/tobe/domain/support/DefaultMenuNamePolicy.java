package kitchenpos.core.menus.tobe.domain.support;

import kitchenpos.core.menus.tobe.domain.MenuNamePolicy;
import kitchenpos.core.shared.domain.ProfanityChecker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultMenuNamePolicy implements MenuNamePolicy {

    private final ProfanityChecker profanityChecker;

    public DefaultMenuNamePolicy(ProfanityChecker profanityChecker) {
        this.profanityChecker = profanityChecker;
    }

    @Override
    public MenuNameValidationResult validate(String name) {
        List<String> errors = new ArrayList<>();
        if (name == null || name.isBlank()) {
            errors.add("Menu name은 null 이거나 빈 값이 될 수 없습니다.");
        }
        if (name != null && profanityChecker.containsProfanity(name)) {
            errors.add("Menu name에 비속어가 포함될 수 없습니다.");
        }
        return new MenuNameValidationResult(errors.isEmpty(), errors);
    }
}
