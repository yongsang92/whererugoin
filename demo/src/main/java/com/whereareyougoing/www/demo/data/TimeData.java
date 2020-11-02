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
public class TimeData {
    
    private int time1; // 00:01~06:00
    private int time2; // ~11:00
    private int time3; // ~14:00
    private int time4; // ~17:00
    private int time5; // ~21:00
    private int time6; // ~24:00


    @Builder
    public TimeData(int time1, int time2, int time3, int time4, int time5, int time6) {
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
        this.time4 = time4;
        this.time5 = time5;
        this.time6 = time6;
    }

  
}