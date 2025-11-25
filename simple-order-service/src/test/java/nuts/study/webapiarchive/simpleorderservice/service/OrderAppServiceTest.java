package nuts.study.webapiarchive.simpleorderservice.service;

import nuts.study.webapiarchive.simpleorderservice.controller.dto.OrderCreateRequest;
import nuts.study.webapiarchive.simpleorderservice.domain.log.Log;
import nuts.study.webapiarchive.simpleorderservice.domain.log.LogRepository;
import nuts.study.webapiarchive.simpleorderservice.domain.order.ItemList;
import nuts.study.webapiarchive.simpleorderservice.domain.order.Order;
import nuts.study.webapiarchive.simpleorderservice.domain.order.OrderRepository;
import nuts.study.webapiarchive.simpleorderservice.domain.order.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderAppServiceTest {

    @Autowired
    private OrderAppService appService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private LogRepository logRepository;

    @MockitoSpyBean
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        logRepository.deleteAll();
        Mockito.reset(orderService);
    }

    @Test
    @DisplayName("주문 생성 성공 시 주문과 로그가 저장된다")
    void createPersistsOrderAndLog() {
        OrderCreateRequest request = new OrderCreateRequest(ItemList.ITEM_A, 2);

        appService.crateOrder(request);

        List<Order> orders = orderRepository.findAll();
        List<Log> logs = logRepository.findAll();

        assertThat(orders).hasSize(1);
        assertThat(orders.get(0).getProduct()).isEqualTo(ItemList.ITEM_A);
        assertThat(orders.get(0).getQuantity()).isEqualTo(2);

        assertThat(logs).hasSize(1);
        assertThat(logs.get(0).getMessage()).isEqualTo("ITEM_A : 2");
    }

    @Test
    @DisplayName("주문 생성 실패 시에도 로그가 기록된다")
    void createFailureStillLogs() {
        Mockito.doThrow(new RuntimeException("simulated"))
                .when(orderService)
                .create(Mockito.any(), Mockito.anyInt());

        OrderCreateRequest request = new OrderCreateRequest(ItemList.ITEM_B, 5);

        appService.crateOrder(request);

        List<Order> orders = orderRepository.findAll();
        List<Log> logs = logRepository.findAll();

        assertThat(orders).isEmpty();
        assertThat(logs).hasSize(1);
        assertThat(logs.get(0).getMessage()).startsWith("주문 생성 실패");
    }
}