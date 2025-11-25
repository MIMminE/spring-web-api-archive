package nuts.study.webapiarchive.simpleorderservice.domain.order;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "orders")
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ItemList product;

    private int quantity;

    public static Order of(ItemList product, int quantity) {
        Order order = new Order();
        order.product = product;
        order.quantity = quantity;
        return order;
    }
}