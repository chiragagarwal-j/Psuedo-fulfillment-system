package com.pfsystem.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.pfsystem.dto.FetchPlansDto;
import com.pfsystem.dto.OrderIDDto;
import com.pfsystem.dto.RechargeInfoDto;
import com.pfsystem.dto.RechargePlansDto;
import com.pfsystem.dto.RechargeStatusDto;
import com.pfsystem.dto.ResponseDto;
import com.pfsystem.entities.OrderDetails;
import com.pfsystem.entities.RechargePlans;
import com.pfsystem.entities.RechargeSim;
import com.pfsystem.repository.OrderDetailsRepository;
import com.pfsystem.repository.RechargePlansRepository;
import com.pfsystem.repository.RechargeSimRepository;

@Service
public class RechargeSimService {

    @Autowired
    public RechargePlansRepository rechargePlansRepository;

    @Autowired
    public RechargeSimRepository rechargeSimRepository;

    @Autowired
    public OrderDetailsRepository orderDetailsRepository;

    public List<RechargePlansDto> getAllPlans(FetchPlansDto fetchPlansDto) {
        String categoryName = fetchPlansDto.getCategoryName();
        String operator = fetchPlansDto.getOperator();
        List<RechargePlans> rechargePlansList = rechargePlansRepository.findPlanDetailsByOperatorAndCategory(operator,
                categoryName);

        return rechargePlansList.stream()
                .map(rechargePlan -> {
                    RechargePlansDto dto = new RechargePlansDto();
                    dto.setPlanID(rechargePlan.getId());
                    dto.setPrice(rechargePlan.getPrice());
                    dto.setValidity(rechargePlan.getValidity());
                    dto.setDetails(rechargePlan.getDetails());
                    dto.setCategoryName(rechargePlan.getCategories());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public OrderIDDto createOrderID() {
        OrderDetails orderDetails = new OrderDetails();
        String orderID = orderDetails.generateRandomOrderId();
        orderDetails.setOrderID(orderID);
        orderDetails.setIsPending(true);
        orderDetailsRepository.save(orderDetails);
        return new OrderIDDto(orderID);
    }

    public ResponseEntity<ResponseDto> processingRecharge(RechargeInfoDto rechargeInfoDto) {

        ResponseDto responseDto = new ResponseDto();

        RechargeSim rechargeSim = new RechargeSim();
        rechargeSim.setMobileNumber(rechargeInfoDto.getMobileNumber());
        rechargeSim.setOperator(rechargeInfoDto.getOperator());
        rechargeSim.setOperatorCircle(rechargeInfoDto.getOperatorCircle());
        rechargeSim.setPlanID(rechargeInfoDto.getPlanID());
        Optional<RechargePlans> rechargePlan = rechargePlansRepository.findById(rechargeInfoDto.getPlanID());
        if (rechargeInfoDto.getPayVia() == null || rechargeInfoDto.getPaymentInfo() == null) {
            responseDto.setResponseBody("Recharge failed due to invalid payment info");
            rechargeSim.setOrderDetails(createOrderDetails(rechargePlan.get().getPrice(), rechargeInfoDto.getPayVia(),
                    rechargeInfoDto.getPaymentInfo(), "Failed", rechargeInfoDto.getOrderID()));
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
        rechargeSim.setOrderDetails(createOrderDetails(rechargePlan.get().getPrice(), rechargeInfoDto.getPayVia(),
                rechargeInfoDto.getPaymentInfo(), "Success", rechargeInfoDto.getOrderID()));
        rechargeSimRepository.save(rechargeSim);

        responseDto.setResponseBody("Recharge was Successfully executed");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    private OrderDetails createOrderDetails(String price, String paidVia, String paymentInfo, String paymentStatus,
            String orderID) {
        OrderDetails orderDetails = orderDetailsRepository.findByOrderID(orderID);
        orderDetails.setPrice(price);
        orderDetails.setPaidVia(paidVia);
        orderDetails.setPaymentInfo(paymentInfo);
        orderDetails.setStatus(paymentStatus);
        orderDetails.setOrderTime(new Date());
        orderDetailsRepository.save(orderDetails);

        return orderDetails;
    }

    public RechargeStatusDto fetchRechargeStatus(String orderId) {
        RechargeStatusDto rechargeStatusDto = new RechargeStatusDto();

        OrderDetails orderDetails = orderDetailsRepository.findByOrderID(orderId);

        RechargeSim rechargeSim = rechargeSimRepository.findByOrderDetails(orderDetails);
        if (rechargeSim != null) {
            rechargeStatusDto.setFinalAmount(orderDetails.getPrice());

            rechargeStatusDto.setMobileNumber(rechargeSim.getMobileNumber());
            rechargeStatusDto.setOperator(rechargeSim.getOperator());
            rechargeStatusDto.setOperatorCirle(rechargeSim.getOperatorCircle());

            Optional<RechargePlans> rechargePlans = rechargePlansRepository.findById(rechargeSim.getPlanID());
            if (rechargePlans.isPresent()) {
                RechargePlans plans = rechargePlans.get();
                rechargeStatusDto.setValidity(plans.getValidity());
                rechargeStatusDto.setDetails(plans.getDetails());
                rechargeStatusDto.setOrderTime(orderDetails.getOrderTime());
                rechargeStatusDto.setOrderStatus(orderDetails.getStatus());
            }

            return rechargeStatusDto;
        } else {
            orderDetails.setStatus("Recharge Failed");
            orderDetails.setOrderTime(new Date());
            orderDetailsRepository.save(orderDetails);
            rechargeStatusDto.setOrderTime(orderDetails.getOrderTime());
        }
        System.out.println(rechargeStatusDto.toString());
        return rechargeStatusDto;
    }
}