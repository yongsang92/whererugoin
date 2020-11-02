package com.whereareyougoing.www.demo.data;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Getter
@ToString
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SevenDayData {
    private int mon;
    private int tue;
    private int wen;
    private int thu;
    private int fri;
    private int sat;
    private int sun;


    @Builder
    public SevenDayData(int mon, int tue, int wen, int thu, int fri, int sat, int sun) {
        this.mon = mon;
        this.tue = tue;
        this.wen = wen;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
    }
   

}