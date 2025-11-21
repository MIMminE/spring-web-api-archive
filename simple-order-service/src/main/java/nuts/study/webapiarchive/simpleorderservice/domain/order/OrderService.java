package nuts.study.webapiarchive.simpleorderservice.domain.order;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public Order create(ItemList product, int quantity) {
        // TODO: 구현 예정 — 현재는 단순히 엔티티 생성 반환
        Order order = Order.of(product, quantity);
        return order;
    }
}
