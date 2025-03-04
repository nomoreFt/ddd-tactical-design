package kitchenpos.core.menus.tobe.domain;

import jakarta.persistence.*;
import kitchenpos.core.menus.domain.MenuGroup;
import kitchenpos.core.menus.domain.MenuProduct;
import kitchenpos.core.shared.identifier.MenuId;
import kitchenpos.core.shared.value.Money;

import java.util.List;
import java.util.UUID;

@Table(name = "menu_tobe")
@Entity(name = "MenuTobe")
public class Menu {

    @AttributeOverride(name = "id", column = @Column(name = "id", nullable = false, columnDefinition = "binary(16)"))
    @EmbeddedId
    private MenuId id;

    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "name", nullable = false))
    private MenuName name;

    @Embedded
    @AttributeOverride(name = "price", column = @Column(name = "price", nullable = false, columnDefinition = "decimal(19,2)"))
    private MenuPrice price;

    @ManyToOne(optional = false)
    @JoinColumn(
        name = "menu_group_id",
        columnDefinition = "binary(16)",
        foreignKey = @ForeignKey(name = "fk_menu_to_menu_group")
    )
    private MenuGroup menuGroup;

    @Column(name = "displayed", nullable = false)
    private boolean displayed;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
        name = "menu_id",
        nullable = false,
        columnDefinition = "binary(16)",
        foreignKey = @ForeignKey(name = "fk_menu_product_to_menu")
    )
    private List<MenuProduct> menuProducts;

    @Transient
    private UUID menuGroupId;

    public Menu() {
    }

    public void recalculateDisplayStatus() {
        Money sum = menuProducts.stream()
                .map(MenuProduct::calculatePrice)
                .reduce(Money.ZERO, Money::add);
        // 메뉴 가격이 구성 상품의 총합보다 높으면 표시 안 함
        if (this.price.isBiggerThan(sum)) {
            this.displayed = false;
        }
    }

    public MenuId getId() {
        return id;
    }

    public MenuName getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

}
