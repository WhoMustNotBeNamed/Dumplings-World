package org.whomustnotbenamed.dumplingsworld.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.whomustnotbenamed.dumplingsworld.model.Ingredient;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    public IngredientByIdConverter() {
        ingredientMap.put("DWP", new Ingredient("DWP", "Dumplings with pork", Ingredient.Type.MINCED));
        ingredientMap.put("DWB", new Ingredient("DWB", "Dumplings with beef", Ingredient.Type.MINCED));
        ingredientMap.put("DWPB", new Ingredient("DWPB", "Dumplings with pork and beef", Ingredient.Type.MINCED));
        ingredientMap.put("PR", new Ingredient("PR", "Pepper", Ingredient.Type.SPICE));
        ingredientMap.put("GO", new Ingredient("GO", "Green onions", Ingredient.Type.SPICE));
        ingredientMap.put("BR", new Ingredient("BR", "Butter", Ingredient.Type.ADDITION));
        ingredientMap.put("SC", new Ingredient("SC", "Sour cream", Ingredient.Type.ADDITION));
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }
}
