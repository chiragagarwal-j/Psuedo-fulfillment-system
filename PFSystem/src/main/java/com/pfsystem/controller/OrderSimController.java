package com.pfsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfsystem.dto.MobileNumberDto;
import com.pfsystem.dto.NetworkOperatorDto;
import com.pfsystem.dto.NewSimDto;
import com.pfsystem.dto.NewSimOrderStatusDto;
import com.pfsystem.dto.OrderIDDto;
import com.pfsystem.dto.ResponseDto;
import com.pfsystem.service.NotificationService;
import com.pfsystem.service.OTPService;
import com.pfsystem.service.OrderingSimService;

@CrossOrigin
@RestController
@RequestMapping("/ordersim")
public class OrderSimController {

    @Autowired
    private OrderingSimService orderingSimService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private OTPService otpService;

    @GetMapping("/getOperator")
    public List<NetworkOperatorDto> fetchNetworkOperatorDetails() {
        return orderingSimService.getNetworkOperatorDetails();
    }

    @PostMapping("/newSim/{id}")
    public ResponseEntity<ResponseDto> orderNewSim(@PathVariable Long id, @RequestBody NewSimDto newSimDto) {
        return orderingSimService.createSimCard(id, newSimDto);
    }

    @GetMapping("/createOrderID")
    public OrderIDDto generateOrderID() {
        return orderingSimService.getNewOrderID();
    }

    @GetMapping("/getOrderDetails")
    public NewSimOrderStatusDto fetchOrderDetails(@RequestParam("orderID") String orderID) {
        NewSimOrderStatusDto newSimOrderStatusDto = orderingSimService.getDetails(orderID);
        notificationService.sendNewSimEmail(newSimOrderStatusDto, orderID);
        // notificationService.sendSMSNotification(newSimOrderStatusDto.getExistingNumber(),
        //         newSimOrderStatusDto.toSMSString(orderID));
        return newSimOrderStatusDto;
    }

    @GetMapping("/getOTPNewSim")
    public MobileNumberDto sendOTP(@RequestParam("orderID") String orderID) {
        MobileNumberDto mobileNumberDto = new MobileNumberDto();
        String mobileNumber = orderingSimService.getMobileNumber(orderID);
        mobileNumberDto.setMobileNumber(mobileNumber);
        otpService.generateOTP(mobileNumber);
        return mobileNumberDto;
    }

    @PostMapping("/validateOTP")
    public ResponseEntity<?> fetchOrderStatus(@RequestParam("orderID") String orderID,
            @RequestParam("inputOtp") String inputOtp,
            @RequestParam("mobileNumber") String mobileNumber) {

        if (!otpService.verifyOTP(inputOtp, mobileNumber)) {

            return ResponseEntity.ok(new ResponseDto("Invalid OTP. Please try again."));
        }
        NewSimOrderStatusDto newSimOrderStatusDto = orderingSimService.getDetails(orderID);

        if (newSimOrderStatusDto == null) {
            return ResponseEntity.ok("Order not found.");
        }

        return ResponseEntity.ok(newSimOrderStatusDto);
    }

}
