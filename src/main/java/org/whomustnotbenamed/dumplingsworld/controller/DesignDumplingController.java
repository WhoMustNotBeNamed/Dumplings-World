package org.whomustnotbenamed.dumplingsworld.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.whomustnotbenamed.dumplingsworld.model.Dumpling;
import org.whomustnotbenamed.dumplingsworld.model.DumplingOrder;
import org.whomustnotbenamed.dumplingsworld.model.Ingredient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("dumplingOrder")
public class DesignDumplingController {
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("DWP", "Dumplings with pork", Ingredient.Type.MINCED),
                new Ingredient("DWB", "Dumplings with beef", Ingredient.Type.MINCED),
                new Ingredient("DWPB", "Dumplings with pork and beef", Ingredient.Type.MINCED),
                new Ingredient("PR", "Pepper", Ingredient.Type.SPICE),
                new Ingredient("GO", "Green onions", Ingredient.Type.SPICE),
                new Ingredient("BR", "Butter", Ingredient.Type.ADDITION),
                new Ingredient("SC", "Sour cream", Ingredient.Type.ADDITION)
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

    @PostMapping
    public String processDumpling(Dumpling dumpling, @ModelAttribute DumplingOrder dumplingOrder) {
        log.info("Processing dumpling: " + dumpling);
        dumplingOrder.addDumpling(dumpling);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
