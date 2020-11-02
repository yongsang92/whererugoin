package com.whereareyougoing.www.demo.dd.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlow is a Querydsl query type for Flow
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFlow extends EntityPathBase<Flow> {

    private static final long serialVersionUID = 1025101445L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlow flow = new QFlow("flow");

    public final QTime _super = new QTime(this);

    public final com.whereareyougoing.www.demo.data.QAgeData ageData;

    public final QDistrict district;

    public final com.whereareyougoing.www.demo.data.QGenderData genderData;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regdate = _super.regdate;

    public final com.whereareyougoing.www.demo.data.QSevenDayData sevenDayData;

    public final com.whereareyougoing.www.demo.data.QTimeData timeData;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedate = _super.updatedate;

    public final com.whereareyougoing.www.demo.data.QYearData yearData;

    public QFlow(String variable) {
        this(Flow.class, forVariable(variable), INITS);
    }

    public QFlow(Path<? extends Flow> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFlow(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFlow(PathMetadata metadata, PathInits inits) {
        this(Flow.class, metadata, inits);
    }

    public QFlow(Class<? extends Flow> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ageData = inits.isInitialized("ageData") ? new com.whereareyougoing.www.demo.data.QAgeData(forProperty("ageData")) : null;
        this.district = inits.isInitialized("district") ? new QDistrict(forProperty("district"), inits.get("district")) : null;
        this.genderData = inits.isInitialized("genderData") ? new com.whereareyougoing.www.demo.data.QGenderData(forProperty("genderData")) : null;
        this.sevenDayData = inits.isInitialized("sevenDayData") ? new com.whereareyougoing.www.demo.data.QSevenDayData(forProperty("sevenDayData")) : null;
        this.timeData = inits.isInitialized("timeData") ? new com.whereareyougoing.www.demo.data.QTimeData(forProperty("timeData")) : null;
        this.yearData = inits.isInitialized("yearData") ? new com.whereareyougoing.www.demo.data.QYearData(forProperty("yearData")) : null;
    }

}

