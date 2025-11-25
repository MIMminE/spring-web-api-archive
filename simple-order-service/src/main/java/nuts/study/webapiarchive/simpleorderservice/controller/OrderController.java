package nuts.study.webapiarchive.simpleorderservice.controller;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderCreateRequest request) {
        ItemList item = request.product();
        if (item == null) {
            return ResponseEntity.badRequest().build();
        }
        Order order = orderService.create(item, request.quantity());
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}