package nuts.study.webapiarchive.simpleorderservice.service;

import nuts.study.webapiarchive.simpleorderservice.domain.log.LogService;
import nuts.study.webapiarchive.simpleorderservice.domain.order.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class OrderAppServiceTest {

    private OrderService orderService;
    private LogService logService;
    private OrderAppService appService;

    @BeforeEach
    void setUp() {
        orderService = Mockito.mock(OrderService.class);
        logService = Mockito.mock(LogService.class);
        appService = new OrderAppService(orderService, logService);
    }

    @Test
    void crateOrder_invokesOrderService_and_logs() {
        // TDD: 아직 구현되지 않았지만 호출 계약을 정의
        appService.crateOrder();

        verify(orderService).create(null, 0);
        verify(logService).save(Mockito.any());
    }
}

