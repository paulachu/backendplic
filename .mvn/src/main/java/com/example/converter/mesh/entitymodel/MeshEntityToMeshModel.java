package com.example.converter.mesh.entitymodel;

import com.example.converter.Converter;
import com.example.data.model.MeshModel;
import com.example.domain.entity.MeshEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MeshEntityToMeshModel implements Converter<MeshEntity, MeshModel> {
    @Override
    public MeshModel convertNotNull(MeshEntity from) {
        return new MeshModel().withId(from.getId()).withFilename(from.getFilename());
    }
}
