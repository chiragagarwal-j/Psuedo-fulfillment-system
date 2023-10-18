package com.PFSystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.pfsystem.dto.NetworkOperatorDto;
import com.pfsystem.entities.ICCID;
import com.pfsystem.entities.IMSI;
import com.pfsystem.entities.MSISDN;
import com.pfsystem.entities.NetworkOperator;
import com.pfsystem.entities.SimCard;
import com.pfsystem.exceptions.NetworkOperatorNotFoundException;
import com.pfsystem.repository.ICCIDRepository;
import com.pfsystem.repository.IMSIRepository;
import com.pfsystem.repository.MSISDNRepository;
import com.pfsystem.repository.NetworkOperatorRepository;
import com.pfsystem.repository.SimCardRepository;
import com.pfsystem.service.OrderingSimService;

class OrderingSimServiceTest {

    private OrderingSimService orderingSimService;
    private NetworkOperatorRepository networkOperatorRepository;
    private IMSIRepository imsiRepository;
    private ICCIDRepository iccidRepository;
    private MSISDNRepository msisdnRepository;
    private SimCardRepository simCardRepository;

    @BeforeEach
    void setUp() {
        networkOperatorRepository = mock(NetworkOperatorRepository.class);
        imsiRepository = mock(IMSIRepository.class);
        iccidRepository = mock(ICCIDRepository.class);
        msisdnRepository = mock(MSISDNRepository.class);
        simCardRepository = mock(SimCardRepository.class);

        orderingSimService = new OrderingSimService();
        orderingSimService.networkOperatorRepository = networkOperatorRepository;
        orderingSimService.imsiRepository = imsiRepository;
        orderingSimService.iccidRepository = iccidRepository;
        orderingSimService.msisdnRepository = msisdnRepository;
        orderingSimService.simCardRepository = simCardRepository;
    }

    @Test
    void testGetNetworkOperatorDetails() {
        // Arrange
        List<NetworkOperator> expectedNetworkOperators = new ArrayList<>();
        expectedNetworkOperators.add(new NetworkOperator(1L, "Operator1", "Brand1"));
        expectedNetworkOperators.add(new NetworkOperator(2L, "Operator2", "Brand2"));
        
        when(networkOperatorRepository.getNetworkOperatorDetailsByCountryCodeAndStatus()).thenReturn(expectedNetworkOperators);

        // Act
        List<NetworkOperatorDto> networkOperatorDtos = orderingSimService.getNetworkOperatorDetails();

        // Assert
        assertEquals(expectedNetworkOperators.size(), networkOperatorDtos.size());

        // Verify that the repository method was called
        verify(networkOperatorRepository, times(1)).getNetworkOperatorDetailsByCountryCodeAndStatus();
    }
    
    @Test
    void testCreateSimCard() {
        // Arrange
        Long networkOperatorId = 2001L;
        NetworkOperator networkOperator = new NetworkOperator();
        networkOperator.setId(networkOperatorId);
        when(networkOperatorRepository.findById(networkOperatorId)).thenReturn(Optional.of(networkOperator));

        // Act
        ResponseEntity<String> response = orderingSimService.createSimCard(networkOperatorId);

        // Assert
        verify(imsiRepository).save(any(IMSI.class));
        verify(iccidRepository).save(any(ICCID.class));
        verify(msisdnRepository).save(any(MSISDN.class));
        verify(simCardRepository).save(any(SimCard.class));
        assertEquals("Sim card created successfully", response.getBody());
    }

    @Test
    void testCreateSimCardNetworkOperatorNotFound() {
        // Arrange
        Long networkOperatorId = 0L;
        when(networkOperatorRepository.findById(networkOperatorId)).thenReturn(Optional.empty());

        // Act and Assert
        try {
            orderingSimService.createSimCard(networkOperatorId);
        } catch (NetworkOperatorNotFoundException ex) {
            assertEquals("Network operator not found for ID: " + networkOperatorId, ex.getMessage());
        }
    }

    @Test
    void testGetAll() {
        // Arrange
        List<SimCard> expectedSimCards = new ArrayList<>();
        expectedSimCards.add(new SimCard());
        expectedSimCards.add(new SimCard());
        when(simCardRepository.findAll()).thenReturn(expectedSimCards);

        // Act
        List<SimCard> actualSimCards = orderingSimService.getAll();

        // Assert
        assertEquals(expectedSimCards.size(), actualSimCards.size());
        verify(simCardRepository, times(1)).findAll();
    }
}
