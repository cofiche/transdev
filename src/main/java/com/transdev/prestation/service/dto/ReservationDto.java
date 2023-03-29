package com.transdev.prestation.service.dto;

import com.transdev.prestation.model.Bus;
import com.transdev.prestation.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ReservationDto {

    private Long reservationId;

    private Date journeyDate;
    
    private List<Long> busIds;
    
    private Long clientId;
    
    public ReservationDto(Reservation reservation) {
        List<Long> busIds = new LinkedList<>();
        for (Bus bus : reservation.getBuses()) {
            busIds.add(bus.getBusId());
        }
        this.reservationId = reservation.getReservationId();
        this.journeyDate = reservation.getJourneyDate();
        this.busIds = busIds;
        this.clientId = reservation.getClient().getId();
    }
}
