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

    @GetMapping(path = "/all")
    public List<ReservationDto> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping(path = "/{id}")
    public ReservationDto getReservationById(@PathVariable Long id){
        return reservationService.getReservationById(id);
    }

    @PostMapping
    public void addNewReservation(@RequestBody ReservationDto newReservation) {
        reservationService.addNewReservation(newReservation);
    }

    @DeleteMapping(path = "{reservationId}")
    public void deleteReservation(@PathVariable("reservationId") Long id) {
        reservationService.deleteReservation(id);
    }

    @PostMapping(path = "/pay")
    public void payReservation(@RequestBody PaymentDto paymentDto) {
        reservationService.payReservation(paymentDto);
    }

    //insert into BUS values (1, 'chatillon', 45, now(), 2.4)
    //insert into CLIENT values (1, 'ab@gm.com', 'abde wahman')
    //insert into RESERVATION (RESERVATION_ID, JOURNEY_DATE, BUS_ID, ID) values (1, now(), (select bus_id from bus where bus_id = 1) , (select id from client where id = 1))
}
