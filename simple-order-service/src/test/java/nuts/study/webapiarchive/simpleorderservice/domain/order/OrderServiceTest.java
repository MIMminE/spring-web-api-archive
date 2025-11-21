package nuts.study.webapiarchive.simpleorderservice.domain.order;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceTest {

    @Test
    void create_returnsOrder_withGivenItemAndQuantity() {
        OrderService service = new OrderService();

        Order order = service.create(ItemList.ITEM_A, 3);

        assertThat(order).isNotNull();
        assertThat(order.product()).isEqualTo(ItemList.ITEM_A);
        assertThat(order.quantity()).isEqualTo(3);
        // id는 아직 영속화되지 않았으므로 null 기대
        assertThat(order.id()).isNull();
    }
}

