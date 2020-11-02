package com.whereareyougoing.www.demo.dd.dto;

import com.whereareyougoing.www.demo.data.SevenDayData;

import lombok.Builder;
import lombok.ToString;

@ToString
public class SevenDayDataDto {

    private int mon;
    private int tue;
    private int wen;
    private int thu;
    private int fri;
    private int sat;
    private int sun;

    @Builder
    public SevenDayDataDto(String mon, String tue, String wen, String thu, String fri, String sat, String sun) {
        this.mon = Integer.parseInt(mon);
        this.tue = Integer.parseInt(tue);
        this.wen = Integer.parseInt(wen);
        this.thu = Integer.parseInt(thu);
        this.fri = Integer.parseInt(fri);
        this.sat = Integer.parseInt(sat);
        this.sun = Integer.parseInt(sun);
    }

    public SevenDayData toSevenDayData() {
        return SevenDayData.builder().mon(mon).tue(tue).wen(wen).thu(thu).fri(fri).sat(sat).sun(sun).build();
    }
}
