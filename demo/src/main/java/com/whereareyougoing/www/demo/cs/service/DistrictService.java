package com.whereareyougoing.www.demo.cs.service;

import java.util.List;

import com.whereareyougoing.www.demo.dd.domain.District;
import com.whereareyougoing.www.demo.dd.domain.Flow;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DistrictService {
    public List<String> findAllNames();
    public List<District> findAll();
    public Page<District> findAllwithPaging(Pageable paging);
    public List<District> findAllDistrictWithFlow();
    public void save(District district);
    public District findOneByCode(int code);
    public District findByStreetName(String streetName);
    public List<Flow> findStreetNameWithYear(String streetName,int year);
    public Flow findStreetNameWithYearAndQuarter(String streetName,int year,int quarter);
   
}