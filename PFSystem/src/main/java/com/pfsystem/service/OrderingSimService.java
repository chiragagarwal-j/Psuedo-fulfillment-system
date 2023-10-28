package com.pfsystem.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pfsystem.dto.IMSIDto;
import com.pfsystem.dto.NetworkOperatorDto;
import com.pfsystem.dto.NewSimDto;
import com.pfsystem.dto.NewSimOrderStatusDto;
import com.pfsystem.dto.OrderIDDto;
import com.pfsystem.dto.ResponseDto;
import com.pfsystem.entities.Address;
import com.pfsystem.entities.ICCID;
import com.pfsystem.entities.IMSI;
import com.pfsystem.entities.MSISDN;
import com.pfsystem.entities.NetworkOperator;
import com.pfsystem.entities.OrderDetails;
import com.pfsystem.entities.SimCard;
import com.pfsystem.entities.User;
import com.pfsystem.exceptions.NetworkOperatorNotFoundException;
import com.pfsystem.repository.AddressRepository;
import com.pfsystem.repository.ICCIDRepository;
import com.pfsystem.repository.IMSIRepository;
import com.pfsystem.repository.MSISDNRepository;
import com.pfsystem.repository.NetworkOperatorRepository;
import com.pfsystem.repository.OrderDetailsRepository;
import com.pfsystem.repository.SimCardRepository;
import com.pfsystem.repository.UserRepository;

@Service
public class OrderingSimService {

    @Autowired
    public NetworkOperatorRepository networkOperatorRepository;

    @Autowired
    public IMSIRepository imsiRepository;

    @Autowired
    public ICCIDRepository iccidRepository;

    @Autowired
    public MSISDNRepository msisdnRepository;

    @Autowired
    public SimCardRepository simCardRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public AddressRepository addressRepository;

    @Autowired
    public OrderDetailsRepository orderDetailsRepository;

    public List<NetworkOperatorDto> getNetworkOperatorDetails() {
        List<NetworkOperator> networkOperators = networkOperatorRepository
                .getNetworkOperatorDetailsByCountryCodeAndStatus();
        return networkOperators.stream()
                .map(networkOperator -> new NetworkOperatorDto(networkOperator.getId(), networkOperator.getOperator(),
                        networkOperator.getBrand()))
                .toList();
    }

    public OrderIDDto getNewOrderID() {
        OrderDetails orderDetails = new OrderDetails();
        String orderID = orderDetails.generateRandomOrderId();
        orderDetails.setOrderID(orderID);
        orderDetails.setPaidVia("COD");
        orderDetails.setPaymentInfo("COD");
        orderDetails.setPrice("120");
        orderDetails.setIsPending(true);
        orderDetailsRepository.save(orderDetails);

        OrderIDDto orderIDDto = new OrderIDDto();
        orderIDDto.setOrderID(orderID);
        return orderIDDto;
    }

    public ResponseEntity<ResponseDto> createSimCard(Long id, NewSimDto newSimDto) {
        Optional<NetworkOperator> networkOperatorOptional = networkOperatorRepository.findById(id);
        if (networkOperatorOptional.isEmpty()) {
            throw new NetworkOperatorNotFoundException("Network operator not found for ID: " + id);
        }
        NetworkOperator networkOperator = networkOperatorOptional.get();

        IMSIDto imsiDto = new IMSIDto(networkOperator.getMcc(), networkOperator.getMnc(),
                networkOperator.getOperator(), networkOperator.getBrand());

        IMSI imsi = createIMSI(imsiDto);
        ICCID iccid = createICCID(imsiDto);
        MSISDN msisdn = createMSISDN();
        User user = createUser(newSimDto);
        OrderDetails orderDetails = processOrderDetails(newSimDto.getOrderID());
        createSimCard(newSimDto, iccid, imsi, msisdn, user, orderDetails, createAddress(newSimDto));

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseBody("Sim card created successfully");
        return ResponseEntity.ok(responseDto);
    }

    private IMSI createIMSI(IMSIDto imsiDto) {
        IMSI imsi = new IMSI();
        imsi.setMcc(imsiDto.getMcc());
        imsi.setMnc(imsiDto.getMnc());
        String msin = imsi.generateRandomMSIN();
        imsi.setMsin(msin);
        imsi.setImsiID(imsiDto.getMcc() + imsiDto.getMnc() + msin);
        imsiRepository.save(imsi);
        return imsi;
    }

