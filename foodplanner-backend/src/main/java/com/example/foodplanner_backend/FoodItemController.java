package com.example.foodplanner.controller;

import com.example.foodplanner.model.FoodItem;
import com.example.foodplanner.repository.FoodItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@CrossOrigin(origins = "*")
public class FoodItemController {

    private final FoodItemRepository repo;

    public FoodItemController(FoodItemRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<FoodItem> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public FoodItem addItem(@RequestBody FoodItem item) {
        return repo.save(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
