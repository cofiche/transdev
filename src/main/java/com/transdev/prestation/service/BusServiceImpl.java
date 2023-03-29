package com.transdev.prestation.service;

import com.transdev.prestation.model.Bus;
import com.transdev.prestation.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusServiceImpl implements BusService{


    private final BusRepository busRepository;

    @Autowired
    public BusServiceImpl(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @Override
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    @Override
    public Bus getBusById(Long busId) {
        Optional<Bus> bus = busRepository.findById(busId);
        if (bus != null) {
            return bus.get();
        } else throw new IllegalStateException("Bus with id " + busId + "does not exists");
    }

    @Override
    public void addNewBus(Bus bus) {
        busRepository.save(bus);
    }

    @Override
    public void deleteBus(Long busId) {
        Bus busToDelete = getBusById(busId);
        if (busToDelete != null) {
            busRepository.delete(busToDelete);
        } else {
            throw new IllegalStateException("Bus with id " + busId + "does not exists");
        }
    }


}
