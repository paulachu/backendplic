package com.example.domain.service;

import com.example.domain.entity.LevelEntity;
import com.example.domain.entity.MeshEntity;

import java.util.List;

public interface MeshServiceInterface {
    MeshEntity addMesh(MeshEntity toAdd);

    MeshEntity getByIdMesh(Long id) throws Exception;

    List<MeshEntity> getMeshs() throws Exception;

    boolean deleteMesh(Long id);
}
