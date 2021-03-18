package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Bike {
    
    private int id;
    private String geom;
    private String br_name;
    private String br_classification;
    private String name_address;
    private String number_address;
    private double lat;
    private double lon;
    private String start_time;
    private String end_time;
    private String closed_day;
    private String fee;
    private String utilization_fee;
    private Integer holding_number;
    private Integer bikehold_number;
    private String bike_pump;
    private String bike_pump_type;
    private String bike_repairer;
    private String agency_phone_number;
    private String agency_name;
    private String data_date;
    private String organization_code;
    private String organization_name;
}
