package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Sample {
    
    private int gid;
    private int ctprvn_cd;
    private String ctp_eng_nm;
    private String ctp_kor_nm;
    private String geom;

}