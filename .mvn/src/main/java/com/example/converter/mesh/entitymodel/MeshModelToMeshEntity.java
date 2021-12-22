package com.example.converter.mesh.entitymodel;

import com.example.converter.Converter;
import com.example.data.model.MeshModel;
import com.example.domain.entity.MeshEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MeshModelToMeshEntity implements Converter<MeshModel, MeshEntity> {
    @Override
    public MeshEntity convertNotNull(MeshModel from) {
        return new MeshEntity().withId(from.getId()).withFilename(from.getFilename());
    }
}
