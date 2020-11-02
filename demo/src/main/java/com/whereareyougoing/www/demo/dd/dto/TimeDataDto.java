package com.whereareyougoing.www.demo.dd.dto;

import com.whereareyougoing.www.demo.data.TimeData;

import lombok.Builder;
import lombok.ToString;

@ToString
public class TimeDataDto {

    private int time1; // 00:01~06:00
    private int time2; // ~11:00
    private int time3; // ~14:00
    private int time4; // ~17:00
    private int time5; // ~21:00
    private int time6; // ~24:00

    @Builder
    public TimeDataDto(String time1, String time2, String time3, String time4, String time5, String time6) {
        this.time1 = Integer.parseInt(time1);
        this.time2 = Integer.parseInt(time2);
        this.time3 = Integer.parseInt(time3);
        this.time4 = Integer.parseInt(time4);
        this.time5 = Integer.parseInt(time5);
        this.time6 = Integer.parseInt(time6);
    }

    public TimeData toTimeData(){
        return TimeData.builder().time1(time1).time2(time2).time3(time3).time4(time4).time5(time5).time6(time6).build();
    }
}
