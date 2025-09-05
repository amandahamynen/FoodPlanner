package com.example.foodplanner_backend;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food-items")
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
