package com.whereareyougoing.www.demo.cs.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.whereareyougoing.www.demo.config.KorUrlConverter;
import com.whereareyougoing.www.demo.cs.service.DistrictService;
import com.whereareyougoing.www.demo.dd.domain.District;
import com.whereareyougoing.www.demo.dd.domain.Flow;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictService districtService;
    private final KorUrlConverter korUrlConverter;


    /*검색할 수 있게 모든 지역의 이름을 제공합니다  */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.FOUND)
    @GetMapping(value = "/districts/names", produces = "application/hal+json;charset=UTF-8")
    public ResponseEntity<?>  getAllNames(){

        List<String> result=districtService.findAllNames();
        
        Iterator<String> iter=result.iterator();
        List<EntityModel<Street>> wow=new ArrayList<>();

        while(iter.hasNext()){
            Street st=new Street();
            st.setName(iter.next());
            EntityModel<Street> em=new EntityModel<>(st);
            wow.add(em);
        }
        
        CollectionModel<EntityModel<Street>> wow2=new CollectionModel<>(wow);
    

        return ResponseEntity.ok().body(wow2);
    }
    @EqualsAndHashCode
    @Getter
    static class Street{
        String name;


        public void setName(String name){
            this.name=name;
        }
    }

   /*
     * 모든 지역을 가져와서 지역이름으로 하이퍼링크를 만들어서 제공합니다 지역을 클릭하면 지역의 유동인구 정보를 볼 수 있습니다. 
     * 지역수가 많기 때문에 페이징을 적용해서 전달해줍니다 
     * 2014년 1월부터 2019년12월까지 제공 # 2020년 3월2일 기준
     */
    @ResponseStatus(code = HttpStatus.FOUND)
    @GetMapping(value = "/districts", produces = "application/hal+json;charset=UTF-8")
    public ResponseEntity<?> findAll(Pageable pageable,PagedResourcesAssembler<District> assembler) throws UnsupportedEncodingException {
        Page<District> page = districtService.findAllwithPaging(pageable);

        PagedModel<EntityModel<District>> result = assembler.toModel(page, e -> {
            try {
                return new EntityModel<District>(e,
                linkTo(methodOn(DistrictController.class).findAll(null,null))
                .withSelfRel()
                ,korUrlConverter.toKorURL(linkTo(methodOn(DistrictController.class).findByName(e.getDistrictData().getStreetName()))
                .withRel("District")));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
                return null;
            }
        });
        return ResponseEntity.ok().body(result);
    }
    /*
     * 지역명을 제공하면 그에 따른 유동인구정보를 제공하는 메서드 입니다 
     * 예를 들면 계동길를 전달하면 계동길이 가진 모든 년도에 대한 유동인구를  보여줍니다 
     * 그리고 각 년도별 하이퍼 링크를 제공합니다 예를 들어 계동길/2014 , 계동길/2015  계동길/2016 하이퍼링크를 제공합니다
     */
    @GetMapping(value = "/district/{streetName}", produces = "application/hal+json;charset=UTF-8")
    public ResponseEntity<?> findByName(@PathVariable("streetName") String streetName)
            throws UnsupportedEncodingException {
        District district=districtService.findByStreetName(streetName);
        List<Integer> yearList=district.getFlows().stream().map(flow->flow.getYearData().getYear()).distinct().collect(Collectors.toList());
      

        EntityModel<District> result=new EntityModel<District>(district,
        korUrlConverter.toKorURL(linkTo(methodOn(DistrictController.class).findByName(streetName)).withSelfRel())
        );
         
        Iterator<Integer> iter=yearList.iterator();
        while(iter.hasNext()){
            int year=iter.next().intValue();
            result.add(
                korUrlConverter.toKorURL(linkTo(methodOn(DistrictController.class).findStreetNameWithYear(streetName,year)).withRel("year")).withRel("year")
            );            
        }
        return ResponseEntity.ok().body(result);
    }

    /*
     * 지역명+년도를 제공하면 그에 따른 유동인구정보를 제공하는 메서드 입니다 예를 들면 계동길 2014를 전달하면 계동길 2014년도 분기별
     * 유동이동 정보를 제공합니다 그리고 각 분기별 하이퍼 링크를 제공합니다 예를 들어 계동길/2014/1, 계동길/2014/2, 처럼 분기별
     * 하이퍼링크를 제공합니다
     */
    @GetMapping(value = "/district/{streetName}/{year}", produces = "application/hal+json;charset=UTF-8")
    public ResponseEntity<?> findStreetNameWithYear(@PathVariable("streetName") String streetName,
            @PathVariable("year") int year) throws UnsupportedEncodingException {

        
        List<Flow> flows = districtService.findStreetNameWithYear(streetName, year);
        List<EntityModel<Flow>> flowModel=new ArrayList<>();
        Iterator<Flow> iter=flows.iterator();

        List<Link> links=new ArrayList<>(); 
        // 원래 객체 에만 quarter 링크를 줬는데 Rest Docs를 쓸때 링크 정보를 줄려면 객체 안에 있는 _links를 사용하면 안된다
        // _links 그 자체를 사용해야되서 _links 그 자체에 quarter 링크를 줄려고 만든 객체이다
        
        while(iter.hasNext()){
            Flow flow=iter.next();
            links.add(korUrlConverter.toKorURL(    
                linkTo(methodOn(DistrictController.class).findStreetNameWithYearAndQuarter(streetName, year,flow.getYearData().getQuarter())
                ).withSelfRel()).withRel("quarter"));

            EntityModel<Flow> ef=new EntityModel<Flow>(flow,
            // 분기별 하이퍼링크
            korUrlConverter.toKorURL(    
                linkTo(methodOn(DistrictController.class).findStreetNameWithYearAndQuarter(streetName, year,flow.getYearData().getQuarter())
                ).withSelfRel()).withRel("quarter"));
                flowModel.add(ef);
        }
        Link self= korUrlConverter.toKorURL(
            linkTo(methodOn(DistrictController.class).findStreetNameWithYear(streetName, year)).withSelfRel()).withSelfRel();
        
        Link[] linkArray=new Link[links.size()+1];
        int size=1;
        linkArray[0]=self;
        for(Link link:links){
            linkArray[size++]=link;
        }
        CollectionModel<EntityModel<Flow>> result=new CollectionModel<>(flowModel,linkArray);
        return ResponseEntity.ok().body(result);
    
    }

    /*
     * 지역명+년도+분기 제공하면 그에 따른 유동인구정보를 제공하는 메서드 입니다
     * 예를 들면 계동길/2014/1 를 전달하면 계동길 2014년도 1분기
     * 유동이동 정보를 제공합니다 그리고 각 분기별 하이퍼 링크를 제공합니다 
     * 
     * 본인 하이퍼링크와 해당년도로 갈 수 있는 하이퍼링크를 제공합니다
     */
    @GetMapping(value = "/district/{streetName}/{year}/{quarter}", produces = "application/hal+json;charset=UTF-8")
    public ResponseEntity<?> findStreetNameWithYearAndQuarter(@PathVariable("streetName") String streetName,
            @PathVariable("year") int year, @PathVariable("quarter") int quarter) throws UnsupportedEncodingException {

         
        Flow flow = districtService.findStreetNameWithYearAndQuarter(streetName, year, quarter);
        EntityModel<Flow> result = new EntityModel<Flow>(flow,

        //자기 자신에 대한 하이퍼링크
        korUrlConverter.toKorURL(linkTo(methodOn(DistrictController.class)
            .findStreetNameWithYearAndQuarter(streetName, year, quarter)).withSelfRel()),
        
        //해당 년도에 대한 하이퍼링크 
        korUrlConverter.toKorURL(linkTo(methodOn(DistrictController.class)
            .findStreetNameWithYear(streetName, year)).withRel("year")).withRel("year")
        );
        return ResponseEntity.ok().body(result);
    }

}