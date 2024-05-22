package org.whomustnotbenamed.dumplingsworld.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.whomustnotbenamed.dumplingsworld.model.DumplingOrder;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("dumplingOrder")
public class OrderController {
    @GetMapping("/current")
    public String orderForm(Model model) {
        // почему-то не работало, пришлось добавить модель
        model.addAttribute("dumplingOrder", new DumplingOrder());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(DumplingOrder dumplingOrder, SessionStatus sessionStatus) {
        log.info("Order submitted: " + dumplingOrder);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
