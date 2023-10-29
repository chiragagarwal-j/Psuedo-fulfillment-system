package com.pfsystem.controller;

import com.pfsystem.service.NotificationService;
import com.pfsystem.service.OTPService;
import com.pfsystem.service.OrderingSimService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderSimController.class)
public class OrderSimControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OrderingSimService orderingSimService;

	@MockBean
	private NotificationService notificationService;

	@MockBean
	private OTPService otpService;

	@Test
	public void fetchNetworkOperatorDetails() throws Exception {
		this.mockMvc.perform(get("/ordersim/getOperator"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$[0].id").value("<value>"))
			.andExpect(jsonPath("$[0].operatorCircle").value("<value>"))
			.andExpect(jsonPath("$[0].operator").value("<value>"));
	}
}
