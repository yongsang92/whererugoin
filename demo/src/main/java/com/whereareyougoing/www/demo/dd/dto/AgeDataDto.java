package com.whereareyougoing.www.demo.dd.dto;

import com.whereareyougoing.www.demo.data.AgeData;

import lombok.Builder;
import lombok.ToString;

@ToString
public class AgeDataDto {
    private int teen; 
    private int twenty;
    private int thirty;
    private int forty;
    private int fifty;
    private int sixty;


    @Builder
    public AgeDataDto(String teen, String twenty, String thirty, String forty
    , String fifty, String sixty) {
        this.teen = Integer.parseInt(teen);
        this.twenty = Integer.parseInt(twenty);
        this.thirty =Integer.parseInt(thirty);
        this.forty = Integer.parseInt(forty);
        this.fifty = Integer.parseInt(fifty);
        this.sixty = Integer.parseInt(sixty);
    }

    public AgeData toAgeData(){
        return AgeData.builder().teen(teen).twenty(twenty).thirty(thirty)
        .forty(forty).fifty(fifty).sixty(sixty).build();
    }
}
