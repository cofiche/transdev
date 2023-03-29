package com.transdev.prestation.service;

import com.transdev.prestation.model.Bus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BusService {

    List<Bus> getAllBuses();

    Bus getBusById(Long busId);

    void addNewBus(Bus bus);

    void deleteBus(Long busId);
}
