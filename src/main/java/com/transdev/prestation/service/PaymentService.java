package com.transdev.prestation.service;

import com.transdev.prestation.model.Bus;
import com.transdev.prestation.service.dto.PaymentDto;

import java.util.List;

public interface PaymentService {

    public void finalizePayment(List<Bus> busList, PaymentDto paymentDto);
}
