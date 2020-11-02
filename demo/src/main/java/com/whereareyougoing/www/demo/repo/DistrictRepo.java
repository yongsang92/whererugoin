package com.whereareyougoing.www.demo.repo;

import java.util.List;

import com.whereareyougoing.www.demo.dd.domain.District;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface DistrictRepo extends CrudRepository<District, Long> {
    List<District> findAll();
    Page<District> findAll(Pageable paging);
}