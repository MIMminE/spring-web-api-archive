package nuts.study.webapiarchive.simpleinventoryservice.domain.inventory;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional
    public Inventory deductInventory(ItemList item, int quantity) {
        Inventory inventory = inventoryRepository.findByItem(item)
                .orElseThrow(() -> new IllegalArgumentException("Item not found in inventory: " + item));

        if (inventory.getStock() < quantity) {
            throw new IllegalArgumentException("Insufficient stock for item: " + item);
        }

        inventory.decreaseStock(quantity);
        return inventory;
    }

    public int getStock(ItemList item) {
        Inventory inventory = inventoryRepository.findByItem(item)
                .orElseThrow(() -> new IllegalArgumentException("Item not found in inventory: " + item));
        return inventory.getStock();
    }
}
