package com.example.foodplanner.controller;

import com.example.foodplanner.model.Recipe;
import com.example.foodplanner.model.RecipeIngredients;
import com.example.foodplanner.model.FoodItem;
import com.example.foodplanner.repository.RecipeRepository;
import com.example.foodplanner.repository.FoodItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@CrossOrigin(origins = "*")
public class RecipeController {

    private final RecipeRepository repo;
    private final FoodItemRepository foodItemRepo;

    public RecipeController(RecipeRepository repo, FoodItemRepository foodItemRepo) {
        this.repo = repo;
        this.foodItemRepo = foodItemRepo;
    }

    @GetMapping
    public List<Recipe> getAll() {
        return repo.findAll();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        for (RecipeIngredients ingredient : recipe.getIngredients()) {
            ingredient.setRecipe(recipe);
            Long foodItemId = ingredient.getFoodItem().getId();
            FoodItem fullFoodItem = foodItemRepo.findById(foodItemId).orElse(null);
            ingredient.setFoodItem(fullFoodItem);
        }
        return repo.save(recipe);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        repo.deleteById(id);
    }
}