package ru.testfield.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.testfield.ansible.service.InventoryService;

import java.util.Map;

@RestController
public class InventoryController extends AbstractWebController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @RequestMapping("/inventory/list")
    public Map<String,Object> getInventory(){
        return inventoryService.getInventory();
    }
}
