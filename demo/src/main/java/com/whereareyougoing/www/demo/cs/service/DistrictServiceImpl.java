package com.whereareyougoing.www.demo.cs.service;

import static com.whereareyougoing.www.demo.dd.domain.QDistrict.district;
import static com.whereareyougoing.www.demo.dd.domain.QFlow.flow;

import java.util.List;

import javax.transaction.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.whereareyougoing.www.demo.dd.domain.District;
import com.whereareyougoing.www.demo.dd.domain.Flow;
import com.whereareyougoing.www.demo.repo.DistrictRepo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepo districtRepo;
    private final JPAQueryFactory qf;


    
    @Override
    public List<String> findAllNames() {
       return qf.select(district.districtData.streetName).from(district).fetch();
    }

    @Override
    public List<District> findAll() {
        return districtRepo.findAll();
    }
    @Override
    public Page<District> findAllwithPaging(Pageable paging) {
        return districtRepo.findAll(paging);
    }

    @Transactional
    @Override
    public List<District> findAllDistrictWithFlow() {
        return qf.selectFrom(district).leftJoin(district.flows, flow).fetchJoin().fetch();

    }

    @Override
    public void save(District district) {
        districtRepo.save(district);

    }

    @Transactional
    @Override
    public District findOneByCode(int code) {
        return qf.selectFrom(district).where(district.districtData.code.eq(code)).fetchOne();
    }

    @Transactional
    @Override
    public District findByStreetName(String streetName) {
        return qf.selectFrom(district).where(district.districtData.streetName.eq(streetName)).fetchOne();
    }

    @Transactional
    @Override
    public List<Flow> findStreetNameWithYear(String streetName, int year) {
        return qf.selectFrom(flow).join(flow.district, district)
                .where(district.districtData.streetName.eq(streetName), flow.yearData.year.eq(year)).fetch();

    }

    @Transactional
    @Override
    public Flow findStreetNameWithYearAndQuarter(String streetName, int year, int quarter) {
        return qf.selectFrom(flow).join(flow.district, district).where(district.districtData.streetName.eq(streetName),
                flow.yearData.year.eq(year), flow.yearData.quarter.eq(quarter)).fetchOne();
    }


  

 

}