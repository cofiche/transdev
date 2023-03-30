package com.transdev.prestation.service;

import com.transdev.prestation.service.dto.PaymentDto;
import com.transdev.prestation.service.dto.ReservationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService {

    List<ReservationDto> getAllReservations();

    ReservationDto getReservationById(Long reservationId);

    void addNewReservation(ReservationDto reservation);

    void deleteReservation(Long reservationId);

    void updateReservation(ReservationDto reservationDto, Long reservationId);

    void payReservation(PaymentDto paymentDto);
}
