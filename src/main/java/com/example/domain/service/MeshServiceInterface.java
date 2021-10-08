package com.example.domain.service;

import com.example.domain.entity.MeshEntity;

public interface MeshServiceInterface {
    MeshEntity addMesh(MeshEntity toAdd);

    MeshEntity getByIdMesh(Long id);
}
