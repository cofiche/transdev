package com.transdev.prestation.controller;

import com.transdev.prestation.service.ReservationService;
import com.transdev.prestation.service.dto.PaymentDto;
import com.transdev.prestation.service.dto.ReservationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @CrossOrigin("http://localhost:4200/")
    @GetMapping(path = "/all")
    public List<ReservationDto> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @CrossOrigin("http://localhost:4200/")
    @GetMapping(path = "/{id}")
    public ReservationDto getReservationById(@PathVariable Long id){
        return reservationService.getReservationById(id);
    }

    @CrossOrigin("http://localhost:4200/")
    @PostMapping
    public void addNewReservation(@RequestBody ReservationDto newReservation) {
        reservationService.addNewReservation(newReservation);
    }

    @CrossOrigin("http://localhost:4200/")
    @DeleteMapping(path = "{reservationId}")
    public void deleteReservation(@PathVariable("reservationId") Long id) {
        reservationService.deleteReservation(id);
    }

    @CrossOrigin("http://localhost:4200/")
    @PutMapping(path = "/edit/{id}")
    public void updateReservation(@RequestBody ReservationDto reservation, @PathVariable Long id) {
        reservationService.updateReservation(reservation, id);
    }

    @PostMapping(path = "/pay")
    public void payReservation(@RequestBody PaymentDto paymentDto) {
        reservationService.payReservation(paymentDto);
    }

}
