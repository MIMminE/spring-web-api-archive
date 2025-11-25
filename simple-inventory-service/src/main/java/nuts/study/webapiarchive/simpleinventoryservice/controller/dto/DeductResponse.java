package nuts.study.webapiarchive.simpleinventoryservice.controller.dto;

public record DeductResponse(
        boolean success,
        int requestItem,
        int requestQuantity,

) {
}
