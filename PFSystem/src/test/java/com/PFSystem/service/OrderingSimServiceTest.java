package com.PFSystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.pfsystem.dto.NetworkOperatorDto;
import com.pfsystem.entities.NetworkOperator;
import com.pfsystem.repository.AddressRepository;
import com.pfsystem.repository.ICCIDRepository;
import com.pfsystem.repository.IMSIRepository;
import com.pfsystem.repository.MSISDNRepository;
import com.pfsystem.repository.NetworkOperatorRepository;
import com.pfsystem.repository.SimCardRepository;
import com.pfsystem.repository.UserRepository;
import com.pfsystem.service.OrderingSimService;

class OrderingSimServiceTest {

    private OrderingSimService orderingSimService;
    private NetworkOperatorRepository networkOperatorRepository;
    private IMSIRepository imsiRepository;
    private ICCIDRepository iccidRepository;
    private MSISDNRepository msisdnRepository;
    private UserRepository userRepository;
    private SimCardRepository simCardRepository;
    private AddressRepository addressRepository;

    @BeforeEach
    void setUp() {
        networkOperatorRepository = mock(NetworkOperatorRepository.class);
        imsiRepository = mock(IMSIRepository.class);
        iccidRepository = mock(ICCIDRepository.class);
        msisdnRepository = mock(MSISDNRepository.class);
        userRepository = mock(UserRepository.class);
        simCardRepository = mock(SimCardRepository.class);
        addressRepository = mock(AddressRepository.class);

        orderingSimService = new OrderingSimService();
        orderingSimService.networkOperatorRepository = networkOperatorRepository;
        orderingSimService.imsiRepository = imsiRepository;
        orderingSimService.iccidRepository = iccidRepository;
        orderingSimService.msisdnRepository = msisdnRepository;
        orderingSimService.userRepository = userRepository;
        orderingSimService.simCardRepository = simCardRepository;
        orderingSimService.addressRepository = addressRepository;
    }

    @Test
    void testGetNetworkOperatorDetails() {
        // Arrange
        List<NetworkOperator> expectedNetworkOperators = new ArrayList<>();
        expectedNetworkOperators.add(new NetworkOperator(1L, "Operator1", "Brand1"));
        expectedNetworkOperators.add(new NetworkOperator(2L, "Operator2", "Brand2"));

        when(networkOperatorRepository.getNetworkOperatorDetailsByCountryCodeAndStatus())
                .thenReturn(expectedNetworkOperators);

        // Act
        List<NetworkOperatorDto> networkOperatorDtos = orderingSimService.getNetworkOperatorDetails();

        // Assert
        assertEquals(expectedNetworkOperators.size(), networkOperatorDtos.size());

        // Verify that the repository method was called
        verify(networkOperatorRepository, times(1)).getNetworkOperatorDetailsByCountryCodeAndStatus();
    }

}
