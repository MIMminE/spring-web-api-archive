package nuts.study.webapiarchive.simpleorderservice.controller;

import nuts.study.webapiarchive.simpleorderservice.controller.dto.OrderCreateRequest;
import nuts.study.webapiarchive.simpleorderservice.domain.order.ItemList;
import nuts.study.webapiarchive.simpleorderservice.domain.order.Order;
import nuts.study.webapiarchive.simpleorderservice.domain.order.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService = new OrderService();

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderCreateRequest request) {
        // 요청에서 ItemList가 null일 수 있음 — 간단 검증
        ItemList item = request.getProduct();
        if (item == null) {
            return ResponseEntity.badRequest().build();
        }
        Order order = orderService.create(item, request.getQuantity());
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
