package com.pfsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pfsystem.dto.FetchPlansDto;
import com.pfsystem.dto.RechargePlansDto;
import com.pfsystem.entities.RechargePlans;
import com.pfsystem.repository.RechargePlansRepository;
import com.pfsystem.repository.RechargeSimRepository;

@Service
public class RechargeSimService {

    @Autowired
    public RechargePlansRepository rechargePlansRepository;

    @Autowired
    public RechargeSimRepository rechargeSimRepository;

    public List<RechargePlansDto> getAllPlans(FetchPlansDto fetchPlansDto) {
        String categoryName = fetchPlansDto.getCategoryName();
        String operator = fetchPlansDto.getOperator();
        List<RechargePlans> rechargePlansList = rechargePlansRepository.findPlanDetailsByOperatorAndCategory(operator,
                categoryName);

        return rechargePlansList.stream()
                .map(rechargePlan -> {
                    RechargePlansDto dto = new RechargePlansDto();
                    dto.setPrice(rechargePlan.getPrice());
                    dto.setValidity(rechargePlan.getValidity());
                    dto.setDetails(rechargePlan.getDetails());
                    dto.setCategoryName(rechargePlan.getCategories());
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
