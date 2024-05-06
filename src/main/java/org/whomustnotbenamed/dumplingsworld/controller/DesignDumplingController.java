package org.whomustnotbenamed.dumplingsworld.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.whomustnotbenamed.dumplingsworld.model.Dumpling;
import org.whomustnotbenamed.dumplingsworld.model.DumplingOrder;
import org.whomustnotbenamed.dumplingsworld.model.Ingredient;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("dumplingOrder")
public class DesignDumplingController {
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = List.of(
                new Ingredient("DWP", "Dumplings with pork", Ingredient.Type.MINCED_PORK),
                new Ingredient("DWB", "Dumplings with beef", Ingredient.Type.MINCED_VEAL),
                new Ingredient("DWPB", "Dumplings with pork and beef", Ingredient.Type.MINCED_MIX),
                new Ingredient("BR", "Butter", Ingredient.Type.BUTTER),
                new Ingredient("SC", "Sour cream", Ingredient.Type.SOUR_CREAM)
        );

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "dumplingOrder")
    public DumplingOrder order() {
        return new DumplingOrder();
    }

    @ModelAttribute(name = "dumpling")
    public Dumpling dumpling() {
        return new Dumpling();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
