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
import org.springframework.web.bind.annotation.RestController;

import com.pfsystem.dto.FetchPlansDto;
import com.pfsystem.dto.OrderIDDto;
import com.pfsystem.dto.RechargeInfoDto;
import com.pfsystem.dto.RechargePlansDto;
import com.pfsystem.dto.RechargeStatusDto;
import com.pfsystem.dto.ResponseDto;
import com.pfsystem.service.RechargeSimService;

@CrossOrigin
@RestController
@RequestMapping("/rechargesim")
public class RechargeSimController {

    @Autowired
    private RechargeSimService rechargeSimService;

    @PostMapping("/getPlans")
    public List<RechargePlansDto> fetchPlanDetails(@RequestBody FetchPlansDto fetchPlansDto) {
        return rechargeSimService.getAllPlans(fetchPlansDto);
    }

    @GetMapping("/createOrderID")
    public OrderIDDto creatingOrderID() {
        return rechargeSimService.createOrderID();
    }

    @PostMapping("/processRecharge")
    public ResponseEntity<ResponseDto> processRecharge(@RequestBody RechargeInfoDto rechargeInfoDto) {
        return rechargeSimService.processingRecharge(rechargeInfoDto);
    }

    @GetMapping("/getRechargeOrderDetails/{orderID}")
    public RechargeStatusDto getRechargeOrderDetails(@PathVariable String orderID) {
        return rechargeSimService.fetchRechargeStatus(orderID);
    }
}