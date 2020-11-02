package com.whereareyougoing.www.demo.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QYearData is a Querydsl query type for YearData
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QYearData extends BeanPath<YearData> {

    private static final long serialVersionUID = 442634576L;

    public static final QYearData yearData = new QYearData("yearData");

    public final NumberPath<Integer> quarter = createNumber("quarter", Integer.class);

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QYearData(String variable) {
        super(YearData.class, forVariable(variable));
    }

    public QYearData(Path<? extends YearData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QYearData(PathMetadata metadata) {
        super(YearData.class, metadata);
    }

}

