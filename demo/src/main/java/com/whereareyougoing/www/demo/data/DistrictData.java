package com.whereareyougoing.www.demo.data;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DistrictData {
    private int code; 
    private String streetName;

    
    @Builder
    public DistrictData(int code, String streetName) {
        this.code = code;
        this.streetName = streetName;
    }

   
    

}