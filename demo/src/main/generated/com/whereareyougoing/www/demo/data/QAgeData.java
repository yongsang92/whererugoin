package com.whereareyougoing.www.demo.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAgeData is a Querydsl query type for AgeData
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QAgeData extends BeanPath<AgeData> {

    private static final long serialVersionUID = 1772657760L;

    public static final QAgeData ageData = new QAgeData("ageData");

    public final NumberPath<Integer> fifty = createNumber("fifty", Integer.class);

    public final NumberPath<Integer> forty = createNumber("forty", Integer.class);

    public final NumberPath<Integer> sixty = createNumber("sixty", Integer.class);

    public final NumberPath<Integer> teen = createNumber("teen", Integer.class);

    public final NumberPath<Integer> thirty = createNumber("thirty", Integer.class);

    public final NumberPath<Integer> twenty = createNumber("twenty", Integer.class);

    public QAgeData(String variable) {
        super(AgeData.class, forVariable(variable));
    }

    public QAgeData(Path<? extends AgeData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAgeData(PathMetadata metadata) {
        super(AgeData.class, metadata);
    }

}

