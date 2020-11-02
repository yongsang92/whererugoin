package com.whereareyougoing.www.demo.dd.dto;

import com.whereareyougoing.www.demo.data.DistrictData;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class DistrictDataDto {

    int code;
    String streetName;
    
    @Builder
    public DistrictDataDto(String code, String streetName) {
        
        this.code = Integer.parseInt(code);
        this.streetName = streetName;
    }



    public DistrictData toDistrictData(){
        return DistrictData.builder().code(code).streetName(streetName).build();
    }
 
}
