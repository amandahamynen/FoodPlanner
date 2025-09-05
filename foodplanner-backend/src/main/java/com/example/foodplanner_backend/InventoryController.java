package com.example.foodplanner_backend;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {

    private final InventoryRepository inventoryRepo;

    public InventoryController(InventoryRepository inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    @GetMapping
    public List<Inventory> getAll() {
        return inventoryRepo.findAll();
    }

    @PostMapping
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return inventoryRepo.save(inventory);
    }

    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable Long id) {
        inventoryRepo.deleteById(id);
    }
}