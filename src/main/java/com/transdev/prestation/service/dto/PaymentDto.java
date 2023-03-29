package com.transdev.prestation.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class PaymentDto {

    private Long reservationId;

    private String paymentMethod;

    @Nullable
    private String email;

    @Nullable
    private String card;

    @Nullable
    private String code;
}
