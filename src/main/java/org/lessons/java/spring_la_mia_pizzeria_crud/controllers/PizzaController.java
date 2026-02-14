package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.models.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository repo;

    @GetMapping
    public String getIndex(Model model) {
        List<Pizza> list = repo.findAll();
        model.addAttribute("pizzas", list);
        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String getShow(Model model, @PathVariable("id") Integer pizzaId) {
        Optional<Pizza> pizza = repo.findById(pizzaId);

        if (pizza.isEmpty()) {
            return "pizzas/404";
        }

        model.addAttribute("pizza", pizza.get());
        return "pizzas/show";
    }

    @GetMapping("/search")
    public String getIndex(Model model, @RequestParam("q") String name) {
        List<Pizza> list = repo.findByNameContaining(name);
        model.addAttribute("pizzas", list);
        return "pizzas/index";
    }

}
