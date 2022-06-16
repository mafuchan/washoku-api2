package com.mcrecraft.recipedb.recipes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    public Iterable<Recipe> list() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> findById(Long id) {
        return recipeRepository.findById(id);
    }

    public Recipe create(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Optional<Recipe> update(Recipe recipe) {
        Optional<Recipe> foundRecipe = recipeRepository.findById(recipe.getId());

        if (foundRecipe.isPresent()) {
            Recipe updatedRecipe = foundRecipe.get();
            updatedRecipe.setName(recipe.getName());
            updatedRecipe.setIngredients(recipe.getIngredients());
            updatedRecipe.setDirections(recipe.getDirections());

            recipeRepository.save(updatedRecipe);
            return Optional.of(updatedRecipe);
        } else {
            return Optional.empty();
        }
    }

    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}