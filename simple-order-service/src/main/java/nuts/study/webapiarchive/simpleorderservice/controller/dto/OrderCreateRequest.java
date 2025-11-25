package nuts.study.webapiarchive.simpleorderservice.controller.dto;

import nuts.study.webapiarchive.simpleorderservice.domain.order.ItemList;

public record OrderCreateRequest(
        ItemList product,
        int quantity
) {
}