package nuts.study.webapiarchive.simpleinventoryservice.controller;

import lombok.RequiredArgsConstructor;
import nuts.study.webapiarchive.simpleinventoryservice.service.InventoryAppService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryAppService inventoryAppService;



}
