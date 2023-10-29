package com.pfsystem.controller;

import com.pfsystem.service.NotificationService;
import com.pfsystem.service.OTPService;
import com.pfsystem.service.OrderingSimService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
				.andExpect(status().isOk());
	}

	@Test
	public void fetchOrderStatus() throws Exception {
		this.mockMvc.perform(post("/ordersim/validateOTP")
				.param("inputOtp", "abc")
				.param("mobileNumber", "abc")
				.param("orderID", "abc"))
				.andExpect(status().isOk());
	}

	@Test
	public void generateOrderID() throws Exception {
		this.mockMvc.perform(get("/ordersim/createOrderID"))
				.andExpect(status().isOk());
	}

	@Test
	public void sendOTP() throws Exception {
		this.mockMvc.perform(get("/ordersim/getOTPNewSim")
				.param("orderID", "abc"))
				.andExpect(status().isOk());
	}
}
