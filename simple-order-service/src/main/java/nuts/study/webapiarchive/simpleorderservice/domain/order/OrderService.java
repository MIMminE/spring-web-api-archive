package nuts.study.webapiarchive.simpleorderservice.domain.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order create(ItemList product, int quantity) {
        Order order = Order.of(product, quantity);
        orderRepository.save(order);
        return order;
    }
}