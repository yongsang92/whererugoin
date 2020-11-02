package com.whereareyougoing.www.demo.dd.dto;

import com.whereareyougoing.www.demo.data.GenderData;

import lombok.Builder;
import lombok.ToString;

@ToString
public class GenderDataDto {
    private int man; // man: 남자 유동인구
    private int woman; // woman: 여자 유동인구

    
    @Builder
    public GenderDataDto(String man, String woman) {
        
        this.man = Integer.parseInt(man);
        this.woman = Integer.parseInt(woman);
    }

    public GenderData toGenderData(){
        return GenderData.builder().man(man).woman(woman).build();
    }


}
