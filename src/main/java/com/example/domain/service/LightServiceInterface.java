package com.example.domain.service;

import com.example.domain.entity.LevelEntity;
import com.example.domain.entity.LightEntity;

import java.util.List;

public interface LightServiceInterface {
    LightEntity addLight(LightEntity toAdd);

    LightEntity putLight(LightEntity lightEntityToAdd, Long id);

    LightEntity getByIdLight(Long id);

    List<LightEntity> getLights();

    boolean deleteLight(Long id);
}
