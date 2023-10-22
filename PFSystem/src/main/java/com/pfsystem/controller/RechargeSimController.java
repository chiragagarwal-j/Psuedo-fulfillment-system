package com.pfsystem.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfsystem.service.RechargeSimService;

@CrossOrigin
@RestController
@RequestMapping("/rechargesim")
public class RechargeSimController {

    @Autowired
    private RechargeSimService rechargeSimService;

    @GetMapping("/getOperatorCircle")
    public void fetchNetworkOperatorDetails() throws IOException {
        rechargeSimService.equals(rechargeSimService);
    }

}
