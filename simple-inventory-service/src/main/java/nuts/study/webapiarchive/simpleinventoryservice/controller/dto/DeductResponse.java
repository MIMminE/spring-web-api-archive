package nuts.study.webapiarchive.simpleinventoryservice.controller.dto;

public record DeductResponse(
        boolean success,
        String requestItem,
        int requestQuantity,
        int itemPrice
) {
}