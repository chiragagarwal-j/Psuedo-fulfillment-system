package com.pfsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pfsystem.dto.IMSIDto;
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

import jakarta.transaction.Transactional;

@Service
public class OrderingSimService {

    @Autowired
    private NetworkOperatorRepository networkOperatorRepository;

    @Autowired
    private IMSIRepository imsiRepository;

    @Autowired
    private ICCIDRepository iccidRepository;

    @Autowired
    private MSISDNRepository msisdnRepository;

    @Autowired
    private SimCardRepository simCardRepository;

    public List<NetworkOperatorDto> getNetworkOperatorDetails() {
        List<NetworkOperator> networkOperators = networkOperatorRepository
                .getNetworkOperatorDetailsByCountryCodeAndStatus();
        List<NetworkOperatorDto> networkOperatorDtos = networkOperators.stream()
                .map(networkOperator -> new NetworkOperatorDto(networkOperator.getId(), networkOperator.getOperator(),
                        networkOperator.getBrand()))
                .collect(Collectors.toList());
        return networkOperatorDtos;
    }

    @Transactional
    public ResponseEntity<String> createSimCard(Long id) {
        Optional<NetworkOperator> networkOperatorOptional = networkOperatorRepository.findById(id);

        if (networkOperatorOptional.isEmpty()) {
            throw new NetworkOperatorNotFoundException("Network operator not found for ID: " + id);
        }
        SimCard simCard = new SimCard();
        NetworkOperator networkOperator = networkOperatorOptional.get();
        IMSIDto imsiDto = new IMSIDto(networkOperator.getMCC(), networkOperator.getMNC(),
                networkOperator.getOperator(), networkOperator.getBrand());

        IMSI imsi = new IMSI();
        imsi.setMCC(imsiDto.getMCC());
        imsi.setMNC(imsiDto.getMNC());
        String MSIN = imsi.generateRandomMSIN();
        imsi.setMSIN(MSIN);
        imsi.setIMSIid(imsiDto.getMCC() + imsiDto.getMNC() + MSIN);
        imsiRepository.save(imsi);

        ICCID iccid = new ICCID();
        iccid.setMNC(imsiDto.getMNC());
        String randomIAN = iccid.generateRandomIAN();
        iccid.setIAN(randomIAN);
        String iccidWithoutCheckDigit = "89" + "91" + imsiDto.getMNC() + randomIAN;
        String checkDigit = iccid.calculateCheckDigit(iccidWithoutCheckDigit);
        iccid.setX(checkDigit);
        iccid.setICCIDid("89" + "91" + imsiDto.getMNC() + randomIAN + checkDigit);
        iccidRepository.save(iccid);

        MSISDN msisdn = new MSISDN();
        String NSN = msisdn.generateIndianMobileNumber();
        msisdn.setNSN(NSN);
        msisdn.setMSISDNid("91" + NSN);
        msisdnRepository.save(msisdn);

        simCard.setICCID(iccid);
        simCard.setIMSI(imsi);
        simCard.setMSISDN(msisdn);
        simCard.setUser(null);
        simCardRepository.save(simCard);
        return ResponseEntity.ok("Sim card created successfully");
    }

    public List<SimCard> getAll(){
        List<SimCard> allCards= simCardRepository.findAll();
        return allCards;
    }

}
