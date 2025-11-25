package nuts.study.webapiarchive.simpleorderservice.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    @DisplayName("주어진 상품과 수량으로 주문을 생성한다")
    void createReturnsOrder() {
        Order order = orderService.create(ItemList.ITEM_A, 3);

        assertThat(order).isNotNull();
        assertThat(order.getProduct()).isEqualTo(ItemList.ITEM_A);
        assertThat(order.getQuantity()).isEqualTo(3);
        // id는 아직 영속화되지 않았으므로 null 기대
        assertThat(order.getId()).isEqualTo(1L);
    }
}