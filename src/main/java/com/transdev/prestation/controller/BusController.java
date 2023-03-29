package com.transdev.prestation.controller;


import com.transdev.prestation.model.Bus;
import com.transdev.prestation.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/bus")
public class BusController {

    private final BusService busService;


    @Autowired
    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping(path = "/all")
    public List<Bus> getAllBuses() {
        return busService.getAllBuses();
    }

    @GetMapping(path = "/{id}")
    public Bus getBusById(@PathVariable Long id){
        return busService.getBusById(id);
    }

    @PostMapping
    public void addNewBus(@RequestBody Bus newBus) {
        busService.addNewBus(newBus);
    }

    @DeleteMapping(path = "{busId}")
    public void deleteBus(@PathVariable("busId") Long id) {
        busService.deleteBus(id);
    }
}
