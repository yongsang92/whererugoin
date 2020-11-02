package com.whereareyougoing.www.demo.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.whereareyougoing.www.demo.dd.dto.AgeDataDto;
import com.whereareyougoing.www.demo.dd.dto.DistrictDataDto;
import com.whereareyougoing.www.demo.dd.dto.GenderDataDto;
import com.whereareyougoing.www.demo.dd.dto.SevenDayDataDto;
import com.whereareyougoing.www.demo.dd.dto.TimeDataDto;
import com.whereareyougoing.www.demo.dd.dto.YearDataDto;

import org.springframework.stereotype.Component;

@Component
public class CsvDataSorter {

    public List<String> arrayToList(String[] next) {
        return Arrays.asList(next);
    }

    public DistrictDataDto extractDistrictData(List<String> dataList) {

        Iterator<String> iter = getDistrictDataList(dataList).iterator();

        while (iter.hasNext()) {
            String code = iter.next();
            String streetName = iter.next();
            return DistrictDataDto.builder().code(code).streetName(streetName).build();
        }
        return null;

    }

    private List<String> getDistrictDataList(List<String> dataList) {
        return new ArrayList<>(dataList.subList(0, 2));
    }

    public YearDataDto extractYearData(List<String> dataList) {

        Iterator<String> iter = extractYearDataList(dataList).iterator();

        while (iter.hasNext()) {
            String year = iter.next();
            String quarter = iter.next();
            return YearDataDto.builder().year(year).quarter(quarter).build();
        }
        return null;

    }

    private List<String> extractYearDataList(List<String> dataList) {
        return new ArrayList<>(dataList.subList(2, 4));
    }

    public GenderDataDto extractGenderData(List<String> dataList) {
        Iterator<String> iter = extractGenderDataList(dataList).iterator();
        while (iter.hasNext()) {
            String man = iter.next();
            String woman = iter.next();
            return GenderDataDto.builder().man(man).woman(woman).build();
        }
        return null;
    }

    private List<String> extractGenderDataList(List<String> dataList) {
        return new ArrayList<>(dataList.subList(4, 6));
    }

    public AgeDataDto extractAgeData(List<String> dataList) {
        Iterator<String> iter = extractAgeDataList(dataList).iterator();
        while (iter.hasNext()) {
            String teen = iter.next();
            String twenty = iter.next();
            String thirty = iter.next();
            String forty = iter.next();
            String fifty = iter.next();
            String sixty = iter.next();
            return AgeDataDto.builder().teen(teen).twenty(twenty).thirty(thirty).forty(forty).fifty(fifty).sixty(sixty)
                    .build();
        }
        return null;
    }

    private List<String> extractAgeDataList(List<String> dataList) {
        return new ArrayList<>(dataList.subList(6, 12));
    }

    public TimeDataDto extractTimeData(List<String> dataList) {
        Iterator<String> iter = extractTimeDataList(dataList).iterator();
        while (iter.hasNext()) {
            String time1 = iter.next();
            String time2 = iter.next();
            String time3 = iter.next();
            String time4 = iter.next();
            String time5 = iter.next();
            String time6 = iter.next();
            return TimeDataDto.builder().time1(time1).time2(time2).time3(time3).time4(time4).time5(time5).time6(time6)
                    .build();
        }
        return null;

    }

    private List<String> extractTimeDataList(List<String> dataList) {
        return new ArrayList<>(dataList.subList(12, 18));
    }

    public SevenDayDataDto extractSevenDayData(List<String> dataList) {
        Iterator<String> iter = extractSevenDayDataList(dataList).iterator();
        while (iter.hasNext()) {
            String mon = iter.next();
            String tue = iter.next();
            String wen = iter.next();
            String thu = iter.next();
            String fri = iter.next();
            String sat = iter.next();
            String sun = iter.next();
            return SevenDayDataDto.builder().mon(mon).tue(tue).wen(wen).thu(thu).fri(fri).sat(sat).sun(sun).build();

        }

        return null;
    }

    private List<String> extractSevenDayDataList(List<String> dataList) {
        return new ArrayList<>(dataList.subList(18, dataList.size()));
    }

}