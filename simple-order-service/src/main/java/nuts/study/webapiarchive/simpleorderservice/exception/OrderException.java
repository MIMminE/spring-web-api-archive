package nuts.study.webapiarchive.simpleorderservice.exception;

import lombok.Getter;

@Getter
public class OrderException extends RuntimeException {
    private final String item;

    public OrderException(String message, String item) {
        super(message);
        this.item = item;
    }
}
