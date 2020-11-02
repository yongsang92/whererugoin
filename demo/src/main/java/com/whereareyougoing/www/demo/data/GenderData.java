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
public class GenderData {
    private int man; // man: 남자 유동인구
    private int woman; // woman: 여자 유동인구

    
    @Builder
    public GenderData(int man, int woman) {
        this.man = man;
        this.woman = woman;
    }

    

}