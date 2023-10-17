package com.PFSystem.Controller;

import com.pfsystem.PfSystemApplication;
import com.pfsystem.service.OrderingSimService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = PfSystemApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class OrderSimControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderingSimService orderingSimService;

    // @Test
    // public void testFetchNetworkOperatorDetails() throws Exception {
    // // Mock service response
    // when(orderingSimService.getNetworkOperatorDetails()).thenReturn(null, null);

    // mockMvc.perform(MockMvcRequestBuilders.get("/ordersim/getOperator")
    // .contentType(MediaType.APPLICATION_JSON))
    // .andExpect(MockMvcResultMatchers.status().isOk());
    // // .andExpect(/* Add more assertions for the response content */);
    // }

    @Test
    public void testOrderNewSim() throws Exception {
        Long simCardId = 2001L; // Replace with a valid simCardId

        // Mock service response
        when(orderingSimService.createSimCard(simCardId)).thenReturn(ResponseEntity.ok("Sim card created successfully"));

        mockMvc.perform(MockMvcRequestBuilders.post("/ordersim/newsim/" + simCardId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
                // .andExpect(/* Add more assertions for the response content */);
    }
}
