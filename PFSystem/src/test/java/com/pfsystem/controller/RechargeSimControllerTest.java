package com.pfsystem.controller;

import com.pfsystem.service.NotificationService;
import com.pfsystem.service.RechargeSimService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RechargeSimController.class)
class RechargeSimControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RechargeSimService rechargeSimService;

	@MockBean
	private NotificationService notificationService;

    @Test
    void creatingOrderID() throws Exception {
		this.mockMvc.perform(get("/rechargesim/createOrderID"))
				.andExpect(status().isOk());
	}

}
