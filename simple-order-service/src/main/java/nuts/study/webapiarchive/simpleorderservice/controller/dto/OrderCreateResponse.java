package nuts.study.webapiarchive.simpleorderservice.controller.dto;

import nuts.study.webapiarchive.simpleorderservice.domain.order.ItemList;

public record OrderCreateResponse(
        boolean success,
        ItemList product,
        int quantity
) {
}
