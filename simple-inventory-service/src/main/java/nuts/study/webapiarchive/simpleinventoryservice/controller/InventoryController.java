package nuts.study.webapiarchive.simpleinventoryservice.controller;

import lombok.RequiredArgsConstructor;
import nuts.study.webapiarchive.simpleinventoryservice.controller.dto.DeductRequest;
import nuts.study.webapiarchive.simpleinventoryservice.controller.dto.DeductResponse;
import nuts.study.webapiarchive.simpleinventoryservice.domain.inventory.ItemList;
import nuts.study.webapiarchive.simpleinventoryservice.exception.DeductException;
import nuts.study.webapiarchive.simpleinventoryservice.service.InventoryAppService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryAppService inventoryAppService;

    @PostMapping
    public ResponseEntity<DeductResponse> deduct(@RequestBody DeductRequest request) {
        try {
            return ResponseEntity.ok(inventoryAppService.deductInventory(ItemList.valueOf(request.product()), request.quantity()));
        } catch (DeductException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}