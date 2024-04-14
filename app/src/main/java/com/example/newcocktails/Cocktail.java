package com.example.newcocktails;

import java.util.List;

public class Cocktail {
    private int id;
    private boolean isAlcoholic;
    private String name, imageUrl, category, instructions;

    List<String> ingredients;

    public Cocktail(int id, boolean isAlcoholic, String name, String imageUrl, String category, String instructions, List<String> ingredients) {
        this.id = id;
        this.isAlcoholic = isAlcoholic;
        this.name = name;
        this.imageUrl = imageUrl;
        this.category = category;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAlcoholic() {
        return isAlcoholic;
    }

    public void setAlcoholic(boolean alcoholic) {
        isAlcoholic = alcoholic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
