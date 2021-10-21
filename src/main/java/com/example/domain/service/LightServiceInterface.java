package com.example.domain.service;

import com.example.domain.entity.LightEntity;

public interface LightServiceInterface {
    LightEntity addLight(LightEntity toAdd);

    LightEntity putLight(LightEntity lightEntityToAdd, Long id);

    LightEntity getByIdLight(Long id);
}
