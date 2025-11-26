package nuts.study.webapiarchive.simpleinventoryservice.domain.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryService inventoryService;

    @Test
    void deductInventory() {

        when(inventoryRepository.findByItem(ItemList.ITEM_A))
                .thenReturn(Optional.of(Inventory.of(ItemList.ITEM_A, 20, 2000)));

        Inventory inventory = inventoryService.deductInventory(ItemList.ITEM_A, 8);

        // then assertions
        assertEquals(12, inventory.getStock());
    }

    // 재고가 부족한 경우 예외가 발생하는지 테스트
    @Test
    void deductInventory_InsufficientStock() {
        when(inventoryRepository.findByItem(ItemList.ITEM_B))
                .thenReturn(Optional.of(Inventory.of(ItemList.ITEM_B, 5, 500)));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            inventoryService.deductInventory(ItemList.ITEM_B, 10);
        });

        assertEquals("Insufficient stock for item: ITEM_B", exception.getMessage());
    }
}