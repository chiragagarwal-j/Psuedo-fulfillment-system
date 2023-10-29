package com.pfsystem.service;

import com.pfsystem.dto.FetchPlansDto;
import com.pfsystem.dto.OrderIDDto;
import com.pfsystem.dto.RechargePlansDto;
import com.pfsystem.repository.OrderDetailsRepository;
import com.pfsystem.repository.RechargePlansRepository;
import com.pfsystem.repository.RechargeSimRepository;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RechargeSimServiceTest {
	@Autowired
	private RechargeSimService rechargeSimService;

	@Autowired
	public RechargePlansRepository rechargePlansRepository;

	@Autowired
	public RechargeSimRepository rechargeSimRepository;

	@Autowired
	public OrderDetailsRepository orderDetailsRepository;

	@Test
	void createOrderID() {
		OrderIDDto actualOrderID = rechargeSimService.createOrderID();

		assertNotNull(actualOrderID);
		assertNotNull(actualOrderID.getOrderID());
	}

	@Test
	void getAllPlans() {
		FetchPlansDto fetchPlansDto = new FetchPlansDto();
		List<RechargePlansDto> actualRechargePlansList = rechargeSimService.getAllPlans(fetchPlansDto);

		assertNotNull(actualRechargePlansList);
	}

}
