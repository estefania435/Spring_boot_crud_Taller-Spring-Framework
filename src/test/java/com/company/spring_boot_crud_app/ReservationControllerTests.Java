package com.company.spring_boot_crud_app;

import com.company.spring_boot_crud_app.model.Reservation;
import com.company.spring_boot_crud_app.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ReservationControllerTests {

    @InjectMocks
    private ReservationController reservationController;

    @Mock
    private ReservationService reservationService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllreservas() {
        when(reservationService.getAllReservas()).thenReturn(new ArrayList<>());

        String viewName = reservationController.getAllreservas(model);

        verify(model, times(1)).addAttribute("reservas", new ArrayList<>());
        assertEquals("reservas", viewName);
    }

    @Test
    void testCreatereservaForm() {
        String viewName = reservationController.createreservaForm(model);

        verify(model, times(1)).addAttribute("reservation", new Reservation());
        assertEquals("create_reserva", viewName);
    }

    @Test
    void testSavereserva() {
        Reservation reservation = new Reservation();
        
        String viewName = reservationController.savereserva(reservation);

        verify(reservationService, times(1)).saveReserva(reservation);
        assertEquals("redirect:/reservas", viewName);
    }

    @Test
    void testEditreservaForm() {
        Long id = 1L;
        Reservation reservation = new Reservation();
        when(reservationService.getReservaById(id)).thenReturn(Optional.of(reservation));

        String viewName = reservationController.editreservaForm(id, model);

        verify(model, times(1)).addAttribute("reservation", reservation);
        assertEquals("edit_reserva", viewName);
    }

    @Test
    void testUpdatereserva() {
        Long id = 1L;
        Reservation reservation = new Reservation();
        
        String viewName = reservationController.updatereserva(id, reservation);

        verify(reservationService, times(1)).updateReserva(id, reservation);
        assertEquals("redirect:/reservas", viewName);
    }

    @Test
    void testDeletereserva() {
        Long id = 1L;

        String viewName = reservationController.deletereserva(id);

        verify(reservationService, times(1)).deleteReserva(id);
        assertEquals("redirect:/reservas", viewName);
    }
}
