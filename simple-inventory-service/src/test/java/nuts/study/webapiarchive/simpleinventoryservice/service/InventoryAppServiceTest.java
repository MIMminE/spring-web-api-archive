package nuts.study.webapiarchive.simpleinventoryservice.service;

import nuts.study.webapiarchive.simpleinventoryservice.controller.dto.DeductResponse;
import nuts.study.webapiarchive.simpleinventoryservice.domain.inventory.Inventory;
import nuts.study.webapiarchive.simpleinventoryservice.domain.inventory.InventoryService;
import nuts.study.webapiarchive.simpleinventoryservice.domain.inventory.ItemList;
import nuts.study.webapiarchive.simpleinventoryservice.domain.log.LogService;
import nuts.study.webapiarchive.simpleinventoryservice.exception.DeductException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryAppServiceTest {

    @InjectMocks
    private InventoryAppService inventoryAppService;

    @Mock
    private InventoryService inventoryService;

    @Mock
    private LogService logService;

    @Test
    void deductInventory_Success_ReturnsDeductResponseAndLogsSave() {
        // Arrange
        ItemList item = ItemList.ITEM_A;
        int quantity = 3;
        int initialStock = 10;
        int price = 5000;
        Inventory inventory = Inventory.of(item, initialStock - quantity, price);

        when(inventoryService.deductInventory(item, quantity)).thenReturn(inventory);

        // Act
        DeductResponse response = inventoryAppService.deductInventory(item, quantity);

        // Assert
        assertNotNull(response);
        assertTrue(response.success());
        assertEquals(item.name(), response.requestItem());
        assertEquals(quantity, response.requestQuantity());
        assertEquals(price, response.itemPrice());

        verify(logService).save(anyString());
        verify(inventoryService).deductInventory(item, quantity);
    }

    @Test
    void deductInventory_WhenInventoryServiceThrows_LogsFailingSaveAndThrowsDeductException() {
        // Arrange
        ItemList item = ItemList.ITEM_B;
        int quantity = 5;
        int availableStock = 2;

        when(inventoryService.deductInventory(item, quantity)).thenThrow(new IllegalArgumentException("Insufficient stock"));
        when(inventoryService.getStock(item)).thenReturn(availableStock);

        // Act & Assert
        DeductException ex = assertThrows(DeductException.class, () -> inventoryAppService.deductInventory(item, quantity));

        // DeductException should wrap cause and carry available stock
        assertEquals(availableStock, ex.getAvailableStock());

        verify(logService).failingSave(anyString());
        verify(inventoryService).getStock(item);
    }
}