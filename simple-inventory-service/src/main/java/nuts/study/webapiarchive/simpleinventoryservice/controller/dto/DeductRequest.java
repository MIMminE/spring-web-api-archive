package nuts.study.webapiarchive.simpleinventoryservice.controller.dto;

public record DeductRequest(
        String product,
        int quantity
) {
}
