package com.transdev.prestation.service;

import com.transdev.prestation.enums.PaymentMethod;
import com.transdev.prestation.model.Bill;
import com.transdev.prestation.model.Bus;
import com.transdev.prestation.repository.BillRepository;
import com.transdev.prestation.service.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{

    private static final float REDUCTION_VALUE = 100;
    private static final float REDUCTION_PERCENTAGE = 5;

    private final BillRepository billRepository;

    @Autowired
    public PaymentServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public void finalizePayment(List<Bus> busList, PaymentDto paymentDto) {
        float journeyPrice = 0f;
        Bill bill = new Bill();
        if (PaymentMethod.PAYPAL.name().equals(paymentDto.getPaymentMethod())) {
            if (paymentDto.getEmail() != null) {
                for (Bus bus : busList) {
                    System.out.println(bus.getPrice().floatValue());
                    journeyPrice += bus.getPrice().floatValue();
                }
            }
        } else if (PaymentMethod.CREDIT_CARD.name().equals(paymentDto.getPaymentMethod())) {
            if (paymentDto.getCard() != null & paymentDto.getCode() != null) {
                for (Bus bus : busList) {
                    journeyPrice += bus.getPrice();
                }
            }
        }
        if (journeyPrice > REDUCTION_VALUE) {
            journeyPrice = journeyPrice * REDUCTION_PERCENTAGE / 100;
        }
        bill.setPaymentMethod(paymentDto.getPaymentMethod());
        bill.setReservationId(paymentDto.getReservationId());
        bill.setJourneyPrice(journeyPrice);
        billRepository.save(bill);
    }
}
