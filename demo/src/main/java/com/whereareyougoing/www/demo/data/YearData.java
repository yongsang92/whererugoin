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
public class YearData {
    private int year; 
    private Integer quarter;

    
    @Builder
    public YearData(Integer year, Integer quarter) {
        this.year = year;
        this.quarter = quarter;
    }

    

}