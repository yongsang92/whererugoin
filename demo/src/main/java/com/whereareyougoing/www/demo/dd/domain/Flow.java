package com.whereareyougoing.www.demo.dd.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.whereareyougoing.www.demo.data.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(exclude = { "district" })
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Flow extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    YearData yearData;
    @Embedded
    GenderData genderData;
    @Embedded
    AgeData ageData;
    @Embedded
    TimeData timeData;
    @Embedded
    SevenDayData sevenDayData;

    @ManyToOne
    @JoinColumn(name = "DISTRICT_ID")
    @JsonIgnore
    private District district;

    @Builder
    public Flow(YearData yearData, GenderData genderData, AgeData ageData, TimeData timeData,
            SevenDayData sevenDayData) {
        this.yearData = yearData;
        this.genderData = genderData;
        this.ageData = ageData;
        this.timeData = timeData;
        this.sevenDayData = sevenDayData;

    }

    public void setDistrict(District district) {

        this.district = district;

    }

}