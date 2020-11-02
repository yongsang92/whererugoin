package com.whereareyougoing.www.demo.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTimeData is a Querydsl query type for TimeData
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QTimeData extends BeanPath<TimeData> {

    private static final long serialVersionUID = -94891040L;

    public static final QTimeData timeData = new QTimeData("timeData");

    public final NumberPath<Integer> time1 = createNumber("time1", Integer.class);

    public final NumberPath<Integer> time2 = createNumber("time2", Integer.class);

    public final NumberPath<Integer> time3 = createNumber("time3", Integer.class);

    public final NumberPath<Integer> time4 = createNumber("time4", Integer.class);

    public final NumberPath<Integer> time5 = createNumber("time5", Integer.class);

    public final NumberPath<Integer> time6 = createNumber("time6", Integer.class);

    public QTimeData(String variable) {
        super(TimeData.class, forVariable(variable));
    }

    public QTimeData(Path<? extends TimeData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTimeData(PathMetadata metadata) {
        super(TimeData.class, metadata);
    }

}

