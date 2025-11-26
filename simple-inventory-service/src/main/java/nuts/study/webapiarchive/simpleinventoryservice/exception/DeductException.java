package nuts.study.webapiarchive.simpleinventoryservice.exception;

import lombok.Getter;

@Getter
public class DeductException extends RuntimeException {

    private final int availableStock;

    public DeductException(Throwable cause, int availableStock) {
        super(cause);
        this.availableStock = availableStock;
    }
}
