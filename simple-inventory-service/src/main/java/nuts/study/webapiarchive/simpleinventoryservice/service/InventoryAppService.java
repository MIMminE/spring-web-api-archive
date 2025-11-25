package nuts.study.webapiarchive.simpleinventoryservice.service;


import lombok.RequiredArgsConstructor;
import nuts.study.webapiarchive.simpleinventoryservice.domain.inventory.InventoryService;
import nuts.study.webapiarchive.simpleinventoryservice.domain.inventory.ItemList;
import nuts.study.webapiarchive.simpleinventoryservice.domain.log.LogService;
import nuts.study.webapiarchive.simpleinventoryservice.exception.DeductException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryAppService {

    private final InventoryService inventoryService;
    private final LogService logService;

    public void deductInventory(ItemList item, int quantity) {
        try {
            inventoryService.deductInventory(item, quantity);
            logService.save("Successfully deducted " + quantity + " of item: " + item);
        } catch (Exception e) {
            logService.failingSave("Failed to deduct inventory for item: " + item + " with quantity: " + quantity + ". Error: " + e.getMessage());
            throw new DeductException(e, inventoryService.getStock(item));
        }
    }
}