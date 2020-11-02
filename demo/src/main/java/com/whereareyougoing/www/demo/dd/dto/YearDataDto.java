package com.whereareyougoing.www.demo.dd.dto;

import com.whereareyougoing.www.demo.data.YearData;

import lombok.Builder;
import lombok.ToString;

@ToString
public class YearDataDto {
    private Integer year; 
    private Integer quarter;

    
    @Builder
    public YearDataDto(String year, String quarter) {
        this.year =  Integer.parseInt(year);
        this.quarter = Integer.parseInt(quarter);
    }
    public YearData toYearData(){
        return YearData.builder().year(year).quarter(quarter).build();
    }

}
