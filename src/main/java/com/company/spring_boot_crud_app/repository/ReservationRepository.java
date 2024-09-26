package com.company.spring_boot_crud_app.repository;

import com.company.spring_boot_crud_app.model.Reservation;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    
}
