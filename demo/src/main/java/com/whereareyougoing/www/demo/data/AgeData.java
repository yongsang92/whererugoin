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
public class AgeData {
    private int teen; 
    private int twenty;
    private int thirty;
    private int forty;
    private int fifty;
    private int sixty;


    @Builder
    public AgeData(int teen, int twenty, int thirty, int forty, int fifty, int sixty) {
        this.teen = teen;
        this.twenty = twenty;
        this.thirty = thirty;
        this.forty = forty;
        this.fifty = fifty;
        this.sixty = sixty;
    }

}