package nuts.study.webapiarchive.simpleorderservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nuts.study.webapiarchive.simpleorderservice.controller.dto.OrderCreateRequest;
import nuts.study.webapiarchive.simpleorderservice.controller.dto.OrderCreateResponse;
import nuts.study.webapiarchive.simpleorderservice.domain.log.LogService;
import nuts.study.webapiarchive.simpleorderservice.domain.order.ItemList;
import nuts.study.webapiarchive.simpleorderservice.domain.order.Order;
import nuts.study.webapiarchive.simpleorderservice.domain.order.OrderService;
import nuts.study.webapiarchive.simpleorderservice.exception.OrderException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderAppService {

    private final OrderService orderService;
    private final LogService logService;

    @Transactional
    public OrderCreateResponse crateOrder(OrderCreateRequest orderCreateRequest) {
        ItemList product = orderCreateRequest.product();
        int quantity = orderCreateRequest.quantity();

        try {
            Order order = orderService.create(product, quantity);
            logService.save(order.getProduct() + " : " + order.getQuantity());
            return new OrderCreateResponse(true, order.getProduct(), order.getQuantity());
        } catch (Exception e) {
            logService.failingSave("주문 생성 실패: " + e.getMessage());
            throw new OrderException("주문 생성에 실패했습니다.", product.name());
        }
    }
}