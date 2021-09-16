package com.example.converter.mesh.dtoentity;

import com.example.converter.Converter;
import com.example.domain.entity.MeshEntity;
import com.example.presentation.mesh.AddMeshResponse;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MeshEntityToAddMeshResponse implements Converter<MeshEntity, AddMeshResponse> {
    @Override
    public AddMeshResponse convertNotNull(MeshEntity from) {
        return new AddMeshResponse().withId(from.getId()).withFilename(from.getFilename()).withPresignedUrl(from.getPresignedUrl());
    }
}
