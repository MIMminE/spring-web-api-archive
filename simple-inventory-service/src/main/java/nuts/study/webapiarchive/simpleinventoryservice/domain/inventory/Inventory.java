package nuts.study.webapiarchive.simpleinventoryservice.domain.inventory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ItemList item;
    private Integer stock;
    private Double price;

    public static Inventory of(ItemList item, Integer stock, Double price) {
        Inventory inventory = new Inventory();
        inventory.item = item;
        inventory.stock = stock;
        inventory.price = price;
        return inventory;
    }

    public void decreaseStock(int quantity) {
        this.stock -= quantity;
    }
}