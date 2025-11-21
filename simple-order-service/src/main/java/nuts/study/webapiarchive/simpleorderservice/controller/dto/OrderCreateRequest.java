package nuts.study.webapiarchive.simpleorderservice.controller.dto;

import nuts.study.webapiarchive.simpleorderservice.domain.order.ItemList;

public class OrderCreateRequest {
    private ItemList product;
    private int quantity;

    public OrderCreateRequest() {
    }

    public OrderCreateRequest(ItemList product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ItemList getProduct() {
        return product;
    }

    public void setProduct(ItemList product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

