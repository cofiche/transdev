package com.transdev.prestation;

import com.transdev.prestation.model.Bus;
import com.transdev.prestation.model.Client;
import com.transdev.prestation.model.Reservation;
import com.transdev.prestation.repository.BusRepository;
import com.transdev.prestation.repository.ClientRepository;
import com.transdev.prestation.repository.ReservationRepository;
import com.transdev.prestation.service.PaymentService;
import com.transdev.prestation.service.dto.ReservationDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DataJpaTest
public class ReservationServiceTest {

    private final ReservationRepository reservationRepository;

    private final ClientRepository clientRepository = mock(ClientRepository.class);

    private final BusRepository busRepository = mock(BusRepository.class);

    private final PaymentService paymentService = mock(PaymentService.class);


    @Autowired
    public ReservationServiceTest(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    @Test
    public void testAddNewReservation() {
        //Given

        ReservationDto reservationDto = new ReservationDto(
                1L,
                new Date(),
                List.of(1L),
                1L
        );


        Client client = new Client(
                1L,
                "abdel",
                "abdel@gmail.com",
                List.of(new Reservation())
                );

        Bus bus = new Bus(
                1L,
                "montrouge",
                48L,
                new Date(),
                2.4f,
                List.of(new Reservation())
        );

        Reservation reservation = new Reservation();
        reservation.setReservationId(1l);
        reservation.setClient(client);
        reservation.setBuses(List.of(bus));
        reservation.setJourneyDate(new Date());

        //When
        when(clientRepository.getReferenceById(reservationDto.getClientId())).thenReturn(client);
        when(busRepository.getReferenceById(reservationDto.getBusIds().get(0))).thenReturn(bus);

        //Then

        Reservation savedReservation = reservationRepository.save(reservation);

        //Assertions.assertEquals(reservation, savedReservation);


    }
}
