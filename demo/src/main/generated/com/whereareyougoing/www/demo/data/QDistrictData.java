package com.whereareyougoing.www.demo.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDistrictData is a Querydsl query type for DistrictData
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QDistrictData extends BeanPath<DistrictData> {

    private static final long serialVersionUID = 1256038305L;

    public static final QDistrictData districtData = new QDistrictData("districtData");

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    public final StringPath streetName = createString("streetName");

    public QDistrictData(String variable) {
        super(DistrictData.class, forVariable(variable));
    }

    public QDistrictData(Path<? extends DistrictData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDistrictData(PathMetadata metadata) {
        super(DistrictData.class, metadata);
    }

}

