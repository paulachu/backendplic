package com.example.domain.service;

import com.example.domain.entity.SkyboxEntity;

public interface SkyboxServiceInterface {
    SkyboxEntity addSkybox(SkyboxEntity toAdd);

    SkyboxEntity getByIdSkybox(Long id) throws Exception;
}
