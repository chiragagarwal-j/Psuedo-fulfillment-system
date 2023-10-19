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

import com.pfsystem.dto.NetworkOperatorDto;
import com.pfsystem.dto.NewSimDto;
import com.pfsystem.dto.ResponseDto;
import com.pfsystem.entities.SimCard;
import com.pfsystem.service.OrderingSimService;

@CrossOrigin
@RestController
@RequestMapping("/ordersim")
public class OrderSimController {
    
    @Autowired
    private OrderingSimService orderingSimService;

    @GetMapping("/getOperator")
    public List<NetworkOperatorDto> fetchNetworkOperatorDetails(){
       return orderingSimService.getNetworkOperatorDetails();
    }

    @PostMapping("/newSim/{id}")
    public ResponseEntity<ResponseDto> orderNewSim(@PathVariable Long id, @RequestBody NewSimDto newSimDto ){
        return orderingSimService.createSimCard(id,newSimDto);
    }

    @GetMapping("/getAllSimCards")
    public List<SimCard> getAllSimCards(){
        return orderingSimService.getAll();
    }
}