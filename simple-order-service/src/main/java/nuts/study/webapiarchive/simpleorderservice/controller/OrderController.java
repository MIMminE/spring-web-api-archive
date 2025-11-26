package nuts.study.webapiarchive.simpleorderservice.controller;

import lombok.RequiredArgsConstructor;
import nuts.study.webapiarchive.simpleorderservice.controller.dto.OrderCreateRequest;
import nuts.study.webapiarchive.simpleorderservice.controller.dto.OrderCreateResponse;
import nuts.study.webapiarchive.simpleorderservice.domain.order.ItemList;
import nuts.study.webapiarchive.simpleorderservice.domain.order.Order;
import nuts.study.webapiarchive.simpleorderservice.domain.order.OrderService;
import nuts.study.webapiarchive.simpleorderservice.exception.OrderException;
import nuts.study.webapiarchive.simpleorderservice.service.OrderAppService;
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

    private final OrderAppService orderAppService;

    @PostMapping
    public ResponseEntity<OrderCreateResponse> create(@RequestBody OrderCreateRequest request) {
        ItemList item = request.product();
        if (item == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            OrderCreateResponse orderCreateResponse = orderAppService.crateOrder(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(orderCreateResponse);
        } catch (OrderException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}