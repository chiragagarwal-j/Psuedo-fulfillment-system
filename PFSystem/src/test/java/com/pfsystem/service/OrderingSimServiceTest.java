package com.pfsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.pfsystem.dto.IMSIDto;
import com.pfsystem.dto.NetworkOperatorDto;
import com.pfsystem.dto.NewSimDto;
import com.pfsystem.dto.OrderIDDto;
import com.pfsystem.entities.Address;
import com.pfsystem.entities.ICCID;
import com.pfsystem.entities.IMSI;
import com.pfsystem.entities.MSISDN;
import com.pfsystem.entities.NetworkOperator;
import com.pfsystem.entities.OrderDetails;
import com.pfsystem.entities.SimCard;
import com.pfsystem.entities.User;
import com.pfsystem.repository.AddressRepository;
import com.pfsystem.repository.ICCIDRepository;
import com.pfsystem.repository.IMSIRepository;
import com.pfsystem.repository.MSISDNRepository;
import com.pfsystem.repository.NetworkOperatorRepository;
import com.pfsystem.repository.OrderDetailsRepository;
import com.pfsystem.repository.SimCardRepository;
import com.pfsystem.repository.UserRepository;

class OrderingSimServiceTest {

	private OrderingSimService orderingSimService;
	private NetworkOperatorRepository networkOperatorRepository;
	private IMSIRepository imsiRepository;
	private ICCIDRepository iccidRepository;
	private MSISDNRepository msisdnRepository;
	private UserRepository userRepository;
	private SimCardRepository simCardRepository;
	private AddressRepository addressRepository;
	private OrderDetailsRepository orderDetailsRepository;

	@BeforeEach
	void setUp() {
		networkOperatorRepository = mock(NetworkOperatorRepository.class);
		imsiRepository = mock(IMSIRepository.class);
		iccidRepository = mock(ICCIDRepository.class);
		msisdnRepository = mock(MSISDNRepository.class);
		userRepository = mock(UserRepository.class);
		simCardRepository = mock(SimCardRepository.class);
		addressRepository = mock(AddressRepository.class);
		orderDetailsRepository = mock(OrderDetailsRepository.class);

		orderingSimService = new OrderingSimService();
		orderingSimService.networkOperatorRepository = networkOperatorRepository;
		orderingSimService.imsiRepository = imsiRepository;
		orderingSimService.iccidRepository = iccidRepository;
		orderingSimService.msisdnRepository = msisdnRepository;
		orderingSimService.userRepository = userRepository;
		orderingSimService.simCardRepository = simCardRepository;
		orderingSimService.addressRepository = addressRepository;
		orderingSimService.orderDetailsRepository = orderDetailsRepository;
	}

	@Test
	void testGetNetworkOperatorDetails() {
		List<NetworkOperator> expectedNetworkOperators = new ArrayList<>();
		expectedNetworkOperators.add(new NetworkOperator(1L, "Operator1", "Brand1"));
		expectedNetworkOperators.add(new NetworkOperator(2L, "Operator2", "Brand2"));

		when(networkOperatorRepository.getNetworkOperatorDetailsByCountryCodeAndStatus())
				.thenReturn(expectedNetworkOperators);

		List<NetworkOperatorDto> networkOperatorDtos = orderingSimService.getNetworkOperatorDetails();

		assertEquals(expectedNetworkOperators.size(), networkOperatorDtos.size());

		verify(networkOperatorRepository, times(1)).getNetworkOperatorDetailsByCountryCodeAndStatus();
	}

	@Test
	void testGetNewOrderID() {
		// Arrange and Act
		OrderIDDto orderIDDto1 = orderingSimService.getNewOrderID();
		OrderIDDto orderIDDto2 = orderingSimService.getNewOrderID();

		// Assert
		assertNotNull(orderIDDto1);
		assertNotNull(orderIDDto2);
		assertNotEquals(orderIDDto1.getOrderID(), orderIDDto2.getOrderID());
	}

	@Test
	void testCreateIMSI() {
		IMSIDto imsiDto = new IMSIDto("MCC", "MNC", "Operator", "Brand");

		when(imsiRepository.save(any(IMSI.class))).thenReturn(new IMSI());

		IMSI imsi = orderingSimService.createIMSI(imsiDto);

		assertNotNull(imsi);
	}

	@Test
	void testCreateICCID() {
		IMSIDto imsiDto = new IMSIDto("MCC", "MNC", "Operator", "Brand");

		when(iccidRepository.save(any(ICCID.class))).thenReturn(new ICCID());

		ICCID iccid = orderingSimService.createICCID(imsiDto);

		assertNotNull(iccid);
	}

	@Test
    void testCreateMSISDN() {
        when(msisdnRepository.save(any(MSISDN.class))).thenReturn(new MSISDN());

        
        MSISDN msisdn = orderingSimService.createMSISDN();

        
        assertNotNull(msisdn);
    }

	@Test
	void testCreateUser() {
		NewSimDto newSimDto = new NewSimDto();
		newSimDto.setFirstName("John");
		newSimDto.setLastName("Doe");
		newSimDto.setEmail("john.doe@example.com");

		when(userRepository.save(any(User.class))).thenReturn(new User());

		User user = orderingSimService.createUser(newSimDto);

		assertNotNull(user);
		assertEquals("John", user.getFirstName());
		assertEquals("Doe", user.getLastName());
		assertEquals("john.doe@example.com", user.getEmail());
	}

	@Test
	void testCreateAddress() {
		NewSimDto newSimDto = new NewSimDto();
		newSimDto.setAddressLine1("123 Main St");
		newSimDto.setAddressLine2("Apt 4B");
		newSimDto.setCity("City");
		newSimDto.setPincode("12345");
		newSimDto.setState("State");

		when(addressRepository.save(any(Address.class))).thenReturn(new Address());

		Address address = orderingSimService.createAddress(newSimDto);

		assertNotNull(address);
		assertEquals("123 Main St", address.getAddressLine1());
		assertEquals("Apt 4B", address.getAddressLine2());
		assertEquals("City", address.getCity());
		assertEquals("12345", address.getPincode());
		assertEquals("State", address.getState());
	}

	@Test
	void testProcessOrderDetails() {
		String orderID = "123456";

		when(orderDetailsRepository.findByOrderID(eq(orderID))).thenReturn(new OrderDetails());

		OrderDetails orderDetails = orderingSimService.processOrderDetails(orderID);

		assertNotNull(orderDetails);
		assertEquals("Success", orderDetails.getStatus());
		assertFalse(orderDetails.getIsPending());
	}

	@Test
	void testGetMobileNumber() {
		OrderDetails orderDetails = new OrderDetails();
		SimCard simCard = new SimCard();
		simCard.setExistingNumber("9876543210");

		Mockito.when(orderDetailsRepository.findByOrderID(Mockito.anyString())).thenReturn(orderDetails);
		Mockito.when(simCardRepository.findByOrderDetails(orderDetails)).thenReturn(simCard);

		String mobileNumber = orderingSimService.getMobileNumber("yourOrderID");

		assertEquals("9876543210", mobileNumber);
	}

}
