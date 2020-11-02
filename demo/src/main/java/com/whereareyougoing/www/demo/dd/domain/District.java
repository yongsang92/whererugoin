package com.whereareyougoing.www.demo.dd.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.whereareyougoing.www.demo.data.DistrictData;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id",callSuper =false)
public class District extends Time{

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Embedded
    DistrictData districtData; 
    

    @OneToMany(mappedBy = "district",cascade = CascadeType.ALL)
    private List<Flow> flows=new ArrayList<>();

    @Builder
    public District(DistrictData districtData){
        this.districtData=districtData;
    }
 
  
    public void addFlow(Flow flow){
        flows.add(flow);
        flow.setDistrict(this);
    }
  
  


}