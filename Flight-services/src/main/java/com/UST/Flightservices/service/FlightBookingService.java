package com.UST.Flightservices.service;

import com.UST.Flightservices.dto.BookingRequest;
import com.UST.Flightservices.dto.FlightBookingAcknowledgement;
import com.UST.Flightservices.entity.Passengerinfo;
import com.UST.Flightservices.entity.Paymentinfo;
import com.UST.Flightservices.exception.InsufficentAmountException;
import com.UST.Flightservices.repo.Passengerrepo;
import com.UST.Flightservices.repo.Paymentrepo;
import com.UST.Flightservices.utils.PaymentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class FlightBookingService {

    @Autowired
    private Passengerrepo passengerrepo;
    @Autowired
    private Paymentrepo paymentrepo;
    @Transactional
    public FlightBookingAcknowledgement bookingFlightTicket(BookingRequest request) throws InsufficentAmountException {
        Passengerinfo passangerinfo =request.getPassengerinfo();
        passangerinfo=passengerrepo.save(passangerinfo);
        Paymentinfo paymentinfo=request.getPaymentinfo();
        PaymentUtils.validateCreditLimit(paymentinfo.getAccountNo(),passangerinfo.getFare());
        paymentinfo.setPassengerId(passangerinfo.getPId());
        paymentinfo.setAmount(passangerinfo.getFare());
        paymentrepo.save(paymentinfo);
        return new FlightBookingAcknowledgement("success",passangerinfo.getFare(),
                UUID.randomUUID().toString().split("-")[0],passangerinfo);
    }
}
