package com.pfsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfsystem.dto.FetchPlansDto;
import com.pfsystem.dto.RechargePlansDto;
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

}
