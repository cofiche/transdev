package com.transdev.prestation.service;

import com.transdev.prestation.model.Bus;
import com.transdev.prestation.model.Client;
import com.transdev.prestation.model.Reservation;
import com.transdev.prestation.repository.BusRepository;
import com.transdev.prestation.repository.ClientRepository;
import com.transdev.prestation.repository.ReservationRepository;
import com.transdev.prestation.service.dto.PaymentDto;
import com.transdev.prestation.service.dto.ReservationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;

    private final ClientRepository clientRepository;

    private final BusRepository busRepository;

    private final PaymentService paymentService;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, ClientRepository clientRepository, BusRepository busRepository, PaymentService paymentService) {
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
        this.busRepository = busRepository;
        this.paymentService = paymentService;
    }


    @Override
    public List<ReservationDto> getAllReservations() {
        List<ReservationDto> reservations = new LinkedList<>();
        List<Reservation> reservationList = reservationRepository.findAll();
        for (Reservation reservation : reservationList) {
            ReservationDto reservationDto = new ReservationDto(reservation);
            reservations.add(reservationDto);
        }
        return reservations;
    }

    @Override
    public ReservationDto getReservationById(Long reservationId) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if (reservation != null) {
            ReservationDto reservationDto = new ReservationDto(reservation.get());
            return reservationDto;
        } else throw new IllegalStateException("Reservation with id " + reservationId + "does not exists");
    }

    @Override
    public void addNewReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        Client client = clientRepository.getReferenceById(reservationDto.getClientId());
        List<Bus> busList = new LinkedList<>();
        for (Long busId : reservationDto.getBusIds()) {
            Bus bus = busRepository.getReferenceById(busId);
            busList.add(bus);
        }
        reservation.setJourneyDate(reservationDto.getJourneyDate());
        reservation.setBuses(busList);
        reservation.setClient(client);
        reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Long reservationId) {
        Reservation reservation = getReservationById_(reservationId);
        if (reservation != null) {
            reservationRepository.delete(reservation);
        } else throw new IllegalStateException("Reservation with id " + reservationId + "does not exists");
    }

    @Override
    public void updateReservation(ReservationDto reservationDto, Long reservationId) {
        Reservation reservation = getReservationById_(reservationId);
        if (reservation != null) {
            reservation.setJourneyDate(reservationDto.getJourneyDate());
            Client client = clientRepository.getReferenceById(reservationDto.getClientId());
            if (client != null) reservation.setClient(client);
            List<Bus> busList = new LinkedList<>();
            for (Long busId: reservationDto.getBusIds()) {
                Bus bus = busRepository.getReferenceById(busId);
                if (bus != null) busList.add(bus);
            }
            reservation.setBuses(busList);
        }
        reservationRepository.save(reservation);
    }

    @Override
    public void payReservation(PaymentDto paymentDto) {
        Optional<Reservation> reservation = reservationRepository.findById(paymentDto.getReservationId());
        if (reservation != null) {
            List<Bus> busList = reservation.get().getBuses();
                paymentService.finalizePayment(busList, paymentDto);
        } else throw new IllegalStateException("Reservation with id " + reservation.get().getReservationId() + "does not exists");
    }

    private Reservation getReservationById_(Long reservationId) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if (reservation != null) {
            return reservation.get();
        } else throw new IllegalStateException("Reservation with id " + reservationId + "does not exists");
    }
}
