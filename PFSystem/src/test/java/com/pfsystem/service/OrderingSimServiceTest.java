package com.pfsystem.service;

import com.pfsystem.dto.NetworkOperatorDto;
import java.util.List;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.pfsystem.dto.NewSimDto;
import com.pfsystem.entities.Address;
import com.pfsystem.dto.IMSIDto;
import com.pfsystem.entities.ICCID;
import com.pfsystem.entities.IMSI;
import com.pfsystem.entities.MSISDN;
import org.springframework.http.ResponseEntity;
import com.pfsystem.dto.ResponseDto;
import com.pfsystem.entities.User;
import com.pfsystem.entities.OrderDetails;
import com.pfsystem.entities.SimCard;
import com.pfsystem.dto.NewSimOrderStatusDto;
import com.pfsystem.dto.OrderIDDto;

@SpringBootTest
public class OrderingSimServiceTest {
	@Autowired
	private OrderingSimService orderingSimService;

	@Test
	public void getNetworkOperatorDetails() {
		List<NetworkOperatorDto> expected = null;
		List<NetworkOperatorDto> actual = orderingSimService.getNetworkOperatorDetails();

		assertEquals(expected, actual);
	}

	@Test
	public void createAddress() {
		NewSimDto newSimDto = new NewSimDto();
		Address expected = new Address();
		Address actual = orderingSimService.createAddress(newSimDto);

		assertEquals(expected, actual);
	}

	@Test
	public void createICCID() {
		IMSIDto imsiDto = new IMSIDto();
		ICCID expected = new ICCID();
		ICCID actual = orderingSimService.createICCID(imsiDto);

		assertEquals(expected, actual);
	}

	@Test
	public void createIMSI() {
		IMSIDto imsiDto = new IMSIDto();
		IMSI expected = new IMSI();
		IMSI actual = orderingSimService.createIMSI(imsiDto);

		assertEquals(expected, actual);
	}

	@Test
	public void createMSISDN() {
		MSISDN expected = new MSISDN();
		MSISDN actual = orderingSimService.createMSISDN();

		assertEquals(expected, actual);
	}

	@Test
	public void createSimCard() {
		Long id = 123L;
		NewSimDto newSimDto = new NewSimDto();
		ResponseEntity<ResponseDto> expected = null;
		ResponseEntity<ResponseDto> actual = orderingSimService.createSimCard(id, newSimDto);

		assertEquals(expected, actual);
	}

	@Test
	public void createSimCardTODO() {
		NewSimDto newSimDto = new NewSimDto();
		ICCID iccid = new ICCID();
		IMSI imsi = new IMSI();
		MSISDN msisdn = new MSISDN();
		User user = new User();
		OrderDetails orderDetails = new OrderDetails();
		Address address = new Address();
		SimCard expected = new SimCard();
		SimCard actual = orderingSimService.createSimCard(newSimDto, iccid, imsi, msisdn, user, orderDetails, address);

		assertEquals(expected, actual);
	}

	@Test
	public void createUser() {
		NewSimDto newSimDto = new NewSimDto();
		User expected = new User();
		User actual = orderingSimService.createUser(newSimDto);

		assertEquals(expected, actual);
	}

	@Test
	public void getDetails() {
		String orderID = "abc";
		NewSimOrderStatusDto expected = new NewSimOrderStatusDto();
		NewSimOrderStatusDto actual = orderingSimService.getDetails(orderID);

		assertEquals(expected, actual);
	}

	@Test
	public void getMobileNumber() {
		String orderID = "abc";
		String expected = "abc";
		String actual = orderingSimService.getMobileNumber(orderID);

		assertEquals(expected, actual);
	}

	@Test
	public void getNetworkOperatorDetailsTODO() {
		List<NetworkOperatorDto> expected = null;
		List<NetworkOperatorDto> actual = orderingSimService.getNetworkOperatorDetails();

		assertEquals(expected, actual);
	}

	@Test
	public void getNewOrderID() {
		OrderIDDto expected = new OrderIDDto();
		OrderIDDto actual = orderingSimService.getNewOrderID();

		assertEquals(expected, actual);
	}

	@Test
	public void processOrderDetails() {
		String orderID = "abc";
		OrderDetails expected = new OrderDetails();
		OrderDetails actual = orderingSimService.processOrderDetails(orderID);

		assertEquals(expected, actual);
	}

	@Test
	public void getNetworkOperatorDetailsTODOTODO() {
		List<NetworkOperatorDto> expected = null;
		List<NetworkOperatorDto> actual = orderingSimService.getNetworkOperatorDetails();

		assertEquals(expected, actual);
	}
}
