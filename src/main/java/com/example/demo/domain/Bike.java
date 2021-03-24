package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Bike {
    
    private int id;
    private String brName;
    private String brClassification;
    private String nameAddress;
    private String numberAddress;
    private double lat;
    private double lon;
    private String startTime;
    private String endTime;
    private String closedDay;
    private String fee;
    private String utilizationFee;
    private Integer holdingNumber;
    private Integer bikeholdNumber;
    private String bikePump;
    private String bikePumpType;
    private String bikeRepairer;
    private String agencyPhoneNumber;
    private String agencyName;
    private String dataDate;
    private String organizationCode;
    private String organizationName;
}