    private ICCID createICCID(IMSIDto imsiDto) {
        ICCID iccid = new ICCID();
        iccid.setMnc(imsiDto.getMnc());
        String randomIAN = iccid.generateRandomIAN();
        iccid.setIan(randomIAN);
        String iccidWithoutCheckDigit = "89" + "91" + imsiDto.getMnc() + randomIAN;
        String checkDigit = iccid.calculateCheckDigit(iccidWithoutCheckDigit);
        iccid.setX(checkDigit);
        iccid.setIccidID("89" + "91" + imsiDto.getMnc() + randomIAN + checkDigit);
        iccidRepository.save(iccid);
        return iccid;
    }

    private MSISDN createMSISDN() {
        MSISDN msisdn = new MSISDN();
        String nsn = msisdn.generateIndianMobileNumber();
        msisdn.setNsn(nsn);
        msisdn.setMsisdnID("91" + nsn);
        msisdnRepository.save(msisdn);
        return msisdn;
    }

    private User createUser(NewSimDto newSimDto) {
        User user = new User();
        user.setFirstName(newSimDto.getFirstName());
        user.setLastName(newSimDto.getLastName());
        user.setEmail(newSimDto.getEmail());
        userRepository.save(user);
        return user;
    }

    private SimCard createSimCard(NewSimDto newSimDto, ICCID iccid, IMSI imsi, MSISDN msisdn, User user,
            OrderDetails orderDetails, Address address) {
        SimCard simCard = new SimCard();
        simCard.setIccid(iccid);
        simCard.setImsi(imsi);
        simCard.setMsisdn(msisdn);
        simCard.setExistingNumber(newSimDto.getExistingNumber());
        simCard.setType(newSimDto.getType());
        simCard.setAadhaarCard(newSimDto.getAadhaarCard());
        simCard.setUser(user);
        simCard.setOrderDetails(orderDetails);
        simCard.setAddresses(address);
        simCardRepository.save(simCard);
        return simCard;
    }

    private Address createAddress(NewSimDto newSimDto) {
        Address address = new Address();
        address.setAddressLine1(newSimDto.getAddressLine1());
        address.setAddressLine2(newSimDto.getAddressLine2());
        address.setCity(newSimDto.getCity());
        address.setPincode(newSimDto.getPincode());
        address.setState(newSimDto.getState());
        addressRepository.save(address);
        return address;
    }

    private OrderDetails processOrderDetails(String orderID) {
        OrderDetails orderDetails = orderDetailsRepository.findByOrderID(orderID);
        orderDetails.setStatus("Success");
        orderDetails.setIsPending(false);
        orderDetailsRepository.save(orderDetails);
        return orderDetails;
    }

    public NewSimOrderStatusDto getDetails(String orderID) {
        NewSimOrderStatusDto newSimOrderStatusDto = new NewSimOrderStatusDto();
        OrderDetails orderDetails = orderDetailsRepository.findByOrderID(orderID);
        newSimOrderStatusDto.setOrderTime(orderDetails.getOrderTime());
        newSimOrderStatusDto.setPaidVia(orderDetails.getPaidVia());
        newSimOrderStatusDto.setPrice(orderDetails.getPrice());
        newSimOrderStatusDto.setStatus(orderDetails.getStatus());

        SimCard simCard = simCardRepository.findByOrderDetails(orderDetails);
        newSimOrderStatusDto.setExistingNumber(simCard.getExistingNumber());

        Optional<User> user = userRepository.findById(simCard.getUser().getId());
        newSimOrderStatusDto.setName(user.get().getFirstName() + " " + user.get().getLastName());
        newSimOrderStatusDto.setEmail(user.get().getEmail());

        Optional<Address> address = addressRepository.findById(simCard.getAddresses().getId());
        newSimOrderStatusDto.setAddress(address.get().getAddressLine1() + "," + address.get().getAddressLine2() + ","
                + address.get().getCity() + "," + address.get().getState() + "," + address.get().getPincode() + ".");

        Optional<MSISDN> msisdn = msisdnRepository.findById(simCard.getMsisdn().getId());
        newSimOrderStatusDto.setNewSimNumber(msisdn.get().getNsn());

        return newSimOrderStatusDto;
    }

}
