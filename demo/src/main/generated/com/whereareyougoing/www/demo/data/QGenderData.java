package com.whereareyougoing.www.demo.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGenderData is a Querydsl query type for GenderData
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QGenderData extends BeanPath<GenderData> {

    private static final long serialVersionUID = 2012924820L;

    public static final QGenderData genderData = new QGenderData("genderData");

    public final NumberPath<Integer> man = createNumber("man", Integer.class);

    public final NumberPath<Integer> woman = createNumber("woman", Integer.class);

    public QGenderData(String variable) {
        super(GenderData.class, forVariable(variable));
    }

    public QGenderData(Path<? extends GenderData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGenderData(PathMetadata metadata) {
        super(GenderData.class, metadata);
    }

}

