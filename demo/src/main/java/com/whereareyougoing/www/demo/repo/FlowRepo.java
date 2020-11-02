package com.whereareyougoing.www.demo.repo;


import com.whereareyougoing.www.demo.dd.domain.Flow;

import org.springframework.data.repository.CrudRepository;

public interface FlowRepo extends CrudRepository<Flow, Long> {
  
}