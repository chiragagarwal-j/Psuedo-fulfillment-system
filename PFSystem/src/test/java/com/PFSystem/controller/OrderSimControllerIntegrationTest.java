package com.PFSystem.controller;

import com.pfsystem.dto.NetworkOperatorDto;
import com.pfsystem.entities.SimCard;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderSimControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static HttpHeaders headers;

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    private String createURLWithPort(String path) {
        return "http://localhost:" + port + "/ordersim" + path;
    }

    @Test
    @Sql(statements = "INSERT INTO PFSystem.network_operator (mcc, mnc, bands, brand, country_code, country_name, notes, operator, status, `type`) VALUES('405', '854', 'LTE', 'Jio', 'IN', 'India', '-', 'Andhra Pradesh', 'Operational', 'National')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

    public void testGetOperatorDetails() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List<NetworkOperatorDto>> response = restTemplate.exchange(
                createURLWithPort("/getOperator"),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<NetworkOperatorDto>>() {
                });
        List<NetworkOperatorDto> operatorDetails = response.getBody();
        assertNotNull(operatorDetails);
        assertEquals(response.getStatusCode().value(), 200);
    }

    @Test
    public void testOrderNewSim() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/newsim/1"),
                HttpMethod.POST,
                entity,
                String.class);
        String responseBody = response.getBody();
        assertEquals("Sim card ordered successfully", responseBody);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetAllSimCards() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List<SimCard>> response = restTemplate.exchange(
                createURLWithPort("/getAllSimCards"),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<SimCard>>() {
                });
        List<SimCard> simCards = response.getBody();
        assertNotNull(simCards);
        assertEquals(response.getStatusCode().value(), 200);
    }
}
