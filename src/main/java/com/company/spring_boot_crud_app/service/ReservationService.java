package com.company.spring_boot_crud_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.spring_boot_crud_app.model.Reservation;
import com.company.spring_boot_crud_app.repository.ReservationRepository;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    
    public List<Reservation> getAllReservas() {
        return (List<Reservation>) reservationRepository.findAll();
    }
    
    public Optional<Reservation> getReservaById(Long id) {
        return reservationRepository.findById(id);
    }
    
    public Reservation saveReserva(Reservation reserva) {
        return reservationRepository.save(reserva);
    }

    public Reservation updateReserva(Long id, Reservation reserva) {
        // Busca el Reservao existente por ID
        Reservation existingReserva = reservationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reservao no encontrado con id: " + id));

        // Actualiza los campos que necesites
        existingReserva.setReserva(reserva.getReserva());
        // Agrega otros campos que desees actualizar

        // Guarda y devuelve el Reservao actualizado
        return reservationRepository.save(existingReserva);
    }
    
    public void deleteReserva(Long id) {
        reservationRepository.deleteById(id);
    }
    
}
