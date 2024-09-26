package com.company.spring_boot_crud_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.company.spring_boot_crud_app.model.Reservation;
import com.company.spring_boot_crud_app.service.ReservationService;

@Controller
@RequestMapping("/reservas")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public String getAllreservas(Model model) {
        model.addAttribute("reservas", reservationService.getAllReservas());
        return "reservas";
    }
    
    @GetMapping("/create")
    public String createreservaForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "create_reserva";
    }
    
    @PostMapping
    public String savereserva(@ModelAttribute("reservation") Reservation reserva) {
        reservationService.saveReserva(reserva);
        return "redirect:/reservas";
    }

    @GetMapping("/edit/{id}")
    public String editreservaForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("reservation", reservationService.getReservaById(id).orElse(null));
        return "edit_reserva";
    }
    
    @PostMapping("/edit/{id}")
    public String updatereserva(@PathVariable Long id, @ModelAttribute("reservation") Reservation reserva) {
        reserva.setId(id);
        System.out.println("el reservao es " + reserva);
        reservationService.updateReserva(id, reserva);
        return "redirect:/reservas";
    }

    @GetMapping("/delete/{id}")
    public String deletereserva(@PathVariable Long id) {
        reservationService.deleteReserva(id);
        return "redirect:/reservas";
    }
    
}
