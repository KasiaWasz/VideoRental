package com.videorental.dtos.rentals;

import org.springframework.util.Assert;

import java.math.BigDecimal;

public class RentalFeeDto {

    private String clientFirstName;
    private String clientLastName;
    private BigDecimal fee;


    public RentalFeeDto(String clientFirstName, String clientLastName, BigDecimal fee) {

        Assert.notNull(clientFirstName, "clientFirstName must not be null");
        Assert.notNull(clientLastName, "clientLastName must not be null");
        Assert.notNull(fee, "fee must not be null");

        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.fee = fee;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public BigDecimal getFee() {
        return fee;
    }
}
