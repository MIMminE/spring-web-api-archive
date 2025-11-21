package nuts.study.webapiarchive.simpleorderservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nuts.study.webapiarchive.simpleorderservice.domain.log.LogService;
import nuts.study.webapiarchive.simpleorderservice.domain.order.OrderService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderAppService {

    private final OrderService orderService;
    private final LogService logService;

    @Transactional
    public void crateOrder(){

    }

}