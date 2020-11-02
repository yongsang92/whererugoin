package com.whereareyougoing.www.demo.dd.dto;

import lombok.Data;

@Data
public class FlowResult<T> {

    private String streetName;

    private T flows;


    public FlowResult(String streetName,T flows){
        this.streetName=streetName;
        this.flows=flows;
    }

}