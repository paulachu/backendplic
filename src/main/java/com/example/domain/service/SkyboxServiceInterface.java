package com.example.domain.service;

import com.example.domain.entity.LevelEntity;
import com.example.domain.entity.SkyboxEntity;

import java.util.List;

public interface SkyboxServiceInterface {
    SkyboxEntity addSkybox(SkyboxEntity toAdd);
    List<SkyboxEntity> getSkyboxs() throws Exception;
    SkyboxEntity getByIdSkybox(Long id) throws Exception;
}
