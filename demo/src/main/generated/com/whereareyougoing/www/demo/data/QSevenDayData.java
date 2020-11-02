package com.whereareyougoing.www.demo.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSevenDayData is a Querydsl query type for SevenDayData
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QSevenDayData extends BeanPath<SevenDayData> {

    private static final long serialVersionUID = 1335810018L;

    public static final QSevenDayData sevenDayData = new QSevenDayData("sevenDayData");

    public final NumberPath<Integer> fri = createNumber("fri", Integer.class);

    public final NumberPath<Integer> mon = createNumber("mon", Integer.class);

    public final NumberPath<Integer> sat = createNumber("sat", Integer.class);

    public final NumberPath<Integer> sun = createNumber("sun", Integer.class);

    public final NumberPath<Integer> thu = createNumber("thu", Integer.class);

    public final NumberPath<Integer> tue = createNumber("tue", Integer.class);

    public final NumberPath<Integer> wen = createNumber("wen", Integer.class);

    public QSevenDayData(String variable) {
        super(SevenDayData.class, forVariable(variable));
    }

    public QSevenDayData(Path<? extends SevenDayData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSevenDayData(PathMetadata metadata) {
        super(SevenDayData.class, metadata);
    }

}

