package nuts.study.webapiarchive.simpleorderservice.domain.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public record Order(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
        ItemList product,
        int quantity
) {
    // 편의 보조 생성자: id를 생략하고 생성할 수 있게 함
    public Order(ItemList product, int quantity) {
        this(null, product, quantity);
    }

    // 편의 팩토리 메서드
    public static Order of(ItemList product, int quantity) {
        return new Order(product, quantity);
    }
}