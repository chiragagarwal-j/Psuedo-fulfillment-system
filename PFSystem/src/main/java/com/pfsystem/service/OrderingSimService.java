package com.pfsystem.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pfsystem.dto.IMSIDto;
import com.pfsystem.dto.NetworkOperatorDto;
import com.pfsystem.dto.NewSimDto;
import com.pfsystem.dto.ResponseDto;
import com.pfsystem.entities.Address;
import com.pfsystem.entities.ICCID;
import com.pfsystem.entities.IMSI;
import com.pfsystem.entities.MSISDN;
import com.pfsystem.entities.NetworkOperator;
import com.pfsystem.entities.SimCard;
import com.pfsystem.entities.User;
import com.pfsystem.exceptions.NetworkOperatorNotFoundException;
import com.pfsystem.repository.AddressRepository;
import com.pfsystem.repository.ICCIDRepository;
import com.pfsystem.repository.IMSIRepository;
import com.pfsystem.repository.MSISDNRepository;
import com.pfsystem.repository.NetworkOperatorRepository;
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

    public List<NetworkOperatorDto> getNetworkOperatorDetails() {
        List<NetworkOperator> networkOperators = networkOperatorRepository
                .getNetworkOperatorDetailsByCountryCodeAndStatus();
        return networkOperators.stream()
                .map(networkOperator -> new NetworkOperatorDto(networkOperator.getId(), networkOperator.getOperator(),
                        networkOperator.getBrand()))
                .toList();
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
        createSimCard(newSimDto, iccid, imsi, msisdn, user);
        createAddress(newSimDto, user);
    
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
    
    private SimCard createSimCard(NewSimDto newSimDto, ICCID iccid, IMSI imsi, MSISDN msisdn, User user) {
        SimCard simCard = new SimCard();
        simCard.setIccid(iccid);
        simCard.setImsi(imsi);
        simCard.setMsisdn(msisdn);
        simCard.setExistingNumber(newSimDto.getExistingNumber());
        simCard.setType(newSimDto.getType());
        simCard.setAadhaarCard(newSimDto.getAadhaarCard());
        simCard.setUser(user);
        simCardRepository.save(simCard);
        return simCard;
    }
    
    private Address createAddress(NewSimDto newSimDto, User user) {
        Address address = new Address();
        address.setAddressLine1(newSimDto.getAddressLine1());
        address.setAddressLine2(newSimDto.getAddressLine2());
        address.setCity(newSimDto.getCity());
        address.setPincode(newSimDto.getPincode());
        address.setState(newSimDto.getState());
        address.setUser(user);
        addressRepository.save(address);
        return address;
    }
    

    public List<SimCard> getAll() {
        return simCardRepository.findAll();
    }

}
