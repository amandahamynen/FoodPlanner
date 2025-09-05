package com.example.foodplanner_backend;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String instructions;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<RecipeIngredients> ingredients = new HashSet<>();

    public Recipe() {}

    public Recipe(String name, String instructions) {
        this.name = name;
        this.instructions = instructions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Set<RecipeIngredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<RecipeIngredients> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(RecipeIngredients ingredient) {
        ingredient.setRecipe(this);
        ingredients.add(ingredient);
    }

    public void removeIngredient(RecipeIngredients ingredient) {
        ingredients.remove(ingredient);
        ingredient.setRecipe(null);
    }

}
